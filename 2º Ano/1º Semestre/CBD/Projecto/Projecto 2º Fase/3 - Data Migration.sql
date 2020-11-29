-- SET IDENTITY_INSERT OFF TO ALL TABLES
--EXEC sp_MSforeachtable @command1="SET IDENTITY_INSERT ? OFF"
--SET IDENTITY_INSERT [AdventureNewData].[Customers].[Customer] OFF;

-- CURSOR
DECLARE @GeneralCursor CURSOR 

-- -----------------------------------------------------
-- Table ProductCategory
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table ProductSubCategory
-- -----------------------------------------------------
DECLARE @SubCategoryKey nvarchar(50)
DECLARE @EnglishSubCategoryName nvarchar(50) 
DECLARE @FrenchSubCategoryName nvarchar(50) 
DECLARE @SpanishSubCategoryName nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [ProductSubCategoryKey], [EnglishProductSubCategoryName] ,[FrenchProductSubCategoryName] ,[SpanishProductSubCategoryName] 
	FROM [AdventureOldData].[dbo].[ProductSubCategory]);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @SubCategoryKey, @EnglishSubCategoryName, @FrenchSubCategoryName, @SpanishSubCategoryName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		SET IDENTITY_INSERT [AdventureNewData].[Products].[ProductSubCategory] ON;
		INSERT INTO [AdventureNewData].[Products].[ProductSubCategory] (ProductSubCategoryKey, EnglishProductSubCategoryName, SpanishProductSubCategoryName, FrenchProductSubCategoryName) 
		VALUES (CONVERT(int, @SubCategoryKey) ,@EnglishSubCategoryName, @SpanishSubCategoryName, @FrenchSubCategoryName);
		SET IDENTITY_INSERT [AdventureNewData].[Products].[ProductSubCategory] OFF;
		FETCH NEXT FROM @GeneralCursor INTO @SubCategoryKey, @EnglishSubCategoryName, @FrenchSubCategoryName, @SpanishSubCategoryName;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductSubCategory;
SELECT * FROM AdventureOldData.dbo.ProductSubCategory;


-- -----------------------------------------------------
-- Table Category_SubCategory
-- -----------------------------------------------------
-- CATEGORY
DECLARE @ProductEnglishCategory nvarchar(50)
DECLARE @ProductSpanishCategory nvarchar(50)
DECLARE @ProductFrenchCategory nvarchar(50)
DECLARE @ProductCategoryKey int
-- SUBCATEGORY
DECLARE @ProductSubCategoryKey int

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [ProductSubCategoryKey], [EnglishProductCategoryName], [FrenchProductCategoryName], [SpanishProductCategoryName] 
	FROM [AdventureOldData].[dbo].[Products]);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO  @ProductSubCategoryKey, @ProductEnglishCategory, @ProductFrenchCategory, @ProductSpanishCategory;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		-- CATEGORY KEY
		SELECT @ProductCategoryKey = [ProductCategoryKey] 
		FROM [AdventureNewData].[Products].[ProductCategory] 
		WHERE ([EnglishProductCategoryName] LIKE @ProductEnglishCategory) AND ([SpanishProductCategoryName] LIKE @ProductSpanishCategory) 
		AND ([FrenchProductCategoryName] LIKE @ProductFrenchCategory);  

		INSERT INTO [AdventureNewData].[Products].[Category_SubCategory] (ProductCategoryKey, ProductSubCategoryKey) 
		VALUES (@ProductCategoryKey, @ProductSubCategoryKey);
		FETCH NEXT FROM @GeneralCursor INTO  @ProductSubCategoryKey, @ProductEnglishCategory, @ProductFrenchCategory, @ProductSpanishCategory;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.Category_SubCategory;


-- -----------------------------------------------------
-- Table ProductSize
-- -----------------------------------------------------
DECLARE @Size nvarchar(5) 

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [Size] 
	FROM [AdventureOldData].[dbo].[Products] 
	WHERE [Size] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @Size;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductSize] (Size) 
		VALUES (@Size);
		FETCH NEXT FROM @GeneralCursor INTO @Size;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductSize;

-- -----------------------------------------------------
-- Table ProductSizeRange
-- -----------------------------------------------------
DECLARE @SizeRange nvarchar(15) 

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [SizeRange] 
	FROM [AdventureOldData].[dbo].[Products] 
	WHERE [SizeRange] != 'NA');
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @SizeRange;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductSizeRange] (SizeRange) 
		VALUES (@SizeRange);
		FETCH NEXT FROM @GeneralCursor INTO @SizeRange;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductSizeRange;


