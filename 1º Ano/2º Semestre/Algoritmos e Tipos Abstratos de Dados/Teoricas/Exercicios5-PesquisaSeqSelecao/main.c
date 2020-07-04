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
    printf("Let's verify if there is any \"Lara Pires\" in the student's array.\n");
    printf("the result is: %s\n", studentExists(studentArr, 5, "Lara Pires") ? "Yes" : "No");
    printf("And a student named \"Filipe Vasconcelos\"?.\n");
    printf("the result is: %s\n", studentExists(studentArr, 5, "Filipe Vasconcelos") ? "Yes" : "No");
     
     /*Ex. 2*/
    printf("Now let's see who is the youngest student: \n");
    studentPrint(youngestStudent(studentArr, 5));

    return EXIT_SUCCESS;
}


