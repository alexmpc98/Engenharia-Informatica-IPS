-- Etapa 2 - A/B
select concat(FirstName, ' ' , MiddleName, ' ' , LastName) as 'Nome Completo', EmailAddress as 'Email' 
from AdventureWorksLT2012.SalesLt.Customer 
order by LastName desc;

-- Etapa 2 - C
Select sc.CustomerID,  sc.FirstName from AdventureWorksLT2012.SalesLT.SalesOrderHeader sh
full outer join AdventureWorksLT2012.SalesLT.Customer sc on sh.CustomerID = sc.CustomerID where SalesOrderID is null;

-- Etapa 3 - A
Select CustomerID,sum(TotalDue) as Total from AdventureWorksLT2012.SalesLT.SalesOrderHeader
group by CustomerID
order by CustomerID asc;

-- Etapa 3 - B
Select TOP 1 CustomerID, max(TotalDue) as Total
from AdventureWorksLT2012.SalesLT.SalesOrderHeader 
group by CustomerID
order by max(totalDue) desc;


-- Etapa 3 - C
select Percentagem.Product as Name,round(Percentagem/Total,2,2) as PV from
(Select cast(sum(UnitPrice - UnitPriceDiscount) as float) as Total 
from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.SalesOrderDetail sd 
on sp.ProductID = sd.ProductID) as Total
join
(Select cast(sum(UnitPrice - UnitPriceDiscount) as float) * 100 as Percentagem,sp.Name as Product
from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.SalesOrderDetail sd 
on sp.ProductID = sd.ProductID 
group by sp.Name, sp.ProductID) as Percentagem on 1=1 order by Percentagem/Total desc;



-- Etapa 3 - D
Select Name,Description from AdventureWorksLT2012.SalesLT.vProductAndDescription;

-- Etapa 3 - E
Select sp.Name,gc.ParentProductCategoryName, gc.ProductCategoryName from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.vGetAllCategories gc on sp.ProductCategoryID = gc.ProductCategoryID;

-- Etapa 3 - F
Select sp.Name,sp.ListPrice from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.vGetAllCategories gc 
on sp.ProductCategoryID = gc.ProductCategoryID and gc.ParentProductCategoryName = 'Bikes';

-- Etapa 3 - G
Select count(*) as Number_Products, gc.ProductCategoryName from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.vGetAllCategories gc on sp.ProductCategoryID = gc.ProductCategoryID
group by gc.ProductCategoryID,gc.ProductCategoryName order by count(*) asc;

-- Etapa 3 - H
Select count(*) as Number_Products, gc.ProductCategoryName from AdventureWorksLT2012.SalesLT.Product sp
join AdventureWorksLT2012.SalesLT.vGetAllCategories gc on sp.ProductCategoryID = gc.ProductCategoryID
group by gc.ProductCategoryID,gc.ProductCategoryName having count(*) > 20 order by count(*) asc;


-- Etapa 4 - A
CREATE TABLE Estatisticas (
   Area varchar(40),
   NomeTabela varchar(40),
   NumRegistos int,
);

 
-- Etapa 4 - B e C
insert into Estatisticas
Select 'Customer' as 'Area', 'SalesLT.Customer' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.Customer group by year(ModifiedDate)) as Reg;

-- Etapa 4 - D
-- Produtos
insert into Estatisticas
Select 'Products' as 'Area', 'SalesLT.Products' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.Product group by year(ModifiedDate)) as Reg;

-- Endereços (Adress)
insert into Estatisticas
Select 'Address' as 'Area', 'SalesLT.Address' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.Address group by year(ModifiedDate)) as Reg;

-- ProductModel
insert into Estatisticas
Select 'ProductModel' as 'Area', 'SalesLT.ProductModel' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.ProductModel group by year(ModifiedDate)) as Reg;

-- Customer Address
insert into Estatisticas
Select 'CustomerAddress' as 'Area', 'SalesLT.CustomerAddress' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.CustomerAddress group by year(ModifiedDate)) as Reg;

-- ProductCategory
insert into Estatisticas
Select 'ProductCategory' as 'Area', 'SalesLT.ProductCategory' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.ProductCategory group by year(ModifiedDate)) as Reg;

-- ProductDescription
insert into Estatisticas
Select 'ProductDescription' as 'Area', 'SalesLT.ProductDescription' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.ProductCategory group by year(ModifiedDate)) as Reg;

-- ProductModelProductDescription
insert into Estatisticas
Select 'ProductModelProductDescription' as 'Area', 
'SalesLT.ProductModelProductDescription' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.ProductModelProductDescription group by year(ModifiedDate)) as Reg;

-- SalesOrderDetail
insert into Estatisticas
Select 'SalesOrderDetail' as 'Area', 'SalesLT.SalesOrderDetail' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.SalesOrderDetail group by year(ModifiedDate)) as Reg;

--SalesOrderHeader
insert into Estatisticas
Select 'SalesOrderHeader' as 'Area', 'SalesLT.SalesOrderHeader' as 'NomeTabela', avg(Registos) as 'NumRegistos' from (
Select count(*) as Registos, year(ModifiedDate) as Ano
from AdventureWorksLT2012.SalesLT.SalesOrderHeader group by year(ModifiedDate)) as Reg;







