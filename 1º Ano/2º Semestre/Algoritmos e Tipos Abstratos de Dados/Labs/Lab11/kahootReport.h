#pragma once

typedef struct kahootReport{
    int week;
    int rank;
    char nickname[20];
    int total_score;
    int correct_answers;
    int incorrect_answers;
} KahootReport;

/**
 * @brief 
 *  TotalScore – valor médio da classificações obtidas
	NumberWeeks – numero de semanas que participou
	TotalCorrect  - número de Respostas corretas na totalidade das semanas
	TotalIncorrect  - número

 * 
 */


KahootReport kahootReportCreate(int week, int rank, char nickname[20], int total_score, int correct_answers, int incorrect_answers);
void kahootReportPrint(KahootReport kr);


