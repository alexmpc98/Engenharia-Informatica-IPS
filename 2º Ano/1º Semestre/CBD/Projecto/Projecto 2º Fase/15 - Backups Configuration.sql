-----------------------------
-- Backup AdventureNewData --
-----------------------------
-- Creating dump device for Full Backups
USE AdventureNewData;
EXEC sp_addumpdevice 'disk', 'AdventureNewDataBackups', 'C:\ProjectoGrupo6\Backups\ANDBackupDevice.bak'; 

-- Creating dump device for Diferential Backups
USE AdventureNewData;
EXEC sp_addumpdevice 'disk', 'AdventureNewDataDiferentialBackups', 'C:\ProjectoGrupo6\Backups\ANDDiffBackupDevice.bak';  

-- Full Backup
GO
BACKUP DATABASE AdventureNewData TO [AdventureNewDataBackups] WITH FORMAT, NAME = N'AdventureNewData-FULL Database Backup', SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Partial Backup -> Products Filegroup
GO
BACKUP DATABASE AdventureNewData 
FILEGROUP = 'Products_Filegroup' 
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupPartial-Products.bak' WITH FORMAT, SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Partial Backup -> Sales Filegroup
GO
BACKUP DATABASE AdventureNewData 
FILEGROUP = 'Sales_Filegroup' 
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupPartial-Sales.bak' WITH FORMAT, SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Partial Backup -> Products Filegroup
GO
BACKUP DATABASE AdventureNewData 
FILEGROUP = 'Customer_Filegroup' 
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupPartial-Customer_Filegroup.bak' WITH FORMAT, SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Partial Backup -> Products Filegroup
GO
BACKUP DATABASE AdventureNewData 
FILEGROUP = 'Accounts_Filegroup' 
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupPartial-Accounts_Filegroup.bak' WITH FORMAT, SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Partial Backup -> Products Filegroup
GO
BACKUP DATABASE AdventureNewData 
FILEGROUP = 'Management_Filegroup' 
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupPartial-Management_Filegroup.bak' WITH FORMAT, SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Diferential Backup
GO
BACKUP DATABASE AdventureNewData TO [AdventureNewDataDiferentialBackups] 
WITH DIFFERENTIAL , FORMAT,  NAME = N'AdventuraNewData-Diff Database Backup', SKIP, REWIND, NOUNLOAD,  STATS = 10

-- Log Backup
USE MASTER;
BACKUP LOG AdventureNewData TO DISK = 'C:\ProjectoGrupo6\Backups\ANDTLogBackup.trn'
WITH NAME = N'AdventuraNewData-LOG Database Backup', NORECOVERY, NO_TRUNCATE

-- Tail Backup
USE master;
ALTER DATABASE AdventureNewData SET single_user with rollback immediate
BACKUP LOG AdventureNewData
TO DISK = 'C:\ProjectoGrupo6\Backups\ANDBackupTail.TRN' WITH NORECOVERY, CONTINUE_AFTER_ERROR
GO
RESTORE DATABASE AdventureNewData FROM [AdventureNewDataBackups] WITH FILE = 1
ALTER DATABASE AdventureNewData SET MULTI_USER

-- Restore Database with Full Backup
USE master
RESTORE DATABASE AdventureNewData FROM [AdventureNewDataBackups] WITH FILE = 1,REPLACE,RECOVERY


-- Restore Database with Diferential Backup
use master
RESTORE DATABASE AdventureNewData FROM [AdventureNewDataBackups] WITH FILE = 1, NORECOVERY, REPLACE
RESTORE DATABASE AdventureNewData FROM [AdventureNewDataDiferentialBackups] WITH RECOVERY, REPLACE

-- Restore filelist from the Backups
USE master
RESTORE FILELISTONLY 
FROM [AdventureNewDataBackups]

-- Restore FUll -> LOG
RESTORE DATABASE AdventureNewData FROM [AdventureNewDataBackups] WITH FILE = 1, NORECOVERY, REPLACE
RESTORE LOG AdventureNewData FROM DISK = 'C:\ProjectoGrupo6\Backups\ANDTLogBackup.TRN' WITH RECOVERY



