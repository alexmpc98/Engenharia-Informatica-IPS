USE [AdventureNewData];

-- -----------------------------------------------------
-- PROCEDURE TO CREATE A COMMENT
-- -----------------------------------------------------
GO
CREATE OR ALTER PROCEDURE [Products].newComment (
	@CommentText varchar(150),
	@CommentClassification varchar(50),
	@CommentDate date,
	@ProductKey int,
	@CustomerEmail varchar(150)
)
AS
	BEGIN
		INSERT INTO [Products].[ProductComment]
		(
			CommentText,
			CommentClassification,
			CommentDate,
			ProductKey,
			CustomerEmail
		)
		VALUES (
			@CommentText,
			@CommentClassification,
			@CommentDate,
			@ProductKey,
			@CustomerEmail
		)
	END
GO

EXEC [Products].newComment 'Sem Palavras. Cinco Estrelas!', 5, '12-04-2019', 288, 'eugene10@adventure-works.com';

--SELECT * FROM [AdventureNewData].[Products].[ProductComment];


-- -----------------------------------------------------
-- PROCEDURE TO GENERATE MULTIPLE COMMENTS
-- -----------------------------------------------------
GO
CREATE OR ALTER PROCEDURE [Products].populateComments 
AS
	BEGIN
		-- CURSOR
		DECLARE @Cursor CURSOR 
		DECLARE @CursorEmail CURSOR 
		DECLARE @iterator int
		DECLARE @CommentText varchar(150)
		DECLARE @CommentClassification varchar(50)
		DECLARE @CommentDate date
		DECLARE @count int
		DECLARE @ProductKey int
		DECLARE @CustomerEmail varchar(150)

		SET @iterator = 0

		SET @CursorEmail = CURSOR FOR (
			SELECT DISTINCT c.[EmailAddress] 		
			FROM [AdventureNewData].[Customers].[Customer] c
		);
		SET @Cursor = CURSOR FOR (
			SELECT DISTINCT p.[ProductKey] 		
			FROM [AdventureNewData].[Products].[Product] p
		);
		OPEN @Cursor;
		OPEN @CursorEmail;
		FETCH NEXT FROM @Cursor INTO @ProductKey;
		FETCH NEXT FROM @CursorEmail INTO @CustomerEmail;
		WHILE @@FETCH_STATUS = 0 
		BEGIN 
		
				PRINT @ProductKey
					INSERT INTO [Products].[ProductComment]
					(
						CommentText,
						CommentClassification,
						CommentDate,
						ProductKey,
						CustomerEmail
					)
					VALUES (
						CONCAT('Muito Bom! ', @iterator),
						(SELECT ROUND(5*RAND(), 0)),
						DATEADD(day, @iterator, '2018/08/01'),
						@ProductKey,
						@CustomerEmail
					)
			SET @iterator = @iterator + 1;
			FETCH NEXT FROM @Cursor INTO @ProductKey;
			FETCH NEXT FROM @CursorEmail INTO @CustomerEmail;
		END 
		CLOSE @CursorEmail; 
		CLOSE @Cursor; 
	END
GO

EXEC [Products].populateComments;




