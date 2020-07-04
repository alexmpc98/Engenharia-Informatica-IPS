#include "utils.h"
#include "listElem.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "kahoot.h"
#include "list.h"

/**
 * @brief Separação do ficheiro
 * 
 * @param string Valor a ser implementado no token após separação por delimitador
 * @param nFields Numero de campos
 * @param delim O que delimita/separa os valores
 * @return String
 */
char** split(char *string, int nFields, const char *delim){
    char **tokens = (char**) malloc(sizeof(char*) * nFields);

    int index = 0;
    char *token = (char*)strtok(string,delim);

    while(token){
        tokens[index++] = token;
        token = strtok(NULL,delim);
    }
    return tokens;
}


/**
 * @brief Importação da informação de um ficheiro para uma lista
 * 
 * @param filename Nome do ficheiro (com extensão)
 * @param listKR Lista que recebe os valores lidos do ficheiro
 */
void importKahootFromFile(char *filename, PtList *listKR){
    FILE *f = NULL;

    f = fopen(filename,"r");
    if(f == NULL){
        printf("An error ocurred... It was not possible to open the file %s ...\n",filename);
        return;
    }

    char nextline[1024];
    int countKR = 0; // kahoot report count
    bool firstline = true;

    *listKR = listCreate(10);

    while(fgets(nextline,sizeof(nextline),f)){
        if(strlen(nextline) < 1 )
            continue;

    /* As the first line of the file contains the name of the fields it should be ignored */
    if(firstline){
        firstline = false;
        continue;
    }

    char **tokens = split(nextline,6,";");

    // At this moment the tokens array is composed with the following "strings"
    //tokens[0] - week
    //tokens[1] - rank
    //tokens[2] - nickname;
    //tokens[3] - total_score;
    //tokens[4] - correct_answers;
    //tokens[5] - incorrect_answers;

    KahootReport kr = KahootReportCreate(atoi(tokens[0]),atoi(tokens[1]),tokens[2],atoi(tokens[3]),
    atoi(tokens[4]),atoi(tokens[5]));

    free(tokens);
    
    int error_code = listAdd(*listKR,countKR,kr);
    
    if(error_code == LIST_FULL || error_code == LIST_INVALID_RANK || error_code == LIST_NO_MEMORY
    || error_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    countKR++;
}
    printf("\n\n%d kahoot reports were read!\n\n",countKR);
    fclose(f);
}

/**
 * @brief Ordenação dos dados importados por ordem alfabética, com recurso a selection sort
 * 
 * @param listKR Lista que contem os dados importados
 */
void sortByAlphabeticalOrder(PtList *listKR){
    int ptSize = 0;  
    int size_code = listSize(*listKR,&ptSize);
    if(size_code == LIST_FULL || size_code == LIST_INVALID_RANK || size_code == LIST_NO_MEMORY
    || size_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    int error1 = 0;
    int error2 = 0;
    int error3 = 0;
    int error4 = 0;
    int error5 = 0;
    KahootReport kr;
    KahootReport kr2;
    KahootReport kr3;
    for(int i=0; i<ptSize-1;i++){
        for(int j=i+1;j<ptSize;j++){
            /* Get List 1 2*/
            error1 = listGet(*listKR,i,&kr);

            if(error1 == LIST_FULL || error1 == LIST_INVALID_RANK || error1 == LIST_NO_MEMORY
            || error1 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
            }

            /* Get List 2 */
            error2 = listGet(*listKR,j,&kr2);

            if(error2 == LIST_FULL || error2 == LIST_INVALID_RANK || error2 == LIST_NO_MEMORY
            || error2 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
            }
            /* Compare */
            if(strcmp(kr.nickname,kr2.nickname)>0){
                /* Swap Operation 1*/
                error3 = listSet(*listKR,i,kr3,&kr);

                if(error3 == LIST_FULL || error3 == LIST_INVALID_RANK || error3 == LIST_NO_MEMORY
                || error3 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
                
                /* Swap Operation 2*/
                error4 = listSet(*listKR,j,kr,&kr2);

                if(error4 == LIST_FULL || error4 == LIST_INVALID_RANK || error4 == LIST_NO_MEMORY
                || error4 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
                
                /* Swap Operation 3*/
                error5 = listSet(*listKR,i,kr2,&kr3);

                if(error5 == LIST_FULL || error5 == LIST_INVALID_RANK || error5 == LIST_NO_MEMORY
                || error5 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
            }
        }
    }
}

/**
 * @brief Ordenação dos dados importados por ordem ascendente de semana, com recurso a bubble sort
 * 
 * @param listKR Lista que contem os dados importados
 */
void sortByWeekAscendingOrder(PtList *listKR){
    int ptSize = 0;  
    int size_code = listSize(*listKR,&ptSize);
    if(size_code == LIST_FULL || size_code == LIST_INVALID_RANK || size_code == LIST_NO_MEMORY
    || size_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    int error1 = 0;
    int error2 = 0;
    int error3 = 0;
    int error4 = 0;
    int error5 = 0;
    KahootReport kr;
    KahootReport kr2;
    KahootReport kr3;
    for(int i=0; i<ptSize;i++){
        for(int j=0;j<ptSize-i-1;j++){
            /* Get List 1 2*/
            error1 = listGet(*listKR,j,&kr);
            if(error1 == LIST_FULL || error1 == LIST_INVALID_RANK || error1 == LIST_NO_MEMORY
            || error1 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
            }

            /* Get List 2 */
            error2 = listGet(*listKR,j+1,&kr2);

            if(error2 == LIST_FULL || error2 == LIST_INVALID_RANK || error2 == LIST_NO_MEMORY
            || error2 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
            }
            /* Compare */
            if(kr.week>kr2.week){
                /* Swap Operation 1*/
                error3 = listSet(*listKR,j,kr3,&kr);
                if(error3 == LIST_FULL || error3 == LIST_INVALID_RANK || error3 == LIST_NO_MEMORY
                || error3 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
                
                /* Swap Operation 2*/
                error4 = listSet(*listKR,j,kr,&kr2);

                if(error4 == LIST_FULL || error4 == LIST_INVALID_RANK || error4 == LIST_NO_MEMORY
                || error4 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
                
                /* Swap Operation 3*/
                error5 = listSet(*listKR,i,kr2,&kr3);

                if(error5 == LIST_FULL || error5 == LIST_INVALID_RANK || error5 == LIST_NO_MEMORY
                || error5 == LIST_NULL){
                printf("An error ocurred... Please try again...\n");
                return;
                }
            }
        }
    }
}
