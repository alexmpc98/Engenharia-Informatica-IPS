--1.1--
create schema [Developer_Schema]

--1.2--
create table [table1](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

create table [Developer_Schema].[table2](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

--1.3--
CREATE LOGIN [UserS1] WITH PASSWORD = '123';
CREATE LOGIN [UserS2] WITH PASSWORD = '321';
CREATE LOGIN [UserS3] WITH PASSWORD = '132';

--1.4--
CREATE USER [User1] FOR LOGIN [UserS1]
WITH DEFAULT_SCHEMA = [Developer_Schema];

CREATE USER [User2] FOR LOGIN [UserS2]
WITH DEFAULT_SCHEMA = [Developer_Schema];

CREATE USER [User3] FOR LOGIN [UserS3]
WITH DEFAULT_SCHEMA = [Developer_Schema];

--2.5--
--Developer--
GRANT SELECT, INSERT, ALTER, DELETE, CREATE, DROP ON SCHEMA::[Developer_Schema] TO User1;
--Client--
GRANT SELECT, INSERT, ALTER ON SCHEMA::[Developer_Schema] TO User2;
--Reader--
GRANT SELECT ON SCHEMA::[Developer_Schema] TO User3;


--3.8--
EXECUTE AS LOGIN = 'UserS1';  
--a

INSERT INTO [table1] VALUES ('TesteUserS1T1');
INSERT INTO [Developer_Schema].[table2] VALUES ('TesteUserS1T2');

-- O utilizador não consegue inserir valores na table1, devido a pertencer ao schema dbo, enquanto que ao inserir na table2, já lhe é permitido
-- devido ás permissões fornecidas anteriormente.

--b--
create table [dbo].[table3](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

-- O UserS1, não consegue criar a tabela no schema dbo, devido a não ter permissões para alterar algo no schema dbo.

--c--
create table [Developer_Schema].[table3](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

-- Agora é permitido ao utilizador criar a tabela , devido a ter permissões para criar tabelas no schema definido

--3.9--
REVERT
EXECUTE AS LOGIN = 'UserS2';  
--a--
INSERT INTO [table1] VALUES ('TesteTable1UserS2');
INSERT INTO [Developer_Schema].[table2] VALUES ('TesteTable2UserS2');

-- De forma igual ao UserS1, o cliente também pode inserir dados na table2, devido às permissões fornecidas anteriormente (Client)
-- enquanto que quanto à inserção na table1, por este não conter permissões de inserção no schema [dbo], não lhe será permitida a inserção

--b--
create table [dbo].[table4](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

-- Não é permitido a este utilizador (UserS2) criar a tabela dentro do schema dbo, devido a este não possuir qualquer permissão sobre o schema [dbo].

--c--
create table [Developer_Schema].[table4](
[id] [int] IDENTITY(1,1) NOT NULL
,[name] varchar(50) NULL);

-- Não é permitido a este utilizador (UserS2) criar a tabela dentro do schema Developer_Schema, pois este só pode efetuar consulta/inserção/modificação de dados sobre o schema.

--3.10--
REVERT
EXECUTE AS LOGIN = 'UserS3';  
--a--
INSERT INTO [table1] VALUES ('TesteTable1UserS2');
INSERT INTO [Developer_Schema].[table2] VALUES ('TesteTable2UserS2');

-- Este utilizador não possui permissão para inserir em ambas as tabelas (dos dois schemas). Este apenas tem permissão para listar os dados do schema Developer_Schema

--b--
Select * from [table1];
Select * from [Developer_Schema].[table2];

-- O utilizador UserS3 não consegue listar os dados da table1, por não ter qualquer permissão para o schema [dbo]. Mas consegue listar os dados da table2,
-- devido a pertencer ao schema [Developer_Schema], onde este possui como única permissão fornecida anteriormente a listagem.