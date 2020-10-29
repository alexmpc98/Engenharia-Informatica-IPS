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
    PtMap mapKR=mapCreate(5);
    
    /*Nickname	TotalScore	NumberOfWeeks	TotalCorrectAnwers	TotalofncorrectAnwers
        Ann	56	5	16	4
        Petter	12	4	4	12
        Lukas	32	2	8	0
    */
    KahootStatistic kst;
    char nickname[15];
    float av1,av2,av3;
    KahootStatistic ks1=kahootStatisticCreate();
    KahootStatistic ks2=kahootStatisticCreate();
    KahootStatistic ks3=kahootStatisticCreate();
    kahootStatisticUpdate(&ks1,56,5,16,4);
    mapPut(mapKR,stringCodeCreate("Ann"), ks1);
    kahootStatisticUpdate(&ks2,12,4,4,12);
    mapPut(mapKR,stringCodeCreate("Petter"), ks2);
    kahootStatisticUpdate(&ks3,32,2,8,0);
    mapPut(mapKR,stringCodeCreate("Lukas"),ks3);
    mapPrint(mapKR);
    printf("Qual o user que quer ver ? ");
    scanf("%s",nickname);
    statisticsCalculation(mapKR,nickname,&av1,&av2,&av3);
    printf("\nPercentage of Correct Anwers %.0f%% \nAverage of Correct Anwers %.1f\nAverage of Score per week %.1f\n", av1,av2,av3);
    mapDestroy(&mapKR);

   return EXIT_SUCCESS;
}
