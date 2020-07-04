/**
 * @file main.c
 * @author Filipa Ferrada
 */

#include <stdio.h>
#include <stdlib.h>

#include "student.h"

int main(){

    Student studentArr[5] = {{145678478, "Hugo Vieira", 23}, {193498649, "Maria Carvalho", 18}, {190530864, "Joaquim Dinis", 19}, 
                             {180076238, "Lara Pires", 20}, {156978478, "Diogo Silva", 23}};
    
    /*Ex. 1*/
    printf("Students List:\n");
    printStudentArr(studentArr, 5);
     
     /*Ex. 2*/
    printf("Students List (sort ascending by name):\n");
    studentArrSortByName(studentArr, 5);
    printStudentArr(studentArr, 5);

    return EXIT_SUCCESS;
}


