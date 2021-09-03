#Library Load
library(readxl);
library(DescTools);
library(Rcmdr);
library(fdth);
library(moments);

# Functions
# Function for mode
getmode <- function(x) {
  ux <- unique(x)
  tab <- tabulate(match(x, ux))
  ux[tab == max(tab)]
}

# You have to change the directory, to the directory where you have allocated the file
directory <- "D:/GIT_WORKSPACES/Workspace_ME/Trabalho1/";
# directory <- "C:/Users/Tim/Documents/GitHub/Workspace_ME/Trabalho1/";
seoulBikeSharingDemand <- read_excel(paste(directory,"SeoulBikeSharingDemand.xlsx", sep=""));

# Verify the table
View(seoulBikeSharingDemand);

# Statistic info about every variable
summary(seoulBikeSharingDemand);

##/////////////////////////////////////////// Nominal Variables /////////////////////////////////////////////

############################################# SEASONS ################################################

# Separating a qualitative nominal variable - Seasons
Desc(seoulBikeSharingDemand$Seasons);
seasons.tb <- table(seoulBikeSharingDemand$Seasons);
seasons.tbpercentage <- prop.table(seasons.tb) * 100;

# Constructing a Bar Plot and a Pie Chart about the variable
# Bar Plots are often used to represent graphically qualitative variables
Barplot(seoulBikeSharingDemand$Seasons, xlab="Seasons", ylab="Number of Bikes", label.bars=TRUE);
# Pie Chats are even more often used to represent graphically qualitative variables
piechart(seoulBikeSharingDemand$Seasons, xlab="",ylab="", main="Seasons Pie Chart", scale="frequency");

#Statistics Measurements
# Its doesn't exist median, quantiles or mean when the variable is qualitative (as it is), 
# therefore the only measurement applicable is mode

# mode variable
mode <- getmode(seoulBikeSharingDemand$Seasons);
print(mode); # Spring and Summer are the mode , as it is bimodal


############################################# HOLIDAY ################################################

# Separating a qualitative nominal variable - Holiday
Desc(seoulBikeSharingDemand$Holiday);
holidays.tb <- table(seoulBikeSharingDemand$Holiday);
holidays.tbpercentage <- prop.table(holidays.tb) * 100;

# Constructing a Bar Plot and a Pie Chart about the variable
# Bar Plots are often used to represent graphically qualitative variables
Barplot(seoulBikeSharingDemand$Holiday, xlab="Holiday(Y/N)", ylab="Number of Bikes", label.bars=TRUE);
# Pie Chats are even more often used to represent graphically qualitative variables
piechart(seoulBikeSharingDemand$Holiday, xlab="",ylab="", main="Holiday Pie Chart", scale="frequency");

#Statistics Measurements
# Its doesn't exist median, quantiles or mean when the variable is qualitative (as it is), 
# therefore the only measurement applicable is mode

# mode variable
mode <- getmode(seoulBikeSharingDemand$Holiday);
print(mode) # "No Holiday" should be the mode

############################################# FUNCTIONING DAY ################################################

# Separating a qualitative nominal variable - Functioning Day
Desc(seoulBikeSharingDemand$Functioning.Day);
functioningday.tb <- table(seoulBikeSharingDemand$Functioning.Day);
functioningday.tbpercentage <- prop.table(functioningday.tb ) * 100;

# Constructing a Bar Plot and a Pie Chart about the variable
# Bar Plots are often used to represent graphically qualitative variables
Barplot(seoulBikeSharingDemand$Functioning.Day, xlab="Functioning Day(Y/N)", ylab="Number of Bikes", label.bars=TRUE);
# Pie Chats are even more often used to represent graphically qualitative variables
piechart(seoulBikeSharingDemand$Functioning.Day, xlab="",ylab="", main="Functioning Day Pie Chart", scale="frequency");

#Statistics Measurements
# Its doesn't exist median, quantiles or mean when the variable is qualitative (as it is), 
# therefore the only measurement applicable is mode

# mode variable
mode <- getmode(seoulBikeSharingDemand$Functioning.Day);
print(mode); # "Yes" should be the mode


############################################# HOUR ################################################
# Separating the quantitative discrete variable - Hour 
Desc(seoulBikeSharingDemand$Hour);
hour.tb <- table(seoulBikeSharingDemand$Hour);
hour.tbpercentage <- prop.table(hour.tb) * 100;



