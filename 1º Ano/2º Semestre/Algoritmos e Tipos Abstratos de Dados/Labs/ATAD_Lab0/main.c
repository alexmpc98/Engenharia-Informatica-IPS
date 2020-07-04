#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/* Alusão a funções */
bool isEven(int);
/* Ponto de entrada do programa */
int main()
{
 int number, result;
 printf("Este programa verifica se um número pedido\n");
 printf("ao utilizador é par ou impar.\n\n");
 printf("Introduza o número: ");
 scanf("%d", &number);
 result = isEven(number);
 if (result){
 printf("O número é par\n");
 }else{
 printf("O número é impar\n");
 }
 return EXIT_SUCCESS;
}
/* Definição de funções */
bool isEven(int n)
{
 return (n % 2 == 0) ? 1 : 0; // 1/true se verdade, 0/false caso contrário.
}