#include <string.h>
#include "listElem.h"
#include "list.h"

char ** split(char *string, int nFields, const char *delim);
void importKahootFromFile(char *filename, PtList *listKR);
void sortByAlphabeticalOrder(PtList *listKR);
void sortByWeekAscendingOrder(PtList *listKR);