#include "utils.h"
#include "structs.h"
#include "list.h"
#include "listElem.h"
#include "comandosb.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>


/**
 * @brief Este método efetua uma separação da lista de pacientes, nos estados deceased (morto), isolated (isolado/infectado) e released (libertado), calculando em simultaneo, a idade média entre cada estado
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 */
void average(PtList *listPatients){
    Patient patient;
    int year = getCurrentDate().year;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    double sumDeceasedPatients = 0.0;
    double sumReleasedPatients = 0.0;
    double sumIsolatedPatients = 0.0;
    int counterIsolated = 0;
    int counterDeceased = 0;
    int counterReleased = 0;
    for(int i = 0; i < ptSize; i++){
        listGet(*listPatients, i, &patient);
            if(strncmp(patient.status,"isolated",8) == 0 && patient.birthYear > 0){
                sumIsolatedPatients = sumIsolatedPatients + ((double)year - (double)patient.birthYear);
                counterIsolated++;
            }
            else if(strncmp(patient.status,"deceased",8) == 0 && patient.birthYear > 0){
                sumDeceasedPatients = sumDeceasedPatients + ((double)year - (double)patient.birthYear);
                counterDeceased++;
            }
            if(strncmp(patient.status,"released",8) == 0 && patient.birthYear > 0){
                sumReleasedPatients = sumReleasedPatients + ((double)year - (double)patient.birthYear);
                counterReleased++;
            }
    }
    printf(" Average Age for deceased patients: %2.f\n",(double)sumDeceasedPatients/counterDeceased);
    printf(" Average Age for released patients: %2.f\n",(double)sumReleasedPatients/counterReleased);
    printf(" Average Age for isolated patients: %2.f\n",(double)sumIsolatedPatients/counterIsolated);
}


/**
 * @brief Com recurso a recursividade, este método cria uma "arvore" de infecção a partir de um paciente. Ou seja, quem o infetou, e quem infetou o causador da infeção do paciente procurado, etc.
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 * @param id Número identificador do paciente que o utilizador deseja procurar, ou iniciar a "arvore"
 * @param bol Este valor booleano, não é manipulado pelo utilizador. Serve apenas para indicar ao programa, se a impressão é da "raiz" (paciente procurado), ou se já foi impresso, e é necessário passar ao resto da árvore
 */
void follow(PtList *listPatients,long int id,bool bol){
    Patient patient;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    long int infectedBy = 0;
    for(int i=0; i < ptSize; i++){
        listGet(*listPatients, i, &patient);
            if(patient.id == id){
                if(!bol){
                printf("Following Patient: ID: %ld, SEX: %s, AGE: %d, COUNTRY/REGION: %s/%s, STATE: %s"
                ,patient.id,patient.sex,getCurrentDate().year-patient.birthYear,patient.country,patient.region,patient.status);
                }
                else{
                    printf("contamined by Patient: ID: %ld, SEX: %s, AGE: %d, COUNTRY/REGION: %s/%s, STATE: %s"
                    ,patient.id,patient.sex,getCurrentDate().year-patient.birthYear,patient.country,patient.region,patient.status);
                }
                if(patient.infectedBy > 0){
                    infectedBy = patient.infectedBy;      
                    follow(listPatients,infectedBy,true);  
                    break;       
                }
                else{
                    printf("contaminated by Unknown\n");
                }       
            }
            else if(i == ptSize - 1){
                printf("contaminated by Patient : ID:%ld : does not exist record \n",id);
                break;
            }
    }
}

/**
 * @brief Este método serve para imprimir as percentagens dos diferentes sexos/generos que existem na lista de pacientes 
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 */
void sex(PtList *listPatients){
    Patient patient;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    int counterFemales = 0;
    int counterMales = 0;
    int counterUnknown = 0;
    for(int i=0;i < ptSize; i++){
        listGet(*listPatients, i, &patient);
        if(strcmp(patient.sex,"male") == 0){
            counterMales = counterMales + 1;
        }
        else if(strcmp(patient.sex,"female") == 0){
            counterFemales = counterFemales + 1;
        }
        else{
            counterUnknown = counterUnknown + 1;
        }
    }
    double percentageMale,percentageFemale,percentageUnknown = 0.0;
    
    percentageMale = (double)counterMales/ptSize * 100;
    percentageFemale = (double)counterFemales/ptSize * 100;
    percentageUnknown = (double)counterUnknown/ptSize * 100;

    printf("Percentage of Females: %2.f %% \n",percentageFemale);
    printf("Percentage of Males: %2.f %% \n",percentageMale);
    printf("Percentage of Unknown:%2.f %% \n",percentageUnknown);
    printf("Total of patients: %d \n",ptSize);
}


