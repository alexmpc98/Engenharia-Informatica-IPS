USE [AdventureWorksLT2012]

-- Etapa 1 - A
GO
CREATE or alter FUNCTION SalesLT.fnTotalVendasCliente(@ClientID int)
RETURNS float
AS
BEGIN
	declare @return float
	select @return = Sum(TotalDue)
	FROM SalesLT.SalesOrderHeader
	WHERE CustomerID = @ClientID

	return @return
END
GO

SELECT top 1 SalesLT.fnTotalVendasCliente(29531) as Total
FROM SalesLT.SalesOrderHeader
GROUP BY CustomerID
GO 

-- Etapa 1 - B
CREATE or alter FUNCTION SalesLT.fnTotalVendas()
RETURNS float
AS
BEGIN
	declare @return float
	select @return = Sum(TotalDue)
	FROM SalesLT.SalesOrderHeader
	return @return
END
GO

SELECT top 1 SalesLT.fnTotalVendas() as 'Total de Vendas'
FROM SalesLT.SalesOrderHeader
GO 

-- Etapa 1 - C
CREATE or ALTER PROCEDURE SalesLT.spClientesCidade (@City varchar(50))
AS select concat(cust.Firstname, ' ', cust.MiddleName, ' ', cust.LastName) as Name, ad.City
	from SalesLT.Customer cust
	join SalesLT.CustomerAddress ca on cust.CustomerID = ca.CustomerID
	join SalesLT.Address ad on ca.AddressID = ad.AddressID
	where ad.City = @City;
GO

exec SalesLT.spClientesCidade 'Toronto';

-- Etapa 1 - D
GO
CREATE or alter PROCEDURE SalesLT.spListaCompras
AS
Declare @Details Cursor
Declare @CustomerID bigint
Declare @First nvarchar(100)
Declare @Last nvarchar(100) 
Declare @OrderNumber nvarchar(100)
Declare @OrderDate datetime
Declare @TotalDue float
Set @Details = Cursor for(
	select c.CustomerID, FirstName, LastName, PurchaseOrderNumber, OrderDate, TotalDue
	FROM SalesLT.Customer c, SalesLT.SalesOrderHeader s
	WHERE c.CustomerID = s.CustomerID)
Open @Details
While @@FETCH_STATUS = 0
	BEGIN
	Fetch Next from @Details Into @CustomerID, @First, @Last, @OrderNumber, @OrderDate, @TotalDue
	Print 'Cliente:' + Cast(@CustomerID as varchar) + ' ' + @First + ' ' + @Last +' - ' 
	+ @OrderNumber + ', ' + Cast(@OrderDate as varchar) + ', ' + cast(@TotalDue as varchar)
	END
Close @Details
Deallocate @Details

exec SalesLt.spListaCompras

GO
-- Etapa 2 - A
CREATE or alter FUNCTION dbo.fnCodificaPassword(@password varchar(100))
RETURNS varbinary(250)
AS
BEGIN
	declare @pw varchar(200)
	select @pw = hashbytes('SHA1',@password)
	return convert(varbinary(250),@pw)
END
GO

select dbo.fnCodificaPassword('Teste')
GO

-- Etapa 2 - B

CREATE TABLE SalesLT.CustomerPW (
CustomerID int not null,
CustomerPW varbinary(250),
NameStyle bit,
Title nvarchar(8) null,
FirstName nvarchar(50) not null,
MiddleName nvarchar(50) null,
LastName nvarchar(50) not null,
Suffix nvarchar(10) null,
CompanyName nvarchar(128) null,
SalesPerson nvarchar(256) null,
EmailAddress nvarchar(50) null,
Phone nvarchar(25) null,
ModifiedDate datetime not null
);

-- Etapa 2 - C
GO
CREATE or ALTER PROCEDURE SalesLT.spNovoCliente (
@CustomerID int,
@CustomerPW varchar(250),
@FirstName nvarchar(50),
@LastName nvarchar(50),
@EmailAdress nvarchar(50))
AS 
BEGIN 
declare @pw varchar(300)
select @pw = dbo.fnCodificaPassword(@CustomerPW)
INSERT INTO SalesLT.CustomerPW(CustomerID, CustomerPW,FirstName,LastName,EmailAddress,ModifiedDAte)
Values (@CustomerID,convert(varbinary(250),@pw),@FirstName,@LastName,@EmailAdress,GETDATE());
END
GO