# Calculating the classes amplitude with Sturges Rule (kHour)
maxHour <- max(seoulBikeSharingDemand$Hour);
minHour <- min(seoulBikeSharingDemand$Hour);
kHour <- trunc(1+log2(length(seoulBikeSharingDemand$Hour)));
amplitudeHour = (maxHour - minHour)/kHour


# Frequency table with classes of variable Hour
tf16 <- fdt(seoulBikeSharingDemand$Hour, breaks = 'Sturges', h = amplitudeHour,
            start=minHour, end = maxHour+0.6)

# Histogram
hist(seoulBikeSharingDemand$Hour, main="Bikes per hours",
     xlab="Number of Bikes", ylab="Hours", ylim=c(0,1200), breaks = seq(from=0,to=24,by=1.6),xlim=range(0,25))


############################################# RENTED BIKE COUNT ################################################
# Separating the quantitative discrete variable - Rented Bike Count
numericBikeCount <- as.numeric(seoulBikeSharingDemand$`Rented Bike Count`);
Desc(numericBikeCount);
rentedBikeCount.tb <- table(numericBikeCount);
rentedBikeCount.tbpercentage <- prop.table(rentedBikeCount.tb) * 100;

maxCount <- max(numericBikeCount);
minCount <- min(numericBikeCount);
kCount <- trunc(1+log2(length(numericBikeCount)));
amplitudeCount = (maxCount - minCount)/kCount;

# Frequency Table
tf16 <- fdt(numericBikeCount, breaks = 'Sturges', h = amplitudeCount,
            start=minCount, end = maxCount);

# Histogram
hist(numericBikeCount, main="Bike Count",
     xlab="Bike Count", ylab="Frequency", breaks = seq(from=0,to=3556,by=254), xlim=range(0,4000), ylim=range(0,3000));

#Using the fuction mode
mode <- getmode(numericBikeCount);
print(mode); # 0 should be the mode

# Using the brute value, we've got 127 as mode

# Mean
mean(numericBikeCount);

# 1st Quartile
quantile(numericBikeCount, .25, type=4)

# Second Quartile or Median
median(numericBikeCount);

# Third Quartile - explained in the word report
quantile(numericBikeCount, .75, type=4)

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericBikeCount, type=4)

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericBikeCount, probs = c(0.25, 0.50, 0.75))

# Skewness
skewness(numericBikeCount);

# Kurtosis
kurtosis(numericBikeCount);

# Boxplot diagram
boxplot(numericBikeCount, main="Bike Count");



##/////////////////////////////////////////// Continuous Variables /////////////////////////////////////////////


############################################# TEMPERATURES ################################################
# Separating the quantitative continuous variable - Temperature
numericTemperatures <- as.numeric(seoulBikeSharingDemand$`Temperature(°C)`);
Desc(numericTemperatures);
temperature.tb <- table(numericTemperatures);
temperature.tbpercentage <- prop.table(temperature.tb) * 100;

maxTemperature <- max(numericTemperatures);
minTemperature <- min(numericTemperatures);
kTemperature <- trunc(1+log2(length(numericTemperatures)));
amplitudeTemperature = (maxTemperature - minTemperature)/kTemperature;

# Frequency Table
tf16 <- fdt(numericTemperatures, breaks = 'Sturges', h = amplitudeTemperature,
            start=minTemperature, end = maxTemperature);

# Histogram
hist(numericTemperatures, main="Temperature",
     xlab="Temperature", ylab="Frequency", breaks = seq(from=minTemperature,to=maxTemperature,by=amplitudeTemperature), 
     xlim=range(minTemperature,maxTemperature), ylim=range(0,1500));

#Using the fuction mode
mode <- getmode(numericTemperatures);
print(mode); # 0 should be the mode

# Mean
meanTemperature <- mean(numericTemperatures);

# First Quartile
q25Temperature <- quantile(numericTemperatures, .25, type=4);

# Second Quartile or Median
median(numericTemperatures);

# Third Quartile - explained in the word report
q75Temperature <- quantile(numericTemperatures, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericTemperatures, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericTemperatures, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericTemperatures, main="Temperatures");

# ------ Dispersion measures ------ 
# Amplitude
maxTemperature - minTemperature;

# Interquartile amplitude 
q75Temperature - q25Temperature;

# Variance
var(numericTemperatures);

# Standard deviation
sdTemperature <- sd(numericTemperatures);

# Variation Coeficient
(sdTemperature/meanTemperature) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericTemperatures);

