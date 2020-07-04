
#include <stdio.h>
#include <string.h>
#include <stdlib.h> 
#include "vector.h"
#include <stdbool.h>


int main() {
    Vector vector1 = vectorCreate(4,6);
    vectorPrint(vector1);
    double vector1Length = vectorLength(vector1);
    printf("\nVector1 Length: %f", vector1Length);

    Vector vector2 = vectorCreate(-3,2);
    vectorPrint(vector2);
    double vector2Length = vectorLength(vector2);
    printf("\nVector2 Length: %f", vector2Length);

    int vectorDot = vectorDotProduct(vector1, vector2);
    printf("\nVectorDot: %d", vectorDot);

    Vector vList1[] = { vectorCreate(4,6), vectorCreate(-3,2), vectorCreate(1,3) };
    printf("\nvList1 exist orthogonals: %s", (existOrthogonals(vList1, 3) == 1) ? "true" : "false");
    //vList1 exist orthogonals? true
    Vector vList2[] = { vectorCreate(4,6), vectorCreate(1,3), vectorCreate(0,2), vectorCreate(-1,5) };
    printf("\nvList2 exist orthogonals: %s \n", (existOrthogonals(vList2, 4) == 1) ? "true" : "false");
    //vList2 exist orthogonals? false

    return EXIT_SUCCESS;
} 

