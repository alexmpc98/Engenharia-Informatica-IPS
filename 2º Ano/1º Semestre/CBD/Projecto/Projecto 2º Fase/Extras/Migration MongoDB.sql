-- CURSOR
DECLARE @GeneralCursor CURSOR 

-- -----------------------------------------------------
-- GENERATE MONGO DB MIGRATION PRODUTOS
-- -----------------------------------------------------

DECLARE @ProductKey nvarchar(50) 
	
DECLARE @EnglishProductName nvarchar(50) 
DECLARE @FrenchProductName nvarchar(50) 
DECLARE @SpanishProductName nvarchar(50)

DECLARE @EnglishCategoryName nvarchar(50) 
DECLARE @FrenchCategoryName nvarchar(50) 
DECLARE @SpanishCategoryName nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT p.[ProductKey], 
					pn.[EnglishProductName], 
					pc.[EnglishProductCategoryName] 
	FROM [AdventureNewData].[Products].[Product] p
		 INNER JOIN [AdventureNewData].[Products].[ProductName] pn
		 ON pn.[ProductNameKey] = p.[ProductNameKey]
		 INNER JOIN [AdventureNewData].[Products].[Category_SubCategory] csc
		 ON csc.[ProductCategoryKey] = p.[CategorySubCategoryKey]
		 INNER JOIN [AdventureNewData].[Products].[ProductCategory] pc
		 ON pc.[ProductCategoryKey] = csc.[ProductCategoryKey] 
	);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @ProductKey, @EnglishProductName, @EnglishCategoryName;
	WHILE @@FETCH_STATUS = 0 
	BEGIN 
		PRINT 'db.produtos.insert({_id:' + @ProductKey + ', name:"' + @EnglishProductName + '", category:"' + @EnglishCategoryName + '"})';
		FETCH NEXT FROM @GeneralCursor INTO @ProductKey, @EnglishProductName, @EnglishCategoryName;
	END 
	CLOSE @GeneralCursor; 


PRINT CHAR(13)+CHAR(10)

-- -----------------------------------------------------
-- GENERATE MONGO DB MIGRATION CLIENTES
-- -----------------------------------------------------

-- PRODUCT
DECLARE @CustomerKey nvarchar(50) 

DECLARE @Email nvarchar(50)
DECLARE @Country nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT c.[CustomerKey], 
					c.[EmailAddress],
					cc.[CountryRegionName]
	FROM [AdventureNewData].[Customers].[Customer] c
	     INNER JOIN [AdventureNewData].[Customers].[CustomerCountryRegion] cc
		 ON cc.[CustomerCountryRegionKey] = c.[CustomerCountryRegionKey]
	);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @CustomerKey, @Email, @Country;
	WHILE @@FETCH_STATUS = 0 
	BEGIN 
		PRINT 'db.clientes.insert({email:"' + @Email + '", country:"' + @Country + '"})';
		FETCH NEXT FROM @GeneralCursor INTO @CustomerKey, @Email, @Country;
	END 
	CLOSE @GeneralCursor; 



-- -----------------------------------------------------
-- PROCEDURE TO CREATE A COMMENT
-- -----------------------------------------------------
GO
CREATE OR ALTER PROCEDURE newComment (
	@CommentText varchar(150),
	@CommentClassification varchar(50),
	@CommentDate date
)
AS
	BEGIN
		INSERT INTO [Products].[Product]
		(
			CommentText,
			CommentClassification,
			CommentDate
		)
		VALUES (
			@CommentText,
			@CommentClassification,
			@CommentDate
		)
	END
GO


