/**
 * @file student.h
 * @author Filipa Ferrada
 */

#pragma once
#include <stdbool.h>

/**
 * @brief Definition of type struct student.
 *
 */
typedef struct student {
    int number;
    char name[51];
    int age;
} Student;

/*Function's prototypes*/

/**
 * @brief Creates a new student.
 * 
 * @param number The student's number.
 * @param name   The student's name.
 * @param age    The student's age.
 * 
 * @return The new created student.
 */
Student studentCreate(int number, char* name, int age);

/**
 * @brief Prints a specific student.
 * 
 * @param s The student to be printed.
 * 
 */
void studentPrint(Student s);

/**
 * @brief Searches for the name of a student in an array of students
 * 
 * @param studentArr The student's array.
 * @param size       The size of the array.
 * @param name       The student's name.
 * 
 * @return True if the student exists, False otherwise.
 */
bool studentExists(Student *studentArr, int size, char *name);

/**
 * @brief Selects the youngest student in an array of students
 * 
 * @param studentArr The student's array.
 * @param size       The size of the array.
 * 
 * @return The youngest student of the student's array.
 */
Student youngestStudent(Student *studentArr, int size);

/**
* @brief Prints the students of the array of students
*
* @param studentArr The student's array.
* @param size The size of the array.
*
*/
void printStudentArr(Student *studentArr, int size);

/**
* @brief Sorts alphabetically in ascernding order the students of the array of students
*
* @param studentArr The student's array.
* @param size The size of the array.
*
*/
void studentArrSortByName(Student *studentArr, int size);