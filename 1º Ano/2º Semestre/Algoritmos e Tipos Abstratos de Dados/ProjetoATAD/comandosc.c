#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include "structs.h"
#include "list.h"
#include "listElem.h"
#include "map.h"
#include "mapElem.h"
#include "comandosc.h"
#include "utils.h"

/**
 * @brief Método que mostra a lista de regiões por ordem alfabética, que tem pessoas ainda doentes.
 * 
 * @param *mapRegions PtMap
 * @param *listPatients PtList
 */
void regions(PtMap *mapRegions, PtList *listPatients)
{
    int size = 0;
    int mapSizeErrorCode = mapSize(*mapRegions, &size);

    Region tempReg;
    Patient patient;
    MapKey *mapKeyArray = mapKeys(*mapRegions);

    int ptListSize = 0;
    int listSizeErrorCode = listSize(*listPatients, &ptListSize);

    Region regionArray[size];

    for (int i = 0; i < size; i++)
    {
        mapGet(*mapRegions, mapKeyArray[i], &tempReg);
        for (int j = 0; j < ptListSize; j++)
        {
            listGet(*listPatients, j, &patient);
            if (strcmp(patient.region, mapKeyArray[i].region) == 0 && strlen(patient.region) > 0)
            {
                if (isIso(patient))
                {
                    regionArray[i] = tempReg;
                }
            }
        }
    }

    // Sorting using bubble sort
    for (int j = 0; j < size - 1; j++)
    {
        for (int i = j + 1; i < size; i++)
        {
            if (strcmp(regionArray[j].name, regionArray[i].name) > 0)
            {
                tempReg = regionArray[j];
                regionArray[j] = regionArray[i];
                regionArray[i] = tempReg;
            }
        }
    }

    for (int i = 0; i < size - 1; i++)
    {
        printRegion(regionArray[i]);
    }

    free(mapKeyArray);
}

/**
 * @brief Método que escreve no ficheiro report.txt, a mortalidade e a taxa de incidência total e por região.
 * 
 * @param *mapRegions PtMap
 * @param *listPatients PtList
 */
void report(PtMap *mapRegions, PtList *listPatients)
{
    remove("report.txt");
    FILE *fp = fopen("report.txt", "a");

    int size = 0;
    int mapSizeErrorCode = mapSize(*mapRegions, &size);

    Region tempReg;
    Patient patient, patientC;
    MapKey *mapKeyArray = mapKeys(*mapRegions);

    int ptListSize = 0;
    int listSizeErrorCode = listSize(*listPatients, &ptListSize);

    Region regionArray[size];

    int deaths = 0;
    int cases = 0;
    int population = 0;
    int infected = 0;
    int write_country = 0;
    int write_region = 0;
    int totalInfected = 0;
    int totalCases = 0;
    int totalDeaths = 0;
    int totalPopulation = 0;

    float lethality = 0;
    float mortality = 0;
    float incidentRate = 0;

    listGet(*listPatients, 0, &patientC);
    for (int i = 0; i < size; i++)
    {
        deaths = 0;
        cases = 0;
        infected = 0;
        mapGet(*mapRegions, mapKeyArray[i], &tempReg);
        population = tempReg.population;
        for (int j = 0; j < ptListSize; j++)
        {
            listGet(*listPatients, j, &patient);
            if (strcmp(patient.region, mapKeyArray[i].region) == 0 && strlen(patient.region) > 0)
            {
                if (isDeceased(patient))
                {
                    deaths++;
                    cases++;
                }
                else if (isIso(patient))
                {
                    infected++;
                    cases++;
                }
            }
        }
        totalInfected += infected;
        totalCases += cases;
        totalDeaths += deaths;
        totalPopulation += population;
    }

    lethality = ((float)totalDeaths / (float)totalCases) * 100;
    mortality = ((float)totalDeaths / (float)totalPopulation) * 10000;
    incidentRate = ((float)totalInfected / (float)totalPopulation) * 100;
    write_country = fprintf(fp, "%s  Mortality: %0.2f%c  Incident Rate: %0.2f%c  Lethality: %0.2f%c\n", patientC.country, mortality, '%', incidentRate, '%', lethality, '%');
    for (int i = 0; i < size; i++)
    {
        deaths = 0;
        cases = 0;
        infected = 0;
        mapGet(*mapRegions, mapKeyArray[i], &tempReg);
        population = tempReg.population;
        for (int j = 0; j < ptListSize; j++)
        {
            listGet(*listPatients, j, &patient);
            if (strcmp(patient.region, mapKeyArray[i].region) == 0 && strlen(patient.region) > 0)
            {
                if (isDeceased(patient))
                {
                    deaths++;
                    cases++;
                }
                else if (isIso(patient))
                {
                    infected++;
                    cases++;
                }
            }
        }
        if (cases > 0 || deaths > 0 || infected > 0)
        {
            lethality = ((float)deaths / (float)cases) * 100;
            mortality = ((float)deaths / (float)population) * 10000;
            incidentRate = ((float)infected / (float)population) * 100;
            write_region = fprintf(fp, "%s  Mortality: %0.2f%c  Incident Rate: %0.2f%c  Lethality: %0.2f%c\n", mapKeyArray[i].region, mortality, '%', incidentRate, '%', lethality, '%');
        }
        else
        {
            write_region = fprintf(fp, "%s  unknown (no population data)\n", mapKeyArray[i].region);
        }
    }
    fclose(fp);
    if (write_country > 0 && write_region > 0)
    {
        printf("Report created\n");
    }
    else
    {
        printf("Report not created\n");
        remove("report.txt");
    }
    free(mapKeyArray);
}
