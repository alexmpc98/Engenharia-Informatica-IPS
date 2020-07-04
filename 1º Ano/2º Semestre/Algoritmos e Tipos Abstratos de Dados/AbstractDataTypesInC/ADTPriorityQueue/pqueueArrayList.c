/**
 * @file pqueueArrayList.c
 * @author your name (you@domain.com)
 * @brief 
 * @version 0.1
 * @date 2020-05-26
 * 
 * @copyright Copyright (c) 2020
 * 
 * The front of the queue is at the end of the array;
 * Elements are inserted ordered by priority;
 * Elements are removed from the end of the array;
 * 
 */

#include "pqueue.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct pqueueImpl {
    PQueueElem *elements;
    int capacity;
    int size;
} PQueueImpl;

PtPQueue pqueueCreate(unsigned int initialCapacity) {

    PtPQueue pqueue = (PtPQueue) malloc( sizeof(PQueueImpl) );
    if( pqueue == NULL ) return NULL;

    pqueue->elements = (PQueueElem*) calloc( initialCapacity, sizeof(PQueueElem) );
    if( pqueue->elements == NULL ) {
        free(pqueue);
        return NULL;
    }

    pqueue->capacity = initialCapacity;
    pqueue->size = 0;

    return pqueue;
}

int     pqueueDestroy(PtPQueue *ptQueue) {
    PtPQueue pqueue = *ptQueue;

    if( pqueue == NULL ) return PQUEUE_NULL;

    free(pqueue->elements);
    free(pqueue);

    *ptQueue = NULL;

    return PQUEUE_OK;
}

static bool ensureCapacity(PtPQueue queue) {
	if (queue->size == queue->capacity) {
		int newCapacity = queue->capacity * 2;
		PQueueElem* newArray = (PQueueElem*) realloc( queue->elements, 
									newCapacity * sizeof(PQueueElem) );
		
		if(newArray == NULL) return false;

		queue->elements = newArray;
		queue->capacity = newCapacity;
	}
	
	return true;
}

int     pqueueEnqueue(PtPQueue pqueue, PQueueElem elem) {
    if( pqueue == NULL ) return PQUEUE_NULL;

    if(!ensureCapacity(pqueue)) return PQUEUE_FULL;

    int insertIndex = pqueue->size;
    for(int i=0; i<pqueue->size; i++) {
        if( pqueueElemPriority( pqueue->elements[i] >= pqueueElemPriority(elem) ) ) {
            insertIndex = i;
            break;
        }
    }
    /* Ver livro | estruturas lineares | inserção em posição arbitrária! */
    for(int i = pqueue->size; i > insertIndex; i--) {
        pqueue->elements[i] = pqueue->elements[i - 1];
    }

    pqueue->elements[insertIndex] = elem;
    pqueue->size++;

    return PQUEUE_OK;
}

int     pqueueDequeue(PtPQueue pqueue, PQueueElem *ptElem) {
    if( pqueue == NULL ) return PQUEUE_NULL;

    if (pqueue->size == 0) return PQUEUE_EMPTY;

    /* front element is at the end of the array!! */
    *ptElem = pqueue->elements[ pqueue->size - 1 ];

    pqueue->size--;

    return PQUEUE_OK;
}

int     pqueueFront(PtPQueue pqueue, PQueueElem *ptElem) {
    if( pqueue == NULL ) return PQUEUE_NULL;

    if (pqueue->size == 0) return PQUEUE_EMPTY;

    /* front element is at the end of the array!! */
    *ptElem = pqueue->elements[ pqueue->size - 1 ];

    return PQUEUE_OK;
}

int     pqueueSize(PtPQueue pqueue, int *ptSize) {
    if( pqueue == NULL ) return PQUEUE_NULL;

    *ptSize = pqueue->size;

    return PQUEUE_OK;
}

bool    pqueueIsEmpty(PtPQueue pqueue) {
    if( pqueue == NULL ) return true;

    return (pqueue->size == 0);
}

int     pqueueClear(PtPQueue pqueue) {
    if( pqueue == NULL ) return PQUEUE_NULL;

    pqueue->size = 0;

    return PQUEUE_OK;
}

void    pqueuePrint(PtPQueue pqueue) {
    if (pqueue == NULL) {
		printf("(PQueue NULL) \n");
	}
	else if (pqueue->size == 0) {
		printf("(PQueue Empty) \n");
	}
	else {
		printf("PQueue contents (front to end): \n");
		for (int i = pqueue->size - 1; i >= 0; i--) {
			pqueueElemPrint(pqueue->elements[i]);
			printf(" ");
		}
		printf("\n------------------------------ \n");
	}
}