-- -----------------------------------------------------
-- Table Size_SizeRange
-- -----------------------------------------------------
-- Size
DECLARE @ProductSize nvarchar(5)
DECLARE @ProductSizeKey int 
-- SizeRange
DECLARE @ProductSizeRange nvarchar(25)
DECLARE @ProductSizeRangeKey int

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [Size], [SizeRange] 
	FROM [AdventureOldData].[dbo].[Products] 
	WHERE [Size] IS NOT NULL AND [SizeRange] != 'NA');
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @ProductSize, @ProductSizeRange;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		-- SIZE KEY 
		SELECT @ProductSizeKey = [ProductSizeKey] 
		FROM [AdventureNewData].[Products].[ProductSize] 
		WHERE [Size] LIKE @ProductSize;
		-- SIZERANGE KEY
		SELECT @ProductSizeRangeKey = [ProductSizeRangeKey] 
		FROM [AdventureNewData].[Products].[ProductSizeRange] 
		WHERE [SizeRange] LIKE @ProductSizeRange;

		INSERT INTO [AdventureNewData].[Products].[Size_SizeRange] (ProductSizeKey, ProductSizeRangeKey) 
		VALUES (@ProductSizeKey, @ProductSizeRangeKey);
		FETCH NEXT FROM @GeneralCursor INTO @ProductSize, @ProductSizeRange;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.Size_SizeRange;


-- -----------------------------------------------------
-- Table ProductColor
-- -----------------------------------------------------
DECLARE @ColorName nvarchar(50) 

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [Color] 
	FROM [AdventureOldData].[dbo].[Products] );
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @ColorName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductColor] (ColorName) 
		VALUES (@ColorName);
		FETCH NEXT FROM @GeneralCursor INTO @ColorName;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductColor;


-- -----------------------------------------------------
-- Table ProductDescription
-- -----------------------------------------------------
DECLARE @EnglishDescription nvarchar(50) 
DECLARE @FrenchDescription nvarchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [EnglishDescription], [FrenchDescription] 
	FROM [AdventureOldData].[dbo].[Products] 
	WHERE [EnglishDescription] IS NOT NULL AND [FrenchDescription] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @EnglishDescription, @FrenchDescription;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductDescription] (EnglishDescription, FrenchDescription) 
		VALUES (@EnglishDescription, @FrenchDescription);
		FETCH NEXT FROM @GeneralCursor INTO @EnglishDescription, @FrenchDescription;
	END 
	CLOSE @GeneralCursor; 

SELECT * FROM AdventureNewData.Products.ProductDescription;


-- -----------------------------------------------------
-- Table ProductModel
-- -----------------------------------------------------
DECLARE @ModelName nvarchar(50) 

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [ModelName]
	FROM [AdventureOldData].[dbo].[Products] );
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @ModelName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductModel] (ModelName) 
		VALUES (@ModelName);
		FETCH NEXT FROM @GeneralCursor INTO @ModelName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Products.ProductModel;


-- -----------------------------------------------------
-- Table ProductName
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table ProductUnitMeasureCode
-- -----------------------------------------------------
DECLARE @WeightUnitMeasureCode nvarchar(2) 
DECLARE @SizeUnitMeasureCode nvarchar(2)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [WeightUnitMeasureCode] ,[SizeUnitMeasureCode] 
	FROM [AdventureOldData].[dbo].[Products] 
	WHERE [WeightUnitMeasureCode] IS NOT NULL OR [SizeUnitMeasureCode] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @WeightUnitMeasureCode, @SizeUnitMeasureCode;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Products].[ProductMeasureUnitCode] (WeightUnitMeasureCode, SizeUnitMeasureCode) 
		VALUES (@WeightUnitMeasureCode, @SizeUnitMeasureCode);
		FETCH NEXT FROM @GeneralCursor INTO @WeightUnitMeasureCode, @SizeUnitMeasureCode;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Products.ProductMeasureUnitCode;


-- -----------------------------------------------------
-- Table Product
-- -----------------------------------------------------
-- PRODUCT
DECLARE @ProductKey int 
DECLARE @ListPrice money
DECLARE @FinishedGoodsFlag bit
DECLARE @SafetyStockLevel int
DECLARE @StandardCost money
DECLARE @Weight float
DECLARE @DaysToManufacture tinyint
DECLARE @ProductLine varchar(1)
DECLARE @DealerPrice money
DECLARE @Class varchar(1)
DECLARE @Style varchar(1)
DECLARE @Status varchar(10)
-- COLOR
DECLARE @ProductColor nvarchar(50)
DECLARE @ProductColorKey int
-- MODEL
DECLARE @ProductModel nvarchar(50)
DECLARE @ProductModelKey int
-- DESCRIPTION
DECLARE @ProductEnglishDescription varchar(50)
DECLARE @ProductFrenchDescription varchar(50)
DECLARE @ProductDescriptionKey int
-- NAME
DECLARE @EnglishNameProduct varchar(50)
DECLARE @SpanishNameProduct varchar(50)
DECLARE @FrenchNameProduct varchar(50)
DECLARE @ProductNameKey int
-- MEASUREUNITCODE
DECLARE @ProductWeightUnitMeasureCode varchar(15)
DECLARE @ProductSizeUnitMeasureCode varchar(15)
DECLARE @ProductMeasureUnitCodeKey int
-- CATEGORY_SUBCATEGORY
DECLARE @ProductCategorySubCategoryKey int
DECLARE @PSubCategoryKey int
DECLARE @ProductCategoryEnglish varchar(50)
DECLARE @ProductCategorySpanish varchar(50)
DECLARE @ProductCategoryFrench varchar(50)
-- SIZE_SIZERANGE
DECLARE @PSize varchar(5)
DECLARE @PSizeRange varchar(15)
DECLARE @ProductSizeSizeRangeKey int

