-- Etapa 1
use AdventureWorksLT2012;

-- Ponto 1 
select column_name, is_nullable from AdventureWorksLT2012.INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME = 'Address';

-- Ponto 2
select sc.name,is_identity as 'Identity' 
from sys.columns sc
join sys.tables st 
on sc.object_id = st.object_id 
where st.name = 'Address';

-- Ponto 3
select tc.table_name, kc.column_name
from AdventureWorksLT2012.INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
join INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS KC
ON TC.CONSTRAINT_TYPE = 'PRIMARY KEY' and TC.CONSTRAINT_NAME = KC.CONSTRAINT_NAME
where tc.TABLE_NAME = 'Product';

-- Ponto 4
SELECT  
    tab1.name AS NomeDaTabela,
    col1.name AS NomeDaColuna,
    tab2.name AS TabelaReferenciada,
    col2.name AS ColunaReferenciada
FROM sys.foreign_key_columns fkc
JOIN sys.tables tab1
    ON tab1.object_id = fkc.parent_object_id
JOIN sys.columns col1
    ON col1.column_id = parent_column_id AND col1.object_id = tab1.object_id
JOIN sys.tables tab2
    ON tab2.object_id = fkc.referenced_object_id
JOIN sys.columns col2
    ON col2.column_id = referenced_column_id AND col2.object_id = tab2.object_id

-- Etapa 2
GO
create procedure etapa2 (@tabela varchar(30))
as
exec('create procedure sp_Ins' + @tabela + ' as 
SET IDENTITY_INSERT AdventureWorksLT2012.SalesLT.'+ @tabela + 'ON
')
GO

exec etapa2 'Product';

drop procedure etapa2;





