#include <stdio.h>

int main()
{
    FILE *in_file;
    int number1, number2, sum;

    in_file = fopen("numeros.txt", "r");

    if (in_file == NULL)
    {
        printf("Can't open file for reading.\n");
    }
    else
    {
        fscanf(in_file, "%d", &number1);
        fscanf(in_file, "%d", &number2);
        sum = number1 + number2;
        printf("Sum of numbers %d and %d is %d", number1, number2, sum);
        fclose(in_file);
    }
    return 0;
}
