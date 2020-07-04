#include <stdio.h> 
#include <stdlib.h>

typedef struct array {
    int *numbers;
    int size;
} Array;

typedef struct array* PtArray;

int fib(int n);
PtArray fibArrayCreate(int n);
void fibArrayPrint(PtArray arr);
void fibArrayDestroy(PtArray *ptArray);
void fibArrayExpand(PtArray arr);

int main(){
    int n;

    printf("Length of fib sequence?: ");
    scanf("%d", &n);
    PtArray pointer = fibArrayCreate(n);
    printf("Address of fib array: %p\n", &pointer);
    fibArrayPrint(pointer);
    printf("Address of fib array: %p\n", &pointer);
    fibArrayExpand(pointer);
    fibArrayPrint(pointer);
    fibArrayDestroy(&pointer);
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
 * @return Struct Array
 */
PtArray fibArrayCreate(int n) {
    Array *arr = (Array*)malloc(sizeof(Array));
    arr->numbers = (int*)calloc(n, sizeof(int));
    /*arr->size = (int*)malloc(sizeof(int));*/
    arr->size = n;
    
    arr->numbers[0] = fib(0);
    arr->numbers[1] = fib(1);
    for(int i=2;i<n;i++){
        arr->numbers[i] = fib(i-1) + fib(i-2);
    }
    printf("New: %p\n", arr );
    return arr;
}

/**
 * @brief Algoritmo utilizado para demonstrar os valores de fibonnaci de um array de inteiros
 * 
 * @param arr Struct Array
 * 
 */
void fibArrayPrint(PtArray arr) {
    if(arr == NULL){
        printf("\n(NULL)\n");
        return;
    }
    int size = arr->size;
    printf("{");
    for(int i=0;i<size-1;i++){
        printf(" %d, ",arr->numbers[i]);
    }
    printf("%d}\n",arr->numbers[size-1]);
}

/**
 * @brief Algoritmo que serve para por o endereço e o valor no endereço a NULL
 * 
 * @param arr Struct Array
 * 
 */
void fibArrayDestroy(PtArray *ptArray) {
    free((*ptArray)->numbers);
    free(*ptArray);
    *ptArray = NULL;  
}


/**
 * @brief Algoritmo que recebe o endereço de um array alocado previamente e liberta a memória alocada e atribui o valor NULL
 * 
 * @param arr Struct Array
 * 
 */
void fibArrayExpand(PtArray arr) {
    int sizeTmp = arr->size;
    arr->numbers = (int*) realloc(arr->numbers, 2 * sizeTmp * sizeof(int));
    arr->size = sizeTmp * 2;
    for(int i = sizeTmp - 1; i < arr->size; i++){
        arr->numbers[i] = fib(i-1) + fib(i-2);
    }
}