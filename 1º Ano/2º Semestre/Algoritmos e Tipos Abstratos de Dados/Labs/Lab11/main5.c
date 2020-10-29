#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"
#include "map.h"
#include "utils.h"
#include "kahootReport.h"
#include "statisticsUtils.h"


int main() {

    PtList listKR = NULL;
    char nickname[15];
    float av1,av2,av3;
    importKahootReportsFromFile("kahootReports.csv", &listKR);
    PtMap mapKR=createStatisticsMap(listKR);
    mapPrint(mapKR);
    printf("Qual o user que quer ver ? ");
    scanf("%s",nickname);
    statisticsCalculation(mapKR,nickname,&av1,&av2,&av3);
    printf("\nPercentage of Correct Anwers %.0f%% \nAverage of Correct Anwers %.1f\nAverage of Score per week %.1f\n", av1,av2,av3);   
    mapDestroy(&mapKR);

    return EXIT_SUCCESS;4
}
