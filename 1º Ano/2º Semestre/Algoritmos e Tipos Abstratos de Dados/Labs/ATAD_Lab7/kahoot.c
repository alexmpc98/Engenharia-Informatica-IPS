#include "kahoot.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/**
 * @brief Este algoritmo permite criar um KahootReport
 * 
 * @param week Semana em que o Kahoot foi realizado
 * @param rank Posição que o jogador em questão, ficou
 * @param nickname Nome que o jogador optou por utilizar
 * @param total_score Pontuação total
 * @param correct_answers Respostas correctas
 * @param incorrect_answers Respostas incorrectas
 *
 * @return KahootReport
 */
KahootReport KahootReportCreate(int week, int rank, char nickname[50], int total_score,int correct_answers, int incorrect_answers){
    KahootReport kr;
    kr.week = week;
    kr.rank = rank;
    strcpy(kr.nickname,nickname);
    kr.total_score = total_score;
    kr.correct_answers = correct_answers;
    kr.incorrect_answers = incorrect_answers;
    return kr;
}

/**
 * @brief Este algoritmo permite demonstrar ao utilizador o conteudo de um KahootReport
 * 
 * @param kr Estrutura de KahootReport a ser imprimida
 */
void KahootReportPrint(KahootReport kr){
    printf("%d | %d | %s | %d | %d | %d\n",kr.week,kr.rank,kr.nickname,kr.total_score,kr.correct_answers,kr.incorrect_answers);
}