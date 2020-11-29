use AdventureNewData

----------------------------------------------------------------
------------------------ADVENTURE NEW DATA----------------------
----------------------------------------------------------------
-- 1 - Monetary Total per Year 
GO
CREATE OR ALTER VIEW Sales.SalesPerYear_VW
AS
SELECT sum(sh.SalesAmount) as 'TOTAL', YEAR(SH.OrderDate) AS 'YEAR'
FROM [AdventureNewData].[Sales].[SalesOrderHeader] SH 
JOIN [AdventureNewData].[Sales].[SalesOrderDetail] SD on SH.SalesOrderHeaderKey = SD.SalesOrderHeaderKey 
GROUP BY YEAR(SH.OrderDate)
ORDER BY YEAR(SH.OrderDate) OFFSET 0 ROWS

GO
Select * from Sales.SalesPerYear_VW;

-- 2 - Monetary Total per Year and Sales Territory Country
GO
CREATE or ALTER VIEW Sales.SalesPerYearAndCountry_VW
AS
SELECT sum(sh.SalesAmount) as 'TOTAL', ST.SalesTerritoryCountry AS 'Country',YEAR(SH.OrderDate) AS 'ANO'
FROM 
[AdventureNewData].[Sales].SalesOrderDetail SD 
JOIN [AdventureNewData].[Sales].[SalesOrderHeader] SH ON SD.SalesOrderHeaderKey = SH.SalesOrderHeaderKey 
JOIN [AdventureNewData].[Sales].[SalesTerritory] ST ON SH.SalesTerritoryKey = ST.SalesTerritoryKey
GROUP BY ST.SalesTerritoryCountry,YEAR(SH.OrderDate)

GO
select * from Sales.SalesPerYearAndCountry_VW;

-- 3 - Monetary Total per year and Product SubCategory

GO
CREATE OR ALTER VIEW Sales.SalesPerYearAndSubcategory_VW
AS
SELECT sum(SH.SalesAmount) AS 'TOTAL', 
PSC.EnglishProductSubCategoryName AS 'SUB-CATEGORY NAME', YEAR(SH.OrderDate) AS 'YEAR'
FROM [AdventureNewData].[Sales].[SalesOrderDetail] SD
JOIN [AdventureNewData].[Sales].[SalesOrderHeader] SH ON SD.SalesOrderHeaderKey = SH.SalesOrderHeaderKey
JOIN [AdventureNewData].[Products].[Product] PR ON SD.ProductKey = PR.ProductKey
JOIN [AdventureNewData].[Products].[Category_SubCategory] CSC ON CSC.CategorySubCategoryKey = PR.CategorySubCategoryKey
JOIN [AdventureNewData].[Products].[ProductSubCategory] PSC ON CSC.ProductSubCategoryKey = PSC.ProductSubCategoryKey
GROUP BY PSC.EnglishProductSubCategoryName, YEAR(SH.OrderDate)

GO
Select * from Sales.SalesPerYearAndSubcategory_VW;

-- 4 - Monetary Total per year and Product Category
GO
CREATE OR ALTER VIEW Sales.SalesPerYearAndCategory_VW
AS
SELECT sum(SH.SalesAmount) AS 'TOTAL', 
PC.EnglishProductCategoryName AS 'CATEGORY NAME', YEAR(SH.OrderDate) AS 'YEAR'
FROM [AdventureNewData].[Sales].[SalesOrderDetail] SD
JOIN [AdventureNewData].[Sales].[SalesOrderHeader] SH ON SD.SalesOrderHeaderKey = SH.SalesOrderHeaderKey
JOIN [AdventureNewData].[Products].[Product] PR ON SD.ProductKey = PR.ProductKey
JOIN [AdventureNewData].[Products].[Category_SubCategory] CSC ON CSC.CategorySubCategoryKey = PR.CategorySubCategoryKey
JOIN [AdventureNewData].[Products].[ProductCategory] PC ON CSC.ProductCategoryKey = PC.ProductCategoryKey
GROUP BY PC.EnglishProductCategoryName, YEAR(SH.OrderDate)

GO
SELECT * FROM Sales.SalesPerYearAndCategory_VW

-- 5 - Number of clients per year and Sales Territory Country

GO
CREATE OR ALTER VIEW Sales.NumberOfClientsPerYearAndSalesTerritoryCountry_VW
AS
SELECT COUNT(*) AS 'NUMBER OF CUSTOMERS', 
ST.SalesTerritoryCountry AS 'COUNTRY', YEAR(SH.OrderDate) AS 'YEAR'
From [AdventureNewData].[Sales].[SalesOrderDetail] SD 
JOIN [AdventureNewData].[Sales].[SalesOrderHeader] SH on SD.SalesOrderHeaderKey = SH.SalesOrderHeaderKey
JOIN [AdventureNewData].[Customers].[Customer] CS ON  SH.CustomerKey = CS.CustomerKey
JOIN [AdventureNewData].[Sales].[SalesTerritory] ST on SH.SalesTerritoryKey = ST.SalesTerritoryKey
GROUP BY ST.SalesTerritoryCountry, YEAR(SH.OrderDate)
ORDER BY ST.SalesTerritoryCountry, YEAR(SH.OrderDate) OFFSET 0 ROWS;

