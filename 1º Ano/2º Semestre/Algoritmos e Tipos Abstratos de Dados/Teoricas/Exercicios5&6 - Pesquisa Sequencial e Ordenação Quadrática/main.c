#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void printArr(int *arr,int size);
int searchOcorrences(int *arr, int size, int val, int *arrIndexes);
int minorValue(int size,int arr[size]);
int secondBiggestValue(int size,int arr[size]);
void orderArray(int size, int arr[size]);
int minorValueOrd(int size,int arr[size]);
int secondBiggestValueOrd(int size,int arr[size]);
void ascendingOrder(char *str);

int main(){

int arr[] = {3,4,6,3};
int arrOrd[] = {1,2,3,4};
int arrIndexes[4] = {0};
char String[] = "abcdab";

int a = searchOcorrences(arr,4,6,arrIndexes);
int b = minorValue(4,arr);
int c = secondBiggestValue(4,arr);
int d = minorValueOrd(4,arrOrd);
int e = secondBiggestValueOrd(4,arrOrd);

printf("Ocorrências do valor no array: %d\n",a);
printArr(arrIndexes,a);
orderArray(4,arr);
printf("Ocorrências de Menor valor: %d\n",b);
printf("Second biggest value: %d\n",c);
printf("Now with ordered index!\n");
printf("Ocurrences of Minor Value Ord. Array : %d\n",d);
printf("Second biggest value Ord. Array : %d\n",e);

ascendingOrder(String);
}

/* 1 - Search Occurrences and indexes*/
int searchOcorrences(int *arr, int size, int val, int *arrIndexes){
    int count = 0;
    for(int i = 0; i< size ; i++){
        if(val == arr[i]){
            arrIndexes[count++] = i;
        }
    }
    return count;
}
/* COMPLEXITY : O(n) / Linear */

void printArr(int *arr,int size){
    printf("{%d", arr[0]);
    for(int i=1; i<size; i++){
        printf(", %d", arr[i]);
    }
    printf("}\n");
}

/* 2 - Minor value and counter */
int minorValue(int size,int arr[size]){
    int minorValue = arr[0];
    int count = 0;
    for(int i=0; i<size; i++){
        if(minorValue > arr[i]){
            minorValue = arr[i];
        }
    }
    printf("Menor valor: %d\n", minorValue);
    for(int j=0;j<size;j++){
        if(arr[j] == minorValue){
            count = count + 1;
        }
    }
    return count;
}
/* COMPLEXITY : O(n) - Linear */


/* 3 - Second Biggest Value */
int secondBiggestValue(int size,int arr[size]){
    int biggestValue,secondBiggestValue = arr[0];

    for(int i=0; i<size;i++){
        if(biggestValue < arr[i]){
            secondBiggestValue = biggestValue;
            biggestValue = arr[i];
        }
        else if(arr[i] > secondBiggestValue && arr[i] != biggestValue){
            secondBiggestValue = arr[i];
        }
    }
    return secondBiggestValue;
}
/* COMPLEXITY : O(n) - Linear */


/* 4 - Bubble Sort */
void orderArray(int size, int arr[size]){
    int newNumb = 0;
    printf("Sorting...\n");
    for(int i=0;i<size;i++){
        for(int a=0; a < size - i - 1; a++){
            if(arr[a] > arr[a+1]){
                newNumb = arr[a];
                arr[a] = arr[a+1];
                arr[a+1] = newNumb;
            }
        }
    }
    printArr(arr,size);
    printf("Sorted!\n");
}
/* COMPLEXITY : O(n²) - Quadratic */ 

/* 5 -  Minor value and counter + Second Biggest Value*/
int minorValueOrd(int size,int arr[size]){
    int minorValue = arr[0];
    int count = 0;
    for(int i=0;i<size;i++){
        if(arr[i] == minorValue){
            count = count + 1;
        }
    }
    return count;
}
int secondBiggestValueOrd(int size,int arr[size]){
    int biggestValue = arr[size-1];
    int secondBiggestValue = arr[0];

    for(int i=0; i<size;i++){
        if(biggestValue < arr[i]){
            secondBiggestValue = biggestValue;
            biggestValue = arr[i];
        }
        else if(arr[i] > secondBiggestValue && arr[i] != biggestValue){
            secondBiggestValue = arr[i];
        }
    }
    return secondBiggestValue;
}


/* 6 - Selection Sort */
void ascendingOrder(char *str){
    char c;
    int length = strlen(str);

    for(int i=0; i<length-1;i++){
        for(int j=i+1; j<length;j++){
            if(str[i] > str[j]){
                c= str[i];
                str[i] = str[j];
                str[j] = c;
            }
        }
    }

    printf("New String: %s\n", str);
}
/* COMPLEXITY : O(n²) - Quadratic */ 

