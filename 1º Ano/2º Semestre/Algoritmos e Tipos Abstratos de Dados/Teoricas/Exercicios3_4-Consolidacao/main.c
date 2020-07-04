#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void removeCharacters(char *str);
void toUpperCase(char *str);
void namePresentation(char *name);
int length(char *str);
void changeCharacters(char *string, char CharToChange, char NewChar);
void greaterValues(int *array, int length, int value);
void lowerValue(int rows, int columns,int matrix[rows][columns]);
void sumAboveMainDiagonal(int rows, int columns,int matrix[rows][columns]);


int main(){

char str[1000] = "Vou estudar ATAD, prometo.";
char str2[100] = "abcabcabc";
char nome[1000] = "Alexandre Manuel Parreira Coelho";
char newName[100];

removeCharacters(str);
namePresentation(nome);
changeCharacters(str2,'b','l');

int array[6] = {4,2,3,6,7,8};
greaterValues(array,6,6);

int matrix[5][5] = {{5 , 4 , 3 , 3 , 2}, 
                    {3 , 4 , 2 , 2 , 2},
                    {9 , 2 , 2 , 2 , 5},
                    {7 , 2 , 3 , 8 , 4},
                    {5 , 4 , 3 , 2 , 7}};
                    
lowerValue(5,5,matrix);
sumAboveMainDiagonal(5,5,matrix);
}


/* 1. Algoritmo que retira todos os caracteres repetidos */

/* 
    Algorithm: toUpperCase
    input:[*str - char with pointer]

BEGIN
i <- 0
    WHILE str[i] DO
        IF str[i] >= 'a' AND str[i] <= 'z' DO
            str[i] <- str[i] - 32
        END IF
            i <- i + i
    END WHILE
END

ALGORITHM TIME COMPLEXITY: LINEAR / O(n) 
*/

void toUpperCase(char *str){
    int i = 0;
        while(str[i]){
            if(str[i] >= 'a' && str[i] <= 'z'){
                str[i] = str[i] - 32;
            }
            i++;
        }    
}
/*
    Algorithm: removeCharacters
    input:[*str - char with pointer]

BEGIN
    index1 <- 0
    index2 <- 0
    index3 <- 0
    str <- toUpperCase(str)
    size <- length(str)
        FOR index1 <- 0 TO (index1<size) DO
            FOR index2 <- index1 + 1 TO (str[index2]  ≠ '\0') DO
                IF(str[index2] == str[index1]) THEN
                    FOR index3 <- index2 TO (str[index3]  ≠ '\0') DO
                        str[index3] <- str[index3+1]
                    END FOR
            END FOR
        END FOR
        PRINT "1. String sem caracteres repetidos: $str"
END

ALGORITHM TIME COMPLEXITY: CUBIC / O(n³) 

*/

void removeCharacters(char *str){
    int index1 = 0;
    int index2 = 0;
    int index3 = 0;
    int size = length(str);
    toUpperCase(str);

        for(index1=0; index1<size; index1++){            
            for(index2=index1+1; str[index2] != '\0'; index2++){
                if(str[index2] == str[index1]){
                    for(index3=index2; str[index3] != '\0'; index3++){
                        str[index3] = str[index3+1];
                    }
                }
            }
        }
    printf("1. String sem caracteres repetidos: %s\n",str);
}

/* 2. Algoritmo que recebe um nome e apresenta o apelido e o 1º nome */

/*
    Algorithm: namePresentation
    input:[*name - char with pointer]

BEGIN
    j <- length(name)
    index <- j-1
    WHILE (name[index] ≠ ' ') DO
        index <- index - 1
    END WHILE
    WHILE (index < j) DO
        index <- index + 1
        PRINT "$name[index]"
    END WHILE
        PRINT ", "
        index <- 0
    WHILE (name[index] ≠ ' ')
        index <- index + 1
        PRINT "$name[index]"
    END WHILE
END

ALGORITHM TIME COMPLEXITY: LINEAR / O(n) 
*/

void namePresentation(char *name){
    int j = length(name);
    int index = j-1;

    while(name[index] != ' '){
        --index;
    }
    while(index < j){
        printf("%c",name[index++]); 
    }
    printf("%s", ",");
    index = 0;
    while(name[index] != ' '){
        printf("%c",name[index++]);
    }
    printf("%c",'\n');
} 

