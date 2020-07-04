#include <stdio.h>
#include <stdlib.h>
#include "kahoot.h"
#include "utils.h"
#include "list.h"
#include "listElem.h"

int main(){
    PtList kr = NULL; 
    // Nivel 3.11
    importKahootFromFile("kahootReports.csv",&kr);
    printf("Nivel 3.11\n");
    sortByAlphabeticalOrder(&kr);
    listPrint(kr);
    listDestroy(&kr);

    // Nivel 3.12
    importKahootFromFile("kahootReports.csv",&kr);
    printf("Nivel 3.12\n");
    sortByWeekAscendingOrder(&kr);
    listPrint(kr);
    listDestroy(&kr);
    return EXIT_SUCCESS;
}