# Kurtosis
kurtosis(numericTemperatures);


# ------------ Relate to Bike Count ------------

# First Class
firstClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < -13.71
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= -17.8);
firstClassBikes <- sum(as.numeric(firstClass$`Rented Bike Count`));

# Second Class
secondClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < -9.629
                      & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= -13.71);
secondClassBikes <- sum(as.numeric(secondClass$`Rented Bike Count`));

# Third Class
thirdClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < -5.543
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= -9.629);
thirdClassBikes <- sum(as.numeric(thirdClass$`Rented Bike Count`));

# Fourth Class
fourthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < -1.457
                      & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= -5.543);
fourthClassBikes <- sum(as.numeric(fourthClass$`Rented Bike Count`));

# Fifth Class
fifthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 2.629
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= -1.457);
fifthClassBikes <- sum(as.numeric(fifthClass$`Rented Bike Count`));

# Sixth Class
sixthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 6.714
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 2.629);
sixthClassBikes <- sum(as.numeric(sixthClass$`Rented Bike Count`));

# Seventh Class
seventhClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 10.8
                       & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 6.714);
seventhClassBikes <- sum(as.numeric(seventhClass$`Rented Bike Count`));

# Eighth Class
eighthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 14.89
                      & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) > 10.8);
eighthClassBikes <- sum(as.numeric(eighthClass$`Rented Bike Count`));

# Ninth Class
ninthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 18.97
                      & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 14.89);
ninthClassBikes <- sum(as.numeric(ninthClass$`Rented Bike Count`));

# Tenth Class
tenthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 23.06
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 18.97);
tenthClassBikes <- sum(as.numeric(tenthClass$`Rented Bike Count`));

# Eleventh Class
eleventhClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 27.14
                     & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 23.06);
eleventhClassBikes <- sum(as.numeric(eleventhClass$`Rented Bike Count`));

# Twelfth Class
twelfthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 31.23
                        & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 27.14);
twelfthClassBikes <- sum(as.numeric(twelfthClass$`Rented Bike Count`));

# Thirteenth Class
thirteenthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) < 35.31
                        & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 31.23);
thirteenthClassBikes <- sum(as.numeric(thirteenthClass$`Rented Bike Count`));

# Fourteenth Class
fourteenthClass <- subset(seoulBikeSharingDemand, as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) <= 39.4
                          & as.numeric(seoulBikeSharingDemand$`Temperature(°C)`) >= 35.31);
fourteenthClassBikes <- sum(as.numeric(fourteenthClass$`Rented Bike Count`));


############################################# HUMIDITY ################################################
# Separating the quantitative continuous variable - Humidity(%)
numericHumidity <- as.numeric(seoulBikeSharingDemand$`Humidity(%)`);
Desc(numericHumidity);
humidity.tb <- table(numericHumidity);
humidity.tbpercentage <- prop.table(humidity.tb) * 100;

maxHumidity <- max(numericHumidity);
minHumidity <- min(numericHumidity);
kHumidity <- trunc(1+log2(length(numericHumidity)));
amplitudeHumidity = (maxHumidity - minHumidity)/kHumidity;

# Frequency Table
tf16 <- fdt(numericHumidity, breaks = 'Sturges', h = amplitudeHumidity,
            start=minHumidity, end = maxHumidity);

# Histogram
hist(numericHumidity, main="Humidity",
     xlab="Humidity", ylab="Frequency", breaks = seq(from=minHumidity,to=maxHumidity,by=amplitudeHumidity), 
     xlim=range(minHumidity,maxHumidity), ylim=range(0,1200));

#Using the fuction mode
mode <- getmode(numericHumidity);
print(mode); # 0 should be the mode

# Mean
meanHumidity <- mean(numericHumidity);

# First Quartile
q25Humidity <- quantile(numericHumidity, .25, type=4);

# Second Quartile or Median
median(numericHumidity);

# Third Quartile - explained in the word report
q75Humidity <- quantile(numericHumidity, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericHumidity, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericHumidity, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericHumidity, main="Humidity");

# ------ Dispersion measures ------
# Amplitude
maxHumidity - minHumidity;

# Interquartile amplitude 
q75Humidity - q25Humidity;

# Variance
var(numericHumidity);

# Standard deviation
sdHumidity <- sd(numericHumidity);

# Variation Coeficient
(sdHumidity/meanHumidity) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericHumidity);

