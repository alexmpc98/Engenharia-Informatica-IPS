/**
 * @file pqueue.h
 * @brief Definition of the ADT Priority Queue in C.
 * 
 * Defines the type PtPQueue and associated operations.
 */
#pragma once

#define PQUEUE_OK            0
#define PQUEUE_NULL          1
#define PQUEUE_NO_MEMORY     2
#define PQUEUE_EMPTY         3
#define PQUEUE_FULL          4

#include <stdbool.h>
#include "pqueueElem.h"

struct pqueueImpl;
typedef struct pqueueImpl *PtPQueue;

PtPQueue pqueueCreate(unsigned int initialCapacity);
int     pqueueDestroy(PtPQueue *ptQueue);
int     pqueueEnqueue(PtPQueue pqueue, PQueueElem elem);
int     pqueueDequeue(PtPQueue pqueue, PQueueElem *ptElem);
int     pqueueFront(PtPQueue pqueue, PQueueElem *ptElem);
int     pqueueSize(PtPQueue pqueue, int *ptSize);
bool    pqueueIsEmpty(PtPQueue pqueue);
int     pqueueClear(PtPQueue pqueue);
void    pqueuePrint(PtPQueue pqueue);