SET @GeneralCursor = CURSOR FOR (
	SELECT [ProductKey], 
		   [ProductSubcategoryKey], 
		   [EnglishProductCategoryName], 
		   [SpanishProductCategoryName],
		   [FrenchProductCategoryName], 
		   [WeightUnitMeasureCode], 
		   [SizeUnitMeasureCode], 
		   [EnglishProductName], 
		   [SpanishProductName], 
		   [FrenchProductName],
		   CONVERT(money, [StandardCost]) as 'StandardCost',
		   [FinishedGoodsFlag],
		   [Color],
		   [SafetyStockLevel],
		   CONVERT(money, [ListPrice]) as 'ListPrice',
		   [Size],
		   [SizeRange],
		   CONVERT(float, [Weight]) as 'Weight',
		   CAST([DaysToManufacture] as tinyint) as 'DaysToManufacture',
		   [ProductLine],
		   CONVERT(money, [DealerPrice]) as 'DealerPrice',
		   [Class],
		   [Style],
		   [ModelName],
		   [EnglishDescription],
		   [FrenchDescription],
		   [Status]
	FROM [AdventureOldData].[dbo].[Products]);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor
	INTO @ProductKey, 
		 @PSubCategoryKey,
		 @ProductCategoryEnglish, 
		 @ProductCategorySpanish, 
		 @ProductCategoryFrench,
		 @ProductWeightUnitMeasureCode,
		 @ProductSizeUnitMeasureCode,
		 @EnglishNameProduct,
		 @SpanishNameProduct,
		 @FrenchNameProduct,
		 @StandardCost,
		 @FinishedGoodsFlag,
		 @ProductColor,
		 @SafetyStockLevel,
		 @ListPrice,
		 @PSize,
		 @PSizeRange,
		 @Weight,
		 @DaysToManufacture,
		 @ProductLine,
		 @DealerPrice,
		 @Class,
		 @Style,
		 @ProductModel,
		 @ProductEnglishDescription,
		 @ProductFrenchDescription,
		 @Status;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		-- COLOR KEY
		SELECT  @ProductColorKey = [ProductColorKey] 
		FROM [AdventureNewData].[Products].[ProductColor] 
		WHERE [ColorName] LIKE @ProductColor;
		-- NAME KEY
		SELECT  @ProductNameKey = [ProductNameKey] 
		FROM [AdventureNewData].[Products].[ProductName] 
		WHERE [EnglishProductName] LIKE NULLIF(@EnglishNameProduct, '') OR 
		[SpanishProductName] LIKE NULLIF(@SpanishNameProduct, '') OR 
		[FrenchProductName] LIKE NULLIF(@FrenchNameProduct, '');

		PRINT @ProductNameKey
		-- DESCRIPTION KEY
		SELECT  @ProductDescriptionKey = [ProductDescriptionKey] 
		FROM [AdventureNewData].[Products].[ProductDescription] 
		WHERE [EnglishDescription] = @ProductEnglishDescription AND 
		[FrenchDescription] = @ProductFrenchDescription;
		-- MODEL KEY
		SELECT  @ProductModelKey = [ProductModelKey] 
		FROM [AdventureNewData].[Products].[ProductModel] 
		WHERE [ModelName] = @ProductModel;
		-- MEASURE UNIT CODE KEY
		SELECT @ProductMeasureUnitCodeKey = [ProductMeasureUnitCodeKey] 
		FROM [AdventureNewData].[Products].[ProductMeasureUnitCode] 
		WHERE [WeightUnitMeasureCode] = @ProductWeightUnitMeasureCode AND 
		[SizeUnitMeasureCode] = @ProductSizeUnitMeasureCode;
		-- CATEGORY_SUBCATEGORY KEY
		SELECT @ProductCategorySubCategoryKey = [CategorySubCategoryKey] 
		FROM [AdventureNewData].[Products].[Category_SubCategory] 
		WHERE [ProductSubCategoryKey] = @PSubCategoryKey; 
		-- SIZE_SIZERANGE KEY
		SELECT @ProductSizeSizeRangeKey = [SizeSizeRangeKey] 
		FROM [AdventureNewData].[Products].[Size_SizeRange] 
		WHERE [ProductSizeKey] = (SELECT [ProductSizeKey] 
		FROM [AdventureNewData].[Products].[ProductSize] 
		WHERE [Size] = @PSize) AND [ProductSizeRangeKey] = (SELECT [ProductSizeRangeKey] 
		FROM [AdventureNewData].[Products].[ProductSizeRange] 
		WHERE [SizeRange] = @PSizeRange);

		SET IDENTITY_INSERT [AdventureNewData].[Products].[Product] ON;
		INSERT INTO [AdventureNewData].[Products].[Product] 
		(
			ProductKey, 
			ListPrice, 
			FinishedGoodsFlag, 
			ProductColor,
			SafetyStockLevel,
			StandardCost,
			Weight,
			DaysToManufacture,
			ProductLine,
			DealerPrice,
			Class,
			Style,
			Status,
		    ProductModelKey,
			ProductDescriptionKey,
			ProductNameKey,
			CategorySubCategoryKey,
			ProductMeasureUnitCodeKey,
			SizeSizeRangeKey
		) 
		VALUES 
		(
			@ProductKey, 
			@ListPrice,
			@FinishedGoodsFlag, 
			@ProductColorKey,
			@SafetyStockLevel,
			@StandardCost,
			@Weight,
			@DaysToManufacture,
			@ProductLine,
			@DealerPrice,
			@Class,
			@Style,
			@Status,
			@ProductModelKey,
			@ProductDescriptionKey,
			@ProductNameKey,
			@ProductCategorySubCategoryKey,
			@ProductMeasureUnitCodeKey,
			@ProductSizeSizeRangeKey
		);
		SET IDENTITY_INSERT [AdventureNewData].[Products].[Product] OFF;
		FETCH NEXT FROM @GeneralCursor
		INTO @ProductKey, 
			 @PSubCategoryKey,
			 @ProductCategoryEnglish, 
			 @ProductCategorySpanish, 
			 @ProductCategoryFrench,
			 @ProductWeightUnitMeasureCode,
			 @ProductSizeUnitMeasureCode,
			 @EnglishNameProduct,
			 @SpanishNameProduct,
			 @FrenchNameProduct, 
			 @StandardCost,
			 @FinishedGoodsFlag,
			 @ProductColor,
			 @SafetyStockLevel,
			 @ListPrice,
			 @PSize,
			 @PSizeRange,
			 @Weight,
			 @DaysToManufacture,
			 @ProductLine,
			 @DealerPrice,
			 @Class,
			 @Style,
			 @ProductModel,
			 @ProductEnglishDescription,
			 @ProductFrenchDescription,
			 @Status;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Products.Product;
