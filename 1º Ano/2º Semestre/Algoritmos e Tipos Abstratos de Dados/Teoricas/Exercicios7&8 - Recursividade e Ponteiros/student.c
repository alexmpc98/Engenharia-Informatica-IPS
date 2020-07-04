#include "student.h"
#include <stdio.h> 
#include <stdlib.h>


/**
 * @brief Criação de estudante
 * 
 * @param number Numero de estudante
 * @param name Nome de estudante
 * @return Student
 */
Student studentCreate(int number,char* name,int finalGrade){
    Student newStudent;
    newStudent.studentNumber = number;
    newStudent.name = name;
    newStudent.finalGrade = finalGrade;
    return newStudent;
}