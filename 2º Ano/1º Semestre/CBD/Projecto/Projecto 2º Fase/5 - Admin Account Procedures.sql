use AdventureNewData;
-- Password Hash --
GO
CREATE or alter FUNCTION Accounts.hashPassword(@password varchar(100))
RETURNS varbinary(250)
AS
BEGIN
	declare @pw varchar(200)
	select @pw = hashbytes('SHA1',@password)
	return convert(varbinary(250),@pw)
END

-- Insert Error Data and SysUser -- 
GO
CREATE OR ALTER PROCEDURE Managements.InsertErrors 
AS
	BEGIN
		INSERT INTO Managements.Error 
		(
				ErrorName
		)	
		VALUES (
			'You have to insert 3 questions!',
			'You have to insert 3 answers!',
			'You have to insert the new password!',
			'You have to insert your email!',
			'This Email already exists!',
			'Incorrect Credentials',
			'You have to fill the email field',
			'You have to fill the password field',
			'You are already logged',
			'You are already logged out'
		);
	END

insert into Accounts.UserAccount values ('sysuser@adventureworks.com', Accounts.hashPassword('EMPTY SPACES'));


-- Insert Security Questions -- 
GO
CREATE OR ALTER PROCEDURE Accounts.InsertSecurityQuestions 
AS
	BEGIN
		INSERT INTO Accounts.SecurityQuestion 
		(
				QuestionText
		)	
		VALUES (
			'O que significa CBD?',
			'Projecto para entregar até?',
			'Vamos reprovar?',
			'O projecto tem quantas fases?',
			'CBD é a minha cadeira favorita?',
			'Vamos reprovar?'
		)
	END


-- New User -- 
GO
CREATE or ALTER PROCEDURE Accounts.spNewUserAccount (
	@Email varchar(250)
)
AS 
	BEGIN 
	DECLARE @Error varchar(100)
	DECLARE @ErrorKey int
	DECLARE @ErrorCursor Cursor
	DECLARE @UserCursor Cursor
	DECLARE @UserKey int
	DECLARE @SysUserLogOutDate datetime
	SET @ErrorCursor = CURSOR FOR (
		SELECT [ErrorName]  
		FROM [AdventureNewData].[Managements].[Error] where [ErrorKey] = @ErrorKey);
		SET @UserCursor = CURSOR FOR (
		SELECT cast([UserAccountKey] as int)
		FROM [AdventureNewData].[Accounts].[UserAccount]
		WHERE trim(cast([Email] as varchar)) = trim(cast(@Email as varchar)));
	SET @ErrorKey = 0;
	SET @SysUserLogOutDate = (
	SELECT [LogoutDate]
		FROM [AdventureNewData].[Accounts].[UserAuthentication]
		WHERE [UserAccountKey] = 1);
	IF (@SysUserLogOutDate != cast('1900-01-01 00:00:00.000' as datetime))
		BEGIN
			SET @ErrorKey = 2
		END
	ELSE IF LEN(@Email) = 0
		BEGIN
			set @ErrorKey = 4
			OPEN @ErrorCursor;
			WHILE @@FETCH_STATUS = 0
				BEGIN 
					FETCH NEXT FROM @ErrorCursor into @Error;
				END
			CLOSE @ErrorCursor;
			PRINT concat('Error ', @ErrorKey)
			INSERT INTO Managements.ErrorLog values(@ErrorKey,1,GETDATE())
			RAISERROR(@Error, 16, 1)
			ROLLBACK
		END
	ELSE IF((select count(*) FROM Accounts.UserAccount 
	WHERE trim(cast(Email as varchar)) = trim(cast(@Email as varchar))) > 0)
		BEGIN
			set @ErrorKey = 5
			OPEN @ErrorCursor;
			WHILE @@FETCH_STATUS = 0
				BEGIN 
					FETCH NEXT FROM @ErrorCursor into @Error;
				END
			CLOSE @ErrorCursor;
			PRINT concat('Error ', @ErrorKey)
			INSERT INTO Managements.ErrorLog values(@ErrorKey,1,GETDATE())
			RAISERROR(@Error,16, 1)
			ROLLBACK
		END
	ELSE
		BEGIN
			INSERT INTO Accounts.UserAccount 
			(
				Email,
				PasswordHash
			)
			VALUES (
				@Email, 
				Accounts.hashPassword('RESET_PWADVGRP')
			)
			WAITFOR DELAY '00:00:01'
			PRINT concat('Welcome to Adventure Works ', @email, ' , make sure you get your personal password!')
			DEALLOCATE @UserCursor;
			DEALLOCATE @ErrorCursor;
		END
	END