-- Etapa 2 - D
GO
create or alter function dbo.fnAutenticar(@email varchar(60), @password varchar(128))
RETURNS int
BEGIN
	declare @CustomerIdentification int
	select @CustomerIdentification = CustomerID
	FROM SalesLT.CustomerPW 
	WHERE EmailAddress = @email
	AND CustomerPW = @password
	return isnull(@CustomerIdentification, 0)
END
go

Select dbo.fnAutenticar('alexcoelho@gmail.com',dbo.fnCodificaPassword('Teste'))

-- Etapa 3 - A
create table SalesLT.CustomerLog(
Log_data datetime not null,
Log_Operacao char(1) check(Log_Operacao IN ('U','D')) not null,
CustomerID int not null,
NameStyle bit,
Title nvarchar(8) null,
FirstName nvarchar(50) not null,
MiddleName nvarchar(50) null,
LastName nvarchar(50) not null,
Suffix nvarchar(10) null,
CompanyName nvarchar(128) null,
SalesPerson nvarchar(256) null,
EmailAddress nvarchar(50) null,
Phone nvarchar(25) null,
PasswordHash varchar(128) not null,
PasswordSalt varchar(10) not null,
rowguid uniqueidentifier not null,
ModifiedDate datetime not null
);

GO
create trigger CustomerLogs on 
SalesLT.Customer FOR DELETE , UPDATE
AS
BEGIN
  IF EXISTS (select 0 FROM DELETED)
	BEGIN
	  IF EXISTS (SELECT 0 FROM Inserted)
		INSERT INTO SalesLT.CustomerLog(Log_data,Log_Operacao,
		CustomerID,NameStyle,Title,FirstName,MiddleName,LastName,Suffix,CompanyName,
		SalesPerson,EmailAddress,Phone,PasswordHash,PasswordSalt,rowguid,ModifiedDate)
		 SELECT CURRENT_TIMESTAMP,'U',U.CustomerID,U.NameStyle,U.Title,U.FirstName,U.MiddleName,U.LastName,
		 U.Suffix,U.CompanyName,U.SalesPerson,U.EmailAddress,U.Phone,U.PasswordHash,
		 U.PasswordSalt,U.rowguid,U.ModifiedDate from DELETED U
	   ELSE
	   INSERT INTO SalesLT.CustomerLog(Log_data,Log_Operacao,
	    CustomerID,NameStyle,Title,FirstName,MiddleName,LastName,Suffix,CompanyName,
		SalesPerson,EmailAddress,Phone,PasswordHash,PasswordSalt,rowguid,ModifiedDate)
		 SELECT CURRENT_TIMESTAMP,'D',D.CustomerID,D.NameStyle,D.Title,D.FirstName,D.MiddleName,D.LastName,
		 D.Suffix,D.CompanyName,D.SalesPerson,D.EmailAddress,D.Phone,D.PasswordHash,
		 D.PasswordSalt,D.rowguid,D.ModifiedDate from DELETED D
END
END
GO

-- Etapa 3 - B
GO
create trigger TrgNewPW on 
SalesLT.Customer AFTER UPDATE
AS
BEGIN
  IF UPDATE(PasswordHash)
	BEGIN
		INSERT INTO SalesLT.CustomerPW(CustomerID,CustomerPW,NameStyle,Title,FirstName,MiddleName,LastName,
		Suffix,CompanyName,SalesPerson,EmailAddress,Phone,ModifiedDate)
		 SELECT U.CustomerID,hashbytes('SHA1',U.PasswordHash),U.NameStyle, U.Title,U.FirstName,
		 U.MiddleName, U.LastName, U.Suffix, U.CompanyName, U.SalesPerson, U.EmailAddress, U.Phone, GETDATE()
		 from DELETED U
END
END
GO


CREATE TRIGGER TrgExists
ON SalesLT.CustomerPW
FOR INSERT
AS
BEGIN
	DECLARE @Email varchar(60)
	SELECT @Email = EmailAddress from inserted

	IF((select count(*) FROM SalesLT.CustomerPW WHERE EmailAddress = @Email)>1)
	BEGIN
		ROLLBACK
		RAISERROR('Email Duplicado!', 16, 1)
	END
