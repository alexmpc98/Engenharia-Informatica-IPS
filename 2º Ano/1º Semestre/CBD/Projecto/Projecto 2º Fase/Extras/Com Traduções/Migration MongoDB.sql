-- -----------------------------------------------------
-- GENERATE MONGO DB MIGRATION
-- -----------------------------------------------------
-- CURSOR
DECLARE @GeneralCursor CURSOR 

-- PRODUCT
DECLARE @ProductKey nvarchar(50) 
	
DECLARE @EnglishProductName nvarchar(50) 
DECLARE @FrenchProductName nvarchar(50) 
DECLARE @SpanishProductName nvarchar(50)

DECLARE @EnglishCategoryName nvarchar(50) 
DECLARE @FrenchCategoryName nvarchar(50) 
DECLARE @SpanishCategoryName nvarchar(50)


DECLARE @Category nvarchar(50)

-- CLIENTE
DECLARE @Email nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT p.[ProductKey], 
					pn.[EnglishProductName], 
					pn.[SpanishProductName], 
					pn.[FrenchProductName], 
					pc.[EnglishProductCategoryName], 
					pc.[SpanishProductCategoryName], 
					pc.[FrenchProductCategoryName] 
	FROM [AdventureNewData].[Products].[Product] p
		 INNER JOIN [AdventureNewData].[Products].[ProductName] pn
		 ON pn.[ProductNameKey] = p.[ProductNameKey]
		 INNER JOIN [AdventureNewData].[Products].[Category_SubCategory] csc
		 ON csc.[ProductCategoryKey] = p.[CategorySubCategoryKey]
		 INNER JOIN [AdventureNewData].[Products].[ProductCategory] pc
		 ON pc.[ProductCategoryKey] = csc.[ProductCategoryKey]
	);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @ProductKey, @EnglishProductName, @FrenchProductName, @SpanishProductName, @EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName;
	WHILE @@FETCH_STATUS = 0 
	BEGIN 
		/*INSERT INTO [AdventureNewData].[Products].[ProductCategory] (EnglishProductCategoryName, SpanishProductCategoryName, FrenchProductCategoryName) 
		VALUES (@EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName);*/
		PRINT 'db.products.insert({_id:' + @Id + ', name:"' + @Name + '", category:"' + @Category + '"})';
		FETCH NEXT FROM @GeneralCursor INTO  @EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName;
	END 
	CLOSE @GeneralCursor; 


	-- PROCEDURE PARA CRIAR O Texto do comentário, Classificação (0 a 5) e Data do comentário 




	DECLARE @EnglishProductName nvarchar(50) 
DECLARE @FrenchProductName nvarchar(50) 
DECLARE @SpanishProductName nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [EnglishProductName] ,[FrenchProductName] ,[SpanishProductName] 
	FROM [AdventureOldData].[dbo].[Products] );
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @EnglishProductName, @FrenchProductName, @SpanishProductName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductName] (EnglishProductName, SpanishProductName, FrenchProductName) 
		VALUES (@EnglishProductName, @FrenchProductName, @SpanishProductName);
		FETCH NEXT FROM @GeneralCursor INTO @EnglishProductName, @FrenchProductName, @SpanishProductName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Products.ProductName;


DECLARE @EnglishCategoryName nvarchar(50) 
DECLARE @FrenchCategoryName nvarchar(50) 
DECLARE @SpanishCategoryName nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [EnglishProductCategoryName], [SpanishProductCategoryName], [FrenchProductCategoryName]  
	FROM [AdventureOldData].[dbo].[Products] );
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO  @EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName;
	WHILE @@FETCH_STATUS = 0 
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductCategory] (EnglishProductCategoryName, SpanishProductCategoryName, FrenchProductCategoryName) 
		VALUES (@EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName);
		FETCH NEXT FROM @GeneralCursor INTO  @EnglishCategoryName, @SpanishCategoryName, @FrenchCategoryName;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductCategory;





