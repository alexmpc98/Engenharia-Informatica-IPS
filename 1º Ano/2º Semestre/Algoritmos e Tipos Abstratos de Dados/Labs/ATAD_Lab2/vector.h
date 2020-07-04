#include <stdbool.h>
typedef struct 
{
    int x;
    int y;
} Vector;

Vector vectorCreate(int x, int y);
void vectorPrint(Vector v);
double vectorLength(Vector v);
int vectorDotProduct(Vector v1, Vector v2);
bool existOrthogonals(Vector list[], int listSize);