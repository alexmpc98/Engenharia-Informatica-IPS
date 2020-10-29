#pragma once
#include "utils.h"
#include <string.h>
#include "listElem.h"
#include "list.h"
#include "map.h"
#include "mapElem.h"

void importPatientsFromFile(char *filename, PtList *listPatients);
void importRegionsFromFile(char *filename, PtMap *mapRegions);