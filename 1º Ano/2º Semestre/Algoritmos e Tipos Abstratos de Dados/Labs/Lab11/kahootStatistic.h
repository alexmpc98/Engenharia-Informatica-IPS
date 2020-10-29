#pragma once
#define KSTAT_OK 0
#define KSTAT_NOT_FOUND 1
#define KSTAT_NULL 2

/**
 * @brief data structure to store information about kahoot answers per user
 * totalScore - total of score in the weeks participated
 * numberWeeks - number of weeks that the user participated
 * totalCorrect - number of total anwers correct obtained
 * totalIncorrect - number of total anwers incorrect obtained
 */
typedef struct statistic{
int totalScore;
int numberWeeks;
int totalCorrect;
int totalIncorrect;
}KahootStatistic;

/**
 * @brief create a statistic and initialize all the fiels to zero
 * 
 * @return KahootStatistic - the struture created and initialized 
 */

KahootStatistic kahootStatisticCreate();
/**
 * @brief increment the value of fields of the pointer of kst
 * 
 * @param kst pointer to KahootStatistic 
 * @param score - the value to increment to score
 * @param weeks - the value to increment to weeks
 * @param correct -the value to increment to totalCorrect
 * @param incorrect -the value to increment to totalIncorrect
 * @return KSTAT_OK if kst is diferent of null, KSTAT_NULL otherwise
 */
int kahootStatisticUpdate(KahootStatistic *kst,int score, int weeks, int correct, int incorrect);

/**
 * @brief Print the content of KahootStatitic 
 * 
 * @param st 
 */
void kahootStatisticPrint(KahootStatistic st);