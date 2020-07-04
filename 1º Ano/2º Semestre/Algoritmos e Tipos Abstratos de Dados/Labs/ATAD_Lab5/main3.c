#include <stdio.h> 
#include <stdlib.h>

int fib(int n);
int* fibArrayCreate(int n);
void fibArrayPrint(int *arr,int size);
void fibArrayDestroy(int **arr);

int main(){
    int *array;
    int **arrayPointer = &array;
    int n;

    printf("Length of fib sequence?:");
    scanf("%d", &n);
    array = fibArrayCreate(n);
    printf("Address of fib array: %p\n", &array);
    fibArrayPrint(array,n);
    fibArrayDestroy(arrayPointer);
    printf("Address of fib array: %p", *arrayPointer);
    fibArrayPrint(array,n);
}


/**
 * @brief Algoritmo recursivo para calcular os números de fibonnaci
 * 
 * @param n n-ésimo número
 * 
 * @return int 
 */
int fib(int n){
    if(n == 0)
        return 0;
    else if(n == 1)
        return 1;
    else
        return (fib(n-1) + fib(n-2));
}

/**
 * @brief Algoritmo recursivo para colocar os valores calculados de fibonnaci num array de inteiros
 * 
 * @param n n-ésimo número
 * 
 * @return int array
 */
int* fibArrayCreate(int n){
    int *array = NULL;
    array = (int*) calloc(n,sizeof(int));
    array[0] = fib(0);
    array[1] = fib(1);
    for(int i=2;i<n;i++){
        array[i] = fib(i-1) + fib(i-2);
    }
    return array;
}

/**
 * @brief Algoritmo utilizado para demonstrar os valores de fibonnaci de um array de inteiros
 * 
 * @param arr Array de inteiros
 * @param size Tamanho do array
 * 
 */
void fibArrayPrint(int *arr,int size){
    if(arr == NULL){
        printf("\n(NULL)\n");
        return;
    }
    printf("{");
    for(int i=0;i<size-1;i++){
        printf(" %d, ",arr[i]);
    }
    printf("%d}\n",arr[size-1]);
}

/**
 * @brief Algoritmo que serve para por o endereço e o valor no endereço a NULL
 * 
 * @param arr Endereço de um array
 * 
 */
void fibArrayDestroy(int **arr){
    free(*arr);
    *arr = NULL;  
}