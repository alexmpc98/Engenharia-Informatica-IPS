#include <stdio.h> 
#include <stdlib.h>
#include <time.h>

void randomArray(int *intArray,int size);
void intArrayPrint(int *intArray,int size);
void arraySort(int *intArray,int size);

int main(){
    int intArray[10];

    randomArray(intArray,10);
    printf("Vetor Gerado aleatoriamente:\n");
    intArrayPrint(intArray,10);
    printf("Vetor ordenado de forma crescente:\n");
    arraySort(intArray,10);
    
}

/**
 * @brief Este algoritmo permite a inserção de valores aleatorios num array de inteiros
 * 
 * @param intArray Array de inteiros
 * @param size Tamanho do array
 *
 */
void randomArray(int *intArray,int size){
    srand(time(0));

    for(int i=0; i<size; i++){
        intArray[i] = (rand() % (99 + 1));
    }
}

/**
 * @brief Este algoritmo permite gerar um output para o utilizador de um array de inteiros previamente inserido
 * 
 * @param intArray Array de inteiros
 * @param size Tamanho do array
 * 
 */
void intArrayPrint(int *intArray,int size){
    for(int i=0;i<size;i++){
        printf(" %d |",intArray[i]);  
    }
    printf("\n");
}

/**
 * @brief Este algoritmo permite a ordenação de um array inteiro, através de selection sort
 * 
 * @param intArray Array de inteiros
 * @param size Tamanho do array
 * 
 */
void arraySort(int *intArray,int size){
    int newNumb;
    for(int i=0; i<size-1;i++){
        for(int j=i+1; j<size;j++){
            if(intArray[i] > intArray[j]){
                newNumb = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = newNumb;
            }
        }
    }
    intArrayPrint(intArray,size);   
}