-- Procedure de Associação da Pergunta ao Customer --
GO
CREATE OR ALTER PROCEDURE Accounts.spAddQuestionUser (
	@SecurityQuestionKey int,
	@Answer varchar(100),
	@UserKey int
)
AS
	BEGIN
		INSERT INTO UserAccount_SecurityQuestion
		(
			SecurityQuestionKey,
			UserAccountKey,
			AnswerText
		)
		VALUES (
			@SecurityQuestionKey,
			@UserKey,
			@UserKey
		)
	END
GO

-- USER 1--
EXEC Accounts.spNewUserAccount 'alexandre.coelho@adventureworks.com'; 
EXEC Accounts.spAddQuestionUser 'O que significa CBD?','Complementos de Base de Dados',1;
EXEC Accounts.spAddQuestionUser 'Projecto até?','Sexta-Feira',1;
EXEC Accounts.spAddQuestionUser 'Vamos reprovar?', 'Espero que não',1;
-- USER 2 --
EXEC Accounts.spNewUserAccount 'sergio.verissimo@adventureworks.com'; 
EXEC Accounts.spAddQuestionUser 'O projecto tem quantas fases?','Duas',2;
EXEC Accounts.spAddQuestionUser 'CBD é a minha cadeira favorita?','Sim',2;
EXEC Accounts.spAddQuestionUser 'Vamos reprovar?', 'Espero que não',2;
EXEC Accounts.spNewUserAccount 'sergio.verissimo@adventureworks.com'; 

-- Trigger Login Ativo --
GO
CREATE TRIGGER UserIsLogged
ON Accounts.UserAuthentication
FOR INSERT
AS
	BEGIN
		DECLARE @UserLogoutDate datetime
		DECLARE @UserAccountKey int
		DECLARE @ErrorKey int
		DECLARE @Error varchar(100)
		DECLARE @ErrorLoginCursor Cursor
		SET @ErrorLoginCursor = CURSOR FOR (
		SELECT [ErrorName]  
		FROM [AdventureNewData].[Managements].[Error] where [ErrorKey] = @ErrorKey);
		SET @ErrorKey = 9
		OPEN @ErrorLoginCursor;
		WHILE @@FETCH_STATUS = 0
		BEGIN 
			FETCH NEXT FROM @ErrorLoginCursor into @Error;
		END
	CLOSE @ErrorLoginCursor;

		SELECT @UserLogoutDate = LogoutDate, @UserAccountKey = UserAccountKey from inserted
		IF((select count(*) FROM Accounts.UserAuthentication WHERE LogoutDate = @UserLogoutDate
		and UserAccountKey = @UserAccountKey)>1)
		BEGIN
		INSERT INTO Managements.ErrorLog values(@ErrorKey,@UserAccountKey,GETDATE())
		PRINT concat('Error ', @ErrorKey)
		WAITFOR DELAY '00:00:01'
		RAISERROR(@Error,16, 1)
		ROLLBACK
		END
	END
GO

