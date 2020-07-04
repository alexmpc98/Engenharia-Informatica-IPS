#include <stdio.h>
#include <stdlib.h>
#include "kahoot.h"
#include "utils.h"
#include "list.h"
#include "listElem.h"


int getQuestions(PtList *listKR, int week);
void totalAverageScore(PtList *listKR);

int main(){
    PtList kr = NULL; 
    int week = 0;
    int a = 0;
    importKahootFromFile("kahootReports.csv",&kr);
    printf("Insert week number: ");
    scanf("%d",&week);
    a = getQuestions(&kr,week);
    printf("This week %d had %d questions\n",week,a);
    totalAverageScore(&kr);
    listDestroy(&kr);
    return EXIT_SUCCESS;
}

/**
 * @brief Este algoritmo permite obter a quantidade de questões que existiram numa dada semana
 * 
 * @param listKR Lista que contem todos os dados previamente importados
 * @param week Semana que o utilizador deseja consultar
 *
 * @return int
 */
int getQuestions(PtList *listKR, int week){
    int ptSize = 0;  
    int size_code = listSize(*listKR,&ptSize);
    if(size_code == LIST_FULL || size_code == LIST_INVALID_RANK || size_code == LIST_NO_MEMORY
    || size_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return -1;
    }
    KahootReport kr;
    int counter = 0;
    for(int i=0; i<ptSize;i++){
         listGet(*listKR,i,&kr);
         if(kr.week == week){
             counter++;
         }
    }
    return counter-1;
}

/**
 * @brief Este algoritmo imprime sobre a forma de uma matriz a pontuação média por semana
 * 
 * @param listKR Lista que contem todos os dados previamente importados
 *
 */
void totalAverageScore(PtList *listKR){ 
    int ptSize = 0;  
    int size_code = listSize(*listKR,&ptSize);
    if(size_code == LIST_FULL || size_code == LIST_INVALID_RANK || size_code == LIST_NO_MEMORY
    || size_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    KahootReport kr;
    int week = 0;
    for(int i = 0; i < ptSize; i++){
         listGet(*listKR, i, &kr);
         if(kr.week != week){
             week++;
         }        
    }
    double sum = 0.0;
    int score_size = 0;
    printf("\n| Week | Total_Score Average   |\n");
    for(int i = 0; i < week; i++){ 
        listGet(*listKR, i, &kr);
        score_size = getQuestions(listKR,i+1);
        for(int j = 0; j < score_size; j++){
            sum = sum + kr.total_score;
        }
        printf("| %d    | %0.2f               |\n", i+1, (double)sum/score_size);
        sum = 0.0;
    }
    printf("\n");
}
