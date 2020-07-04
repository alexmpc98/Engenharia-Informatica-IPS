#include <stdio.h> 
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include "pointers.h"


bool doubleAndSquare(int *val){
    if(&val < 0)
        return false;

    double squareRoot = sqrt(*val);
    int Double = *val * *val;
    if(squareRoot == sqrt(*val) || Double == *val * *val)
        return true;
}

bool meanMedian(int *arr, int arrSize, double *mean, int *median){
    if(arrSize == 0)
        return false;
    
    double sum = 0;
    for(int i=0; i<arrSize; i++){
        sum = sum + arr[i];
    }
    *mean = sum/arrSize;
    
    for(int i=0;i<arrSize-1;i++){
        for(int j = i+1; j<arrSize; j++){
            if(arr[i] < arr[j]){
                int new = arr[i];
                arr[i] = arr[j];
                arr[j] = new;
            }
        }
    }

    if(arrSize % 2 == 0)
        *median = ((arr[arrSize/2] + arr[arrSize/2 - 1]) / 2);
    else
        *median = arr[arrSize/2];
    
    return true;
}

void printNumbers(int *arr, int size){
    
    while(size!=0){
        printf(" %d | ", *arr++);
        size--;
    }

}