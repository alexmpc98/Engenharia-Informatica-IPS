c.
Definir uma promoção na encomenda d. 
Alterar as datas de Início e Fim de uma promoção 
(não deve ser possível atribuir uma promoção que não esteja ativa) e. Alterar o Estado dos Produtos 
No 

-- Associate to Sub-Category (its associated with Category)
GO
CREATE OR ALTER PROCEDURE Products.spAssociateProductSubCategory
(@ProductName varchar(50), @SubCategoryName varchar(50))
AS
BEGIN
DECLARE @ProductKey int
DECLARE @ProductNameKey int
DECLARE @SubCategoryKey int
DECLARE @CategoryKey int
DECLARE @CategoryName varchar(50) 

SET @ProductNameKey = (
SELECT ProductNameKey FROM [AdventureNewData].[Products].[ProductName]
WHERE trim(EnglishProductName) = trim(@ProductName) or 
trim(SpanishProductName) = trim(@ProductName) or trim(FrenchProductName) = trim(@ProductName)
);

SET @ProductKey = (
SELECT ProductKey FROM [AdventureNewData].[Products].[Product]
WHERE ProductNameKey = @ProductNameKey
);

SET @SubCategoryKey = (
SELECT ProductSubCategoryKey FROM [AdventureNewData].[Products].[ProductSubCategory] 
WHERE trim(EnglishProductSubCategoryName) = trim(@SubCategoryName)
OR trim(SpanishProductSubCategoryName) = trim(@SubCategoryName) or 
trim(FrenchProductSubCategoryName) = trim(@SubCategoryName)
);

SET @CategoryKey = (
SELECT ProductCategoryKey FROM [AdventureNewData].[Products].[Category_SubCategory]
WHERE ProductSubCategoryKey = @SubCategoryKey
);

SET @CategoryName =(
SELECT EnglishProductCategoryName
FROM Products.ProductCategory WHERE ProductCategoryKey = @CategoryKey
);
PRINT concat('You associated ', @ProductName, ' with the Sub-Category ' , @SubCategoryName, ' 
which is associated with the category ', @CategoryName);
UPDATE [AdventureNewData].[Products].[Product] SET CategorySubCategoryKey = @CategoryKey
WHERE ProductKey = @ProductKey
END

exec Products.spAssociateProductSubCategory 'All-Purpose Bike Stand','Mountain Bikes'


select * from Products.ProductPromotion;


GO
CREATE OR ALTER PROCEDURE Sales.spAssociatePromotionToSale
(@SalesOrderNumber varchar(13), @PromotionKey int)
AS
BEGIN
DECLARE @SalesOrderHeaderKey int
DECLARE @UnitPrice money
DECLARE @NewUnitPrice money
DECLARE @PriceDiscount money
DECLARE @ProductOnPromotion int
DECLARE @Active bit
DECLARE @ProductOnOrder int
DECLARE @Quantity int
DECLARE @AmountDiscounted int
DECLARE @Amount money
DECLARE @SalesStatusKey int

SET @ProductOnOrder = ( 
SELECT ProductKey FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

SET @SalesOrderHeaderKey = (
SELECT SalesOrderHeaderKey FROM [AdventureNewData].[SALES].[SalesOrderHeader]
WHERE SalesOrderNumber = @SalesOrderNumber
);

SET @ProductOnPromotion = (
SELECT ProductKey FROM [AdventureNewData].[Products].[ProductPromotion] WHERE ProductPromotionKey = @PromotionKey
);

SET @UnitPrice = (
Select sum(UnitPrice) from [AdventureNewData].[Sales].[SalesOrderDetail] WHERE
SalesOrderHeaderKey = @SalesOrderHeaderKey and ProductKey = @ProductOnOrder
);
SET @Amount = (
SELECT SalesAmount FROM [AdventureNewData].[Sales].[SalesOrderHeader] WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

SET @PriceDiscount = (
SELECT PriceDiscount from [AdventureNewData].[Products].[ProductPromotion] WHERE @PromotionKey = @PromotionKey
);

SET @Quantity = (
SELECT sum(OrderQuantity) FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey and ProductKey = @ProductOnOrder
);
SELECT sum(OrderQuantity) FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey and ProductKey = @ProductOnOrder	

SET @NewUnitPrice = @UnitPrice - @PriceDiscount;

SET @AmountDiscounted = @Amount - (@Quantity * @NewUnitPrice);

SET @Active = (
SELECT Active FROM [AdventureNewData].[Products].[ProductPromotion] WHERE ProductPromotionKey = @PromotionKey
);

SET @SalesStatusKey = (
SELECT SalesStatusKey FROM [AdventureNewData].[Sales].[SalesOrderHeader] WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

IF (@Active = 0)
	BEGIN
	PRINT('This Promotion is not valid!');
	END
ELSE IF (@ProductOnOrder < @ProductOnPromotion OR @ProductOnOrder > @ProductOnPromotion)
	BEGIN
	PRINT('There is no promotion possibly applied to this order');
	END
ELSE IF (@SalesStatusKey > 1)
	BEGIN
	PRINT('Order already finished processing status')
	END
ELSE
BEGIN
UPDATE [AdventureNewData].[Sales].[SalesOrderHeader] SET SalesAmount = @AmountDiscounted 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
UPDATE [AdventureNewData].[Sales].[SalesOrderDetail] SET UnitPrice = @NewUnitPrice
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey AND ProductKey = @ProductOnOrder
END
PRINT concat('You added Promotion Nº',@PromotionKey, 
' to sale ' ,@SalesOrderHeaderKey,' which discounted ' , @PriceDiscount);
END


EXEC Sales.spAssociatePromotionToSale 'SO51187',3


CREATE OR ALTER PROCEDURE