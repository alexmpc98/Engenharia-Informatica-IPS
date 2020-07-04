#include <stdbool.h>

typedef struct {
     Student student;
     float result;
} Grade;

Grade gradeCreate(int number, char* name, float result);
char* approvation(float result);
void gradePrint(Grade g);
void gradeArrayPrint(Grade *gradeArr,int size);
int getNumberApproved(Grade *GradeArr,int size);
bool resultExists(Grade *gradeArr, int size, float value);
int getMajorGrade(Grade *gradeArr,int size);
int getMinorGrade(Grade *gradeArr,int size);
void gradeArrayStats(Grade *gradeArr,int size);
void gradeArrSortByResult(Grade *gradeArr,int size);
void gradeArrSortByNumber(Grade *gradeArr,int size);