--SELECT * FROM AdventureOldData.dbo.Products;


-- -----------------------------------------------------
-- Table CustomerEducation
-- -----------------------------------------------------
DECLARE @EducationName varchar(25)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [Education]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE [Education] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @EducationName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerEducation] (EducationName) 
		VALUES (@EducationName);
		FETCH NEXT FROM @GeneralCursor INTO @EducationName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerEducation;


-- -----------------------------------------------------
-- Table CustomerOccupation
-- -----------------------------------------------------
DECLARE @OccupationName varchar(25)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [Occupation]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE [Occupation] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @OccupationName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerOccupation] (OccupationName) 
		VALUES (@OccupationName);
		FETCH NEXT FROM @GeneralCursor INTO @OccupationName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerOccupation;


-- -----------------------------------------------------
-- Table CustomerStateProvince
-- -----------------------------------------------------
DECLARE @StateProvinceCode varchar(5)
DECLARE @StateProvinceName varchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [StateProvinceCode], [StateProvinceName]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE [StateProvinceCode] IS NOT NULL AND [StateProvinceName] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @StateProvinceCode, @StateProvinceName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerStateProvince] (StateProvinceCode, StateProvinceName) 
		VALUES (@StateProvinceCode, @StateProvinceName);
		FETCH NEXT FROM @GeneralCursor INTO @StateProvinceCode, @StateProvinceName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerStateProvince;


-- -----------------------------------------------------
-- Table CustomerYearlyIncome
-- -----------------------------------------------------
DECLARE @YearlyIncome money

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [YearlyIncome]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE [YearlyIncome] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @YearlyIncome;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerYearlyIncome] (YearlyIncome) 
		VALUES (@YearlyIncome);
		FETCH NEXT FROM @GeneralCursor INTO @YearlyIncome;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerYearlyIncome;