# Kurtosis
kurtosis(numericHumidity);


############################################# WIND SPEED ################################################
# Separating the quantitative continuous variable - Wind Speed(m/s)
numericWindSpeeds <- as.numeric(seoulBikeSharingDemand$`Wind speed (m/s)`);
Desc(numericWindSpeeds);
windSpeeds.tb <- table(numericWindSpeeds);
windSpeeds.tbpercentage <- prop.table(windSpeeds.tb) * 100;

maxWindSpeed <- max(numericWindSpeeds);
minWindSpeed <- min(numericWindSpeeds);
kWindSpeed <- trunc(1+log2(length(numericWindSpeeds)));
amplitudeWindSpeed = (maxWindSpeed - minWindSpeed)/kWindSpeed;

# Frequency Table
tf16 <- fdt(numericWindSpeeds, breaks = 'Sturges', h = amplitudeWindSpeed,
            start=minWindSpeed, end = maxWindSpeed);

# Histogram
hist(numericWindSpeeds, main="Wind Speeds",
     xlab="Wind Speeds", ylab="Frequency", breaks = seq(from=minWindSpeed,to=maxWindSpeed,by=amplitudeWindSpeed), 
     xlim=range(minWindSpeed,8), ylim=range(0,2000));

#Using the fuction mode
mode <- getmode(numericWindSpeeds);
print(mode); # 0 should be the mode

# Mean
meanWindSpeeds <- mean(numericWindSpeeds);

# First Quartile
q25WindSpeed <- quantile(numericWindSpeeds, .25, type=4);

# Second Quartile or Median
median(numericWindSpeeds);

# Third Quartile - explained in the word report
q75WindSpeed <- quantile(numericWindSpeeds, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericWindSpeeds, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericWindSpeeds, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericWindSpeeds, main="Wind Speeds");

# ------ Dispersion measures ------   
# Amplitude
maxWindSpeed - minWindSpeed;

# Interquartile amplitude 
q75WindSpeed - q25WindSpeed;

# Variance
var(numericWindSpeeds);

# Standard deviation
sdWindSpeeds <- sd(numericWindSpeeds);

# Variation coeficient
(sdWindSpeeds/meanWindSpeeds) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericWindSpeeds);

# Kurtosis
kurtosis(numericWindSpeeds);


############################################# VISIBILITY ################################################
# Separating the quantitative continuous variable - Visibility (10m)
numericVisibility <- seoulBikeSharingDemand$`Visibility (10m)`;
Desc(numericVisibility);
visibility.tb <- table(numericVisibility);
visibility.tbpercentage <- prop.table(visibility.tb) * 100;

maxVisibility <- max(numericVisibility);
minVisibility <- min(numericVisibility);
kVisibility <- trunc(1+log2(length(numericVisibility)));
amplitudeVisibility = (maxVisibility - minVisibility)/kVisibility;

# Frequency Table
tf16 <- fdt(numericVisibility, breaks = 'Sturges', h = amplitudeVisibility,
            start=minVisibility, end = maxVisibility);

# Histogram(3677/8760) * 100
hist(numericVisibility, main="Visibility",
     xlab="Visibility", ylab="Frequency", breaks = seq(from=minVisibility,to=maxVisibility,by=amplitudeVisibility), 
     xlim=range(minVisibility,maxVisibility), ylim=range(0,4000));

#Using the fuction mode
mode <- getmode(numericVisibility);
print(mode); # 0 should be the mode

# Mean
meanVisibility <- mean(numericVisibility);

# First Quartile
q25Visibility <- quantile(numericVisibility, .25, type=4);

# Second Quartile or Median
median(numericVisibility);

# Third Quartile - explained in the word report
q75Visibility <- quantile(numericVisibility, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericVisibility, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericVisibility, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericVisibility, main="Visibility");

# ------ Dispersion measures ------
# Amplitude
maxVisibility - minVisibility;

# Interquartile amplitude 
q75Visibility - q25Visibility;

# Variance
var(numericVisibility);

# Standard deviation
sdVisibility <- sd(numericVisibility);

# Variation coeficient
(sdVisibility/meanVisibility) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericVisibility);

# Kurtosis
kurtosis(numericVisibility);


