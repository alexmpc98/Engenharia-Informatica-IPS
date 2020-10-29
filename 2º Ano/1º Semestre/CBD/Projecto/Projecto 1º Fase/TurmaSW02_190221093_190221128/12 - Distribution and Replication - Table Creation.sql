-----------------------------------------------------
-- Table creation for Europe database
-----------------------------------------------------
USE AdventureNewDataEurope;
GO
CREATE SCHEMA Customers;
GO
CREATE SCHEMA Products;
GO
CREATE SCHEMA Sales;
GO
-- -----------------------------------------------------
-- Table Education
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerEducation (
  CustomerEducationKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EducationName VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table Occupation
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerOccupation (
  CustomerOccupationKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  OccupationName VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table CommuteDistance
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerCommuteDistance (
  CustomerCommuteDistanceKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CommuteDistanceName VARCHAR(15) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table StateProvince
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerStateProvince (
  CustomerStateProvinceKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StateProvinceCode VARCHAR(5) NOT NULL,
  StateProvinceName VARCHAR(50) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table CountryRegion
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerCountryRegion (
  CustomerCountryRegionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CountryRegionCode VARCHAR(5) NOT NULL,
  CountryRegionName VARCHAR(50) NOT NULL
) ON Customer_Filegroup; 


-- -----------------------------------------------------
-- Table YearlyIncome
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerYearlyIncome (
  CustomerYearlyIncomeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  YearlyIncome MONEY NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table SalesTerritory
-- -----------------------------------------------------
CREATE TABLE Sales.SalesTerritory (
  SalesTerritoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SalesTerritoryRegion VARCHAR(25) NOT NULL,
  SalesTerritoryGroup VARCHAR(25) NOT NULL,
  SalesTerritoryCountry VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table Customer
-- -----------------------------------------------------
CREATE TABLE Customers.Customer (
  CustomerKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  Title VARCHAR(5) NULL,
  FirstName VARCHAR(50) NOT NULL,
  MiddleName VARCHAR(50) NULL,
  LastName VARCHAR(50) NOT NULL,
  NameStyle VARCHAR(50) NULL,
  Birthdate DATE NOT NULL,
  Gender VARCHAR(1) NOT NULL,
  EmailAddress VARCHAR(100) NOT NULL,
  PostalCode VARCHAR(15) NOT NULL,
  Phone VARCHAR(25) NOT NULL,
  HouseOwnerFlag BIT NOT NULL,
  NumberCarsOwned TINYINT NOT NULL,
  City VARCHAR(45) NULL,
  TotalChildren TINYINT NULL,
  NumberChildrenAtHome TINYINT NULL,
  MaritalStatus VARCHAR(1) NULL,
  AddressLine1 VARCHAR(50) NOT NULL,
  AddressLine2 VARCHAR(50), 
  DateFirstPurchase DATE NOT NULL,
  CustomerEducationKey INT NOT NULL,
  CustomerOccupationKey INT NOT NULL,
  CustomerCommuteDistanceKey INT NOT NULL,
  CustomerStateProvinceKey INT NOT NULL,
  CustomerCountryRegionKey INT NOT NULL,
  CustomerYearlyIncomeKey INT NOT NULL,
  SalesTerritoryKey INT NOT NULL,
  FOREIGN KEY (CustomerEducationKey) REFERENCES Customers.CustomerEducation (CustomerEducationKey),
  FOREIGN KEY (CustomerOccupationKey) REFERENCES Customers.CustomerOccupation (CustomerOccupationKey),
  FOREIGN KEY (CustomerCommuteDistanceKey) REFERENCES Customers.CustomerCommuteDistance (CustomerCommuteDistanceKey),
  FOREIGN KEY (CustomerStateProvinceKey) REFERENCES Customers.CustomerStateProvince (CustomerStateProvinceKey),
  FOREIGN KEY (CustomerCountryRegionKey) REFERENCES Customers.CustomerCountryRegion (CustomerCountryRegionKey),
  FOREIGN KEY (CustomerYearlyIncomeKey) REFERENCES Customers.CustomerYearlyIncome (CustomerYearlyIncomeKey),
  FOREIGN KEY (SalesTerritoryKey) REFERENCES Sales.SalesTerritory (SalesTerritoryKey)
) ON Customer_Filegroup;

-- -----------------------------------------------------
-- Table SalesCurrency
-- -----------------------------------------------------
CREATE TABLE Sales.SalesCurrency (
  SalesCurrencyKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CurrencyAlternateKey VARCHAR(50) NULL,
  CurrencyName VARCHAR(50) NULL
) ON Sales_Filegroup;


-- -----------------------------------------------------
-- Table ProductColor
-- -----------------------------------------------------
CREATE TABLE Products.ProductColor (
  ProductColorKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ColorName VARCHAR(15) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductModel
-- -----------------------------------------------------
CREATE TABLE Products.ProductModel (
  ProductModelKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ModelName VARCHAR(100) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductDescription
-- -----------------------------------------------------
CREATE TABLE Products.ProductDescription (
  ProductDescriptionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishDescription VARCHAR(300) NOT NULL,
  FrenchDescription VARCHAR(300) NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductMeasureUnitCode
-- -----------------------------------------------------
CREATE TABLE Products.ProductMeasureUnitCode (
  ProductMeasureUnitCodeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  WeightUnitMeasureCode VARCHAR(2) NULL,
  SizeUnitMeasureCode VARCHAR(2) NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductCategory
-- -----------------------------------------------------
CREATE TABLE Products.ProductCategory (
  ProductCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductCategoryName VARCHAR(50) NOT NULL,
  SpanishProductCategoryName VARCHAR(50) NOT NULL,
  FrenchProductCategoryName VARCHAR(50) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSubCategory
-- -----------------------------------------------------
CREATE TABLE Products.ProductSubCategory (
  ProductSubCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductSubCategoryName VARCHAR(50) NOT NULL,
  SpanishProductSubCategoryName VARCHAR(50) NOT NULL,
  FrenchProductSubCategoryName VARCHAR(50) NOT NULL,
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Category_SubCategory
-- -----------------------------------------------------
CREATE TABLE Products.Category_SubCategory (
  CategorySubCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ProductCategoryKey INT NOT NULL,
  ProductSubCategoryKey INT NOT NULL,
  FOREIGN KEY (ProductCategoryKey) REFERENCES Products.ProductCategory (ProductCategoryKey),
  FOREIGN KEY (ProductSubCategoryKey) REFERENCES Products.ProductSubCategory (ProductSubCategoryKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductName
-- -----------------------------------------------------
CREATE TABLE Products.ProductName (
  ProductNameKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductName VARCHAR(100) NOT NULL,
  SpanishProductName VARCHAR(100),
  FrenchProductName VARCHAR(100)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSize
-- -----------------------------------------------------
CREATE TABLE Products.ProductSize (
  ProductSizeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  Size VARCHAR(5)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSizeRange
-- -----------------------------------------------------
CREATE TABLE Products.ProductSizeRange (
  ProductSizeRangeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SizeRange VARCHAR(15)NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Size_SizeRange
-- -----------------------------------------------------
CREATE TABLE Products.Size_SizeRange (
  SizeSizeRangeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ProductSizeKey INT NOT NULL,
  ProductSizeRangeKey INT NOT NULL,
  FOREIGN KEY (ProductSizeKey) REFERENCES Products.ProductSize (ProductSizeKey),
  FOREIGN KEY (ProductSizeRangeKey) REFERENCES Products.ProductSizeRange(ProductSizeRangeKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Product
-- -----------------------------------------------------
CREATE TABLE Products.Product (
  ProductKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ListPrice MONEY NULL,
  FinishedGoodsFlag BIT NOT NULL,
  ProductColor INT NOT NULL,
  SafetyStockLevel INT NOT NULL,
  StandardCost MONEY NULL,
  Weight FLOAT NULL,
  DaysToManufacture TINYINT NOT NULL,
  ProductLine VARCHAR(1) NULL,
  DealerPrice MONEY NULL,
  Class VARCHAR(1) NULL,
  Style VARCHAR(1) NULL,
  Status VARCHAR(10) NULL,
  ProductModelKey INT NOT NULL,
  ProductDescriptionKey INT NULL,
  ProductNameKey INT NOT NULL,
  CategorySubCategoryKey INT NOT NULL,
  ProductMeasureUnitCodeKey INT NULL,
  SizeSizeRangeKey INT NULL,
  FOREIGN KEY (ProductColor) REFERENCES Products.ProductColor (ProductColorKey),
  FOREIGN KEY (ProductModelKey) REFERENCES Products.ProductModel (ProductModelKey),
  FOREIGN KEY (ProductDescriptionKey) REFERENCES Products.ProductDescription (ProductDescriptionKey),
  FOREIGN KEY (ProductNameKey) REFERENCES Products.ProductName (ProductNameKey),
  FOREIGN KEY (CategorySubCategoryKey) REFERENCES Products.Category_SubCategory (CategorySubCategoryKey),
  FOREIGN KEY (ProductMeasureUnitCodeKey) REFERENCES Products.ProductMeasureUnitCode (ProductMeasureUnitCodeKey),
  FOREIGN KEY (SizeSizeRangeKey) REFERENCES Products.Size_SizeRange (SizeSizeRangeKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductPromotion
-- -----------------------------------------------------
CREATE TABLE Products.ProductPromotion (
  ProductPromotionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  Active BIT NOT NULL,
  PriceDiscount MONEY NOT NULL,
  ProductKey INT NOT NULL,
  FOREIGN KEY (ProductKey) REFERENCES Products.Product (ProductKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table SalesCompany
-- -----------------------------------------------------
CREATE TABLE Sales.SalesCompany (
  SalesCompanyKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CompanyName VARCHAR(50) NOT NULL
) ON Sales_Filegroup;


-- -----------------------------------------------------
-- Table SalesStatus
-- -----------------------------------------------------
CREATE TABLE Sales.SalesStatus (
  SalesStatusKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StatusName VARCHAR(50) NOT NULL,
) ON Sales_Filegroup;

-- -----------------------------------------------------
-- Table SalesOrderHeader
-- -----------------------------------------------------
CREATE TABLE Sales.SalesOrderHeader (
  SalesOrderHeaderKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SalesOrderNumber VARCHAR(15) UNIQUE NOT NULL,
  OrderDate DATE NOT NULL,
  DueDate DATE NOT NULL,
  ShipDate DATE NOT NULL,
  SalesAmount MONEY NOT NULL,
  SalesTerritoryKey INT NOT NULL,
  CustomerKey INT NOT NULL,
  SalesCompanyKey INT NOT NULL,
  SalesCurrencyKey INT NOT NULL,
  FOREIGN KEY (SalesTerritoryKey) REFERENCES Sales.SalesTerritory (SalesTerritoryKey),
  FOREIGN KEY (CustomerKey) REFERENCES Customers.Customer (CustomerKey),
  FOREIGN KEY (SalesCompanyKey) REFERENCES Sales.SalesCompany (SalesCompanyKey),
  FOREIGN KEY (SalesCurrencyKey) REFERENCES Sales.SalesCurrency (SalesCurrencyKey)
) ON Sales_Filegroup;

-- -----------------------------------------------------
-- Table SalesOrderDetail
-- -----------------------------------------------------
CREATE TABLE Sales.SalesOrderDetail (
  SalesOrderDetailKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  OrderQuantity INT NULL,
  UnitPrice MONEY NOT NULL,
  ProductKey INT NOT NULL,
  SalesOrderLineNumber TINYINT NOT NULL,
  SalesOrderHeaderKey INT NOT NULL,
  FOREIGN KEY (ProductKey) REFERENCES Products.Product (ProductKey),
  FOREIGN KEY (SalesOrderHeaderKey) REFERENCES Sales.SalesOrderHeader (SalesOrderHeaderKey)
) ON Sales_Filegroup;

-----------------------------------------------------
-- Table creation for Pacific database
-----------------------------------------------------
USE AdventureNewDataPacific;
GO
CREATE SCHEMA Customers;
GO
CREATE SCHEMA Products;
GO
CREATE SCHEMA Sales;
GO
-- -----------------------------------------------------
-- Table Education
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerEducation (
  CustomerEducationKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EducationName VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table Occupation
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerOccupation (
  CustomerOccupationKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  OccupationName VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table CommuteDistance
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerCommuteDistance (
  CustomerCommuteDistanceKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CommuteDistanceName VARCHAR(15) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table StateProvince
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerStateProvince (
  CustomerStateProvinceKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StateProvinceCode VARCHAR(5) NOT NULL,
  StateProvinceName VARCHAR(50) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table CountryRegion
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerCountryRegion (
  CustomerCountryRegionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CountryRegionCode VARCHAR(5) NOT NULL,
  CountryRegionName VARCHAR(50) NOT NULL
) ON Customer_Filegroup; 


-- -----------------------------------------------------
-- Table YearlyIncome
-- -----------------------------------------------------
CREATE TABLE Customers.CustomerYearlyIncome (
  CustomerYearlyIncomeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  YearlyIncome MONEY NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table SalesTerritory
-- -----------------------------------------------------
CREATE TABLE Sales.SalesTerritory (
  SalesTerritoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SalesTerritoryRegion VARCHAR(25) NOT NULL,
  SalesTerritoryGroup VARCHAR(25) NOT NULL,
  SalesTerritoryCountry VARCHAR(25) NOT NULL
) ON Customer_Filegroup;


-- -----------------------------------------------------
-- Table Customer
-- -----------------------------------------------------
CREATE TABLE Customers.Customer (
  CustomerKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  Title VARCHAR(5) NULL,
  FirstName VARCHAR(50) NOT NULL,
  MiddleName VARCHAR(50) NULL,
  LastName VARCHAR(50) NOT NULL,
  NameStyle VARCHAR(50) NULL,
  Birthdate DATE NOT NULL,
  Gender VARCHAR(1) NOT NULL,
  EmailAddress VARCHAR(100) NOT NULL,
  PostalCode VARCHAR(15) NOT NULL,
  Phone VARCHAR(25) NOT NULL,
  HouseOwnerFlag BIT NOT NULL,
  NumberCarsOwned TINYINT NOT NULL,
  City VARCHAR(45) NULL,
  TotalChildren TINYINT NULL,
  NumberChildrenAtHome TINYINT NULL,
  MaritalStatus VARCHAR(1) NULL,
  AddressLine1 VARCHAR(50) NOT NULL,
  AddressLine2 VARCHAR(50), 
  DateFirstPurchase DATE NOT NULL,
  CustomerEducationKey INT NOT NULL,
  CustomerOccupationKey INT NOT NULL,
  CustomerCommuteDistanceKey INT NOT NULL,
  CustomerStateProvinceKey INT NOT NULL,
  CustomerCountryRegionKey INT NOT NULL,
  CustomerYearlyIncomeKey INT NOT NULL,
  SalesTerritoryKey INT NOT NULL,
  FOREIGN KEY (CustomerEducationKey) REFERENCES Customers.CustomerEducation (CustomerEducationKey),
  FOREIGN KEY (CustomerOccupationKey) REFERENCES Customers.CustomerOccupation (CustomerOccupationKey),
  FOREIGN KEY (CustomerCommuteDistanceKey) REFERENCES Customers.CustomerCommuteDistance (CustomerCommuteDistanceKey),
  FOREIGN KEY (CustomerStateProvinceKey) REFERENCES Customers.CustomerStateProvince (CustomerStateProvinceKey),
  FOREIGN KEY (CustomerCountryRegionKey) REFERENCES Customers.CustomerCountryRegion (CustomerCountryRegionKey),
  FOREIGN KEY (CustomerYearlyIncomeKey) REFERENCES Customers.CustomerYearlyIncome (CustomerYearlyIncomeKey),
  FOREIGN KEY (SalesTerritoryKey) REFERENCES Sales.SalesTerritory (SalesTerritoryKey)
) ON Customer_Filegroup;

-- -----------------------------------------------------
-- Table SalesCurrency
-- -----------------------------------------------------
CREATE TABLE Sales.SalesCurrency (
  SalesCurrencyKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CurrencyAlternateKey VARCHAR(50) NULL,
  CurrencyName VARCHAR(50) NULL
) ON Sales_Filegroup;


-- -----------------------------------------------------
-- Table ProductColor
-- -----------------------------------------------------
CREATE TABLE Products.ProductColor (
  ProductColorKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ColorName VARCHAR(15) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductModel
-- -----------------------------------------------------
CREATE TABLE Products.ProductModel (
  ProductModelKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ModelName VARCHAR(100) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductDescription
-- -----------------------------------------------------
CREATE TABLE Products.ProductDescription (
  ProductDescriptionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishDescription VARCHAR(300) NOT NULL,
  FrenchDescription VARCHAR(300) NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductMeasureUnitCode
-- -----------------------------------------------------
CREATE TABLE Products.ProductMeasureUnitCode (
  ProductMeasureUnitCodeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  WeightUnitMeasureCode VARCHAR(2) NULL,
  SizeUnitMeasureCode VARCHAR(2) NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductCategory
-- -----------------------------------------------------
CREATE TABLE Products.ProductCategory (
  ProductCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductCategoryName VARCHAR(50) NOT NULL,
  SpanishProductCategoryName VARCHAR(50) NOT NULL,
  FrenchProductCategoryName VARCHAR(50) NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSubCategory
-- -----------------------------------------------------
CREATE TABLE Products.ProductSubCategory (
  ProductSubCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductSubCategoryName VARCHAR(50) NOT NULL,
  SpanishProductSubCategoryName VARCHAR(50) NOT NULL,
  FrenchProductSubCategoryName VARCHAR(50) NOT NULL,
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Category_SubCategory
-- -----------------------------------------------------
CREATE TABLE Products.Category_SubCategory (
  CategorySubCategoryKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ProductCategoryKey INT NOT NULL,
  ProductSubCategoryKey INT NOT NULL,
  FOREIGN KEY (ProductCategoryKey) REFERENCES Products.ProductCategory (ProductCategoryKey),
  FOREIGN KEY (ProductSubCategoryKey) REFERENCES Products.ProductSubCategory (ProductSubCategoryKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductName
-- -----------------------------------------------------
CREATE TABLE Products.ProductName (
  ProductNameKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  EnglishProductName VARCHAR(100) NOT NULL,
  SpanishProductName VARCHAR(100),
  FrenchProductName VARCHAR(100)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSize
-- -----------------------------------------------------
CREATE TABLE Products.ProductSize (
  ProductSizeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  Size VARCHAR(5)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductSizeRange
-- -----------------------------------------------------
CREATE TABLE Products.ProductSizeRange (
  ProductSizeRangeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SizeRange VARCHAR(15)NOT NULL
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Size_SizeRange
-- -----------------------------------------------------
CREATE TABLE Products.Size_SizeRange (
  SizeSizeRangeKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ProductSizeKey INT NOT NULL,
  ProductSizeRangeKey INT NOT NULL,
  FOREIGN KEY (ProductSizeKey) REFERENCES Products.ProductSize (ProductSizeKey),
  FOREIGN KEY (ProductSizeRangeKey) REFERENCES Products.ProductSizeRange(ProductSizeRangeKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table Product
-- -----------------------------------------------------
CREATE TABLE Products.Product (
  ProductKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  ListPrice MONEY NULL,
  FinishedGoodsFlag BIT NOT NULL,
  ProductColor INT NOT NULL,
  SafetyStockLevel INT NOT NULL,
  StandardCost MONEY NULL,
  Weight FLOAT NULL,
  DaysToManufacture TINYINT NOT NULL,
  ProductLine VARCHAR(1) NULL,
  DealerPrice MONEY NULL,
  Class VARCHAR(1) NULL,
  Style VARCHAR(1) NULL,
  Status VARCHAR(10) NULL,
  ProductModelKey INT NOT NULL,
  ProductDescriptionKey INT NULL,
  ProductNameKey INT NOT NULL,
  CategorySubCategoryKey INT NOT NULL,
  ProductMeasureUnitCodeKey INT NULL,
  SizeSizeRangeKey INT NULL,
  FOREIGN KEY (ProductColor) REFERENCES Products.ProductColor (ProductColorKey),
  FOREIGN KEY (ProductModelKey) REFERENCES Products.ProductModel (ProductModelKey),
  FOREIGN KEY (ProductDescriptionKey) REFERENCES Products.ProductDescription (ProductDescriptionKey),
  FOREIGN KEY (ProductNameKey) REFERENCES Products.ProductName (ProductNameKey),
  FOREIGN KEY (CategorySubCategoryKey) REFERENCES Products.Category_SubCategory (CategorySubCategoryKey),
  FOREIGN KEY (ProductMeasureUnitCodeKey) REFERENCES Products.ProductMeasureUnitCode (ProductMeasureUnitCodeKey),
  FOREIGN KEY (SizeSizeRangeKey) REFERENCES Products.Size_SizeRange (SizeSizeRangeKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table ProductPromotion
-- -----------------------------------------------------
CREATE TABLE Products.ProductPromotion (
  ProductPromotionKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  Active BIT NOT NULL,
  PriceDiscount MONEY NOT NULL,
  ProductKey INT NOT NULL,
  FOREIGN KEY (ProductKey) REFERENCES Products.Product (ProductKey)
) ON Products_Filegroup;


-- -----------------------------------------------------
-- Table SalesCompany
-- -----------------------------------------------------
CREATE TABLE Sales.SalesCompany (
  SalesCompanyKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  CompanyName VARCHAR(50) NOT NULL
) ON Sales_Filegroup;


-- -----------------------------------------------------
-- Table SalesStatus
-- -----------------------------------------------------
CREATE TABLE Sales.SalesStatus (
  SalesStatusKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  StatusName VARCHAR(50) NOT NULL,
) ON Sales_Filegroup;

-- -----------------------------------------------------
-- Table SalesOrderHeader
-- -----------------------------------------------------
CREATE TABLE Sales.SalesOrderHeader (
  SalesOrderHeaderKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  SalesOrderNumber VARCHAR(15) UNIQUE NOT NULL,
  OrderDate DATE NOT NULL,
  DueDate DATE NOT NULL,
  ShipDate DATE NOT NULL,
  SalesAmount MONEY NOT NULL,
  SalesTerritoryKey INT NOT NULL,
  CustomerKey INT NOT NULL,
  SalesCompanyKey INT NOT NULL,
  SalesCurrencyKey INT NOT NULL,
  FOREIGN KEY (SalesTerritoryKey) REFERENCES Sales.SalesTerritory (SalesTerritoryKey),
  FOREIGN KEY (CustomerKey) REFERENCES Customers.Customer (CustomerKey),
  FOREIGN KEY (SalesCompanyKey) REFERENCES Sales.SalesCompany (SalesCompanyKey),
  FOREIGN KEY (SalesCurrencyKey) REFERENCES Sales.SalesCurrency (SalesCurrencyKey)
) ON Sales_Filegroup;

-- -----------------------------------------------------
-- Table SalesOrderDetail
-- -----------------------------------------------------
CREATE TABLE Sales.SalesOrderDetail (
  SalesOrderDetailKey INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
  OrderQuantity INT NULL,
  UnitPrice MONEY NOT NULL,
  ProductKey INT NOT NULL,
  SalesOrderLineNumber TINYINT NOT NULL,
  SalesOrderHeaderKey INT NOT NULL,
  FOREIGN KEY (ProductKey) REFERENCES Products.Product (ProductKey),
  FOREIGN KEY (SalesOrderHeaderKey) REFERENCES Sales.SalesOrderHeader (SalesOrderHeaderKey)
) ON Sales_Filegroup;