-- -----------------------------------------------------
-- Table CustomerCountryRegion
-- -----------------------------------------------------
DECLARE @CountryRegionCode varchar(5)
DECLARE @CountryRegionName varchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [CountryRegionCode], [CountryRegionName]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE[CountryRegionCode] IS NOT NULL AND [CountryRegionName] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @CountryRegionCode, @CountryRegionName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerCountryRegion] (CountryRegionCode, CountryRegionName) 
		VALUES (@CountryRegionCode, @CountryRegionName);
		FETCH NEXT FROM @GeneralCursor INTO @CountryRegionCode, @CountryRegionName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerCountryRegion;


-- -----------------------------------------------------
-- Table CustomerCommuteDistance
-- -----------------------------------------------------
DECLARE @CommuteDistanceName varchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT [CommuteDistance]
	FROM [AdventureOldData].[dbo].[Customer] 
	WHERE [CommuteDistance] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @CommuteDistanceName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		INSERT INTO [AdventureNewData].[Customers].[CustomerCommuteDistance] (CommuteDistanceName) 
		VALUES (@CommuteDistanceName);
		FETCH NEXT FROM @GeneralCursor INTO @CommuteDistanceName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.CustomerCommuteDistance;


-- -----------------------------------------------------
-- Table SalesTerritory
-- -----------------------------------------------------
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesTerritory] ON;
INSERT INTO [AdventureNewData].[Sales].[SalesTerritory] 
(SalesTerritoryKey,SalesTerritoryRegion ,SalesTerritoryGroup, SalesTerritoryCountry) 
SELECT cast(SalesTerritoryKey as int), SalesTerritoryRegion, SalesTerritoryGroup, SalesTerritoryCountry 
FROM AdventureOldData.dbo.SalesTerritory;
/*WHERE SalesTerritoryRegion != 'NA' AND 
SalesTerritoryGroup != 'NA' AND 
SalesTerritoryCountry != 'NA'*/
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesTerritory] OFF;

SELECT * FROM AdventureNewData.Sales.SalesTerritory;


-- -----------------------------------------------------
-- Table Customer
-- -----------------------------------------------------
-- CUSTOMER
DECLARE @CustomerKey INT;
DECLARE	@Title varchar(5)
DECLARE @FirstName varchar(50)
DECLARE @MiddleName varchar(50)
DECLARE @LastName varchar(50)
DECLARE @NameStyle varchar(50)
DECLARE @BirthDate varchar(50)
DECLARE @MaritalStatus VARCHAR(1)
DECLARE @Gender VARCHAR(1)
DECLARE	@EmailAddress VARCHAR(100)
DECLARE @TotalChildren varchar(5)
DECLARE @NumberChildrenAtHome varchar(5)
DECLARE	@HouseOwnerFlag varchar(1)
DECLARE	@NumberCarsOwned varchar(5)
DECLARE @AddressLine1 varchar(50)
DECLARE @AddressLine2 varchar(50)
DECLARE @City varchar(45)
DECLARE @PostalCode VARCHAR(15);
DECLARE @SalesTerritoryKey INT;
DECLARE @Phone VARCHAR(15);
DECLARE @DateFirstPurchase varchar(50);
-- EDUCATION KEY
DECLARE @CustomerEducation varchar(50);
DECLARE @CustomerEducationKey int;
-- OCCUPATION KEY
DECLARE @CustomerOccupation varchar(50);
DECLARE @CustomerOccupationKey int;
-- COMMUTEDISTANCE KEY
DECLARE @CustomerCommuteDistance varchar(50);
DECLARE @CustomerCommuteDistanceKey int;
-- STATEPROVINCE KEY
DECLARE @CustomerStateProvinceCode varchar(50)
DECLARE @CustomerStateProvinceName varchar(50)
DECLARE @CustomerStateProvinceKey int;
-- COUNTRYREGION KEY
DECLARE @CustomerCountryRegionCode varchar(50)
DECLARE @CustomerCountryRegionName varchar(50)
DECLARE @CustomerCountryRegionKey int;
-- YEARLYINCOME KEY
DECLARE @CustomerYearlyIncome varchar(50)
DECLARE @CustomerYearlyIncomeKey int;

