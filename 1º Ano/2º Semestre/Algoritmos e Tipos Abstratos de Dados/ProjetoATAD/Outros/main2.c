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

typedef char String[255];

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
			quit = 1; /* vai provocar a saída do interpretador */				
		}
		else if (equalsStringIgnoreCase(command, "LOADP")) {
			listPatientsCSV = NULL; 
			if(listIsEmpty(listPatientsCSV)){
				loadP("patients.csv",&listPatientsCSV);
				listPrint(&listPatientsCSV);
				printf("Success!");
			}
			else{
				printf("The Patients list already contains values. Try the CLEAR command to delete the values.");
			}
		}
		else if (equalsStringIgnoreCase(command, "LOADR")) {
			if(mapIsEmpty(mapPatientsCSV)){
				loadR("regions.csv",&mapPatientsCSV);	
				mapPrint(&mapPatientsCSV);
				printf("Success!");
			}
			else{
				printf("The Regions map already contains values. Try the CLEAR command to delete the values.");
			}
		}
		else if (equalsStringIgnoreCase(command, "CLEAR")) {
			printf("Comando CLEAR nao implementado.\n");	
		}
		else if (equalsStringIgnoreCase(command, "AVERAGE")) {
			if(listIsEmpty(listPatientsCSV)){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.");
			}
			else{
				average(&listPatientsCSV);
			}
		}
		else if (equalsStringIgnoreCase(command, "FOLLOW")) {
			if(listIsEmpty(listPatientsCSV)){
				printf("The Patients list doesn't contain values. Try the LOADP command to load information to the patient list.");
			}
			else{
				long int id;
				scanf("%ld",&id);
				follow(&listPatientsCSV,id,false);
			}    
		}
		else if (equalsStringIgnoreCase(command, "SEX")) {
			printf("Comando SEX nao implementado.\n");
		}
		else if (equalsStringIgnoreCase(command, "SHOW")) {
			printf("Comando SHOW nao implementado.\n");
		}
		else if (equalsStringIgnoreCase(command, "TOP5")) {
			printf("Comando TOP5 nao implementado.\n");
		}
		else if (equalsStringIgnoreCase(command, "OLDEST")) {
			printf("Comando OLDEST nao implementado.\n");
		}
		else if (equalsStringIgnoreCase(command, "GROWTH")) {
			printf("Comando GROWTH nao implementado.\n");
		}	
		else if (equalsStringIgnoreCase(command, "MATRIX")) {
			printf("Comando MATRIX nao implementado.\n");
		}
		else if (equalsStringIgnoreCase(command, "REGIONS")) {
			printf("Comando REGIONS nao implementado.\n");
		}	
		else if (equalsStringIgnoreCase(command, "REPORT")) {
			printf("Comando REPORT nao implementado.\n");
		}
		else {
			printf("%s : Comando não encontrado.\n", command);
		}
	}

	/* libertar memória e apresentar mensagem de saída. */

	return (EXIT_SUCCESS);
}

int equalsStringIgnoreCase(char str1[], char str2[]) {
	/* Apenas faz uma comparacao utilizando o strcmp.
	* Necessita de modificacao para obter uma comparacao
	* 'case insensitive' */
	return (strcmp(str1, str2) == 0);
}

void printCommandsMenu() {
	printf("\n===================================================================================");
	printf("\n                          PROJECT: COVID-19                    ");
	printf("\n===================================================================================");
	printf("\nA. Base Commands (LOADP (Load Patients), LOADR (Load Regions),CLEAR).");
	printf("\nB. Simple Indicators and searchs (AVERAGE, FOLLOW, MATRIX, OLDEST, RELEASED, SEX, SHOW, TOP5).");
	printf("\nC. Advanced indicator (REGIONS, REPORTS)");
	printf("\nD. Exit (QUIT)\n\n");
	printf("COMMAND> ");
}
