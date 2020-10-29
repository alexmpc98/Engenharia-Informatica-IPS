#include "deque.h"
#include <stdio.h>
//devrá verificar se o dequeElem.h tem o tipo DequeElem definido como int
int main(){
    
    int elem;
    PtDeque deque= dequeCreate(10);
    dequeEnqueueEnd(deque,10);
    dequeEnqueueEnd(deque,100);
    dequePrint(deque);
    dequeEnqueueFront(deque,7);
    dequeEnqueueFront(deque,70);
    dequePrint(deque);
    dequeDequeueFront(deque,&elem);
    printf("%d\n", elem);
    dequeDequeueEnd(deque,&elem);
    printf("%d\n", elem);
    dequeEnqueueFront(deque,500);
    dequePrint(deque);
    dequeDestroy(&deque);
    return 0;
}