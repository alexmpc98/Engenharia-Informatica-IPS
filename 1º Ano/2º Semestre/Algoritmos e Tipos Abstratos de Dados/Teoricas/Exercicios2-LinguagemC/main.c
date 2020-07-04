#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Student{
    int number;
    char name[51];
    int age;
                      }Student;

void studentPrint(Student s){
    printf("Número: %d | Nome: %s | Idade: %d \n",s.number, s.name, s.age);
                            }

Student studentCreate(int number, char* name, int age){
    Student student;
    student.number = number;
    strcpy(student.name, name); 
    student.age = age;
    studentPrint(student);
    return student;               }




int main() {

  Student Alex;
  Alex.number = 190221093;
  strcpy( Alex.name, "Alex");
  Alex.age = 21;

studentPrint(Alex);
studentCreate(190221000, "AlexB", 22);

return EXIT_SUCCESS;
}

