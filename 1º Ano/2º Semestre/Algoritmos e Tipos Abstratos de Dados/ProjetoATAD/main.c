/* PROJETO  ATAD 2019-20
* Identificacao dos Alunos:
*
*      Numero: 190221093 | Nome: Alexandre Coelho
*      Numero: 190221128 | Nome: Sérgio Veríssimo
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#include "structs.h"
#include "mapElem.h"
#include "map.h"
#include "list.h"
#include "listElem.h"
#include "comandosa.h"
#include "comandosb.h"
#include "comandosc.h"
#include "utils.h"


/* definicao de prototipos de funcoes, definidas depois do main() */
int equalsStringIgnoreCase(char str1[], char str2[]);
void printCommandsMenu();
//...

/*
* Descrição do Programa
*/
int main(int argc, char** argv) {

	/* declaracao de variaveis */
	PtList listPatientsCSV = NULL; 
	PtMap mapPatientsCSV = NULL;


	/* interpretador de comandos */
	String command;
	int quit = 0;

	setlocale(LC_ALL, "PT");
	while (!quit) {
		
		printCommandsMenu();
		fgets(command, sizeof(command), stdin);
		/* descartar 'newline'. Utilizar esta técnica sempre que for lida uma
		* string para ser utilizada, e.g., nome de ficheiro, chave, etc.. */
		command[strlen(command) - 1] = '\0';

		if (equalsStringIgnoreCase(command, "QUIT")) {
			quitCommand(&listPatientsCSV, &mapPatientsCSV);
			quit = 1; /* vai provocar a saída do interpretador */				
		}
		else if (equalsStringIgnoreCase(command, "LOADP")) {
			if(listIsEmpty(listPatientsCSV)){
				loadP("patients.csv",&listPatientsCSV);
				listPrint(listPatientsCSV);
				printf("Success!");
			}
			else{
				printf("The Patients list already contains values. Try the CLEAR command to delete the values.");
			}
		}
		else if (equalsStringIgnoreCase(command, "LOADR")) {
			if(mapIsEmpty(mapPatientsCSV)){
				loadR("regions.csv",&mapPatientsCSV);	
				mapPrint(mapPatientsCSV);
				printf("Success!");
			}
			else{
				printf("The Regions map already contains values. Try the CLEAR command to delete the values.");
			}
		}
		else if (equalsStringIgnoreCase(command, "CLEAR")) {
			clear(&listPatientsCSV, &mapPatientsCSV);
		}
		else if (equalsStringIgnoreCase(command, "AVERAGE")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.");
			}
			else{
				average(&listPatientsCSV);
			}
		}
		else if (equalsStringIgnoreCase(command, "FOLLOW")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				long int id;
				printf("\nInsert the id you desire to search : ");
				scanf("%ld",&id);
				follow(&listPatientsCSV,id,false);
			}    
		}
		else if (equalsStringIgnoreCase(command, "SEX")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				sex(&listPatientsCSV);
			}  
		}
		else if (equalsStringIgnoreCase(command, "SHOW")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				long int id;
				scanf("Insert the id you desire to search: %ld",&id);
				show(&listPatientsCSV,id);
			} 
		}
		else if (equalsStringIgnoreCase(command, "TOP5")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				topFive(&listPatientsCSV);
			}
		}
		else if (equalsStringIgnoreCase(command, "OLDEST")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				oldest(&listPatientsCSV);
			}
		}
		else if (equalsStringIgnoreCase(command, "GROWTH")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				int day = 0, month = 0, year = 0;
				printf("Insert the DAY of the date to search: ");
				scanf("%d",&day);
				printf("\nInsert the MONTH of the date to search: ");
				scanf("%d",&month);
				printf("\nInsert the YEAR of the date to search: ");
				scanf("%d",&year);
				Date dateToSearch = createDate(day,month,year);
    			growth(&listPatientsCSV,dateToSearch);
			}
		}	
		else if (equalsStringIgnoreCase(command, "MATRIX")) {
			if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				matrix(&listPatientsCSV);
			}
		}
		else if (equalsStringIgnoreCase(command, "REGIONS")) {
			if(mapIsEmpty(mapPatientsCSV) || mapPatientsCSV == NULL){
				printf("The Region map doesn't contain values. Try the LOADR command to load information to the patient list.\n");
			}
			else if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				regions(&mapPatientsCSV, &listPatientsCSV);
			}
		}	
		else if (equalsStringIgnoreCase(command, "REPORT")) {
			if(mapIsEmpty(mapPatientsCSV) || mapPatientsCSV == NULL){
				printf("The Region map doesn't contain values. Try the LOADR command to load information to the patient list.\n");
			}
			else if(listIsEmpty(listPatientsCSV) || listPatientsCSV == NULL){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.\n");
			}
			else{
				report(&mapPatientsCSV, &listPatientsCSV);
			}			
		}
		else {
			printf("%s : Comando não encontrado.\n", command);
		}
	}

	/* libertar memória e apresentar mensagem de saída. */

	return (EXIT_SUCCESS);
}


/**
 * @brief Método compara dois arrays de char, ignorando o case type.
 * 
 * @param str1[] Char
 * @param str2[] Char
 */
int equalsStringIgnoreCase(char str1[], char str2[]) {
	/* Apenas faz uma comparacao utilizando o strcmp.
	* Necessita de modificacao para obter uma comparacao
	* 'case insensitive' */
	return (strcmp(str1, str2) == 0);
}


/**
 * @brief Método que mostra o menu no ecrã.
 * 
 */
void printCommandsMenu() {
	printf("\n===================================================================================");
	printf("\n                          	PROJECT: COVID-19                    ");
	printf("\n===================================================================================");
	printf("\nA. Base Commands (LOADP , LOADR , CLEAR).");
	printf("\nB. Simple Indicators and searchs (AVERAGE, FOLLOW, MATRIX, OLDEST, SEX, SHOW, TOP5).");
	printf("\nC. Advanced indicator (REGIONS, REPORT)");
	printf("\nD. Exit (QUIT)\n\n");
	printf("COMMAND> ");
}
