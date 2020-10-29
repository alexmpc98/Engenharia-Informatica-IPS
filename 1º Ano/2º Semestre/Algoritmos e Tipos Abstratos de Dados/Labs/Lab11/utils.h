#pragma once

#include "list.h"

char** split(char *string, int nFields, const char *delim);
void importKahootReportsFromFile(char *filename, PtList *listKR);

void sortByNickname(PtList listKR);
void sortByWeekAscending(PtList listKR);
int questionsPerWeek(PtList listKR, int week);
void printMatrixTotalScoreAvgPerWeek(PtList listKR);

