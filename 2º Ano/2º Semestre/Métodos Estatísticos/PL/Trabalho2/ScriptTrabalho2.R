#Library Load
library(readxl);
library(DescTools);
library(Rcmdr);
library(fdth);
library(moments);

# You have to change the directory, to the directory where you have allocated the file
directory <- "D:/GIT_WORKSPACES/Workspace_ME/Trabalho1/";
# directory <- "C:/Users/Tim/Documents/GitHub/Workspace_ME/Trabalho1/";
seoulBikeSharingDemand <- read_excel(paste(directory,"SeoulBikeSharingDemand.xlsx", sep=""));

# Verify the table
View(seoulBikeSharingDemand);

# Statistic info about every variable
summary(seoulBikeSharingDemand);


############################################# SIMPLE LINEAR REGRESSION (GLOBAL) #############################################
# Rented Bike Count
numericBikeCount <- as.numeric(seoulBikeSharingDemand$`Rented Bike Count`);
# Temperature
numericTemperatures <- as.numeric(seoulBikeSharingDemand$`Temperature(°C)`);

# Dispersion diagrams
plot(numericTemperatures, numericBikeCount, main="Temperatures*Rented Bike Count", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);

#plot(numericBikeCount, numericTemperatures, main="Rented Bike Count*Temperatures", xlab="Rented Bike Count", 
#  ylab="Temperatures", col="red", pch=16, cex=1.1);

# Pearson's linear correlation coefficient
cor <- cor(numericBikeCount, numericTemperatures);
cor.test(numericBikeCount, numericTemperatures, method = "pearson");

# Linear regression line
lm <-lm(numericBikeCount ~ numericTemperatures);
summary(lm);

# Dispersion diagram with regression line
plot(numericTemperatures, numericBikeCount, main="Temperatures*Rented Bike Count", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);
abline(lm);

# Residuals
residuals<-residuals(lm);

# Residuals vs Predicted values
plot(resid(lm) ~ predict(lm), main="Residuals", col="green", pch=16);
abline(h=0);




############################## SIMPLE LINEAR REGRESSION (BY SEASONS - NOMINAL QUALITATIVE) ##################################
# ------------------------------ WINTER ------------------------------
seasonWinter <-subset(seoulBikeSharingDemand,Seasons=="Winter");

# Rented Bike Count
numericBikeCountWinter <- as.numeric(seasonWinter$`Rented Bike Count`);
# Temperature
numericTemperaturesWinter <- as.numeric(seasonWinter$`Temperature(°C)`);

# Dispersion diagram
plot(numericTemperaturesWinter, numericBikeCountWinter, main="Temperatures*Rented Bike Count (Winter)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);

# Pearson's linear correlation coefficient
corWinter <- cor(numericBikeCountWinter, numericTemperaturesWinter);
cor.test(numericBikeCountWinter, numericTemperaturesWinter, method = "pearson");

# Linear regression line
lmWinter <-lm(numericBikeCountWinter ~ numericTemperaturesWinter);
summary(lmWinter);

# Dispersion diagram with regression line
plot(numericTemperaturesWinter, numericBikeCountWinter, main="Temperatures*Rented Bike Count (Winter)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);
abline(lmWinter);

# Residuals
residualsWinter<-residuals(lmWinter);

# Residuals vs Predicted values
plot(resid(lmWinter) ~ predict(lmWinter), main="Residuals (Winter)", col="green", pch=16);
abline(h=0);


# ------------------------------ SPRING ------------------------------
seasonSpring <-subset(seoulBikeSharingDemand,Seasons=="Spring");

# Rented Bike Count
numericBikeCountSpring <- as.numeric(seasonSpring$`Rented Bike Count`);
# Temperature
numericTemperaturesSpring <- as.numeric(seasonSpring$`Temperature(°C)`);

# Dispersion diagram
plot(numericTemperaturesSpring, numericBikeCountSpring, main="Temperatures*Rented Bike Count (Spring)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);

# Pearson's linear correlation coefficient
corSpring <- cor(numericBikeCountSpring, numericTemperaturesSpring);
cor.test(numericBikeCountSpring, numericTemperaturesSpring, method = "pearson");

# Linear regression line
lmSpring <-lm(numericBikeCountSpring ~ numericTemperaturesSpring);
summary(lmSpring);

# Dispersion diagram with regression line
plot(numericTemperaturesSpring, numericBikeCountSpring, main="Temperatures*Rented Bike Count (Spring)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);
abline(lmSpring);

# Residuals
residualsSpring<-residuals(lmSpring);

# Residuals vs Predicted values
plot(resid(lmSpring) ~ predict(lmSpring), main="Residuals (Spring)", col="green", pch=16);
abline(h=0);


# ------------------------------ AUTUMN ------------------------------
seasonAutumn <-subset(seoulBikeSharingDemand,Seasons=="Autumn");

# Rented Bike Count
numericBikeCountAutumn <- as.numeric(seasonAutumn$`Rented Bike Count`);
# Temperature
numericTemperaturesAutumn <- as.numeric(seasonAutumn$`Temperature(°C)`);

# Dispersion diagram
plot(numericTemperaturesAutumn, numericBikeCountAutumn, main="Temperatures*Rented Bike Count (Autumn)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);

# Pearson's linear correlation coefficient
corAutumn <- cor(numericBikeCountAutumn, numericTemperaturesAutumn);
cor.test(numericBikeCountAutumn, numericTemperaturesAutumn, method = "pearson");

# Linear regression line
lmAutumn <-lm(numericBikeCountAutumn ~ numericTemperaturesAutumn);
summary(lmAutumn);

# Dispersion diagram with regression line
plot(numericTemperaturesAutumn, numericBikeCountAutumn, main="Temperatures*Rented Bike Count (Autumn)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);
abline(lmAutumn);

# Residuals
residualsAutumn<-residuals(lmAutumn);

# Residuals vs Predicted values
plot(resid(lmAutumn) ~ predict(lmAutumn), main="Residuals (Autumn)", col="green", pch=16);
abline(h=0);


# ------------------------------ SUMMER ------------------------------
seasonSummer <-subset(seoulBikeSharingDemand,Seasons=="Summer");

# Rented Bike Count
numericBikeCountSummer <- as.numeric(seasonSummer$`Rented Bike Count`);
# Temperature
numericTemperaturesSummer <- as.numeric(seasonSummer$`Temperature(°C)`);

# Dispersion diagram
plot(numericTemperaturesSummer, numericBikeCountSummer, main="Temperatures*Rented Bike Count (Summer)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);

# Pearson's linear correlation coefficient
corSummer <- cor(numericBikeCountSummer, numericTemperaturesSummer);
cor.test(numericBikeCountSummer, numericTemperaturesSummer, method = "pearson");

# Linear regression line
lmSummer <-lm(numericBikeCountSummer ~ numericTemperaturesSummer);
summary(lmSummer);

# Dispersion diagram with regression line
plot(numericTemperaturesSummer, numericBikeCountSummer, main="Temperatures*Rented Bike Count (Summer)", xlab="Temperatures", 
     ylab="Rented Bike Count", col="red", pch=16, cex=1.1);
abline(lmSummer);

# Residuals
residualsSummer<-residuals(lmSummer);

# Residuals vs Predicted values
plot(resid(lmSummer) ~ predict(lmSummer), main="Residuals (Summer)", col="green", pch=16);
abline(h=0);

