############################################# DEW POINT TEMPERATURE ################################################
# Separating the quantitative continuous variable - Dew point temperature (ºC)
numericDewPointTemperature <- as.numeric(seoulBikeSharingDemand$`Dew point temperature(°C)`);
Desc(numericDewPointTemperature);
dewPointTemperature.tb <- table(numericDewPointTemperature);
dewPointTemperature.tbpercentage <- prop.table(dewPointTemperature.tb) * 100;

maxDewPointTemperature <- max(numericDewPointTemperature);
minDewPointTemperature <- min(numericDewPointTemperature);
kDewPointTemperature <- trunc(1+log2(length(numericDewPointTemperature)));
amplitudeDewPointTemperature = (maxDewPointTemperature - minDewPointTemperature)/kDewPointTemperature;

# Frequency Table
tf16 <- fdt(numericDewPointTemperature, breaks = 'Sturges', h = amplitudeDewPointTemperature,
            start=minDewPointTemperature, end = maxDewPointTemperature);

# Histogram
hist(numericDewPointTemperature, main="Dew Point Temperature",
     xlab="Dew Point Temperature", ylab="Frequency", breaks = seq(from=minDewPointTemperature,to=maxDewPointTemperature,by=amplitudeDewPointTemperature), 
     xlim=range(minDewPointTemperature,30), ylim=range(0,1200));

#Using the fuction mode
mode <- getmode(numericDewPointTemperature);
print(mode); # 0 should be the mode

# Mean
meanDewPointTemperature <- mean(numericDewPointTemperature);

# First Quartile
q25DewPointTemperature <- quantile(numericDewPointTemperature, .25, type=4);

# Second Quartile or Median
median(numericDewPointTemperature);

# Third Quartile - explained in the word report
q75DewPointTemperature <- quantile(numericDewPointTemperature, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericDewPointTemperature, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericDewPointTemperature, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericDewPointTemperature, main="Dew Point Temperature");

# ------ Dispersion measures ------
# Amplitude
maxDewPointTemperature - minDewPointTemperature;

# Interquartile amplitude 
q75DewPointTemperature - q25DewPointTemperature;

# Variance
var(numericDewPointTemperature);

# Standard deviation
sdDewPointTemperature <- sd(numericDewPointTemperature);

# Variation coeficient
(sdDewPointTemperature/meanDewPointTemperature) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericDewPointTemperature);

# Kurtosis
kurtosis(numericDewPointTemperature);


############################################# Solar Radiation ################################################
# Separating the quantitative continuous variable - Solar Radiation (MJ/m2)
numericSolarRadiation <- as.numeric(seoulBikeSharingDemand$`Solar Radiation (MJ/m2)`);
Desc(numericSolarRadiation);
solarRadiation.tb <- table(numericSolarRadiation);
solarRadiation.tbpercentage <- prop.table(solarRadiation.tb) * 100;

maxSolarRadiation <- max(numericSolarRadiation);
minSolarRadiation <- min(numericSolarRadiation);
kSolarRadiation <- trunc(1+log2(length(numericSolarRadiation)));
amplitudeSolarRadiation = (maxSolarRadiation - minSolarRadiation)/kSolarRadiation;

# Frequency Table
tf16 <- fdt(numericSolarRadiation, breaks = 'Sturges', h = amplitudeSolarRadiation,
            start=minSolarRadiation, end = maxSolarRadiation);

# Histogram
hist(numericSolarRadiation, main="Solar Radiation",
     xlab="Solar Radiation", ylab="Frequency", breaks = seq(from=minSolarRadiation,to=maxSolarRadiation,by=amplitudeSolarRadiation), 
     xlim=range(minSolarRadiation,maxSolarRadiation), ylim=range(0,6000));

#Using the fuction mode
mode <- getmode(numericSolarRadiation);
print(mode); 

# Mean
meanSolarRadiation <- mean(numericSolarRadiation);

# First Quartile
q25SolarRadiation <- quantile(numericSolarRadiation, .25, type=4);

# Second Quartile or Median
median(numericSolarRadiation);

# Third Quartile - explained in the word report
q75SolarRadiation <- quantile(numericSolarRadiation, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericSolarRadiation, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericSolarRadiation, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericSolarRadiation, main="Solar Radiation");

# ------ Dispersion measures ------
# Amplitude
maxSolarRadiation - minSolarRadiation;

# Interquartile amplitude 
q75SolarRadiation - q25SolarRadiation;

# Variance
var(numericSolarRadiation);

# Standard deviation
sdSolarRadiation <- sd(numericSolarRadiation);

