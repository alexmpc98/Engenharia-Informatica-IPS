-- O respectivo login para a execução encontra-se descrito no relatório
-- Create Logins
CREATE or ALTER PROCEDURE CreateRegistedUserLogin
AS 
BEGIN
DECLARE @UserName varchar(100);
DECLARE @UserCursor cursor;
DECLARE @String nvarchar(300);
DECLARE @Password varchar(100);
DECLARE @PW varbinary(255);

SET @Password = '123';
SELECT @PW = HASHBYTES('SHA1',@Password);
SET @UserCursor = CURSOR FOR (
		SELECT concat([FirstName],' ',[MiddleName],' ', [LastName], '-', [CustomerKey])
		FROM [AdventureNewData].[Customers].[Customer]);
OPEN @UserCursor
WHILE @@FETCH_STATUS = 0
	BEGIN
		FETCH NEXT FROM @UserCursor into @UserName
		SET @String = trim('CREATE LOGIN ' + quotename(@UserName) + ' WITH PASSWORD = ' + quotename(@PW, '''') + ', DEFAULT_DATABASE = AdventureNewData')
		exec(@String)
	END
CLOSE @UserCursor;
DEALLOCATE @UserCursor;
END

exec CreateRegistedUserLogin

-- Create User
GO
CREATE or ALTER PROCEDURE CreateRegistedUser
AS 
BEGIN
DECLARE @UserName varchar(100);
DECLARE @UserCursor cursor;
declare @String nvarchar(200);

SET @UserCursor = CURSOR FOR (
		SELECT concat([FirstName],' ',[MiddleName],' ', [LastName], '-', [CustomerKey])
		FROM [AdventureNewData].[Customers].[Customer]);
OPEN @UserCursor
WHILE @@FETCH_STATUS = 0
	BEGIN
		FETCH NEXT FROM @UserCursor into @UserName
		SET @String = trim('
		USE AdventureNewData;
		CREATE USER ' + quotename(@UserName) + ' FOR LOGIN ' + quotename(@UserName))
		exec(@String)
	END
CLOSE @UserCursor;
DEALLOCATE @UserCursor;
END

exec CreateRegistedUser

-- Add User to Role
GO
CREATE or ALTER PROCEDURE AddCustomerToRegistedRole
AS 
BEGIN
DECLARE @UserName varchar(100);
DECLARE @UserCursor cursor;
declare @String nvarchar(300);

SET @UserCursor = CURSOR FOR (
		SELECT concat([FirstName],' ',[MiddleName],' ', [LastName], '-', [CustomerKey])
		FROM [AdventureNewData].[Customers].[Customer]);
OPEN @UserCursor
WHILE @@FETCH_STATUS = 0
	BEGIN
		FETCH NEXT FROM @UserCursor into @UserName
		SET @String = trim('
		USE AdventureNewData
		ALTER ROLE RegistedRole ADD MEMBER ' + QUOTENAME(@UserName))
		exec(@String)
	END
CLOSE @UserCursor;
DEALLOCATE @UserCursor;
END
		
exec AddCustomerToRegistedRole;

