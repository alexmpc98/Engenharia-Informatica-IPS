#include "pqueueElem.h"
#include <stdio.h>

void pqueueElemPrint(PQueueElem elem) {
    printf("%c ", elem);
}

int pqueueElemPriority(PQueueElem elem) {
    return (int)elem;
}