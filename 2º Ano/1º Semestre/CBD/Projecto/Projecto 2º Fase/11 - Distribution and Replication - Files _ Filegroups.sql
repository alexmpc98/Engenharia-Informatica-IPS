--------------------------------
-- Pacific Files & Filegroups --
--------------------------------
DROP DATABASE IF EXISTS [AdventureNewDataPacific]
CREATE DATABASE [AdventureNewDataPacific]  
ON PRIMARY ( 
NAME = AdventureNewDataPacific, 
FILENAME = 'C:\ProjectoGrupo6\Pacific\AdventureWorksCBD.mdf', 
SIZE = 145MB,
MAXSIZE =  360MB ,
FILEGROWTH = 43MB) 
LOG ON(
Name = AdventureNewDataPacific_Log,
FILENAME = 'C:\ProjectoGrupo6\Pacific\AdventureNewData_Log.ldf',
SIZE = 145MB,
MAXSIZE = 360MB,
FILEGROWTH = 43MB);


-- FileGroup Products 
GO
ALTER DATABASE  [AdventureNewDataPacific]  
ADD FILEGROUP Products_Filegroup;

ALTER DATABASE  [AdventureNewDataPacific]  
ADD FILE 
(NAME =  ProductDescription , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductDescription.ndf', 
SIZE = 608KB, 
MAXSIZE = 851KB, 
FILEGROWTH = 49KB)
TO FILEGROUP Products_Filegroup;  


ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductName , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductName.ndf', 
SIZE = 810KB, 
MAXSIZE = 934KB, 
FILEGROWTH = 25KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductModel , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductModel.ndf', 
SIZE = 512KB, 
MAXSIZE = 555KB, 
FILEGROWTH = 9KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductColor , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductColor.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductSubCategory , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductSubCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 748KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductCategory , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 700KB, 
FILEGROWTH = 8KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductPromotion , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductPromotion.ndf', 
SIZE = 525KB, 
MAXSIZE = 532KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductMeasureUnitCode , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductMeasureUnitCode.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductSize , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductSize.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductSize_SizeRange , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductSize_SizeRange.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  ProductSizeRange , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductSizeRange.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  ProductCategory_SubCategory , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\ProductCategory_SubCategory.ndf', 
SIZE = 512KB, 
MAXSIZE = 517KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  Product , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\Product.ndf', 
SIZE = 588KB, 
MAXSIZE = 676KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

-- FileGroup Sales
GO
ALTER DATABASE [AdventureNewDataPacific]  
ADD FILEGROUP Sales_Filegroup;

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  SalesCompany , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\SalesCompany.ndf', 
SIZE = 556KB, 
MAXSIZE = 570KB, 
FILEGROWTH =  3KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  SalesCurrency , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\SalesCurrency.ndf', 
SIZE = 608KB, 
MAXSIZE = 635KB, 
FILEGROWTH = 6KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  SalesOrderDetail , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\SalesOrderDetail.ndf', 
SIZE = 1MB, 
MAXSIZE = 3MB, 
FILEGROWTH =  227KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  SalesOrderHeader , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\SalesOrderHeader.ndf', 
SIZE = 1MB, 
MAXSIZE = 2MB, 
FILEGROWTH =  187KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  SalesTerritory , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\SalesTerritory.ndf', 
SIZE = 585KB, 
MAXSIZE = 619KB, 
FILEGROWTH =  7KB)
TO FILEGROUP Sales_Filegroup; 

