#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include "queue.h"
#include "queueElem.h"
#include "stack.h"
#include "stackElem.h"

bool evaluatePostfixExpression(char * expression, int *result);
void convertInfixToPostfix(char *suffix, char ** postfix);

int main(){
    char* expression;
    char* newExpression;
    int result = 0;
    char response = 'Y';
    bool eval = false;
    while(true){
        printf("Insert an infix expression (single-digit only) >");
        scanf("%s", expression);

        convertInfixToPostfix(expression, &newExpression); 

        eval = evaluatePostfixExpression(expression, &result);
        printf("The result is: %d\n", result);

        printf("More calculations(Y/N)?\n");
        scanf("%s", &response);
        if (response == 'N' || response == 'n')
            break;
    }
}


/**
 * @brief Calcula o resultado de uma expressão posfixa (ou RPN) bem formada.
          Complexidade algoritmica: O(n)
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


/**
 * @brief Converte uma expressão de entrada dada na notação tradicional, 
 *        infixa para a notação posfixa e que devolva true caso a operação 
 *        tenha decorrido com sucesso e false caso contrário.
 *        Complexidade algoritmica: O(n^3)
 * @param infix   String contendo a expressão infixa
 * @param postfix Referência de uma string contendo a expressão posfixa
 *
 * @return boolean
 */
void convertInfixToPostfix(char *infix, char ** postfix) {
     struct precedence {
        char op;
        int prec;
    } precendence[] =
        { 
            { '(', 1 },
            { ')', 1 },
            { '*', 3 },
            { '/', 3 },
            { '+', 4 },
            { '-', 4 }
        };
    int sizeOfInfix = strlen(infix);
    if(sizeOfInfix > 0) {
        PtQueue infixQueue = queueCreate(sizeOfInfix);
        PtQueue postfixQueue = queueCreate(sizeOfInfix);
        PtStack stack = stackCreate(sizeOfInfix);       
        queueClear(infixQueue);
        queueClear(postfixQueue);
        stackClear(stack);
        queueEnqueue(infixQueue,'(');
        queuePrint(infixQueue);
        for(int i = 0; i < sizeOfInfix; i++){
            queueEnqueue(infixQueue, infix[i]); 
            /*queuePrint(infixQueue);*/
        }
        queueEnqueue(infixQueue,')');
        queuePrint(infixQueue);
        char b, c; 
        int pre_val_stack = 0; 
        int pre_val_queue = 0;
        QueueElem dequeueElem;
        StackElem stackElem1, stackElem2;
        while(!queueIsEmpty(infixQueue)) {
            queueDequeue(infixQueue, &dequeueElem);
            //queueElemPrint(dequeueElem);
            if (isdigit(dequeueElem) > 0) {
                //int value = infix[a] - '0';
                queueEnqueue(postfixQueue, dequeueElem);
                //queuePrint(postfixQueue);
            } else if(dequeueElem == '(') {
                stackPush(stack, dequeueElem);    
                //stackPrint(stack);
            } else if(dequeueElem == '*' || dequeueElem == '+' || dequeueElem == '-' || dequeueElem == '/') {
                printf("Enter: %c\n", dequeueElem );
    
                /*if ( pre_val_stack <= pre_val_queue && pre_val_stack == 0 && pre_val_queue == 0) {
                    stackPop(stack, &stackElem1);
                     //stackPrint(stack);
                    queueEnqueue(postfixQueue, stackElem1); 
                }*/
                while(pre_val_stack <= pre_val_queue && pre_val_stack != 0 && pre_val_queue != 0 && stackIsEmpty(stack) != true) { 
                    stackPeek(stack, &stackElem1);
                    for (int i = 0; i < 7; i++) {
                        if (precendence[i].op == stackElem1) {
                            pre_val_stack = precendence[i].prec;
                        } 
                        if (precendence[i].op == dequeueElem) {
                            pre_val_queue = precendence[i].prec;
                        } 
                        //printf("PRE: %d/%d\n", pre_val_stack, pre_val_queue);
                        /*printf("PRE(S): %c | %c - %d | %d\n", precendence[i].op, stackElem1, pre_val_stack, pre_val_queue);
                        printf("PRE(Q): %c | %c - %d | %d\n", precendence[i].op, dequeueElem, pre_val_stack, pre_val_queue);*/
                    }
                    stackPop(stack, &stackElem1);
                    //stackPrint(stack);
                    queueEnqueue(postfixQueue, stackElem1); 
                }
                stackPush(stack, dequeueElem);
                //stackPrint(stack);
            } else if(dequeueElem == ')') {
                while(stackElem2 != '('){
                    stackPop(stack, &stackElem2); 
                    if (stackElem2 != '(') {
                        queueEnqueue(postfixQueue, stackElem2);
                    }
                    //queuePrint(postfixQueue);
                    //stackPrint(stack);
                }
                stackPop(stack, &stackElem2);
                //stackPrint(stack);
            }          
        }
        printf("\nQUEUE FI:\n");
        queuePrint(infixQueue);
        printf("\nSTACK:\n");
        stackPrint(stack);
        printf("QUEUE FP:\n");
        queuePrint(postfixQueue);
        int index = 0;
        int postfixSize = 0;
        queueSize(postfixQueue, &postfixSize);
        char concat[postfixSize];
        while(!queueIsEmpty(postfixQueue)) {
            queueDequeue(postfixQueue, &dequeueElem); 
            *(*postfix + index) = dequeueElem;    
            index++;      
        }
        queueDestroy(&infixQueue);
        queueDestroy(&postfixQueue);
        stackDestroy(&stack);
    }
}