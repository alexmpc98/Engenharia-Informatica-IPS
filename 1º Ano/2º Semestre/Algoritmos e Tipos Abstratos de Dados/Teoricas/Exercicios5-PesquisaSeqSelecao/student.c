/**
 * @file student.c
 * @author Filipa Ferrada
 */

#include <stdio.h>
#include <string.h>

#include "student.h"

/**
 * @brief Creates a new student.
 * 
 * @param number The student's number.
 * @param name   The student's name.
 * @param age    The student's age.
 * 
 * @return The new created student.
 */
Student studentCreate(int number, char* name, int age){
    Student s;
    s.number = number;
    strcpy(s.name, name);
    s.age = age;  
    return s;
}

/**
 * @brief Prints a specific student.
 * 
 * @param s The student to be printed.
 * 
 */
void studentPrint(Student s){
    printf("Number: %9d | Name: %-20s | Age: %2d\n", s.number, s.name, s.age);
}

/**
 * @brief Searches for the name of a student in an array of students
 * 
 * @param studentArr The student's array.
 * @param size       The size of the array.
 * @param name       The student's name.
 * 
 * @return true if the student exists, false otherwise.
 */
bool studentExists(Student *studentArr, int size, char *name){

    for(int i = 0; i < size; i++){
        if (strcmp(studentArr[i].name, name) == 0)
            return true;
    }
    return false;
}

/**
 * @brief Selects the youngest student in an array of students
 * 
 * @param studentArr The student's array.
 * @param size       The size of the array.
 * 
 * @return The youngest student of the student's array.
 */
Student youngestStudent(Student *studentArr, int size){
    int indexYounger = 0; // assumes that the younger student is at index 0
    
    for(int i = 0; i < size; i++){
        if(studentArr[i].age < studentArr[indexYounger].age)
            indexYounger = i;
    }
    return studentArr[indexYounger];    
}