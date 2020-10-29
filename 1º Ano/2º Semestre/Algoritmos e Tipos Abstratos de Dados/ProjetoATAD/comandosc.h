#pragma once
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include "structs.h"
#include "list.h"
#include "listElem.h"
#include "map.h"
#include "mapElem.h"


void regions(PtMap *mapRegions, PtList *listPatients);
void report(PtMap *mapRegions, PtList *listPatients);

