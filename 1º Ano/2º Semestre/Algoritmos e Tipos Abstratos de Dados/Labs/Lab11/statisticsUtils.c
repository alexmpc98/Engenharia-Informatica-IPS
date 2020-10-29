#include "statisticsUtils.h"
#include <string.h>


int updateStatisticFromReport(KahootReport report, KahootStatistic* kst){
    if(kst==NULL) return KSTAT_NULL;
        
    kst->numberWeeks++;
    kst->totalCorrect+=report.correct_answers;
    kst->totalIncorrect+=report.incorrect_answers;
    kst->totalScore+=report.total_score;
    return KSTAT_OK;
    
}

KahootStatistic createStatisticFromNickname(char* nickname , PtList ptlist){
    KahootReport kr;
    KahootStatistic kst= kahootStatisticCreate();
    int size;
    listSize(ptlist,&size);
    for(int i=0;i<size;i++){
        listGet(ptlist,i,&kr);

        if(strcmp(nickname,kr.nickname)==0){
            updateStatisticFromReport(kr,&kst);
        }
    }
    return kst;
}

PtMap createStatisticsMap(PtList ptlist){
    PtMap mapKR=mapCreate(5);
    KahootReport kr;
    KahootStatistic kst= kahootStatisticCreate();;
    int size;
    listSize(ptlist,&size);
    for(int i=0;i<size;i++){
        listGet(ptlist,i,&kr);
        int error=mapGet(mapKR,stringCodeCreate(kr.nickname),&kst);
        if (error==MAP_UNKNOWN_KEY) {
            kst= kahootStatisticCreate();
        }    
        
        updateStatisticFromReport(kr,&kst);
        mapPut(mapKR,stringCodeCreate(kr.nickname),kst);
    }
    return mapKR; 
 

 }

 int statisticsCalculation(PtMap mapKR, char* nickname, float *averageCorrects, float *averageCorrectsWeek, float *averageScore){
    KahootStatistic kst;
    int error=mapGet(mapKR,stringCodeCreate(nickname),&kst);
    if (error==MAP_UNKNOWN_KEY) {
        return KSTAT_NOT_FOUND;
    }    
    *averageCorrects=kst.totalCorrect*100.0/(kst.totalCorrect+kst.totalIncorrect);
    *averageCorrectsWeek=kst.totalCorrect*1.0/(kst.numberWeeks);
    *averageScore=kst.totalScore*1.0/kst.numberWeeks;
    
    return KSTAT_OK;


 }


