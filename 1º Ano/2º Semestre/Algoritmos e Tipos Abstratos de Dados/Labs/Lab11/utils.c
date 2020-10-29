#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "utils.h"
#include "kahootReport.h"

char** split(char *string, int nFields, const char *delim) {

	char **tokens = (char**) malloc(sizeof(char*) * nFields);

	int index = 0;

	/*
	char *token = (char*)strtok(string, delim);

	while (token) {
		tokens[index++] = token;
		token = strtok(NULL, delim);
	}
	*/

	/* Bruno: Penso que esta minha solução funciona melhor para 
		os casos de campos vazios consecutivos */
	/* Scan string, replace 'delim' with '\0' and copy pointer */
	int len = strlen(string);

	tokens[index++] = &string[0];
	for(int i=0; i < len; i++) {
		if( string[i] == delim[0] ) {
			string[i] = '\0';

			if( i < len - 1 ) {
				tokens[index++] = &string[i] + 1;
			}			
		}

	}

	return tokens;
}

void importKahootReportsFromFile(char *filename, PtList *listKR) { 
	FILE *f = NULL;

	f = fopen(filename, "r");

	if (f == NULL) {
		printf("An error ocurred... It was not possible to open the file %s ...\n", filename);
		return;
	}

	char nextline[1024];

	int countKR = 0; //kahoot report count
	bool firstLine = true;

	*listKR = listCreate(10);

	while (fgets(nextline, sizeof(nextline), f)) {
		if (strlen(nextline) < 1)
			continue;

		/*As the first line of the file contains the names of the fields it should be ignored*/
		if (firstLine){
			firstLine = false;
			continue;
		} 

		char **tokens = split(nextline, 6, ";");
		
		//At this moment the tokens array is composed with the following "strings"
		//tokens[0] - week 
		//tokens[1] - rank
		//tokens[2] - nickname
		//tokens[3] - total_score
		//tokens[4] - correct_answers
		//tokens[5] - incorrect_answers

		KahootReport kr = kahootReportCreate(atoi(tokens[0]), atoi(tokens[1]), 
		                                           tokens[2], atoi(tokens[3]), 
											 atoi(tokens[4]), atoi(tokens[5]));
		
		free(tokens); // release of the memory alocated in function split

		int error_code = listAdd(*listKR, countKR, kr);

		if (error_code == LIST_FULL ||error_code == LIST_INVALID_RANK || 
		    error_code == LIST_NO_MEMORY || error_code == LIST_NULL){
			printf("An error ocurred... Please try again... \n");
			return;
		}
		countKR++;
	}
	
	printf("\n\n%d kahoot reports were read!\n\n", countKR);
	fclose(f);
}

void sortByNickname(PtList listKR){
    int size;
    listSize(listKR, &size);

	KahootReport kr1, kr2;
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size - i - 1; j++){
            listGet(listKR, j, &kr1);
            listGet(listKR, j+1, &kr2);
            if (strcmp(kr1.nickname, kr2.nickname) > 0){
                listSet(listKR, j+1, kr1, &kr2);
                listSet(listKR, j, kr2, &kr1);
            }
        }
    }
}

void sortByWeekAscending(PtList listKR){
	int size;
    listSize(listKR, &size);

	KahootReport kr1, kr2;
	int rankMin;
    for(int i = 0; i < size; i++){
		rankMin = i;
        for(int j = i; j < size; j++){
            listGet(listKR, rankMin, &kr1);
            listGet(listKR, j, &kr2);
            if ( kr1.week > kr2.week){
				rankMin = j;
            }
        }
		listGet(listKR, i, &kr1);
		listSet(listKR, rankMin, kr1, &kr2);
        listSet(listKR, i, kr2, &kr1);
    }
}

int questionsPerWeek(PtList listKR, int week){
	int numQuestions = -1, size;
	listSize(listKR, &size);

	KahootReport kr;
	for(int i = 0; i < size; i++){
		int error = listGet(listKR, i, &kr);
		if (error != LIST_OK)
			return -1;
		if(kr.week == week)
			return kr.correct_answers + kr.incorrect_answers;
	}
	return numQuestions;
}

double getWeekAverage(PtList listKR, int week, int startRank, int *endRank){
	KahootReport kr;
	int sum = 0, count = 0;
	listGet(listKR, startRank, &kr);
	while (kr.week == week){
		sum += kr.total_score;
		count++;
		int error = listGet(listKR, ++startRank, &kr);
		if (error != LIST_OK) break;
	}
	*endRank += count;
	return (double)sum / count;
}

void printMatrixTotalScoreAvgPerWeek(PtList listKR){
	int size, currentWeek, currentRank = 0;
	double avg;

	sortByWeekAscending(listKR); // We have to assure that the list is ordered ascending by week  
	
	listSize(listKR, &size);

	printf("\n\n| %-6s | %-22s |\n", "Week", "Total_Score Average");
	
	KahootReport kr;
	while (currentRank < size){
		listGet(listKR, currentRank, &kr);
		currentWeek = kr.week;
		avg = getWeekAverage(listKR, currentWeek, currentRank, &currentRank);
		printf("| %-6d | %-22.2f |\n", currentWeek, avg);	
	}

	printf("\n");	
}

