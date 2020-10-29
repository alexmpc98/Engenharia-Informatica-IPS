#pragma once

typedef struct date{
    unsigned int day, month,year;
} Date;

typedef struct patient{
    long int id;
    char sex[6]; // {"male", "female"}
    int birthYear;
    char country[40]; //birth country
    char region[40];
    char infectionReason[100];
    long int infectedBy; //id of the infected patient
    Date confirmedDate;
    Date releasedDate;
    Date deceasedDate;
    char status[10]; // {"isolated","released","deceased"}
} Patient;

typedef struct region{
    char name[40];
    char capital[40];
    float area;
    int population;
} Region;

// Creates
Date createDate(unsigned int day, unsigned int month, unsigned int year);
Patient createPatient(long int id,
char sex[6],
int birthYear,
char country[40],
char region[40],
char infectionReason[40],
long int infectedBy,
Date confirmedDate,
Date releasedDate,
Date deceasedDate,
char status[10]);
Region createRegion(char name[40],char capital[40], float area,int population);

//Prints
void printDate(Date dateToPrint);
void printPatient(Patient patientToPrint);
void printRegion(Region regionToPrint);