/**
 * @brief Este método serve unicamente para imprimir informação relativa ao paciente introduzido pelo utilizado. A impressão contém diversos fatores, como a idade, o número de dias que está infectado, etc.
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 * @param id Número identificador do paciente que o utilizador deseja procurar, ou iniciar a "arvore"
 */
void show(PtList *listPatients, long int id){
    Patient patient;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    for(int i=0;i<ptSize; i++){
        listGet(*listPatients,i,&patient);
        if(patient.id == id){
            if(patient.confirmedDate.day > 0){
                if(isIso(patient)){
                    Date bDate = biggestDate(listPatients);
                    int daysDiff = DateDiff(patient.confirmedDate,bDate);
                    if(patient.birthYear > 0){
                        printf("ID: %ld \nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                        patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);
                        break;
                    }
                    else{
                        printf("ID: %ld \nSEX: %s \nAGE: UNKNOWN \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                        patient.id,patient.sex,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);   
                        break;
                    } 
                }
                else{
                    if(isReleased(patient)){
                        int daysDiff = DateDiff(patient.confirmedDate,patient.releasedDate);
                        if(patient.birthYear > 0){
                            printf("ID: %ld \nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                            patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);
                            break;
                        }
                        else{
                            printf("ID: %ld \nSEX: %s \nAGE: UKNOWN \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                            patient.id,patient.sex,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);
                            break;
                        }
                    }
                    else{
                        if(isDeceased(patient)){
                            int daysDiff = DateDiff(patient.confirmedDate,patient.releasedDate);
                            if(patient.birthYear > 0){
                                printf("ID: %ld \nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                                patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);
                                break;
                            }
                            else{
                                printf("ID: %ld \nSEX: %s \nAGE: UKNOWN \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: %d DAYS \n",
                                patient.id,patient.sex,patient.country,patient.region,patient.infectionReason,patient.status,daysDiff);
                                break;
                            }

                        }
                    }
                }  
                            
            }
            else{
                if(patient.birthYear > 0){
                    printf("ID: %ld \nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: UNKNOWN DAYS \n",
                    patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,patient.infectionReason,patient.status);
                    break;
                }
                else{
                    printf("ID: %ld \nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s/%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILLNESS: UNKNOWN DAYS \n",
                    patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,patient.infectionReason,patient.status);
                    break;
                }
            }
        }
    }
}

/**
 * @brief Com este método, é possivel observar um ranking (top 5) dos pacientes que estão infectado à mais tempo
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 */
void topFive(PtList *listPatients){
    Patient patient;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }

    Patient maxPatient;
    Patient regularPatient;
    Patient temporaryPt;
    for(int i=0; i<ptSize-1;i++){
        for(int j=i+1;j<ptSize;j++){
            listGet(*listPatients,i,&maxPatient);
            listGet(*listPatients,j,&regularPatient);
            if(DateDiff(regularPatient.confirmedDate,regularPatient.releasedDate) > DateDiff(maxPatient.confirmedDate,maxPatient.releasedDate)
            && regularPatient.confirmedDate.day != 0 && regularPatient.releasedDate.day != 0 && maxPatient.confirmedDate.day != 0 &&
            maxPatient.releasedDate.day != 0){
                listSet(*listPatients,i,temporaryPt,&regularPatient);
                listSet(*listPatients,j,regularPatient,&maxPatient);
                listSet(*listPatients,i,maxPatient,&temporaryPt);       
            }
        }
    }
    Patient oldest;
    Patient ageDiffTemp;
    Patient ageTemp;
    for(int i=0;i<4;i++){
        for(int j=i+1;j<5;j++){
            listGet(*listPatients,i,&oldest);
            listGet(*listPatients,j,&ageDiffTemp);
            if(getCurrentDate().year - ageDiffTemp.birthYear > getCurrentDate().year - oldest.birthYear && 
            DateDiff(ageDiffTemp.confirmedDate,ageDiffTemp.releasedDate) >= DateDiff(oldest.confirmedDate,oldest.releasedDate)){
                listSet(*listPatients,i,ageTemp,&ageDiffTemp);
                listSet(*listPatients,j,ageDiffTemp,&oldest);
                listSet(*listPatients,i,oldest,&ageTemp);
            }          
        }
    }
    for(int i=0;i<5;i++){
        listGet(*listPatients,i,&patient);
        if(patient.birthYear <= 0){
            printf("ID - %ld\nSEX: %s \nAGE: UNKNOWN \nCOUNTRY/REGION: %s /%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILNESS: %d \n\n",
            patient.id,patient.sex,patient.country,patient.region,
            patient.infectionReason,patient.status,DateDiff(patient.confirmedDate,patient.releasedDate));
        }
        else{
            printf("ID - %ld\nSEX: %s \nAGE: %d \nCOUNTRY/REGION: %s /%s \nINFECTION REASON: %s \nSTATE: %sNUMBER OF DAYS WITH ILNESS: %d \n\n",
            patient.id,patient.sex,getCurrentDate().year - patient.birthYear,patient.country,patient.region,
            patient.infectionReason,patient.status,DateDiff(patient.confirmedDate,patient.releasedDate));
        }
    }
}

/**
 * @brief Este método, serve para obter os pacientes mais velhos, separados pelo sexo/genero, ou seja, o paciente masculino mais velho e o paciente feminino mais velho
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 */
void oldest(PtList *listPatients){
    Patient patient;
    Patient oldestMale;
    Patient oldestFemale;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;
    }
    for(int j=0;j<ptSize;j++){
        listGet(*listPatients,j,&oldestMale);
        if(strcmp(oldestMale.sex,"male") == 0){
            break;
        }
    }
    for(int k=0;k<ptSize;k++){
        listGet(*listPatients,k,&oldestFemale);
        if(strcmp(oldestFemale.sex,"female") == 0){
            break;
        }
    }
    for(int i=0;i<ptSize;i++){
        listGet(*listPatients,i,&patient);
        if(strcmp(patient.sex,"male") == 0 && patient.birthYear < oldestMale.birthYear && patient.birthYear > 0){
            oldestMale = patient;
        }
        if(strcmp(patient.sex,"female") == 0 && patient.birthYear < oldestFemale.birthYear && patient.birthYear > 0){
            oldestFemale = patient;
        }
    }
    int oldestFemaleCounter = 0;
    int oldestMaleCounter = 0;
    printf("Female:\n");
    for(int index=0;index<ptSize;index++){
        listGet(*listPatients,index,&patient);
        if(patient.birthYear == oldestFemale.birthYear && strcmp(patient.sex,"female") == 0){
            oldestFemaleCounter++;
            printf("%d Female : ID:%ld , SEX: %s , AGE: %d , COUNTRY/REGION: %s/%s, STATE: %s",
            oldestFemaleCounter,patient.id,patient.sex,getCurrentDate().year - patient.birthYear,
            patient.country,patient.region,patient.status);
        }
    }
    printf("Male:\n");
    for(int l=0;l<ptSize; l++){
        listGet(*listPatients,l,&patient);
        if(patient.birthYear == oldestMale.birthYear && strcmp(patient.sex,"male") == 0){
            oldestMaleCounter++;
            printf("%d - ID:%ld , SEX: %s , AGE: %d , COUNTRY/REGION: %s/%s, STATE: %s",
            oldestMaleCounter,patient.id,patient.sex,getCurrentDate().year - patient.birthYear,
            patient.country,patient.region,patient.status);
        }
    }
}


/**
 * @brief Este método imprime o numero de infetados no dia inserido, e compara através de taxas (rate), com o dia anterior ao inserido 
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 * @param date Data inserida pelo utilizador, para a contagem de infetados
 */
void growth(PtList *listPatients, Date date){
    Patient patient;
    int ptSize = 0;
    int sizeErrorCode = listSize(*listPatients,&ptSize);
    if(sizeErrorCode == LIST_FULL || sizeErrorCode == LIST_INVALID_RANK || sizeErrorCode == LIST_NO_MEMORY
    || sizeErrorCode == LIST_NULL){
        printf("An error ocurred... Please try again...\n");
        return;    
    }
    int deceasedCounterDayBefore = 0;
    int infectedCounterDayBefore = 0;
    int deceasedInDateCounter = 0;
    int infectedInDateCounter = 0;
    int dayBefore = 0;
    int monthBefore = 0;
    int yearBefore = 0;
    if(date.day == 1 && date.month == 1){
        yearBefore = date.year-1;
        dayBefore = daysInMonth(yearBefore,12);
    }
    else if(date.day == 1){
        monthBefore = date.month - 1;
        dayBefore = daysInMonth(date.year,monthBefore);
    }
    else{
        dayBefore = date.day-1;
        monthBefore = date.month;
        yearBefore = date.year;
    }
    for(int i=0;i<ptSize;i++){
        listGet(*listPatients,i,&patient); 
        if(dayBefore == patient.deceasedDate.day && monthBefore == patient.deceasedDate.month && yearBefore == patient.deceasedDate.year){
            deceasedCounterDayBefore += 1;
        }
        if(patient.confirmedDate.day == dayBefore && patient.confirmedDate.month == monthBefore && patient.confirmedDate.year == yearBefore){
            infectedCounterDayBefore++;
        }
        if(date.day == patient.deceasedDate.day && date.month == patient.deceasedDate.month && date.year == patient.deceasedDate.year){
            deceasedInDateCounter ++;
        }
        if(patient.confirmedDate.day == date.day && patient.confirmedDate.month == date.month && patient.confirmedDate.year == date.year){
            infectedInDateCounter++;
        }
    }
    double rateOfNewInfected = (((double)infectedInDateCounter - (double)infectedCounterDayBefore) /  (double)infectedCounterDayBefore) * 100;
    double rateOfNewDeceased = (((double)deceasedInDateCounter - (double)deceasedCounterDayBefore) /  (double)deceasedCounterDayBefore) * 100;  
    printf("Date: <%d/%d/%d> \n",dayBefore,monthBefore,yearBefore);
    if(infectedCounterDayBefore > 0){
        printf("Number of deads: %d;\nNumber of infected: %d \n\n",deceasedCounterDayBefore,infectedCounterDayBefore);
    }  
    else{
        printf("There is no record for day Date: <%d/%d/%d> \n",dayBefore,monthBefore,yearBefore);
    }
    printf("Date: <%d/%d/%d> \n",date.day,date.month,date.year);
    if(infectedInDateCounter > 0){
        printf("Number of deads: %d;\nNumber of infected: %d \n\n",deceasedInDateCounter,infectedInDateCounter); 
    }
    else{
        printf("There is no record for day Date: <%d/%d/%d> \n",date.day,date.month,date.year);
    }
    printf("Rate of new infected: %2.f %%  \n",rateOfNewInfected);
    printf("Rate of new infected: %2.f %%  \n",rateOfNewDeceased);
}

/**
 * @brief Este método imprime informação estatistica, disposta numa matriz de 3x3
 * 
 * @param listPatients Lista de pacientes que contem os valores do ficheiro, prontos a serem manipulados
 */
void matrix(PtList *listPatients){
    int isolatedNumbers[6] = {statistics(0, 15,listPatients,"isolated"), statistics(16, 30,listPatients,"isolated"),
    statistics(31, 45,listPatients,"isolated"), statistics(46, 60,listPatients,"isolated"), statistics(61, 75,listPatients,"isolated"),
    statistics(76, 0,listPatients,"isolated")};
    int deceasedNumbers[6] = {statistics(0, 15,listPatients,"deceased"), statistics(16, 30,listPatients,"deceased"),
    statistics(31, 45,listPatients,"deceased"), statistics(46, 60,listPatients,"deceased"), statistics(61, 75,listPatients,"deceased"),
    statistics(76, 0,listPatients,"deceased")};
    int releasedNumbers[6] = {statistics(0, 15,listPatients,"released"), statistics(16, 30,listPatients,"released"),
    statistics(31, 45,listPatients,"released"), statistics(46, 60,listPatients,"released"), statistics(61, 75,listPatients,"released"),
    statistics(76, 0,listPatients,"released")};

    char* headers1[3] = {"Isolated", "Deceased", "Released"};
    char* headers2[6] = {"[0-15]", "[16-30]", "[31-45]", "[46-60]", "[61-75]", "[76...]"};
    
    printf("            %s | %s | %s \n", headers1[0],headers1[1],headers1[2]);
    for(int i=0;i<sizeof(isolatedNumbers)/sizeof(isolatedNumbers[0]);i++){
        printf("%s -        %d |    %d    | %d \n",headers2[i],isolatedNumbers[i], deceasedNumbers[i],releasedNumbers[i]);
    }
}