GO
SELECT * from Sales.NumberOfClientsPerYearAndSalesTerritoryCountry_VW;



----------------------------------------------------------------
------------------------ADVENTURE OLD DATA----------------------
----------------------------------------------------------------

-- 1 - Monetary Total per year and Product Category
GO
CREATE OR ALTER VIEW Sales.SalesPerYearOld_VW
AS
SELECT sum(CONVERT(money, SalesAmount)) as 'TOTAL', YEAR(CONVERT(DATE, OrderDate, 103)) AS 'YEAR'
FROM [AdventureOldData].[dbo].[Sales]  
GROUP BY YEAR(CONVERT(DATE, OrderDate, 103))
ORDER BY YEAR(CONVERT(DATE, OrderDate, 103)) OFFSET 0 ROWS

GO
Select * from Sales.SalesPerYearOld_VW;



-- 2 - Monetary Total per Year and Sales Territory Country
GO
CREATE or ALTER VIEW Sales.SalesPerYearAndCountryOld_VW
AS
SELECT sum(CONVERT(money, SalesAmount)) as 'TOTAL', SalesTerritoryCountry AS 'Country',YEAR(CONVERT(DATE, OrderDate, 103)) AS 'ANO'
FROM 
[AdventureOldData].[dbo].Sales 
GROUP BY SalesTerritoryCountry,YEAR(CONVERT(DATE, OrderDate, 103))

GO
select * from Sales.SalesPerYearAndCountryOld_VW;


-- 3 - Monetary Total per year and Product SubCategory

GO
CREATE OR ALTER VIEW Sales.SalesPerYearAndSubcategoryOld_VW
AS
SELECT sum(CONVERT(money, so.SalesAmount)) AS 'TOTAL', 
EnglishProductSubCategoryName AS 'SUB-CATEGORY NAME', YEAR(CONVERT(DATE, so.OrderDate, 103))AS 'YEAR'
FROM [AdventureOldData].[dbo].[Sales] so
JOIN [AdventureOldData].[dbo].[Products] PR ON so.ProductKey = PR.ProductKey
JOIN [AdventureOldData].[dbo].[ProductSubCategory] PSC ON pr.ProductSubCategoryKey = PSC.ProductSubCategoryKey
GROUP BY PSC.EnglishProductSubCategoryName, YEAR(CONVERT(DATE, so.OrderDate, 103))

GO
Select * from Sales.SalesPerYearAndSubcategoryOld_VW;


-- 4 - Monetary Total per year and Product Category
GO
CREATE OR ALTER VIEW Sales.SalesPerYearAndCategoryOld_VW
AS
SELECT sum(CONVERT(money, sh.SalesAmount)) AS 'TOTAL', 
PR.EnglishProductCategoryName AS 'CATEGORY NAME', YEAR(CONVERT(DATE, sh.OrderDate, 103)) AS 'YEAR'
FROM [AdventureOldData].[dbo].[Sales] Sh
JOIN [AdventureOldData].[dbo].[Products] PR ON sh.ProductKey = PR.ProductKey
GROUP BY PR.EnglishProductCategoryName, YEAR(CONVERT(DATE, sh.OrderDate, 103))

GO
SELECT * FROM Sales.SalesPerYearAndCategoryOld_VW;



-- 5 - Number of clients per year and Sales Territory Country

GO
CREATE OR ALTER VIEW Sales.NumberOfClientsPerYearAndSalesTerritoryCountryOld_VW
AS
SELECT COUNT(*) AS 'NUMBER OF CUSTOMERS', 
Sh.SalesTerritoryCountry AS 'COUNTRY', YEAR(CONVERT(DATE, sh.OrderDate, 103)) AS 'YEAR'
From [AdventureOldData].[dbo].[Sales] Sh 
JOIN [AdventureOldData].[dbo].[Customer] CS ON  SH.CustomerKey = CS.CustomerKey
GROUP BY Sh.SalesTerritoryCountry, YEAR(CONVERT(DATE, sh.OrderDate, 103))
ORDER BY Sh.SalesTerritoryCountry, YEAR(CONVERT(DATE, sh.OrderDate, 103)) OFFSET 0 ROWS;

GO
SELECT * from Sales.NumberOfClientsPerYearAndSalesTerritoryCountryOld_VW;