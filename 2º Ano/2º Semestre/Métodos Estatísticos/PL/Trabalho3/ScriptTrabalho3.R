#Library Load
library(readxl);
library(DescTools);
library(Rcmdr);
library(fdth);
library(moments);

# You have to change the directory, to the directory where you have allocated the file
directory <- "D:/GIT_WORKSPACES/Workspace_ME/Trabalho3/";
# directory <- "C:/Users/Tim/Documents/GitHub/Workspace_ME/Trabalho3/";
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