SET @GeneralCursor = CURSOR FOR (
	 SELECT DISTINCT [CustomerKey], 
			         [Title], 
					 [FirstName], 
					 [MiddleName], 
					 [LastName], 
					 [NameStyle],
					 CONVERT(DATE, [BirthDate], 103) as 'BirthDate',
					 [MaritalStatus], 
					 [Gender], 
					 [EmailAddress],
					 CAST([YearlyIncome] as money) as 'YearlyIncome',
					 CAST([TotalChildren] as tinyint) as 'TotalChildren',
					 CAST([NumberChildrenAtHome] as tinyint) as 'NumberChildrenAtHome',
					 [Education],
					 [Occupation],
					 CAST([HouseOwnerFlag] as bit) as 'HouseOwnerFlag',
					 CAST([NumberCarsOwned] as tinyint) as 'NumberCarsOwned',
					 [AddressLine1],
					 [AddressLine2],
					 [City],
					 [StateProvinceCode],
					 [StateProvinceName],
					 [CountryRegionCode],
					 [CountryRegionName],
					 [PostalCode],
					 CAST([SalesTerritoryKey] as int) as 'SalesTerritoryKey',
					 [Phone],
					 CONVERT(DATE, [DateFirstPurchase], 103) as 'DateFirstPurchase',
					 [CommuteDistance]
	FROM [AdventureOldData].[dbo].[Customer]);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor
		INTO @CustomerKey, 
			 @Title, 
			 @FirstName, 
			 @MiddleName, 
			 @LastName, 
			 @NameStyle, 
			 @BirthDate, 
			 @MaritalStatus, 
			 @Gender, 
			 @EmailAddress,
			 @CustomerYearlyIncome,
			 @TotalChildren,
			 @NumberChildrenAtHome,
			 @CustomerEducation,
			 @CustomerOccupation,
			 @HouseOwnerFlag,
			 @NumberCarsOwned,
			 @AddressLine1,
			 @AddressLine2,
			 @City,
			 @CustomerStateProvinceCode,
			 @CustomerStateProvinceName,
			 @CustomerCountryRegionCode,
			 @CustomerCountryRegionName,
			 @PostalCode,
			 @SalesTerritoryKey,
			 @Phone,
			 @DateFirstPurchase,
			 @CustomerCommuteDistance;

	WHILE @@FETCH_STATUS = 0
	BEGIN 
		-- EDUCATION KEY
		SELECT  @CustomerEducationKey = [CustomerEducationKey] 
		FROM [AdventureNewData].[Customers].[CustomerEducation] 
		WHERE [EducationName] LIKE @CustomerEducation;
		-- OCCUPATION KEY
		SELECT  @CustomerOccupationKey = [CustomerOccupationKey] 
		FROM [AdventureNewData].[Customers].[CustomerOccupation] 
		WHERE [OccupationName] LIKE @CustomerOccupation;
		-- COMMUTEDISTANCE KEY
		SELECT  @CustomerCommuteDistanceKey = [CustomerCommuteDistanceKey] 
		FROM [AdventureNewData].[Customers].[CustomerCommuteDistance] 
		WHERE [CommuteDistanceName] LIKE @CustomerCommuteDistance;
		-- STATEPROVINCE KEY
		SELECT  @CustomerStateProvinceKey = [CustomerStateProvinceKey] 
		FROM [AdventureNewData].[Customers].[CustomerStateProvince] 
		WHERE [StateProvinceCode] LIKE @CustomerStateProvinceCode AND 
		[StateProvinceName] LIKE @CustomerStateProvinceName;
		-- COUNTRYREGION KEY
		SELECT  @CustomerCountryRegionKey = [CustomerCountryRegionKey] 
		FROM [AdventureNewData].[Customers].[CustomerCountryRegion] 
		WHERE [CountryRegionCode] LIKE @CustomerCountryRegionCode AND 
		[CountryRegionName] LIKE @CustomerCountryRegionName;
		-- YEARLYINCOME KEY
		SELECT  @CustomerYearlyIncomeKey = [CustomerYearlyIncomeKey] 
		FROM [AdventureNewData].[Customers].[CustomerYearlyIncome] 
		WHERE [YearlyIncome] LIKE @CustomerYearlyIncome;
		--
		SET IDENTITY_INSERT [AdventureNewData].[Customers].[Customer] ON;
		INSERT INTO [AdventureNewData].[Customers].[Customer] 
		(
			CustomerKey, 
			Title,
			FirstName,
			MiddleName,
			LastName,
			NameStyle,
			Birthdate,
			Gender, 
			EmailAddress,
			PostalCode,
			Phone, 
			HouseOwnerFlag, 
			NumberCarsOwned, 
			City, 
			TotalChildren, 
			NumberChildrenAtHome, 
			MaritalStatus, 
			AddressLine1,
			AddressLine2,
			DateFirstPurchase,
			CustomerEducationKey, 
			CustomerOccupationKey, 
			CustomerCommuteDistanceKey,
			CustomerStateProvinceKey, 
			CustomerCountryRegionKey,
			CustomerYearlyIncomeKey,
			SalesTerritoryKey
		)
		VALUES 
		(
			@CustomerKey, 
			@Title, 
			@FirstName, 
			@MiddleName, 
			@LastName,
			@NameStyle, 
			@Birthdate,
			@Gender, 
			@EmailAddress,
			@PostalCode,
			@Phone, 
			@HouseOwnerFlag, 
			@NumberCarsOwned, 
			@City, 
			@TotalChildren, 
			@NumberChildrenAtHome, 
			@MaritalStatus,
			@AddressLine1,
			@AddressLine2,
			@DateFirstPurchase,
			@CustomerEducationKey,
			@CustomerOccupationKey, 
			@CustomerCommuteDistanceKey,
			@CustomerStateProvinceKey, 
			@CustomerCountryRegionKey,
			@CustomerYearlyIncomeKey,
			@SalesTerritoryKey
		);
		SET IDENTITY_INSERT [AdventureNewData].[Customers].[Customer] OFF;
		FETCH NEXT FROM @GeneralCursor
		INTO @CustomerKey, 
			 @Title, 
			 @FirstName, 
			 @MiddleName, 
			 @LastName, 
			 @NameStyle, 
			 @BirthDate, 
			 @MaritalStatus, 
			 @Gender, 
			 @EmailAddress,
			 @CustomerYearlyIncome,
			 @TotalChildren,
			 @NumberChildrenAtHome,
			 @CustomerEducation,
			 @CustomerOccupation,
			 @HouseOwnerFlag,
			 @NumberCarsOwned,
			 @AddressLine1,
			 @AddressLine2,
			 @City,
			 @CustomerStateProvinceCode,
			 @CustomerStateProvinceName,
			 @CustomerCountryRegionCode,
			 @CustomerCountryRegionName,
			 @PostalCode,
			 @SalesTerritoryKey,
			 @Phone,
			 @DateFirstPurchase,
			 @CustomerCommuteDistance;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Customers.Customer;