-- Simulate Login --
GO
CREATE or ALTER PROCEDURE Accounts.spLogin
(@Email varchar(100),@Password varchar(80))
AS
DECLARE @UserLoginCursor cursor
DECLARE @UserAccountKey int
DECLARE @EmailCursor cursor
DECLARE @EmailLogged varchar(100)
DECLARE @Error varchar(100)
DECLARE @ErrorKey int
DECLARE @ErrorLoginCursor Cursor
BEGIN
	SET @UserLoginCursor = CURSOR FOR (
		SELECT [UserAccountKey]  
		FROM [AdventureNewData].[Accounts].[UserAccount] 
		where trim(cast([Email] as varchar)) = trim(cast(@Email as varchar)) 
		and Accounts.hashPassword(@Password) = PasswordHash
	);
	SET @EmailCursor = CURSOR FOR (
		SELECT [Email]  
		FROM [AdventureNewData].[Accounts].[UserAccount] 
		where trim(cast([Email] as varchar)) = trim(cast(@Email as varchar)) 
		and Accounts.hashPassword(@Password) = PasswordHash
	);
	SET @ErrorLoginCursor = CURSOR FOR (
		SELECT [ErrorName]  
		FROM [AdventureNewData].[Managements].[Error] where [ErrorKey] = @ErrorKey);

	OPEN @UserLoginCursor
	FETCH @UserLoginCursor into @UserAccountKey
	CLOSE @UserLoginCursor
	IF len(@Email) = 0
	BEGIN
	set @ErrorKey = 7;
	OPEN @ErrorLoginCursor;
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			FETCH NEXT FROM @ErrorLoginCursor into @Error;
		END
	CLOSE @ErrorLoginCursor;
	PRINT concat('Error ', @ErrorKey)
	INSERT INTO Managements.ErrorLog values(@ErrorKey,1,GETDATE())
	RAISERROR(@Error,16, 1)
	ROLLBACK
	END
	ELSE IF @UserAccountKey IS NULL
	BEGIN
	set @ErrorKey = 6
	OPEN @ErrorLoginCursor;
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			FETCH NEXT FROM @ErrorLoginCursor into @Error;
		END
	CLOSE @ErrorLoginCursor;
	PRINT concat('Error ', @ErrorKey)
	INSERT INTO Managements.ErrorLog values(@ErrorKey,1,GETDATE())
	RAISERROR(@Error,16, 1)
	ROLLBACK
	END
	ELSE IF len(@Password) = 0
	BEGIN
	set @ErrorKey = 8
	OPEN @ErrorLoginCursor;
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			FETCH NEXT FROM @ErrorLoginCursor into @Error;
		END
	CLOSE @ErrorLoginCursor;
	PRINT concat('Error ', @ErrorKey)
	INSERT INTO Managements.ErrorLog values(@ErrorKey,1,GETDATE())
	RAISERROR(@Error,16, 1)
	ROLLBACK
	END
	ELSE 
	BEGIN
	OPEN @UserLoginCursor
	WHILE @@FETCH_STATUS = 0
	BEGIN
	FETCH @UserLoginCursor into @UserAccountKey
	END
	CLOSE @UserLoginCursor
	Insert into Accounts.UserAuthentication values (GETDATE(),'1900-01-01',@UserAccountKey);
	OPEN @EmailCursor
	FETCH NEXT FROM @EmailCursor into @Email
	CLOSE @EmailCursor
	PRINT concat('Welcome ',@Email);
	END
	DEALLOCATE @UserLoginCursor
	DEALLOCATE @ErrorLoginCursor;
END


EXEC Accounts.spLogin 'alexandre.coelho@adventureworks.com','AdventureWorksCBD123';
EXEC Accounts.spLogin 'sergio.verissimo@adventureworks.com','AdventureWorks123';


-- Simulate Logout --
GO
CREATE OR ALTER PROCEDURE Accounts.spLogout(@Email varchar(100))
AS
DECLARE @UserCursor cursor
DECLARE @UserKey int
DECLARE @LoggedOutDate datetime
DECLARE @ErrorLoginCursor cursor
DECLARE @ErrorKey int
DECLARE @LastLogKey int
DECLARE @Error varchar(100)
BEGIN 
	SET @UserCursor = CURSOR FOR (
		SELECT [UserAccountKey]  
		FROM [AdventureNewData].[Accounts].[UserAccount] 
		where trim(cast([Email] as varchar)) = trim(cast(@Email as varchar)) 
	);

	SET @ErrorLoginCursor = CURSOR FOR (
		SELECT [ErrorName]  
		FROM [AdventureNewData].[Managements].[Error] where [ErrorKey] = @ErrorKey);

	OPEN @UserCursor
	FETCH NEXT FROM @UserCursor into @UserKey
	CLOSE @UserCursor

	SET @LastLogKey = (	
		SELECT TOP 1 [UserLogsKey]  
		FROM [AdventureNewData].[Accounts].[UserAuthentication]
		where UserAccountKey = @UserKey ORDER BY UserLogsKey desc
	);

	SET @LoggedOutDate = (
	SELECT [LogoutDate]  
		FROM [AdventureNewData].[Accounts].[UserAuthentication]
		where UserAccountKey = @UserKey and UserLogsKey = @LastLogKey);

	IF(@LoggedOutDate != cast('1900-01-01 00:00:00.000' as datetime))
	BEGIN
	set @ErrorKey = 10
	OPEN @ErrorLoginCursor;
	WHILE @@FETCH_STATUS = 0
		BEGIN 
			FETCH NEXT FROM @ErrorLoginCursor into @Error;
		END
		CLOSE @ErrorLoginCursor;
		PRINT concat('Error ', @ErrorKey)
		INSERT INTO Managements.ErrorLog values(@ErrorKey,@UserKey,GETDATE())
		RAISERROR(@Error,16, 1)
		ROLLBACK
	END
	ELSE
		BEGIN
			UPDATE Accounts.UserAuthentication SET LogoutDate = GETDATE() 
			WHERE UserAccountKey = @UserKey AND UserLogsKey = @LastLogKey;
			PRINT concat('Goodbye ', @Email) 
		END
	DEALLOCATE @UserCursor
	DEALLOCATE @ErrorLoginCursor
