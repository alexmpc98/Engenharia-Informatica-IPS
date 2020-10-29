#pragma once
#include "structs.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>
#include "list.h"
#include "listElem.h"
#include "map.h"
#include "mapElem.h"

// Metódos auxiliares/utilitários - Comandos A
Date datebuild(char *date);
char * replaceComma(char * strToken);
char ** split(char *string, int nFields, const char *delim);

// Metodos auxiliares/utilitários - Comandos B
Date getCurrentDate();
int statistics(int firstAge, int lastAge, PtList *listPatients, char * status);
int daysInMonth(int year,int month);
int DateDiff(Date date1, Date date2);
int leapYear(int year);
Date biggestDate(PtList *listPatients);
bool isIso(Patient patient);
bool isReleased(Patient patient);
bool isDeceased(Patient patient);