-- FileGroup Customer 
GO
ALTER DATABASE [AdventureNewDataPacific]  
ADD FILEGROUP Customer_Filegroup;

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  Customer , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\Customer.ndf', 
SIZE = 11MB, 
MAXSIZE = 26MB, 
FILEGROWTH = 3MB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  CustomerCommuteDistance , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerCommuteDistance.ndf', 
SIZE = 521KB, 
MAXSIZE = 526KB, 
FILEGROWTH =  1KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  CustomerCountryRegion , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerCountryRegion.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  CustomerEducation , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerEducation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE( 
NAME =  CustomerOccupation , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerOccupation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  CustomerStateProvince , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerStateProvince.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataPacific]  
ADD FILE(
NAME =  CustomerStateYearlyIncome , 
FILENAME = 'C:\ProjectoGrupo6\Pacific\CustomerStateYearlyIncome.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Customer_Filegroup; 

--------------------------------
-- Europe Files & Filegroups --
--------------------------------
DROP DATABASE IF EXISTS [AdventureNewDataEurope]
CREATE DATABASE [AdventureNewDataEurope]  
ON PRIMARY ( 
NAME = AdventureNewDataEurope, 
FILENAME = 'C:\ProjectoGrupo6\Europe\AdventureWorksCBD.mdf', 
SIZE = 145MB,
MAXSIZE =  360MB ,
FILEGROWTH = 43MB) 
LOG ON(
Name = AdventureNewDataEurope_Log,
FILENAME = 'C:\ProjectoGrupo6\Europe\AdventureNewData_Log.ldf',
SIZE = 145MB,
MAXSIZE = 360MB,
FILEGROWTH = 43MB);


-- FileGroup Products 
GO
ALTER DATABASE  [AdventureNewDataEurope]  
ADD FILEGROUP Products_Filegroup;

ALTER DATABASE  [AdventureNewDataEurope]  
ADD FILE 
(NAME =  ProductDescription , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductDescription.ndf', 
SIZE = 608KB, 
MAXSIZE = 851KB, 
FILEGROWTH = 49KB)
TO FILEGROUP Products_Filegroup;  


ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductName , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductName.ndf', 
SIZE = 810KB, 
MAXSIZE = 934KB, 
FILEGROWTH = 25KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductModel , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductModel.ndf', 
SIZE = 512KB, 
MAXSIZE = 555KB, 
FILEGROWTH = 9KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductColor , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductColor.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductSubCategory , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductSubCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 748KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductCategory , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 700KB, 
FILEGROWTH = 8KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductPromotion , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductPromotion.ndf', 
SIZE = 525KB, 
MAXSIZE = 532KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductMeasureUnitCode , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductMeasureUnitCode.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductSize , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductSize.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductSize_SizeRange , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductSize_SizeRange.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  ProductSizeRange , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductSizeRange.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  ProductCategory_SubCategory , 
FILENAME = 'C:\ProjectoGrupo6\Europe\ProductCategory_SubCategory.ndf', 
SIZE = 512KB, 
MAXSIZE = 517KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  Product , 
FILENAME = 'C:\ProjectoGrupo6\Europe\Product.ndf', 
SIZE = 588KB, 
MAXSIZE = 676KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

-- FileGroup Sales
GO
ALTER DATABASE [AdventureNewDataEurope]  
ADD FILEGROUP Sales_Filegroup;

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  SalesCompany , 
FILENAME = 'C:\ProjectoGrupo6\Europe\SalesCompany.ndf', 
SIZE = 556KB, 
MAXSIZE = 570KB, 
FILEGROWTH =  3KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  SalesCurrency , 
FILENAME = 'C:\ProjectoGrupo6\Europe\SalesCurrency.ndf', 
SIZE = 608KB, 
MAXSIZE = 635KB, 
FILEGROWTH = 6KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  SalesOrderDetail , 
FILENAME = 'C:\ProjectoGrupo6\Europe\SalesOrderDetail.ndf', 
SIZE = 1MB, 
MAXSIZE = 3MB, 
FILEGROWTH =  227KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  SalesOrderHeader , 
FILENAME = 'C:\ProjectoGrupo6\Europe\SalesOrderHeader.ndf', 
SIZE = 1MB, 
MAXSIZE = 2MB, 
FILEGROWTH =  187KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  SalesTerritory , 
FILENAME = 'C:\ProjectoGrupo6\Europe\SalesTerritory.ndf', 
SIZE = 585KB, 
MAXSIZE = 619KB, 
FILEGROWTH =  7KB)
TO FILEGROUP Sales_Filegroup; 

-- FileGroup Customer 
GO
ALTER DATABASE [AdventureNewDataEurope]  
ADD FILEGROUP Customer_Filegroup;

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  Customer , 
FILENAME = 'C:\ProjectoGrupo6\Europe\Customer.ndf', 
SIZE = 11MB, 
MAXSIZE = 26MB, 
FILEGROWTH = 3MB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  CustomerCommuteDistance , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerCommuteDistance.ndf', 
SIZE = 521KB, 
MAXSIZE = 526KB, 
FILEGROWTH =  1KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  CustomerCountryRegion , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerCountryRegion.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  CustomerEducation , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerEducation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE( 
NAME =  CustomerOccupation , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerOccupation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  CustomerStateProvince , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerStateProvince.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewDataEurope]  
ADD FILE(
NAME =  CustomerStateYearlyIncome , 
FILENAME = 'C:\ProjectoGrupo6\Europe\CustomerStateYearlyIncome.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Customer_Filegroup; 