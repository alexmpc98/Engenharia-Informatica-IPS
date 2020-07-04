
#include <stdio.h>
#include <string.h>
#include <stdlib.h> 
#include <stdbool.h>

int strLength(char *str);
bool swapStrings(char *s1,char *s2,int by);

int main(){
    /* T1 */
    char str1 [] = "abcdefghij";
    char str2 [] = "klmnopqrst";
    bool test1 = swapStrings(str1, str2, 3);
    printf("By: %d | S1: %s | S2: %s \n", 3, str1, str2);
    /* T2 */
    char str3 [] = "abcdefghij";
    char str4 [] = "klmnopqrst";
    bool test2 = swapStrings(str3, str4, 1);
    printf("By: %d | S1: %s | S2: %s \n", 1, str3, str4);
    /* T3 */
    char str5 [] = "abcdefghij";
    char str6 [] = "klmnopqrst";
    bool test3 = swapStrings(str5, str6, 10);
    printf("By: %d | S1: %s | S2: %s \n", 10, str5, str6);
    /* T4 */
    char str7 [] = "abcdefghij";
    char str8 [] = "klmnopqrst";
    bool test4 = swapStrings(str7, str8, -5);
    printf("By: %d | S1: %s | S2: %s \n", -5, str7, str8);
    printf("\nT1: %s, T2: %s, T3: %s, T4: %s \n", test1 ? "true":"false", test2 ? "true":"false", test3 ? "true":"false", test4 ? "true":"false");
    return EXIT_SUCCESS;
} 

/**
 * @brief Recebe um array de carateres (string) e calcula o seu tamanho.
 * 
 * @param str1 String
 * @return Integer 
 */
int strLength(char *str) {
    int i = 0;
    while (str[i] != '\0') {
        i++;   
    }
    return i++;
}

/**
 * @brief Recebe dois arrays de carateres (strings) e um 
   número natural by e permuta caracteres entre as duas strings.
 * 
 * @param str1 Primeira String
 * @param str2 Segunda String
 * @return Boolean 
 */
bool swapStrings(char *str1, char *str2, int by) {
    int str1Length = strLength(str1);
    int str2Length = strLength(str2);
    char tempChar = ' ';

    if(str1Length != str2Length) { 
        return false;
    }
    if(by > str1Length || by > str2Length || by <= 0) {
        return false;
    }

    for(int i = by-1; i < str1Length; i=i+by){
        tempChar = str1[i];
        str1[i] = str2[i];
        str2[i] = tempChar;
    }
    return true;
}