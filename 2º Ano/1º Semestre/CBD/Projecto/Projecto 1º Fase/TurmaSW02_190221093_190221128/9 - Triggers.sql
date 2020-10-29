USE AdventureNewData

GO
create trigger ProductLog on 
[AdventureNewData].[Products].[Product] FOR DELETE , UPDATE, INSERT
AS
BEGIN
  IF EXISTS (select 0 FROM DELETED)
	BEGIN
	  IF EXISTS (SELECT 0 FROM Inserted)
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('U','ProductLog',GETDATE())
		INSERT INTO [Managements].[ProductLog](ProductKey,ListPrice,FinishedGoodsFlag,ProductColor,SafetyStockLevel,
		StandardCost,Weight,DaysToManufacture,ProductLine,DealerPrice,Class,Style,Status,ProductModelKey,ProductDescriptionKey
		,ProductNameKey,CategorySubCategoryKey,ProductMeasureUnitCodeKey,SizeSizeRangeKey)
		 SELECT U.ProductKey,U.ListPrice,U.FinishedGoodsFlag,U.ProductColor,U.SafetyStockLevel,
		U.StandardCost,U.Weight,U.DaysToManufacture,U.ProductLine,
		U.DealerPrice,U.Class,U.Style,U.Status,U.ProductModelKey,U.ProductDescriptionKey
		,U.ProductNameKey,U.CategorySubCategoryKey,U.ProductMeasureUnitCodeKey,U.SizeSizeRangeKey from DELETED U
		END
	   ELSE 
		IF EXISTS (select 0 FROM DELETED)
		BEGIN
		INSERT INTO [Managements].[ChangeLog] VALUES ('D','ProductLog',GETDATE())
	  INSERT INTO [Managements].[ProductLog](ProductKey,ListPrice,FinishedGoodsFlag,ProductColor,SafetyStockLevel,
		StandardCost,Weight,DaysToManufacture,ProductLine,DealerPrice,Class,Style,Status,ProductModelKey,ProductDescriptionKey
		,ProductNameKey,CategorySubCategoryKey,ProductMeasureUnitCodeKey,SizeSizeRangeKey)
		 SELECT D.ProductKey,D.ListPrice,D.FinishedGoodsFlag,D.ProductColor,D.SafetyStockLevel,
		D.StandardCost,D.Weight,D.DaysToManufacture,D.ProductLine,
		D.DealerPrice,D.Class,D.Style,D.Status,D.ProductModelKey,D.ProductDescriptionKey
		,D.ProductNameKey,D.CategorySubCategoryKey,D.ProductMeasureUnitCodeKey,D.SizeSizeRangeKey from DELETED D
		END
	  END
	  ELSE
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('I','ProductLog',GETDATE())
	  INSERT INTO [Managements].[ProductLog](ProductKey,ListPrice,FinishedGoodsFlag,ProductColor,SafetyStockLevel,
		StandardCost,Weight,DaysToManufacture,ProductLine,DealerPrice,Class,Style,Status,ProductModelKey,ProductDescriptionKey
		,ProductNameKey,CategorySubCategoryKey,ProductMeasureUnitCodeKey,SizeSizeRangeKey)
		 SELECT I.ProductKey,I.ListPrice,I.FinishedGoodsFlag,I.ProductColor,I.SafetyStockLevel,
		I.StandardCost,I.Weight,I.DaysToManufacture,I.ProductLine,
		I.DealerPrice,I.Class,I.Style,I.Status,I.ProductModelKey,I.ProductDescriptionKey
		,I.ProductNameKey,I.CategorySubCategoryKey,I.ProductMeasureUnitCodeKey,I.SizeSizeRangeKey from INSERTED I
	  END
END

GO
create trigger ProductCategoryLog on 
[AdventureNewData].[Products].[ProductCategory] FOR DELETE , UPDATE, INSERT
AS
BEGIN
  IF EXISTS (select 0 FROM DELETED)
	BEGIN
	  IF EXISTS (SELECT 0 FROM Inserted)
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('U','ProductCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductCategoryLog](ProductCategoryKey,EnglishProductCategoryName,
		SpanishProductCategoryName,FrenchProductCategoryName)
		 SELECT U.ProductCategoryKey,U.EnglishProductCategoryName,U.SpanishProductCategoryName,U.FrenchProductCategoryName 
		 from DELETED U
		END
	   ELSE 
		IF EXISTS (select 0 FROM DELETED)
		BEGIN
		INSERT INTO [Managements].[ChangeLog] VALUES ('D','ProductCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductCategoryLog](ProductCategoryKey,EnglishProductCategoryName,
		SpanishProductCategoryName,FrenchProductCategoryName)
		 SELECT D.ProductCategoryKey,D.EnglishProductCategoryName,D.SpanishProductCategoryName,D.FrenchProductCategoryName 
		from DELETED D
		END
	  END
	  ELSE
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('I','ProductCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductCategoryLog](ProductCategoryKey,EnglishProductCategoryName,
		SpanishProductCategoryName,FrenchProductCategoryName)
		 SELECT I.ProductCategoryKey,I.EnglishProductCategoryName,I.SpanishProductCategoryName,I.FrenchProductCategoryName 
		 from INSERTED I
	  END
END

GO
create trigger ProductSubCategoryLog on 
[AdventureNewData].[Products].[ProductSubCategory] FOR DELETE , UPDATE, INSERT
AS
BEGIN
  IF EXISTS (select 0 FROM DELETED)
	BEGIN
	  IF EXISTS (SELECT 0 FROM Inserted)
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('U','ProductSubCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductSubCategoryLog](ProductSubCategoryKey,
		EnglishProductSubCategoryName,SpanishProductSubCategoryName,FrenchProductSubCategoryName)
		 SELECT U.ProductSubCategoryKey,U.EnglishProductSubCategoryName,U.SpanishProductSubCategoryName,U.FrenchProductSubCategoryName 
		 from DELETED U
		END
	   ELSE 
		IF EXISTS (select 0 FROM DELETED)
		BEGIN
		INSERT INTO [Managements].[ChangeLog] VALUES ('D','ProductSubCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductSubCategoryLog](ProductSubCategoryKey,
		EnglishProductSubCategoryName,SpanishProductSubCategoryName,FrenchProductSubCategoryName)
		 SELECT D.ProductSubCategoryKey,D.EnglishProductSubCategoryName,D.SpanishProductSubCategoryName,D.FrenchProductSubCategoryName  
		from DELETED D
		END
	  END
	  ELSE
	  BEGIN
	  INSERT INTO [Managements].[ChangeLog] VALUES ('I','ProductSubCategoryLog',GETDATE())
		INSERT INTO [Managements].[ProductSubCategoryLog](ProductSubCategoryKey,
		EnglishProductSubCategoryName,SpanishProductSubCategoryName,FrenchProductSubCategoryName)
		 SELECT I.ProductSubCategoryKey,I.EnglishProductSubCategoryName,I.SpanishProductSubCategoryName,I.FrenchProductSubCategoryName  
		 from INSERTED I
	  END
END
