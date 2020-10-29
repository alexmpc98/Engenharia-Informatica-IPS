#include "structs.h"
#include "utils.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <ctype.h>
#include "list.h"
#include "listElem.h"
#include "map.h"
#include "mapElem.h"

// Metódos auxiliares/utilitários - Comandos A


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
    int len = strlen(string);
    tokens[index++] = &string[0];
    for(int i=0;i<len;i++){
        if(string[i]==delim[0]){
            string[i] = '\0';
            if(i < len - 1){
                tokens[index++] = &string[i] + 1;
            }
        }
    }
    return tokens;
}

/**
 * @brief Tratamento de string para Data
 * 
 * @param dateToken String que contem uma data que posteriormente é convertida para o tipo date
 * @return Date
 */
Date datebuild(char *dateToken){
    Date newDate;
    int day,month,year;
    if(strlen(dateToken) == 0){
        newDate = createDate(0, 0, 0);
    }
    else{
        sscanf(dateToken,"%d/%d/%d",&day,&month,&year);
        newDate.day = day;
        newDate.month = month;
        newDate.year = year;
    }
    return newDate;
}


/**
 * @brief Troca de virgula para ponto (requisito para string to double)
 * 
 * @param strToken String que conterá o tratamento de troca de caracteres.
 * @return String (com ponteiro)
 */
char * replaceComma(char * strToken){
    char *current_position = strchr(strToken,',');
    while(current_position){
        *current_position = '.';
        current_position = strchr(current_position,',');
    }
    return strToken;
}


// Metódos auxiliares/utilitários - Comandos B

/**
 * @brief Obtenção da data atual do sistema
 * 
 * @return Date
 */
Date getCurrentDate(){
    struct tm* current_time;
    time_t s;
    Date currentDate;
    s = time(NULL);
    current_time = localtime(&s);
    currentDate.year = current_time -> tm_year + 1900;
    currentDate.month = current_time -> tm_mon;
    currentDate.day = current_time -> tm_mday;
    return currentDate;
}

/**
 * @brief Verificação de quantos dias um mês especifico, num ano especifico, possui.
 * 
 * @param year Ano da data que se deseja ver quantos dias o mes tem (por causa de ser diferente em anos bissextos)
 * @param month Mês que o utilizador deseja ver a quantidade de dias que esse possui
 * @return Integer
 */
int daysInMonth(int year, int month){
    int leap = leapYear(year);
    switch(month){
        case 1:
            return 31;
        case 2:
            if(leap == 1)
                return 29;
            else
                return 28;
        case 3:
            return 31;
        case 4:
            return 30;
        case 5:
            return 31;
        case 6:
            return 30;
        case 7:
            return 31;
        case 8:
            return 31;
        case 9:
            return 30;
        case 10:
            return 31;
        case 11:
            return 30;
        case 12:
            return 31;
    }
}


/**
 * @brief Método que retorna a diferença de dias entre duas datas
 * 
 * @param date1 Data inserida pelo utilizador para comparação
 * @param date2 Data inserida pelo utilizador para comparação
 * @return Integer
 */
int DateDiff(Date date1, Date date2){
    int days = 0;
    if(date1.day == 0 || date2.day == 0){
        return 0;
    }
    if(date1.year == date2.year){
        if(date1.month == date2.month){
            if(date1.day < date2.day){
                days = date2.day - date1.day;
                return days;
            }
            else{
                days = date1.day - date2.day;
                return days;
            }
        }
        else if(date1.month != date2.month){
            for(int i=date1.month; i<=date2.month; i++){
               if(i == date1.month){
                   days += daysInMonth(date1.year,i) - date1.day;
               }
               else if(i == date2.month){
                   days += date2.day;
               }
               else{
                   days += daysInMonth(date1.year,i);
               }
            }
            return days;
        }
    } 
    else{
        for(int i=date1.year;i<=date2.year;i++){
            if(i == date1.year){
                for(int j=date1.month;j<=12;j++){
                    if(j == date1.month){
                        days += daysInMonth(date1.year,j) - date1.day;
                    }
                    else{
                        days += daysInMonth(date1.year,j);
                    }
                }
            }
            else if(i == date2.year){
                for(int k=1;k<=date2.month;k++){
                    if(k == date2.month){
                        days += date2.day;
                    }
                    else{
                        days += daysInMonth(date2.year,k);
                    }
                }
            }
            else{
                if(leapYear(i) == 1){
                    days += 366;
                }
                else{
                    days += 365;
                }
            }
        }        
    }
    return days;
}