--SELECT * FROM AdventureOldData.dbo.Customer;


-- -----------------------------------------------------
-- Table SalesCurrency
-- -----------------------------------------------------
DECLARE @SalesCurrencyKey int
DECLARE @CurencyAlternateKey varchar(50)
DECLARE @CurrencyName varchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT *
	FROM [AdventureOldData].[dbo].[Currency] 
	WHERE [CurrencyKey] IS NOT NULL);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor INTO @SalesCurrencyKey, @CurencyAlternateKey, @CurrencyName;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesCurrency] ON;
		INSERT INTO [AdventureNewData].[Sales].[SalesCurrency] (SalesCurrencyKey, CurrencyAlternateKey, CurrencyName) 
		VALUES (@SalesCurrencyKey, @CurencyAlternateKey, @CurrencyName);
		SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesCurrency] OFF;
		FETCH NEXT FROM @GeneralCursor INTO @SalesCurrencyKey, @CurencyAlternateKey, @CurrencyName;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Sales.SalesCurrency;


-- -----------------------------------------------------
-- Table SalesCompany
-- -----------------------------------------------------
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesStatus] ON;
INSERT INTO [AdventureNewData].[Sales].[SalesStatus] (SalesStatusKey, StatusName) 
VALUES (1, 'Processing');
INSERT INTO [AdventureNewData].[Sales].[SalesStatus] (SalesStatusKey, StatusName) 
VALUES (2, 'Shipped');
INSERT INTO [AdventureNewData].[Sales].[SalesStatus] (SalesStatusKey, StatusName) 
VALUES (3, 'Finished');
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesStatus] OFF;
		
SELECT * FROM AdventureNewData.Sales.SalesStatus;


-- -----------------------------------------------------
-- Table SalesCompany
-- -----------------------------------------------------
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesCompany] ON;
-- AdventureWorks
INSERT INTO [AdventureNewData].[Sales].[SalesCompany] (SalesCompanyKey, CompanyName) 
VALUES (1, 'AdventureWorks');
-- AdventureServices
INSERT INTO [AdventureNewData].[Sales].[SalesCompany] (SalesCompanyKey, CompanyName) 
VALUES (2, 'AdventureServices');
SET IDENTITY_INSERT [AdventureNewData].[Sales].[SalesCompany] OFF;
		
SELECT * FROM AdventureNewData.Sales.SalesCompany;

/*SELECT * FROM AdventureOldData.dbo.Products;
SELECT * FROM AdventureOldData.dbo.Customer;
SELECT * FROM AdventureOldData.dbo.Sales;*/


-- -----------------------------------------------------
-- Table SalesOrderHeader
-- -----------------------------------------------------
DECLARE @SalesOrderHeaderKey int
DECLARE @SalesOrderNumber varchar(15)
DECLARE @OrderDate datetime2(7)
DECLARE @DueDate datetime2(7)
DECLARE @ShipDate datetime2(7)
--DECLARE @SalesAmount money
DECLARE @SalesAmountSum money
-- TERRITORY
DECLARE @TerritoryRegion varchar(50)
DECLARE @TerritoryCountry varchar(50)
DECLARE @TerritoryKey int
-- CUSTOMER
DECLARE @SalesCustomerKey int
DECLARE @SalesCompanyKey int
-- CURRENCY
DECLARE @CurrencyKey int

