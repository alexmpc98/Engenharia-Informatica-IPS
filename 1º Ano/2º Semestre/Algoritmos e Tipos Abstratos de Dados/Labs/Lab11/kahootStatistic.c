#include "kahootStatistic.h"
#include "stdlib.h"
#include <stdio.h>
#include <string.h>

int  kahootStatisticUpdate(KahootStatistic *kst,int score, int weeks, int correct, int incorrect){
  
    if(kst==NULL) return KSTAT_NULL;
    kst->numberWeeks+=weeks;
    kst->totalCorrect+=correct;
    kst->totalIncorrect+=incorrect;
    kst->totalScore+=score;
    return KSTAT_OK;
  
}

KahootStatistic kahootStatisticCreate( ){
    KahootStatistic kst;
    kst.numberWeeks=0;
    kst.totalCorrect=0;
    kst.totalIncorrect=0;
    kst.totalScore=0;
    return kst; 
}

void kahootStatisticPrint(KahootStatistic kst){
    printf(" score %-4d| total Corrects %-4d| total incorrect %-4d| total weeks %-4d\n",kst.totalScore, kst.totalCorrect, kst.totalIncorrect,kst.numberWeeks);
   }