#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>


char charAt(char *str, int pos){
    return str[pos];
}

int length(char *str){
    return strlen(str);
}

int countOccurences(char *str, char c){
    int a = length(str);
    int b = 0;
    for(int i=0;i<a;i++){
        if(str[i] == c){
            b++;
                       }
                        }
    return b;
}

bool onlyLetterB(char *str){
        for(int i=0;i<length(str);i++){
            if(isalpha(str[i]) == 0)
                return false;
        }

    return true;
}


bool onlyLetterA(char *str){       
        for(int i=0;i<length(str);i++){
            if((str[i] < 'A' || str[i] > 'Z') && (str[i] < 'a' || str[i] > 'z'))
                return false;                 
        }
       
    return true;
}

void toUpperCase(char *str){
    int i = 0;
        while(str[i]){
            if(str[i] >= 'a' && str[i] <= 'z'){
                str[i] = str[i] - 32;
            }
            i++;
        }    
}

bool areEqual(char *str1, char *str2){
   
   if(length(str1) != length(str2)){
    return false;
   }
   for(int i = 0; i < length(str1);i++){
       if(str1[i] != str2[i]){
           return false;
       }
   }
   return true;
}

bool isPalindrome(char *str){
    toUpperCase(str);
    int i = 0;
    int len = length(str) - 1;
    while(len > i){
        if(str[i++] != str[len--]){
            return false;
        }
    }
    return true;
}

bool hasDuplicates(char *str){
    for(int i=0;i<length(str);i++){
        for(int j= i +1; str[j] != '\0'; j++){
            if(str[j] == str[i]){
                return true;
            }
        }
    }
    return false;
}

void printPairs(char *str){
    for(int i=0; i<length(str); i++){
        for(int j = i + 1;str[j] != '\0'; j++){
            printf("%c %c", str[i], str[j]);
        }
    }
}

int main(){
    /* Variaveis */
    char str[100] = "Vou estudar ATAD, prometo";
    char str1[100] = "Vou estudar ATAD, prometo";
    char str2[100] = "Vou estudar ATAD, prometo";
    char palindrome1[100] = "ovo";
    char palindrome2[100] = "ana";
    char palindrome3[100] = "sopapos";
    char palindrome4[100] = "Sator arepo tenet opera rotas";

    /* 1. Caracter no indice */
    char CharacterInPosition = charAt(str,2);
    printf("Caracter : %c\n",CharacterInPosition);

    /* 2. Tamanho da String */
    int a = length("ABC");
    printf("%d\n",a);

    /* 3. Ocorrências de Caracteres */
    char c;
    printf("Insira o caracter para ser procurado:");
    c = getchar();
    int b = countOccurences(str,c);
    printf("Existem %d ocurrências\n",b);

    /* 4. Verificar letras*/
    /* Versão A */
    if(onlyLetterA(str)) 
    printf("A frase contém apenas letras\n");
    else 
    printf("A frase não contém apenas letras\n");

     /* Versão B */
    if(onlyLetterB(str)) 
    printf("A frase contém apenas letras\n");
    else 
    printf("A frase não contém apenas letras\n");

    /* 5. Caracteres maiusculos */
    toUpperCase(str);
    printf("%s\n",str);

    /* 6. Strings iguais */
    if(areEqual(str1,str2)) 
    printf("As frases são iguais\n");
    else 
    printf("As frases não são iguais\n");

    /* 7. Palindromos */
    if(isPalindrome(palindrome1)) 
    printf("É palindromo\n");
    else 
    printf("Não é palindromo\n");
    
    if(isPalindrome(palindrome2)) 
    printf("É palindromo\n");
    else 
    printf("Não é palindromo\n");
    
    if(isPalindrome(palindrome3)) 
    printf("É palindromo\n");
    else 
    printf("Não é palindromo\n");

    if(isPalindrome(palindrome4)) 
    printf("É palindromo\n");
    else 
    printf("Não é palindromo\n");

    /* 8. Caracteres Duplicados */
    if(hasDuplicates(palindrome1)) 
    printf("Possui caracteres duplicados\n");
    else 
    printf("Não possui caracteres duplicados\n");

    /* 9. Pares Possiveis*/
    printPairs(palindrome3);

}
