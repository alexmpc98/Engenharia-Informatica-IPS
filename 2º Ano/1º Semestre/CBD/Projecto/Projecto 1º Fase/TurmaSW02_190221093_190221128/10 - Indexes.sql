USE AdventureNewData;
-------------
-- Query 1 --
-------------
/*Volume de vendas total por produto*/
Select SUM([Sales].[SalesOrderDetail].[OrderQuantity]) as 'Unidades', PName.[EnglishProductName] as 'Nome'
FROM [Sales].[SalesOrderDetail]
JOIN [Products].[Product] as PRO ON [Sales].[SalesOrderDetail].[ProductKey] = PRO.[ProductKey]
JOIN [Products].[ProductName] as PName ON PRO.ProductNameKey = PName.ProductNameKey
GROUP BY PName.[EnglishProductName]
ORDER BY PName.[EnglishProductName]

CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderDetail_11_114099447__K4_2] ON [Sales].[SalesOrderDetail]
(
	[ProductKey] ASC
)
INCLUDE([OrderQuantity]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

-------------
-- Query 2 --
-------------
/*Qual percentagem de vendas por produto efetuada com promoção*/
select round(TotalSalesWithPromotion/TotalSales * 100,2,2) as Percentagem, ProductName from
(select cast(count(*) as float) as TotalSales from Sales.SalesOrderDetail) as TotalSales
join
(select PN.EnglishProductName as ProductName,cast(count(*) as float) as TotalSalesWithPromotion from Sales.SalesOrderDetail SD
join Products.ProductPromotion PP on SD.ProductKey = PP.ProductKey and PP.Active = 1 
join Products.Product PR on PP.ProductKey = PR.ProductKey
join Products.ProductName PN on PR.ProductNameKey = PN.ProductNameKey
group by PN.EnglishProductName) as TotalSalesWithPromotion on 1=1

CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderDetail_11_114099447__K5] ON [Sales].[SalesOrderDetail]
(
	[SalesOrderLineNumber] ASC
)WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

CREATE STATISTICS [_dta_stat_114099447_5_4] ON [Sales].[SalesOrderDetail]([SalesOrderLineNumber], [ProductKey])

-------------
-- Query 3 --
-------------
/*Calcular o valor total de vendas anual por Região Geográfica*/
select ST.SalesTerritoryRegion as Region, year(OrderDate) as Year,sum(SD.OrderQuantity * SD.UnitPrice) as TotalAmmount from Sales.SalesOrderHeader SH
join Sales.SalesTerritory ST on SH.SalesTerritoryKey = ST.SalesTerritoryKey
join Sales.SalesOrderDetail SD on SH.SalesOrderHeaderKey = SD.SalesOrderHeaderKey
group by ST.SalesTerritoryRegion, year(OrderDate) order by year(OrderDate);

CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderDetail_11_114099447__K6_2_3] ON [Sales].[SalesOrderDetail]
(
	[SalesOrderHeaderKey] ASC
)
INCLUDE([OrderQuantity],[UnitPrice]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderHeader_11_2133582639__K1_K7_3] ON [Sales].[SalesOrderHeader]
(
	[SalesOrderHeaderKey] ASC,
	[SalesTerritoryKey] ASC
)
INCLUDE([OrderDate]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

CREATE STATISTICS [_dta_stat_2133582639_7_1] ON [Sales].[SalesOrderHeader]([SalesTerritoryKey], [SalesOrderHeaderKey])

-------------
-- Query 4 --
-------------
/* Obter para cada ano a Região Geográfica com o maior valor total de vendas*/
SELECT top 1 with ties sum(SOH.SalesAmount) as 'Profit', ST.SalesTerritoryRegion as 'Region',  YEAR(SOH.OrderDate) AS 'Year'
From Sales.SalesOrderHeader as SOH 
JOIN Sales.SalesTerritory as ST on SOH.SalesTerritoryKey = ST.SalesTerritoryKey
GROUP BY ST.SalesTerritoryRegion,YEAR(SOH.OrderDate)
ORDER BY row_number() over (
PARTITION BY YEAR(SOH.OrderDate)
ORDER BY sum(SOH.SalesAmount) desc);


CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderHeader_11_2133582639__K7_3_6] ON [Sales].[SalesOrderHeader]
(
	[SalesTerritoryKey] ASC
)
INCLUDE([OrderDate],[SalesAmount]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

-------------
-- Query 5 --
-------------
/* Prazo médio entre data de encomenda e envio por Região Geográfica */
select round(AVG(cast(ShippingDays as float)),0,0) as AverageDays,Region from(
select DATEDIFF(day,OrderDate,ShipDate) as ShippingDays, ST.SalesTerritoryRegion as Region from Sales.SalesOrderHeader SH
join Sales.SalesTerritory ST on SH.SalesTerritoryKey = ST.SalesTerritoryKey) tt group by Region;

CREATE NONCLUSTERED INDEX [_dta_index_SalesOrderHeader_11_2133582639__K7_3_5] ON [Sales].[SalesOrderHeader]
(
	[SalesTerritoryKey] ASC
)
INCLUDE([OrderDate],[ShipDate]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

