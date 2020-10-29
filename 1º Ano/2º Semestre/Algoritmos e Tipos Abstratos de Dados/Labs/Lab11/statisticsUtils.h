#pragma once
#include "map.h"
#include "kahootReport.h"
#include "list.h"
#include "kahootStatistic.h"


/**
 * @brief update the values of KahootStatistic fields, with the values of report
 * 
 * @param report - data strutured with the values of a participation of a user in a kahoot game
 * @param kst - KahootStatistic to be updated
 * @return int - KSTAT_OK if kst is not NULL, KSTAT_NULL otherwise
 */
int updateStatisticFromReport(KahootReport report, KahootStatistic* kst);

/**
 * @brief Create a Statistic , using tha data existing in the list for a specific user
 * 
 * @param nickname - of the user
 * @param list - with all the KahhotReport Data
 * @return KahootStatistic - created with the data obtained from the list to the user identified by the nickname
 */
KahootStatistic createStatisticFromNickname(char* nickname , PtList list);
/**
 * @brief Create a Statistics Map object (Key->nickname, Value->KaohotStatistic), with the data of Kahhotreports avaiable in ptList
 * 
 * @param ptList -pointer to a List of KahootReport Elements
 * @return PtMap  - Map (Key->nickname, Value->KahootStatistic)
 */

PtMap  createStatisticsMap(PtList ptList);

/**
 * @brief calculate indicators about the statics values stored in mapKR
 * 
 * @param mapKR  - map (Key->nickname, Value->KaohotStatistic) with the date to calculate indicators
 * @param nickname  - key for make the calculations (name of the user)
 * @param averageCorrects - totalCorrects/(totalCorrects + totalIncorrects)
 * @param averageCorrectWeek -totalIncorrects/numberWeeks
 * @param averageScore  - totalScore/numberWeeks
 * @return int - KSTAT_NULL if mapKR is NULL, KSTAT_NOT_FOUND if the nickname not exists in the map, KSTAT_OK otherwise
 */
int statisticsCalculation(PtMap mapKR, char* nickname, float *averageCorrects, float *averageCorrectWeek, float *averageScore);
 
