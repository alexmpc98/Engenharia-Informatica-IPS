#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include "kahootReport.h"


KahootReport kahootReportCreate(int week, int rank, char nickname[20], int total_score, int correct_answers, int incorrect_answers){
    KahootReport kr;
    kr.week = week;
    kr.rank = rank;
    strcpy(kr.nickname, nickname);
    kr.total_score = total_score;
    kr.correct_answers = correct_answers;
    kr.incorrect_answers = incorrect_answers;
    return kr;
}

void kahootReportPrint(KahootReport kr){
    printf(" %-4d| %-4d| %-10s| %-6d| %-4d| %-4d\n", kr.week, kr.rank, kr.nickname, kr.total_score, kr.correct_answers, kr.incorrect_answers);
}

