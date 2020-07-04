#include <stdio.h> 
#include <stdlib.h>
#include <time.h>


void randomArray(int *intArray,int size);
void intArrayPrint(int *intArray,int size);
void arraySort(int *intArray,int size);
int binarySearch(int val, int *arr, int start, int end);

int main(){
    int intArray[10];

    randomArray(intArray,10);
    printf("Vetor Gerado aleatoriamente:\n");
    intArrayPrint(intArray,10);
    printf("Vetor ordenado de forma crescente:\n");
    arraySort(intArray,10);
    
    int val;
    printf("\nInsira o número a procurar no vetor: ");
    scanf("%d",&val);
    int end = sizeof(intArray)/sizeof(intArray[0]) - 1;
    int BinarySearch = binarySearch(val,intArray,0,end);
    BinarySearch == -1 ? printf("O valor %d , não foi encontrado no vetor!\n", val) : 
    printf("\nValor %d encontrado na posição %d do vetor!\n",val,BinarySearch);
    
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


/**
 * @brief Este algoritmo efetua uma procura "binária", dividindo o array ao meio recursivamente até encontrar um valor no array igual ao valor inserido, neste caso, um valor inteiro num array de inteiros
 * 
 * @param val Valor inteiro que o utilizador deseja procurar no array de inteiros
 * @param arr Array de inteiros
 * @param start Posição inicial do array de inteiros
 * @param end Posição final do array de inteiros
 * 
 */
int binarySearch(int val, int *arr, int start, int end){
    int mid = (start + end) / 2;

    if(start > end){
        return -1;
    }
    else if(arr[mid] == val){
        return mid;
    }
    else if(arr[mid] > val){
        return binarySearch(val,arr,start,mid-1);
    }
    else
        return binarySearch(val,arr,mid+1,end);
    
    return -1;
}