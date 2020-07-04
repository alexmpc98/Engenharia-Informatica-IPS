#include <stdio.h> 
#include <stdlib.h>
#include <stdbool.h>
#include "student.h"
#include "pointers.h"

int triangularNumber(int n);
int exponentialNumber(int n);
int euclidesAlgorithm(int x,int y);
int sumFinalGrades(Student *student, int size);
Student selectStudentBestGrade(Student *student,int size);
int numberApprovedStudents(Student *student,int size);
float gradeAverage(Student *student, int size);
void bubbleSort(int *array, int size);


int main(){
    int TNumber = triangularNumber(3);
    int ExpNumber = exponentialNumber(3);
    int EuclidesAlg = euclidesAlgorithm(252,105);
    printf("Ex.0 - %d | Ex.1 - %d | Ex.2 - %d \n", TNumber,ExpNumber, EuclidesAlg);
    
    Student student1 = studentCreate(190221093,"Alex",16);
    Student student2 = studentCreate(190221093,"Joaquim",14);
    Student student3 = studentCreate(190221093,"Luis",12);
    Student student4 = studentCreate(190221093,"João",10);

    Student studentArr[4];
    studentArr[0] = student1;
    studentArr[1] = student2;
    studentArr[2] = student3;
    studentArr[3] = student4;

    int SumFinalGrades = sumFinalGrades(studentArr, 4);
    Student BestStudent = selectStudentBestGrade(studentArr,4);
    int NumberOFApproved = numberApprovedStudents(studentArr,4);
    printf("Ex.3 A - %d | B - %s | C - %d \n", SumFinalGrades, BestStudent.name,NumberOFApproved);

    float avg = gradeAverage(studentArr,4);
    printf("Ex4 - %.2f\n",avg);

    int array[4] = {2,6,1,3};
    bubbleSort(array,4);
    for(int i=0;i<4;i++){
        printf(" %d |",array[i]);
    }
    printf("\nPointers Exercises: \n");

    int val = 4;
    bool DoubleAndSquare = doubleAndSquare(&val);
    
    printf("6- Did it made the right calculus? %s\n", DoubleAndSquare ? "Yes" : "No");

    int array2[5] = {2,5,3,8,10};
    double Mean;
    int Median; 
    bool MeanMedian = meanMedian(array2,5,&Mean,&Median);
    printf("7- Did it made the right calculus? %s\n", DoubleAndSquare ? "Yes" : "No");
    printf("Mean: %.2f , Median: %d\n",Mean,Median);
    printf("Ex.9:");
    printNumbers(array2,5);
    printf("\n");

}

int triangularNumber(int n){
    if(n == 0)
        return 0;
    else
        return n + triangularNumber(n - 1);
}

int exponentialNumber(int n){
    if(n == 0)
        return 1;
    else
        return 2*exponentialNumber(n-1);
}

int euclidesAlgorithm(int x,int y){
    if(y == 0)
        return x;
    else
        return euclidesAlgorithm(y, x % y);
}

int sumFinalGrades(Student *student, int size){
    if(size == 0)
        return 0;
    else
        return student[size-1].finalGrade + sumFinalGrades(student,size-1);
}

Student selectStudentBestGrade(Student *student,int size){
    Student std;
    if(size == 0)
        return student[0];
    else
        std = selectStudentBestGrade(student,size-1);
        
    if(std.finalGrade > student[size-1].finalGrade)
        return std;
    else
        return student[size-1];
}

int numberApprovedStudents(Student *student,int size){
    if(size - 1 < 0)
        return 0;
    if(student[--size].finalGrade > 9)
        return 1 + numberApprovedStudents(student, size);
    else
        return numberApprovedStudents(student, size);
}

float gradeAverage(Student *student, int size){
    if(size-1 < 0)
        return 0;
    return (student[size-1].finalGrade + (size - 1) * gradeAverage(student, size-1)) / size;
}

void bubbleSort(int *array, int size){
    if(size == 1)
        return;
    
    for(int i=0; i <size-1; i++){
        if (array[i] > array[i+1]){
            int new = array[i];
            array[i] = array[i+1];
            array[i+1] = new;
        }
    }

    bubbleSort(array,size-1);
}
