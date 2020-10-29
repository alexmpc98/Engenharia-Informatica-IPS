#pragma once
#include "utils.h"
#include "structs.h"
#include "listElem.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>
#include "list.h"
#include "utils.h"

// Comandos B
void average(PtList *listPatients);
void follow(PtList *listPatients,long int id,bool bol);
void sex(PtList *listPatients);
void show(PtList *listPatients,long int id);
void topFive(PtList *listPatients);
void oldest(PtList *listPatients);
void growth(PtList *listPatients,Date date);
void matrix(PtList *listPatients);



