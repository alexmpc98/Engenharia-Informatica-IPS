#include "utils.h"
#include "structs.h"
#include "listElem.h"
#include "mapElem.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>
#include "list.h"
#include "map.h"


/**
 * @brief Importação da informação sobre pacientes, presente num ficheiro, para um ADT List
 * 
 * @param filename Nome do ficheiro (com extensão)
 * @param listPatients Lista de pacientes que recebe os valores lidos do ficheiro
 */
void loadP(char *filename, PtList *listPatients){
    FILE *f = NULL;

    f = fopen(filename,"r");
    if(f == NULL){
        printf("Cant open %s, or file not found! ...\n",filename);
        return;
    }

    char nextline[1024];
    int countPatients = 0; // Region count
    bool firstline = true;

    *listPatients = listCreate(10);
    while(fgets(nextline,sizeof(nextline),f)){
        if(strlen(nextline) < 1 )
            continue;

    /* As the first line of the file contains the name of the fields it should be ignored */
    if(firstline){
        firstline = false;
        continue;
    }

    char **tokens = split(nextline,11,";");

    // At this moment the tokens array is composed with the following "strings"
    //tokens[0] - ID
    //tokens[1] - Sex
    //tokens[2] - birthYear
    //tokens[3] - Country
    //tokens[4] - Region
    //tokens[5] - Infection Reason
    //tokens[6] - Infected By
    //tokens[7] - Confirmed Date
    //tokens[8] - Released Date
    //tokens[9] - Deceased Date
    //tokens[10] - Status  
    
    Patient patient = createPatient(atol(tokens[0]),tokens[1],atoi(tokens[2]),tokens[3],tokens[4],
    tokens[5],atol(tokens[6]),datebuild(tokens[7]),
    datebuild(tokens[8]),datebuild(tokens[9]),tokens[10]);
    
    free(tokens);

    int error_code = listAdd(*listPatients,countPatients,patient);
    
    if(error_code == LIST_FULL || error_code == LIST_INVALID_RANK || error_code == LIST_NO_MEMORY
    || error_code == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    countPatients++;
}
    fclose(f);
}

/**
 * @brief Importação da informação sobre regiões, presente num ficheiro, para um ADT Map
 * 
 * @param filename Nome do ficheiro (com extensão)
 * @param mapRegions Mapa de regiões que recebe os valores lidos do ficheiro
 */
void loadR(char *filename, PtMap *mapRegions){
    FILE *f = NULL;

    f = fopen(filename,"r");
    if(f == NULL){
        printf("Cant open %s, or file not found! ...\n",filename);
        return;
    }

    char nextline[1024];
    int countRegions = 0; // Region count
    bool firstline = true;

    *mapRegions = mapCreate(4);
    while(fgets(nextline,sizeof(nextline),f)){
        if(strlen(nextline) < 1 )
            continue;

    if(firstline){
        firstline = false;
        continue;
    }

    char **tokens = split(nextline,4,";");

    // At this moment the tokens array is composed with the following "strings"
    //tokens[0] - Region Name
    //tokens[1] - Capital
    //tokens[2] - Population 
    //tokens[3] - Area

    float area;
    char population[100];
    strcpy(population,tokens[3]);
    char finalPopulation[100];
    for(int i=0;i<strlen(population);i++){
        if(population[i] == ','){
            finalPopulation[i] = ' ';
        }
        else{
            finalPopulation[i] = population[i];
        }
    }
    char charFinalTrimmed[100];
    int i = 0;
    int j = 0;

    while(finalPopulation[i] != '\0'){
        if(finalPopulation[i] != ' '){
            charFinalTrimmed[j++] = finalPopulation[i];
        }
        i++;
    }

    Region region = createRegion(tokens[0],tokens[1],atof(replaceComma(tokens[2])),atoi(charFinalTrimmed));

    free(tokens);
    MapKey newKey;
    strcpy(newKey.region,region.name);
    MapValue newMapValue;
    strcpy(newMapValue.name,region.name);
    strcpy(newMapValue.capital,region.capital);
    newMapValue.area = region.area;
    newMapValue.population = region.population;
    int error_code = mapPut(*mapRegions,newKey,newMapValue);

    if(error_code == MAP_NULL || error_code == MAP_FULL || error_code == MAP_NO_MEMORY
    || error_code == MAP_EMPTY || error_code == MAP_UNKNOWN_KEY){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    countRegions++;
}
    fclose(f);
}

/**
 * @brief Este método efetua a limpeza da lista de pacientes e do mapa de regiões
 * 
 * @param listPatients Lista de pacientes que contem informação variada relativamente aos pacientes
 * @param mapRegions Mapa de regiões que recebe os valores lidos do ficheiro
 */
void clear(PtList *listPatients, PtMap *mapRegions){
    int ptMapSize = 0;
    int ptListSize = 0;

    int mapSizeErrorCode = mapSize(*mapRegions,&ptMapSize);
    int listSizeErrorCode = listSize(*listPatients,&ptListSize);

    listClear(*listPatients);
    mapClear(*mapRegions);
    printf("%d records deleted from <Patient | Regions >\n", ptListSize+ptMapSize);
}

/**
 * @brief Este método efetua a limpeza do espaço de memória que contém a informação sobre pacientes e regiões
 * 
 * @param listPatients Lista de pacientes que contem informação variada relativamente aos pacientes
 * @param mapRegions Mapa de regiões que recebe os valores lidos do ficheiro
 */
void quitCommand(PtList *listPatients, PtMap *mapRegions){
    listDestroy(listPatients);
    mapDestroy(mapRegions);
    printf("Memory Cleared\n");
}


