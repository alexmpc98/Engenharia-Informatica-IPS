-- Sale Creation
CREATE OR ALTER PROCEDURE Sales.SalesCreation(@EmailAddress varchar(60), @ProductName varchar(100), @Quantity int, @CurrencyOfPayment varchar(3))
AS
BEGIN
BEGIN TRANSACTION;
DECLARE @CustomerKey int
DECLARE @ProductNameKey int
DECLARE @ProductKey int
DECLARE @ListPrice money
DECLARE @SalesTerritoryKey int
DECLARE @SalesAmount float
DECLARE @LastSalesOrderNumberFetched varchar(10)
DECLARE @NewLastNumber int
DECLARE @NewLastOrderNumber varchar(10)
DECLARE @CurrencyKey int
DECLARE @SalesHeaderKey int
DECLARE @SalesOrderLineNumber int

SET @CurrencyKey = (
SELECT SalesCurrencyKey FROM [AdventureNewData].[Sales].[SalesCurrency] WHERE CurrencyAlternateKey = @CurrencyOfPayment
);

SET @LastSalesOrderNumberFetched =  (
SELECT TOP 1 RIGHT(SalesOrderNumber, LEN(SalesOrderNumber) - 2) as TrimmedSalesOrderNumber
FROM [AdventureNewData].[Sales].[SalesOrderHeader] 
ORDER BY RIGHT(SalesOrderNumber, LEN(SalesOrderNumber) - 2) DESC);

SET @NewLastNumber = cast(@LastSalesOrderNumberFetched as int) + 1;
SET @NewLastOrderNumber = concat('SO',cast(@NewLastNumber as varchar));

SET @CustomerKey = 
(Select CustomerKey from [AdventureNewData].[Customers].[Customer] 
where trim(EmailAddress) = trim(@EmailAddress));
SET @ProductNameKey = 
(Select ProductNameKey from [AdventureNewData].[Products].[ProductName]
WHERE trim(EnglishProductName) = trim(@ProductName) 
or trim(SpanishProductName) = trim(@ProductName) or trim(FrenchProductName) = trim(@ProductName)); 
SET @ProductKey =
(SELECT ProductKey FROM [AdventureNewData].[Products].[Product] WHERE ProductNameKey = @ProductNameKey);
SET @ListPrice = 
(SELECT ListPrice FROM [AdventureNewData].[Products].[Product] WHERE ProductKey = @ProductKey);
SET @SalesTerritoryKey = 
(SELECT SalesTerritoryKey FROM [AdventureNewData].[Customers].[Customer] WHERE CustomerKey = @CustomerKey);

SET @SalesAmount = @ListPrice * @Quantity

INSERT INTO [Sales].[SalesOrderHeader]
VALUES(@NewLastOrderNumber, GETDATE(),GETDATE()+12,GETDATE()+7, @SalesAmount, @SalesTerritoryKey, @CustomerKey,2,@CurrencyKey);

SET @SalesHeaderKey = (
SELECT SalesOrderHeaderKey FROM [AdventureNewData].[Sales].[SalesOrderHeader] WHERE SalesOrderNumber = @NewLastOrderNumber
);

SET @SalesOrderLineNumber = 1;
	WHILE (@Quantity > 0)
	BEGIN
		SET @SalesOrderLineNumber = @SalesOrderLineNumber + 1;
		SET @Quantity = @Quantity - 1;
		INSERT INTO [Sales].[SalesOrderDetail]
		VALUES(1,@ListPrice,@ProductKey,@SalesOrderLineNumber,@SalesHeaderKey);
	END
PRINT 'Thank you for your purchase ' + @EmailAddress
COMMIT;
END

EXEC Sales.SalesCreation 'jon24@adventure-works.com', 'All-Purpose Bike Stand', 4 , 'AUD'; 


-- Adding a Product to a Sale
GO
CREATE OR ALTER PROCEDURE Sales.AddAProductToOrder 
(@ProductName varchar(100), @Quantity int, @SalesOrderNumber varchar(15))
AS
BEGIN
BEGIN TRANSACTION;
DECLARE @ProductKey INT
DECLARE @ProductNameKey INT
DECLARE @NewSalesAmount INT
DECLARE @SalesAmount MONEY
DECLARE @ListPrice MONEY
DECLARE @SalesOrderLineNumber INT
DECLARE @SalesOrderHeaderKey INT

SET @SalesOrderHeaderKey = (
SELECT SalesOrderHeaderKey FROM [AdventureNewData].[Sales].[SalesOrderHeader] WHERE SalesOrderNumber = @SalesOrderNumber
);

SET @ProductNameKey = 
(Select ProductNameKey from [AdventureNewData].[Products].[ProductName]
WHERE trim(EnglishProductName) = trim(@ProductName) 
or trim(SpanishProductName) = trim(@ProductName) or trim(FrenchProductName) = trim(@ProductName)); 

SET @ProductKey =
(SELECT ProductKey FROM [AdventureNewData].[Products].[Product] WHERE ProductNameKey = @ProductNameKey);

SET @ListPrice = 
(SELECT ListPrice FROM [AdventureNewData].[Products].[Product] WHERE ProductKey = @ProductKey);

