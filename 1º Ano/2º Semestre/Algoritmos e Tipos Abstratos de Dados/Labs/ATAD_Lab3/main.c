#include <stdio.h> 
#include <stdlib.h>
#include <stdbool.h>
#include "student.h"
#include "grade.h"

int main(){
    /* NÍVEL 2 */
    char* name = "Alexandre Veríssimo";
    int number = 190221093; 
    Student newStudent = studentCreate(number, name);
    Grade newGrade = gradeCreate(number, name, 20);

    Grade gradeVector[10];
    char* names[10] = {"Hugo Vieira", "Maria Carvalho", "Joaquim Dinis", "Henrique Ferreira",
    "Margarida Mourato", "Diogo Carreira","Ana Marques", "Manuel Quintino", "Joana Firmino", "Lara Pires"};
    const int studentNumbers[10] = {145678478,193498649,190530864,156978458,146978478,185678478,
    175678458,135674468,145678479,165678478};
    const float results[10] = {
    10.40,16.35,09.50,17.40,06.45,12.80,14.60,07.20,16.40,19.30};

    for(int i=0; i<10;i++){
        gradeVector[i] = gradeCreate(studentNumbers[i],names[i],results[i]);  
    }
   
    gradePrint(newGrade);
    gradeArrayPrint(gradeVector,10);

    /* NÍVEL 3 */
    int approved = getNumberApproved(gradeVector,10);
    printf("\nTotal of Approved: %d in 10\n", approved);

    float value = 9.50;
    bool checkIfExists = resultExists(gradeVector,10,value);
    printf("Someone got %.2f values? %s\n\n", value, checkIfExists ? "Yes" : "No");

    /* NÍVEL 4 */
     int majorGrade = getMajorGrade(gradeVector,10);
    printf("The best grade is %.2f values from %s\n", results[majorGrade], names[majorGrade]);

    int minorGrade = getMinorGrade(gradeVector,10);
    printf("The worst grade is %.2f values from %s\n\n", results[minorGrade], names[minorGrade]);

    gradeArrayStats(gradeVector,10);

    /* NÍVEL 5 */
    printf("\nSort all grades by results:\n");
    gradeArrSortByResult(gradeVector,10);

    printf("\nSort all grades by student number:\n");
    gradeArrSortByNumber(gradeVector,10);
}
