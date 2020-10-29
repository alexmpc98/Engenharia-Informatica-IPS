-- Creating Master Key
USE AdventureNewData
GO
CREATE MASTER KEY ENCRYPTION
BY PASSWORD = 'AdventureWorksCBD'
GO

--Roles
DROP ROLE IF EXISTS Administrator;
DROP ROLE IF EXISTS MarketingManager;
DROP ROLE IF EXISTS AnonymousRole;
DROP ROLE IF EXISTS RegistedRole;
CREATE ROLE Administrator;
CREATE ROLE MarketingManager;
CREATE ROLE AnonymousRole;
CREATE ROLE RegistedRole;

--Logins
CREATE LOGIN [Admin] WITH PASSWORD = 'AdventureWorksCBDAdmin';
CREATE LOGIN [MarketingManager] WITH PASSWORD = 'AdventureWorksCBDMarketingManager';
CREATE LOGIN [AnonymousLogin] WITH PASSWORD = 'AdventureWorksCBDAnonUser';
CREATE LOGIN [RegistedLogin] WITH PASSWORD = 'AdventureWorksCBDRegUser';

-- Users
DROP USER IF EXISTS [Admin];
DROP USER IF EXISTS [TheMarketingGuy];
DROP USER IF EXISTS [AnonymousUser];
DROP USER IF EXISTS [RegistedUser];
CREATE USER [Admin] FOR LOGIN [Admin];
CREATE USER [TheMarketingGuy] FOR LOGIN [MarketingManager];
CREATE USER [AnonymousUser] FOR LOGIN [AnonymousLogin];
CREATE USER [RegistedUser] FOR LOGIN [RegistedLogin];

-- User atributed to Role
EXEC sp_addrolemember @rolename = 'Administrator' , @membername = 'Admin';
EXEC sp_addrolemember @rolename = 'MarketingManager' , @membername = 'TheMarketingGuy';
EXEC sp_addrolemember @rolename = 'AnonymousRole' , @membername = 'AnonymousUser';
EXEC sp_addrolemember @rolename = 'RegistedRole' , @membername = 'RegistedUser';

-- Permissions
-- Administrator
USE [AdventureNewData]
GRANT CONTROL ON DATABASE::[AdventureNewData] TO Administrator;


-- Marketing Manager
GRANT SELECT ON SCHEMA::[Sales] TO MarketingManager;
GRANT SELECT ON OBJECT::[Products].[ProductPromotion] TO MarketingManager;

-- Anonymous User
GRANT SELECT ON SCHEMA::[Products] TO AnonymousRole;

-- Registed User
GRANT SELECT ON SCHEMA::[Customers] TO RegistedRole;
GRANT SELECT ON SCHEMA::[Sales] TO RegistedRole;