/* 3. Algoritmo que recebe uma string e dois caracteres e efetua a troca de caracteres, da ocorrencia
do primeiro parametrizado, para o segundo parametrizado */
int length(char *str){
    return strlen(str);
}

/*
    Algorithm: changeCharacters
    input:[*string - char with pointer,
            CharToChange, NewChar - char]

BEGIN
   size <- length(str)
   FOR i <- 0 TO (i<size) DO
        IF (string[i] == CharToChange) THEN
            string[i] <- NewChar
        END IF
   END FOR
   PRINT "New String: $string"
END

ALGORITHM TIME COMPLEXITY: LINEAR / O(n) 
*/

void changeCharacters(char *string, char CharToChange, char NewChar){
    int size = length(string);
    for(int i=0; i<size; i++){
        if(string[i] == CharToChange){
            string[i] = NewChar;
        }
    }
    printf("New String: %s\n", string);
}

/* 4. Algoritmo de Array de Inteiros */
/*
    Algorithm: greaterValues
    input:[*array - int with pointer,
            length,value - Integer]

BEGIN
    FOR i <- 0 TO i < length DO
        IF (array[i] > value) THEN
            PRINT "$array[i] is greater than $value"
        END IF
    END FOR
END

ALGORITHM TIME COMPLEXITY: LINEAR / O(n)
*/

void greaterValues(int *array, int length, int value){
        for(int i=0; i<length; i++){
            if(array[i] > value){
                printf("%d is greater than %d\n", array[i], value);
            }
        }
}

/* 5. Algoritmo para encontrar menor valor na matriz */

/*
    Algorithm: lowerValue
    input:[rows,columns - Integer,
            matrix - 2 dimensional integer array]

BEGIN
    lowerValue <- 0
    FOR rowsIndex <- 0 TO rowsIndex < rows DO
        FOR columnsIndex <- 0 TO columnsIndex < columns DO
            IF (rowsIndex == 0 && columnsIndex == 0) THEN
                lowerValue <- matrix[rowsIndex][columnsIndex]
            END IF
            IF (lowerValue > matrix[rowsIndex][columnsIndex])
                lowerValue <- matrix[rowsIndex][columnsIndex]
            END IF
        END FOR
    END FOR
    PRINT "Lower Value: $lowerValue"
END

ALGORITHM TIME COMPLEXITY: QUADRATIC / O(n²)
*/

void lowerValue(int rows, int columns,int matrix[rows][columns]){
    int lowerValue=0;
    for(int rowsIndex=0;rowsIndex<rows;rowsIndex++){
        for(int columnsIndex=0;columnsIndex<columns;columnsIndex++){
            if(rowsIndex == 0 && columnsIndex == 0)
                lowerValue = matrix[rowsIndex][columnsIndex];
            if(lowerValue > matrix[rowsIndex][columnsIndex])
                lowerValue = matrix[rowsIndex][columnsIndex];
        }
    }
    printf("Lower Value: %d\n", lowerValue);
}

/* 6. Algoritmo para efetuar soma dos elementos da matriz que estão acima da diagonal principal*/

/*
    Algorithm: sumAboveMainDiagonal
    input:[rows,columns - Integer,
            matrix - 2 dimensional integer array]

BEGIN
    sum <- 0
    FOR rowsIndex <- 0 TO rowsIndex < rows DO
        FOR columnsIndex <- 0 TO columnsIndex < columns DO
            IF (columnsIndex > rowsIndex)
                sum <- matrix[rowsIndex][columnsIndex] + sum
            END IF
        END FOR
    END FOR
    PRINT "Sum of all the elements above the main diagonal: $sum"
END

ALGORITHM TIME COMPLEXITY: QUADRATIC / O(n²)
*/

void sumAboveMainDiagonal(int rows, int columns,int matrix[rows][columns]){
    int sum = 0;
    for(int rowsIndex=0;rowsIndex<rows;rowsIndex++){
        for(int columnsIndex=0;columnsIndex<columns;columnsIndex++){
            if(columnsIndex>rowsIndex)
                sum += matrix[rowsIndex][columnsIndex];
        }
    }
    printf("Sum of all the elements above the main diagonal: %d\n",sum);
}

