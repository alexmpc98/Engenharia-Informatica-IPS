-- I
-- 1
CREATE DATABASE  
ON PRIMARY ( 
NAME = Lista_Cliente, 
FILENAME = 'C:\Lab06\Lista_Cliente.mdf', 
SIZE = 50) 
LOG ON(
Name = Lista_Cliente_Log,
FILENAME = 'C:\Lab06\Lista_Cliente_Log.ldf',
SIZE = 20);

-- 2
USE Lista_Cliente;
CREATE TABLE [Cliente](
[ClienteID] INTEGER NOT NULL IDENTITY(1,1),
[PrimeiroNome] VARCHAR(255) NOT NULL,
[UltimoNome] VARCHAR(255) NOT NULL,
[Email] VARCHAR(255) NOT NULL,
[DataNascimento] DATETIME NOT NULL,
[Telefone] VARCHAR(100) NOT NULL,
[Empresa] VARCHAR(255) NOT NULL,
PRIMARY KEY ([ClienteID]));

-- 3
GO
CREATE PROCEDURE InserirVarios (@Inicio int, @Fim int)
AS
BEGIN
		DECLARE @Contador INT = @Inicio
		WHILE (@Contador <= @Fim)
			BEGIN
			INSERT INTO
			[Cliente]
			(
			[PrimeiroNome],[UltimoNome],[Email],
			[DataNascimento],[Telefone],[Empresa]
			)
			VALUES
			(
			'Primeiro' + CONVERT(varchar, @Contador),
			'Ultimo' + CONVERT(varchar, @Contador),
			'email' + CONVERT(varchar, @Contador) + '@dominio.pt','19830611','919191919',
			'Empresa' ++ CONVERT(varchar, @Contador)
			)
			SET @Contador = @Contador + 1
		END
END
GO

-- 4
EXEC InserirVarios 1,100

-- III -
-- 14
EXEC InserirVarios 1,20

-- 18
select * from [Lista_ClienteWLogs].[dbo].Cliente
-- Recuperou os registos inseridos até ser efetuado o backup de transational log

-- 19
select * from [Lista_ClienteLogWTail].[dbo].Cliente
-- Recuperou os registos que foram inseridos logo após o backup de transational log

-- IV
-- 2
create database Faturacao;
create database Telemarketing;
select * from Clie
-- 3
use Faturacao;
CREATE TABLE [Cliente](
[ClienteID] INTEGER NOT NULL IDENTITY(1,1),
[PrimeiroNome] VARCHAR(255) NOT NULL,
[UltimoNome] VARCHAR(255) NOT NULL,
[Email] VARCHAR(255) NOT NULL,
[DataNascimento] DATETIME NOT NULL,
[Telefone] VARCHAR(100) NOT NULL,
[Empresa] VARCHAR(255) NOT NULL,
PRIMARY KEY ([ClienteID]));

use Faturacao;
insert into Cliente
VALUES
			(
			'PrimeiroTeste',
			'UltimoTeste',
			'email' + '@dominio.pt','19830611','919191919',
			'Empresa' 
			)

			select * from Faturacao.dbo.Cliente