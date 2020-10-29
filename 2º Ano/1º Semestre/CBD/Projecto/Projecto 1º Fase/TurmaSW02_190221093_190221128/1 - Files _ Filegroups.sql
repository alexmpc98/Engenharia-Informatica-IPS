DROP DATABASE IF EXISTS [AdventureNewData]
CREATE DATABASE [AdventureNewData]  
ON PRIMARY ( 
NAME = AdventureNewData, 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AdventureWorksCBD.mdf', 
SIZE = 145MB,
MAXSIZE =  360MB ,
FILEGROWTH = 43MB) 
LOG ON(
Name = AdventureNewData_Log,
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AdventureNewData_Log.ldf',
SIZE = 145MB,
MAXSIZE = 360MB,
FILEGROWTH = 43MB);


-- FileGroup Products 
GO
ALTER DATABASE  [AdventureNewData]  
ADD FILEGROUP Products_Filegroup;

ALTER DATABASE  [AdventureNewData]  
ADD FILE 
(NAME =  ProductDescription , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductDescription.ndf', 
SIZE = 608KB, 
MAXSIZE = 851KB, 
FILEGROWTH = 49KB)
TO FILEGROUP Products_Filegroup;  


ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductName , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductName.ndf', 
SIZE = 810KB, 
MAXSIZE = 934KB, 
FILEGROWTH = 25KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductModel , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductModel.ndf', 
SIZE = 512KB, 
MAXSIZE = 555KB, 
FILEGROWTH = 9KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductColor , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductColor.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductSubCategory , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductSubCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 748KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductCategory , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductCategory.ndf', 
SIZE = 660KB, 
MAXSIZE = 700KB, 
FILEGROWTH = 8KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductPromotion , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductPromotion.ndf', 
SIZE = 525KB, 
MAXSIZE = 532KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductMeasureUnitCode , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductMeasureUnitCode.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductSize , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductSize.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductSize_SizeRange , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductSize_SizeRange.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ProductSizeRange , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductSizeRange.ndf', 
SIZE = 521KB, 
MAXSIZE = 527KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  ProductCategory_SubCategory , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ProductCategory_SubCategory.ndf', 
SIZE = 512KB, 
MAXSIZE = 517KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Products_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  Product , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\Product.ndf', 
SIZE = 588KB, 
MAXSIZE = 676KB, 
FILEGROWTH = 18KB)
TO FILEGROUP Products_Filegroup; 

-- FileGroup Sales
GO
ALTER DATABASE [AdventureNewData]  
ADD FILEGROUP Sales_Filegroup;

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  SalesCompany , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\SalesCompany.ndf', 
SIZE = 556KB, 
MAXSIZE = 570KB, 
FILEGROWTH =  3KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  SalesCurrency , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\SalesCurrency.ndf', 
SIZE = 608KB, 
MAXSIZE = 635KB, 
FILEGROWTH = 6KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  SalesOrderDetail , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\SalesOrderDetail.ndf', 
SIZE = 1MB, 
MAXSIZE = 3MB, 
FILEGROWTH =  227KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  SalesOrderHeader , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\SalesOrderHeader.ndf', 
SIZE = 1MB, 
MAXSIZE = 2MB, 
FILEGROWTH =  187KB)
TO FILEGROUP Sales_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  SalesTerritory , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\SalesTerritory.ndf', 
SIZE = 585KB, 
MAXSIZE = 619KB, 
FILEGROWTH =  7KB)
TO FILEGROUP Sales_Filegroup; 

-- FileGroup Customer 
GO
ALTER DATABASE [AdventureNewData]  
ADD FILEGROUP Customer_Filegroup;

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  Customer , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\Customer.ndf', 
SIZE = 11MB, 
MAXSIZE = 26MB, 
FILEGROWTH = 3MB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  CustomerCommuteDistance , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerCommuteDistance.ndf', 
SIZE = 521KB, 
MAXSIZE = 526KB, 
FILEGROWTH =  1KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  CustomerCountryRegion , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerCountryRegion.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  CustomerEducation , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerEducation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  CustomerOccupation , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerOccupation.ndf', 
SIZE = 531KB, 
MAXSIZE = 539KB, 
FILEGROWTH = 2KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  CustomerStateProvince , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerStateProvince.ndf', 
SIZE = 563KB, 
MAXSIZE = 579KB, 
FILEGROWTH = 3KB)
TO FILEGROUP Customer_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  CustomerStateYearlyIncome , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\CustomerStateYearlyIncome.ndf', 
SIZE = 512KB, 
MAXSIZE = 515KB, 
FILEGROWTH = 1KB)
TO FILEGROUP Customer_Filegroup; 

--  FileGroup Accounts
GO
ALTER DATABASE [AdventureNewData]  
ADD FILEGROUP Accounts_Filegroup;

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  AccountsSecurityQuestion , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AccountsSecurityQuestion.ndf', 
SIZE = 656KB, 
MAXSIZE = 695KB, 
FILEGROWTH =  8KB)
TO FILEGROUP Accounts_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  AccountsSentEmails , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AccountsSentEmails.ndf', 
SIZE = 738KB, 
MAXSIZE = 833KB, 
FILEGROWTH = 20KB)
TO FILEGROUP Accounts_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  AccountsUserAccount , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AccountsUserAccount.ndf', 
SIZE = 734KB, 
MAXSIZE = 793KB, 
FILEGROWTH = 12KB)
TO FILEGROUP Accounts_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  AccountsUserAccount_SecurityQuestion , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AccountsUserAccount_SecurityQuestion.ndf', 
SIZE = 614KB, 
MAXSIZE = 643KB, 
FILEGROWTH = 6KB)
TO FILEGROUP Accounts_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE(
NAME =  AccountsUserAuthentication , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\AccountsUserLogs.ndf', 
SIZE = 524KB, 
MAXSIZE = 584KB, 
FILEGROWTH = 12KB)
TO FILEGROUP Accounts_Filegroup; 

-- FileGroup Management
GO
ALTER DATABASE [AdventureNewData]  
ADD FILEGROUP Management_Filegroup;

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  ErrorLog , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\ErrorLog.ndf', 
SIZE = 520KB, 
MAXSIZE = 545KB, 
FILEGROWTH = 5KB)
TO FILEGROUP Management_Filegroup; 

ALTER DATABASE [AdventureNewData]  
ADD FILE( 
NAME =  Error , 
FILENAME = 'C:\ProjectoGrupo6\NorthAmerica\Error.ndf', 
SIZE = 706KB, 
MAXSIZE = 739KB, 
FILEGROWTH = 27KB)
TO FILEGROUP Management_Filegroup; 