# Variation coeficient
(sdSolarRadiation/meanSolarRadiation) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericSolarRadiation);

# Kurtosis
kurtosis(numericSolarRadiation);


############################################# Rainfall ################################################
# Separating the quantitative continuous variable - Rainfall(mm)
numericRainfall <- as.numeric(seoulBikeSharingDemand$`Rainfall(mm)`);
Desc(numericRainfall);
rainfall.tb <- table(numericRainfall);
rainfall.tbpercentage <- prop.table(rainfall.tb) * 100;

maxRainfall <- max(numericRainfall);
minRainfall <- min(numericRainfall);
kRainfall <- trunc(1+log2(length(numericRainfall)));
amplitudeRainfall = (maxRainfall - minRainfall)/kRainfall;

# Frequency Table
tf16 <- fdt(numericRainfall, breaks = 'Sturges', h = amplitudeRainfall,
            start=minRainfall, end = maxRainfall);

# Histogram
hist(numericRainfall, main="Rainfall",
     xlab="Rainfall", ylab="Frequency", breaks = seq(from=minRainfall,to=maxRainfall,by=amplitudeRainfall), 
     xlim=range(minRainfall,maxRainfall), ylim=range(0,10000));

#Using the fuction mode
mode <- getmode(numericRainfall);
print(mode);

# Mean
meanRainfall <- mean(numericRainfall);

# First Quartile
q25Rainfall <- quantile(numericRainfall, .25, type=4);

# Second Quartile or Median
median(numericRainfall);

# Third Quartile - explained in the word report
q75Rainfall <- quantile(numericRainfall, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericRainfall, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericRainfall, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericRainfall, main="Rainfall");

# ------ Dispersion measures ------
# Amplitude
maxRainfall - minRainfall;

# Interquartile amplitude 
q75Rainfall - q25Rainfall;

# Variance
var(numericRainfall);

# Standard deviation
sdRainfall <- sd(numericRainfall);

# Variation coeficient
(sdRainfall/meanRainfall) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericRainfall);

# Kurtosis
kurtosis(numericRainfall);


############################################# Snowfall ################################################
# Separating the quantitative continuous variable - Snowfall (cm)
numericSnowfall <- as.numeric(seoulBikeSharingDemand$`Snowfall (cm)`);
Desc(numericSnowfall);
snowfall.tb <- table(numericSnowfall);
snowfall.tbpercentage <- prop.table(snowfall.tb) * 100;

maxSnowfall <- max(numericSnowfall);
minSnowfall <- min(numericSnowfall);
kSnowfall <- trunc(1+log2(length(numericSnowfall)));
amplitudeSnowfall = (maxSnowfall - minSnowfall)/kSnowfall;

# Frequency Table
tf16 <- fdt(numericSnowfall, breaks = 'Sturges', h = amplitudeSnowfall,
            start=minSnowfall, end = maxSnowfall);

# Histogram
hist(numericSnowfall, main="Snowfall",
     xlab="Snowfall", ylab="Frequency", breaks = seq(from=minSnowfall,to=maxSnowfall,by=amplitudeSnowfall), 
     xlim=range(minSnowfall,maxSnowfall), ylim=range(0,10000));

#Using the fuction mode
mode <- getmode(numericSnowfall);
print(mode);

# Mean
meanSnowfall <- mean(numericSnowfall);

# First Quartile
q25Snowfall <- quantile(numericSnowfall, .25, type=4);

# Second Quartile or Median
median(numericSnowfall);

# Third Quartile - explained in the word report
q75Snowfall <- quantile(numericSnowfall, .75, type=4);

# Quartiles - (0, 0.25, 0.50, 0.75, 1)
quantile(numericSnowfall, type=4);

# Quartiles - (0.25, 0.50, 0.75)
quantile(numericSnowfall, probs = c(0.25, 0.50, 0.75));

# Boxplot diagram
boxplot(numericSnowfall, main="Snowfall");

# ------ Dispersion measures ------
# Amplitude
maxSnowfall - minSnowfall;

# Interquartile amplitude 
q75Snowfall - q25Snowfall;

# Variance
var(numericSnowfall);

# Standard deviation
sdSnowfall <- sd(numericSnowfall);

# Variation coeficient
(sdSnowfall/meanSnowfall) * 100;

# ------ Symmetry and flattening measures ------ 
# Skewness
skewness(numericSnowfall);

# Kurtosis
kurtosis(numericSnowfall);



