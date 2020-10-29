#pragma once
#include "utils.h"
#include <string.h>
#include "listElem.h"
#include "list.h"
#include "map.h"
#include "mapElem.h"

void loadP(char *filename, PtList *listPatients);
void loadR(char *filename, PtMap *mapRegions);
void clear(PtList *listPatients, PtMap *mapPatients);
void quitCommand(PtList *listPatients, PtMap *mapRegions);