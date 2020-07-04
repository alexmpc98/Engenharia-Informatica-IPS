#include <stdio.h>
#include <stdlib.h>

#include "pqueue.h"

int main() {

    PtPQueue pqueue = pqueueCreate(10);

    pqueueEnqueue(pqueue, 'a');
    pqueueEnqueue(pqueue, 'c');
    pqueueEnqueue(pqueue, 'e');
    pqueueEnqueue(pqueue, 'd');
    pqueueEnqueue(pqueue, 'b');

    pqueuePrint(pqueue);

    printf("Dequeuing all... \n");
    while(!pqueueIsEmpty(pqueue)) {
        char c;
        pqueueDequeue(pqueue, &c);
        printf("%c - %d \n", c, (int)c);
    }

    pqueueDestroy(&pqueue);

    return EXIT_SUCCESS;
}