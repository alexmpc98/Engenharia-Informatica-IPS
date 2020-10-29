#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"
#include "utils.h"
#include "kahootReport.h"
#include "statisticsUtils.h"


int main() {

    PtList listKR = NULL;

    char filename[100] =  "kahootReports.csv";
    char nickname[20]="Frank";
    /*Import data from file*/
    importKahootReportsFromFile(filename, &listKR);

    KahootStatistic kst=createStatisticFromNickname(nickname,listKR);
    kahootStatisticPrint(kst);
    
    listDestroy(&listKR);

    return EXIT_SUCCESS;
}