END
GO

-- Etapa 4 - A
CREATE or alter PROCEDURE estadoInicial
AS
Declare @InfoAReporUpdate Cursor
Declare @InfoAReporDelete Cursor
Declare @CustomerID bigint
Declare @NameStyle bit
Declare @Title nvarchar(8)
Declare @FirstName nvarchar(50)
Declare @MiddleName nvarchar(50)
Declare @LastName nvarchar(50)
Declare @Suffix nvarchar(10) 
Declare @CompanyName nvarchar(128) 
Declare @SalesPerson nvarchar(256) 
Declare @EmailAddress nvarchar(50)
Declare @Phone nvarchar(25)
Declare @PasswordHash varchar(128)
Declare @PasswordSalt varchar(10) 
Declare @rowguid uniqueidentifier 
Declare @ModifiedDate datetime
Declare @log_data datetime
Declare @log_operacao char(1)
Set @InfoAReporUpdate = Cursor for(
	select Log_data,Log_Operacao,CustomerId,NameStyle,Title,FirstName,MiddleName,LastName,Suffix,CompanyName,
	SalesPerson, EmailAddress, Phone,PasswordHash, PasswordSalt, rowguid, ModifiedDate
    from SalesLT.CustomerLog where Log_Operacao = 'U')
Open @InfoAReporUpdate
While @@FETCH_STATUS = 0
	BEGIN
	Fetch Next from @InfoAReporUpdate Into @log_data,@log_operacao,@CustomerID, @NameStyle,
	@Title, @FirstName, @MiddleName, @LastName,
	@Suffix,@CompanyName,@SalesPerson,@EmailAddress,@Phone,@PasswordHash,@PasswordSalt,@rowguid,@ModifiedDate
	Update SalesLT.Customer set NameStyle = @NameStyle, Title = @Title, FirstName = @FirstName,
	MiddleName = @MiddleName, LastName = @LastName, Suffix = @Suffix,
	CompanyName = @CompanyName, SalesPerson = @SalesPerson,
	EmailAddress = @EmailAddress, Phone = @Phone, PasswordHash = @PasswordHash, PasswordSalt = @PasswordSalt,
	rowguid = @rowguid, ModifiedDate = @ModifiedDate where CustomerID = @CustomerID
	DELETE FROM SalesLT.CustomerLog where Log_data = @log_data and Log_Operacao = @log_operacao
	END
Close @InfoAReporUpdate
Deallocate @InfoAReporUpdate
Set @InfoAReporDelete = Cursor for(
	select Log_data,Log_Operacao,CustomerId,NameStyle,Title,FirstName,MiddleName,LastName,Suffix,CompanyName,
	SalesPerson, EmailAddress, Phone,PasswordHash, PasswordSalt, rowguid, ModifiedDate
	from SalesLT.CustomerLog where Log_Operacao = 'D')
Open @InfoAReporDelete
While @@FETCH_STATUS = 0
	BEGIN
	Fetch Next from @InfoAReporDelete Into @log_data,@log_operacao,@CustomerID, @NameStyle, @Title, @FirstName, @MiddleName, @LastName,
	@Suffix,@CompanyName,@SalesPerson,@EmailAddress,@Phone,@PasswordHash,@PasswordSalt,@rowguid,@ModifiedDate
	SET IDENTITY_INSERT SalesLT.Customer ON
	Insert into SalesLT.Customer(CustomerID,NameStyle,Title,FirstName,MiddleName,LastName,Suffix,
	CompanyName,SalesPerson,EmailAddress,Phone,PasswordHash,PasswordSalt,rowguid,ModifiedDate)
	values(@CustomerID,@NameStyle,@Title,@FirstName,@MiddleName, 
	@LastName,@Suffix,@CompanyName,@SalesPerson,@EmailAddress,@Phone,@PasswordHash,@PasswordSalt,
	@rowguid,@ModifiedDate) 
	DELETE FROM SalesLT.CustomerLog where Log_data = @log_data and Log_Operacao = @log_operacao
	END
SET IDENTITY_INSERT SalesLT.Customer OFF
Close @InfoAReporDelete
Deallocate @InfoAReporDelete

exec dbo.estadoInicial;

