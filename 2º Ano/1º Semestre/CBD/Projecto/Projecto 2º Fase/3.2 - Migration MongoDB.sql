-- CURSOR
DECLARE @GeneralCursor CURSOR 

-- -----------------------------------------------------
-- GENERATE MONGO DB MIGRATION PRODUTOS
-- -----------------------------------------------------

-- PRODUCT
DECLARE @ProductKey nvarchar(50) 
DECLARE @EnglishProductName nvarchar(50) 
DECLARE @EnglishCategoryName nvarchar(50) 
-- COMMENT
DECLARE @CommentCursor CURSOR
DECLARE @Comment nvarchar(1500)
DECLARE	@CommentText nvarchar(150)
DECLARE @CommentRate int
DECLARE @CommentDate date
DECLARE	@CustomerEmail varchar(150)
DECLARE @i int

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
		SET @Comment = '';
		SET @CommentCursor = CURSOR FOR (
			SELECT DISTINCT pc.[CommentText],
							pc.[CommentClassification],
							pc.[CommentDate],
							pc.[CustomerEmail]
			FROM [AdventureNewData].[Products].[ProductComment] pc
			WHERE pc.[ProductKey] = @ProductKey
		);		

		OPEN @CommentCursor;
		FETCH NEXT FROM @CommentCursor INTO @CommentText, @CommentRate, @CommentDate, @CustomerEmail;
		SET @i = 0;
		WHILE @@FETCH_STATUS = 0 
			BEGIN 	
				IF @i > 0 
					 BEGIN
						SET @Comment = @Comment + ', ';
					 END
				SET @Comment = @Comment + '{email:"' + @CustomerEmail + '", texto:"' + @CommentText + '", classificacao:' + CAST(@CommentRate AS VARCHAR) + ', data:"' + CAST(@CommentDate AS VARCHAR) + '"}';
				--PRINT @comment;
				FETCH NEXT FROM @CommentCursor INTO @CommentText, @CommentRate, @CommentDate, @CustomerEmail;
				SET @i = @i + 1;
			END 
		CLOSE @CommentCursor; 

		PRINT 'db.produtos.insert({_id:' + @ProductKey + ', nome:"' + @EnglishProductName + '", categoria:"' + @EnglishCategoryName + '", comentarios: [' + @Comment + ']})';
		FETCH NEXT FROM @GeneralCursor INTO @ProductKey, @EnglishProductName, @EnglishCategoryName;
	END 
	CLOSE @GeneralCursor; 


PRINT CHAR(13)+CHAR(10)

-- -----------------------------------------------------
-- GENERATE MONGO DB MIGRATION CLIENTES
-- -----------------------------------------------------
DECLARE @CustomerKey nvarchar(50) 

-- CUSTOMER
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
		
		
		PRINT 'db.clientes.insert({email:"' + @Email + '", pais:"' + @Country + '"})';
		FETCH NEXT FROM @GeneralCursor INTO @CustomerKey, @Email, @Country;
	END 
	CLOSE @GeneralCursor; 

--SELECT * FROM [AdventureNewData].[Products].ProductComment;