SET @GeneralCursor = CURSOR FOR (
	SELECT [SalesOrderNumber], 
		   CONVERT(DATE, [OrderDate], 103) as 'OrderDate',
		   CONVERT(DATE, [DueDate], 103) as 'DueDate',
		   CONVERT(DATE, [ShipDate], 103) as 'ShipDate',
		   [SalesTerritoryRegion],
		   [SalesTerritoryCountry],
		   [CurrencyKey],
		   [CustomerKey]
	FROM [AdventureOldData].[dbo].[Sales]
	GROUP BY [SalesOrderNumber], 
		     [OrderDate],
		     [DueDate],
		     [ShipDate], 
		     [SalesTerritoryRegion],
		     [SalesTerritoryCountry],
		     [CurrencyKey],
		     [CustomerKey]
	HAVING COUNT(*) > 1);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor 
		INTO @SalesOrderNumber, 
			 @OrderDate,
			 @DueDate,
			 @ShipDate,
			 @TerritoryRegion,
			 @TerritoryCountry,
			 @CurrencyKey,
			 @SalesCustomerKey;
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		-- TERRITORY KEY
		SELECT @TerritoryKey = [SalesTerritoryKey] 
		FROM [AdventureNewData].[Sales].[SalesTerritory] 
		WHERE [SalesTerritoryRegion] LIKE @TerritoryRegion AND 
		[SalesTerritoryCountry] LIKE @TerritoryCountry;

		SELECT @SalesAmountSum = SUM(CONVERT(money, UnitPrice))
		FROM [AdventureOldData].[dbo].[Sales]
		WHERE [SalesOrderNumber] = @SalesOrderNumber
		GROUP BY [SalesOrderNumber];
		--
		INSERT INTO [AdventureNewData].[Sales].[SalesOrderHeader] 
		(
			SalesOrderNumber, 
			OrderDate, 
			DueDate, 
			ShipDate, 
			SalesAmount, 
			SalesTerritoryKey, 
			CustomerKey, 
			SalesCompanyKey, 
			SalesCurrencyKey,
			SalesStatusKey
		) 
		VALUES 
		(
			@SalesOrderNumber,
			@OrderDate,
			@DueDate,
			@ShipDate,
			@SalesAmountSum,
			@TerritoryKey,
			@SalesCustomerKey,
			1,
			@CurrencyKey,
			3
		);
		FETCH NEXT FROM @GeneralCursor 
		INTO @SalesOrderNumber,
			 @OrderDate,
			 @DueDate,
			 @ShipDate,
			 @TerritoryRegion,
			 @TerritoryCountry,
			 @CurrencyKey,
			 @SalesCustomerKey;
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Sales.SalesOrderHeader;


-- -----------------------------------------------------
-- Table SalesOrderDetail
-- -----------------------------------------------------
DECLARE @SalesOrderDetailKey int
DECLARE @OrderQuantity int
DECLARE @UnitPrice money
DECLARE @SalesProductKey int
DECLARE @OrderHeaderKey int
DECLARE @SalesOrderLineNumber tinyint
DECLARE @OrderNumber varchar(50)

SET @GeneralCursor = CURSOR FOR (
	SELECT DISTINCT CAST([OrderQuantity] as int) as 'OrderQuantity',
				    CAST([UnitPrice] as money) as 'UnitPrice',
				    [ProductKey],
				    CAST([SalesOrderLineNumber] as tinyint) as 'SalesOrderLineNumber',
				    [SalesOrderNumber]
	FROM [AdventureOldData].[dbo].[Sales]);
	OPEN @GeneralCursor;
	FETCH NEXT FROM @GeneralCursor 
	INTO @OrderQuantity,
		 @UnitPrice,
		 @SalesProductKey,
		 @SalesOrderLineNumber,
		 @OrderNumber
	WHILE @@FETCH_STATUS = 0
	BEGIN 
		--PRINT @SalesProductKey
		-- ORDER HEADER KEY
		SELECT @OrderHeaderKey = [SalesOrderHeaderKey] 
		FROM [AdventureNewData].[Sales].[SalesOrderHeader] 
		WHERE [SalesOrderNumber] LIKE @OrderNumber;
		INSERT INTO [AdventureNewData].[Sales].[SalesOrderDetail] 
		(
			OrderQuantity,
			UnitPrice,
			ProductKey, 
			SalesOrderLineNumber,
			SalesOrderHeaderKey
		) 
		VALUES 
		(
			@OrderQuantity,
			@UnitPrice,
			@SalesProductKey,
			@SalesOrderLineNumber, 
			@OrderHeaderKey
		);
		FETCH NEXT FROM @GeneralCursor 
		INTO @OrderQuantity,
		     @UnitPrice,
		     @SalesProductKey,
			 @SalesOrderLineNumber,
		     @OrderNumber    
	END 
	CLOSE @GeneralCursor;

SELECT * FROM AdventureNewData.Sales.SalesOrderDetail;

DEALLOCATE @GeneralCursor;



