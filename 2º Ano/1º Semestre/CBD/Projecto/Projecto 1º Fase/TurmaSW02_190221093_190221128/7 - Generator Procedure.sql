use AdventureNewData
GO
CREATE OR ALTER PROCEDURE [Managements].spGenerator (@Schema Varchar(40), @TableName varchar(100))
AS
BEGIN
DECLARE @SQLInsert nvarchar(MAX)
DECLARE @TableColumnTypes nvarchar(MAX)
DECLARE @SQLDelete nvarchar(MAX)
DECLARE @SQLUpdate nvarchar(MAX)
DECLARE @ColumnName nvarchar(MAX)
DECLARE @CharacterMaximumLength nvarchar(MAX)
DECLARE @NumericPrecision nvarchar(MAX)
DECLARE @ColumnType nvarchar(MAX)
DECLARE @QueryInfo cursor
DECLARE @TableValues nvarchar(MAX)
DECLARE @NullValidation nvarchar(MAX)
DECLARE @LastOrdinal int
DECLARE @Ordinal nvarchar(MAX)

SET @LastOrdinal = (SELECT TOP 1 ORDINAL_POSITION FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_SCHEMA = @Schema and TABLE_NAME = @TableName ORDER BY ORDINAL_POSITION DESC);

SET @QueryInfo = CURSOR FOR(
SELECT cast(COLUMN_NAME as nvarchar), 
cast(CHARACTER_MAXIMUM_LENGTH as nvarchar), cast(NUMERIC_PRECISION as nvarchar), cast(DATA_TYPE
as nvarchar), cast(ORDINAL_POSITION as nvarchar) FROM(
SELECT COLUMN_NAME, CHARACTER_MAXIMUM_LENGTH, NUMERIC_PRECISION, DATA_TYPE, ORDINAL_POSITION
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_SCHEMA = @Schema and TABLE_NAME = @TableName ORDER BY ORDINAL_POSITION ASC OFFSET 0 ROWS) as QueryInfo);

SET @SQLInsert = '
GO 
CREATE OR ALTER PROCEDURE ' + @Schema +'.sp_Ins_' + @TableName + '(';
SET @SQLDelete = '
CREATE OR ALTER PROCEDURE ' + @Schema +'.sp_Del_' + @TableName;
SET @SQLUpdate += 'GO 
CREATE OR ALTER PROCEDURE ' + @Schema +'.sp_Upd_' + @TableName;

OPEN @QueryInfo
FETCH NEXT FROM @QueryInfo INTO @ColumnName, @CharacterMaximumLength, @NumericPrecision, @ColumnType, @Ordinal	

IF(@CharacterMaximumLength IS NOT NULL)
	BEGIN
	SET @SQLDelete += ' (@' + @ColumnName  + ' '  + @ColumnType + '(' + @CharacterMaximumLength + ')';
    SET @SQLUpdate += ' (@' + @ColumnName  + ' '  + @ColumnType + ' (' + @CharacterMaximumLength + ')'; 
	END
IF (@NumericPrecision IS NOT NULL)
	BEGIN
	IF(@ColumnType = 'int' OR @ColumnType = 'money')
		BEGIN
	SET @SQLDelete += ' (@' + @ColumnName  + ' '  + @ColumnType;
		END
	ELSE
	BEGIN
	SET @SQLDelete += ' (@' + @ColumnName  + ' '  + @ColumnType + '(' + @NumericPrecision + ')';
    SET @SQLUpdate += ' (@' + @ColumnName  + ' '  + @ColumnType + ' (' + @NumericPrecision + ')';
	END
	END
SET @SQLDelete += ') AS 
BEGIN
DELETE FROM [' + @Schema + '].[' + @TableName + '] WHERE ' + @ColumnName + ' = @' + @ColumnName + '; 
END';
SET @ColumnName = ''
SET @ColumnType = ''
SET @NumericPrecision = '--'

WHILE @@FETCH_STATUS = 0
BEGIN

IF (COLUMNPROPERTY(OBJECT_ID(@Schema + @TableName), @ColumnName, 'IsPrimary') = 1)
	BEGIN
	SET @ColumnName = ''
	SET @ColumnType = ''
	SET @NumericPrecision = ''
	SET @ColumnType = ''
	END
	IF(@CharacterMaximumLength IS NOT NULL)
	BEGIN
	SET @SQLInsert += '@' + @ColumnName  + ' '  + @ColumnType + '(' + @CharacterMaximumLength + '),';
	SET @SQLUpdate += ' @' + @ColumnName  + ' '  + @ColumnType + ' (' + @CharacterMaximumLength + '),'; 
	END
IF(@NumericPrecision IS NOT NULL)
	BEGIN
	IF(@ColumnType = 'int' OR @ColumnType = 'money')
		BEGIN
	SET @SQLInsert += '@' + @ColumnName  + ' '  + @ColumnType + ',';
	 SET @SQLUpdate += ' @' + @ColumnName  + ' '  + @ColumnType + ','; 
	    END
			IF(@Ordinal = @LastOrdinal)
			BEGIN
			SET @SQLInsert += '@' + @ColumnName  + ' '  + @ColumnType + ' )';
			SET @SQLUpdate += ' (@' + @ColumnName  + ' '  + @ColumnType + ') AS BEGIN'; 
			END
		IF(@NumericPrecision = '--')
			BEGIN
			SET @NumericPrecision = ' '
			END
	ELSE IF (@NumericPrecision IS NULL)
		BEGIN		
		IF(@Ordinal = @LastOrdinal)
			BEGIN
			SET @SQLInsert += '@' + @ColumnName  + ' '  + @ColumnType + '(' + @NumericPrecision + ')';
			SET @SQLUpdate += '@' + @ColumnName  + ' '  + @ColumnType + ' (' + @NumericPrecision + ')'; 
			END
	SET @SQLInsert += '@' + @ColumnName  + ' '  + @ColumnType + '(' + @NumericPrecision + '),';
	 SET @SQLUpdate += ' @' + @ColumnName  + ' '  + @ColumnType + ' (' + @NumericPrecision + ')'; 
		 END
 END
	SET @TableValues = CONCAT(@TableValues,'@',@ColumnName , ',');

IF (COLUMNPROPERTY(OBJECT_ID(@Schema+@TableName),@ColumnName, 'AllowsNull') = 0)
	BEGIN
		SET @NullValidation = 
	CONCAT(@NullValidation, ' IF (@', @ColumnName, ' IS NULL) 
		BEGIN RAISERROR (''Column ', @ColumnName , ' can not be null'', 16, 1); RETURN; END;');
	END
SET @SQLUpdate += ' UPDATE [' + @Schema + '].[' + @TableName + '] SET ' + @ColumnName + ' = ' + @TableValues
FETCH NEXT FROM @QueryInfo INTO @ColumnName, @CharacterMaximumLength, @NumericPrecision, @ColumnType, @Ordinal	
END

CLOSE @QueryInfo
DEALLOCATE @QueryInfo

SET @TableValues = LEFT(@TableValues, LEN(@TableValues) - 1);
SET @SQLInsert += ' AS 
		BEGIN ' + @NullValidation 
		+ ' INSERT INTO [' + @Schema + '].[' + @TableName + '] (' + @ColumnName + ') VALUES (' + @TableValues + ') 
		END
		GO';
		SET @SQLUpdate += ' END
		GO'


EXECUTE(@SQLDelete)
EXEC(@SQLInsert)
EXEC(@SQLUpdate)
END


exec [Managements].spGENERATOR 'Sales', 'SalesOrderDetail'





