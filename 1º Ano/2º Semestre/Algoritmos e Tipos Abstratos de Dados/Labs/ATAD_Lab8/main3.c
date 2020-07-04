#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include "stack.h"
#include "stackElem.h"

bool evaluatePostfixExpression(char * expression, int *result);

int main(){
    char* expression1 = "579-4*+";
    char* expression2 = "57+9-4*";
    char* expression3 = "563+*3/";
    char* expression4 = "37+-62/";
    int result = 0;
    bool evaluatePostFix;
    evaluatePostFix = evaluatePostfixExpression(expression1,&result);
    if(evaluatePostFix)
        printf("The result is: %d\n",result);
    else
        printf("The expression is not in postfix form!\n");
}


/**
 * @brief Calcula o resultado de uma expressão posfixa (ou RPN) bem formada.
 *        Complexidade algoritmica: O(n)
 * @param expression String contendo uma expressão
 * @param result Referência de uma string contendo a expressão com o resultado
 *
 * @return boolean
 */
bool evaluatePostfixExpression(char * expression, int *result){
    int sizeOfExpression = strlen(expression);
    PtStack stack = stackCreate(sizeOfExpression);
    stackClear(stack);
    int a,b,c = 0; 
    for(int i=0;i<sizeOfExpression;i++){
        if(isdigit(expression[i]) > 0){
            int value = expression[i] - '0';
            stackPush(stack,value);
            stackPrint(stack);
        }
        else if(expression[i] == '*'){
            stackPop(stack,&a);
            stackPop(stack,&b);
            c = a*b;
            stackPush(stack,c);
            stackPrint(stack);
        }
        else if(expression[i] == '+'){
            stackPop(stack,&a);
            stackPop(stack,&b);
            c = a+b;
            stackPush(stack,c);
            stackPrint(stack);
        }
        else if(expression[i] == '-'){
            stackPop(stack,&a);
            stackPop(stack,&b);
            c = a-b;
            stackPush(stack,c);
            stackPrint(stack);
        }
        else if(expression[i] == '/'){
            stackPop(stack,&a);
            stackPop(stack,&b);
            c = a/b;
            stackPush(stack,c);
            stackPrint(stack);
        }
    }
    stackPeek(stack,result);
    int size=0;
    stackSize(stack,&size);
    if(size>1 || stackIsEmpty(stack))
        return false; 
    stackDestroy(&stack);
    return true;
}