/**
 * @brief Método para validar se o ano é bissexto ou não
 * 
 * @param year Ano inserido pelo utilizador para validação
 * @return Integer
 */
int leapYear(int year){
    if(year % 400 == 0){
        return 1;
    }
    else if (year % 100 == 0){
        return 0;
    }
    else if(year % 4 == 0){
        return 1;
    }
    else{
        return 0;
    }
}

/**
 * @brief Metodo auxiliar para a criação da matriz de dados
 * 
 * @param firstAge Primeira idade da faixa etária a pesquisar
 * @param lastAge Ultima idade da faixa etária a pesquisar
 * @param listPatients Lista de pacientes que contem a informação necessária
 * @param status O estado em que o paciente se encontra
 * @return Integer
 */
int statistics(int firstAge, int lastAge, PtList *listPatients, char * status){
    Patient patient;
    int ptSize = 0;
    listSize(*listPatients,&ptSize);
    int counter = 0;
    if(firstAge < 0 || lastAge < 0 ){
        return -1;
    }
    else if(firstAge > lastAge && lastAge != 0){
        return -1;
    }
    for(int i=0;i<ptSize;i++){
        listGet(*listPatients,i,&patient);
        if(lastAge !=0){
            if(getCurrentDate().year - patient.birthYear >= firstAge && getCurrentDate().year - patient.birthYear <= lastAge
            && strcmp(patient.status,status) > 0){
                counter = counter + 1;
            }
        }
        else if(lastAge == 0){
            if(getCurrentDate().year - patient.birthYear >= firstAge && strcmp(patient.status,status) > 0){
                counter = counter + 1;
            }
        }
    } 
    return counter;
}

/**
 * @brief Método que retorna a maior data de confirmação, auxiliando o processo de pacientes que ainda se encontram isolados e não tem data de release (considera-se a data de confirmação do ultimo infetado)
 * 
 * @param listPatients Lista de pacientes que contem a informação necessária
 * @return Date
 */
Date biggestDate(PtList *listPatients){
    int size = 0;
    listSize(*listPatients,&size);
    Patient patient;
    Patient bGate;
    Patient temp; 
    for(int i=0;i<size-1;i++){
        for(int j=i+1;j<size;j++){
            listGet(*listPatients,i,&patient);
            listGet(*listPatients,j,&bGate);
            if(patient.confirmedDate.day > 0 && bGate.confirmedDate.day > 0){
                if(patient.confirmedDate.year <= bGate.confirmedDate.year){
                    if(patient.confirmedDate.month < bGate.confirmedDate.month){
                        listSet(*listPatients,i,temp,&patient);
                        listSet(*listPatients,j,patient,&bGate);
                        listSet(*listPatients,i,bGate,&temp);
                    }
                    else if(patient.confirmedDate.month == bGate.confirmedDate.month){
                        if(patient.confirmedDate.day < bGate.confirmedDate.day){
                            listSet(*listPatients,i,temp,&patient);
                            listSet(*listPatients,j,patient,&bGate);
                            listSet(*listPatients,i,bGate,&temp);
                        }
                    }
                    }      
                }
            }
        }
    Patient patientDate;
    listGet(*listPatients,0,&patientDate);
    return patientDate.confirmedDate;
}

/**
 * @brief Metodo de validação que verifica se o paciente inserido pelo utilizador, encontra-se isolado (ou em isolamento).
 * 
 * @param patient Paciente que é verificado
 * @return Boolean
 */
bool isIso(Patient patient){
    if(strncmp(patient.status,"isolated",8) == 0){
        return true;
    }
    else{
        return false;
    }
}

/**
 * @brief Metodo de validação que verifica se o paciente inserido pelo utilizador, se encontra livre do vírus.
 * 
 * @param patient Paciente que é verificado
 * @return Boolean
 */
bool isReleased(Patient patient){
    if(strncmp(patient.status,"released",8) == 0){
        return true;
    }
    else{
        return false;
    }
}


/**
 * @brief Metodo de validação, que verifica se o paciente inserido pelo utilizador, se encontra falecido
 * 
 * @param Patient Paciente que é verificado
 * @return Boolean
 */
bool isDeceased(Patient patient){
    if(strncmp(patient.status,"deceased",8) == 0){
        return true;
    }
    else{
        return false;
    }
}