SET @SalesAmount =
(SELECT SalesAmount FROM [AdventureNewData].[Sales].[SalesOrderHeader] WHERE SalesOrderNumber = @SalesOrderNumber
)
SET @NewSalesAmount = (@ListPrice * @Quantity) + @SalesAmount

UPDATE [AdventureNewData].[Sales].[SalesOrderHeader] SET SalesAmount = @NewSalesAmount
WHERE SalesOrderNumber = @SalesOrderNumber

SET @SalesOrderLineNumber = 1;
	WHILE (@Quantity > 0)
		BEGIN
		SET @SalesOrderLineNumber = @SalesOrderLineNumber + 1;
		SET @Quantity = @Quantity - 1;
		INSERT INTO [AdventureNewData].[Sales].[SalesOrderDetail] VALUES (
			1,@ListPrice,@ProductKey,@SalesOrderLineNumber,@SalesOrderHeaderKey)
		END
		COMMIT; 
END

EXEC Sales.AddAProductToOrder 'All-Purpose Bike Stand', 1, 'SO51187'


-- Alter Quantity Of Product
GO
CREATE OR ALTER PROCEDURE Sales.spAlterQuantityOnOrder 
(@ProductName varchar(50), @Quantity int, @SalesOrderNumber varchar(10))
AS
BEGIN
BEGIN TRANSACTION;
DECLARE @ProductNameKey int
DECLARE @ProductKey int
DECLARE @ProductOnOrder int
DECLARE @SalesOrderHeaderKey int

SET @ProductNameKey = 
(Select ProductNameKey from [AdventureNewData].[Products].[ProductName]
WHERE trim(EnglishProductName) = trim(@ProductName) 
or trim(SpanishProductName) = trim(@ProductName) or trim(FrenchProductName) = trim(@ProductName)); 

SET @ProductKey =
(SELECT ProductKey FROM [AdventureNewData].[Products].[Product] WHERE ProductNameKey = @ProductNameKey);

SET @SalesOrderHeaderKey = (
SELECT SalesOrderHeaderKey FROM [AdventureNewData].[SALES].[SalesOrderHeader]
WHERE SalesOrderNumber = @SalesOrderNumber
);
SET @ProductOnOrder = ( 
SELECT ProductKey FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

IF (@ProductKey < @ProductOnOrder or @ProductKey > @ProductOnOrder)
BEGIN
PRINT 'This product doesn´t belong to this order!'
END
ELSE
BEGIN
UPDATE [AdventureNewData].[Sales].[SalesOrderDetail]  SET OrderQuantity = @Quantity 
WHERE ProductKey = @ProductKey AND SalesOrderHeaderKey = @SalesOrderHeaderKey
END
COMMIT; 
END
SELECT * From Sales.SalesOrderHeader

-- Removing Product of Order
GO
CREATE OR ALTER PROCEDURE Sales.spRemoveProductFromOrder
(@ProductName varchar(50), @SalesOrderNumber varchar(10))
AS
BEGIN
BEGIN TRANSACTION;  
DECLARE @ProductNameKey int
DECLARE @ProductKey int
DECLARE @ProductOnOrder int
DECLARE @SalesOrderHeaderKey int
DECLARE @UnitPrice money
DECLARE @Amount money
DECLARE @NewAmount money

SET @ProductNameKey = 
(Select ProductNameKey from [AdventureNewData].[Products].[ProductName]
WHERE trim(EnglishProductName) = trim(@ProductName) 
or trim(SpanishProductName) = trim(@ProductName) or trim(FrenchProductName) = trim(@ProductName)); 

SET @ProductKey =
(SELECT ProductKey FROM [AdventureNewData].[Products].[Product] WHERE ProductNameKey = @ProductNameKey);

SET @SalesOrderHeaderKey = (
SELECT SalesOrderHeaderKey FROM [AdventureNewData].[SALES].[SalesOrderHeader]
WHERE SalesOrderNumber = @SalesOrderNumber
);
SET @ProductOnOrder = ( 
SELECT ProductKey FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

SET @UnitPrice = (
SELECT UnitPrice FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey and ProductKey = @ProductKey
);

SET @Amount = (
SELECT SalesAmount FROM [AdventureNewData].[Sales].[SalesOrderHeader] 
WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
);

SET @NewAmount = @Amount - @UnitPrice;

IF (@ProductKey < @ProductOnOrder or @ProductKey > @ProductOnOrder)
BEGIN
PRINT 'This product doesn´t belong to this order!'
END
ELSE
BEGIN
DELETE FROM [AdventureNewData].[Sales].[SalesOrderDetail] 
WHERE ProductKey = @ProductKey AND SalesOrderHeaderKey = @SalesOrderHeaderKey
UPDATE [AdventureNewData].[Sales].[SalesOrderHeader] SET SalesAmount = @NewAmount WHERE SalesOrderHeaderKey = @SalesOrderHeaderKey
END
COMMIT; 
END



-- Alter Order State
GO
CREATE OR ALTER PROCEDURE Sales.spAlterOrderState (@OrderKey int, @Status int)
AS
BEGIN
BEGIN TRANSACTION;  
UPDATE [AdventureNewData].[Sales].[SalesOrderHeader] SET SalesStatusKey = @Status
WHERE SalesOrderHeaderKey = @OrderKey
COMMIT; 
END