END


GO
exec Accounts.spLogout 'alexandre.coelho@adventureworks.com';
exec Accounts.spLogout 'sergio.verissimo@adventureworks.com';

--- Recover Password ----
GO
CREATE or ALTER PROCEDURE Accounts.spRecoverPassword (
	@Email varchar(100),
	@NewPassword varchar(80), 
	@Answer1 varchar(100),
	@Answer2 varchar(100),
	@Answer3 varchar(100)
)
AS 
BEGIN
	DECLARE @SecurityQuestionsCursor cursor
	DECLARE @SecurityQuestionKey int
	DECLARE @AnswerRetrieved varchar(100)
	DECLARE @UserKey int
	DECLARE @EqualAnswer bit


	SET @UserKey = (
		SELECT [UserAccountKey]  
		FROM [AdventureNewData].[Accounts].[UserAccount] 
		WHERE trim(cast([Email] as varchar)) = trim(cast(@Email as varchar)) 
	);
	SET @SecurityQuestionsCursor = CURSOR FOR (
		SELECT [SecurityQuestionKey],[AnswerText]  
		FROM [AdventureNewData].[Accounts].[UserAccount_SecurityQuestion] where [UserAccountKey] = @UserKey);
	
	PRINT concat('User Key - ',@UserKey);
	SET @EqualAnswer = 0;
	OPEN @SecurityQuestionsCursor
	FETCH NEXT FROM @SecurityQuestionsCursor into @SecurityQuestionKey,@AnswerRetrieved
	IF(@SecurityQuestionKey = 1 and trim(cast(@Answer1 as varchar)) = trim(cast(@AnswerRetrieved as varchar)))
		BEGIN
			PRINT concat('Entrei aqui ', @SecurityQuestionKey , ' / ', @AnswerRetrieved)
			SET @EqualAnswer = 1
		END
	ELSE
		BEGIN
			SET @EqualAnswer = 0
		END
	PRINT concat('SecurityQuestion Key - ',@SecurityQuestionKey, ' - AnswerRetrieved - ',@AnswerRetrieved);
	WHILE @@FETCH_STATUS = 0
	BEGIN
		FETCH NEXT FROM @SecurityQuestionsCursor into @SecurityQuestionKey,@AnswerRetrieved
		PRINT concat(@SecurityQuestionKey,' / ',@AnswerRetrieved);
		IF(@SecurityQuestionKey = 2 and trim(cast(@Answer2 as varchar)) = trim(cast(@AnswerRetrieved as varchar)))
			BEGIN
				SET @EqualAnswer = 1
			END
		ELSE
			BEGIN
				SET @EqualAnswer = 0
			END
		IF(@SecurityQuestionKey = 3 and trim(cast(@Answer3 as varchar)) = trim(cast(@AnswerRetrieved as varchar)))
			BEGIN
				SET @EqualAnswer = 1
			END
		ELSE
			BEGIN
				SET @EqualAnswer = 0
			END
	END
	CLOSE @SecurityQuestionsCursor
	DEALLOCATE @SecurityQuestionsCursor
END

EXEC Accounts.spRecoverPassword 'alexandre.coelho@adventureworks.com',
'123','Complementos de Base de Dados','Sexta-Feira','Espero que não'


/*                               
select * from Accounts.UserAuthentication;
select * from Accounts.UserAccount;
select * from Accounts.SecurityQuestion;
select * from Accounts.UserAccount_SecurityQuestion;
select * from Managements.ErrorLog;
select * from dbo.Error;
*/

select * from Products.Product;


--EXEC sp_spaceused;