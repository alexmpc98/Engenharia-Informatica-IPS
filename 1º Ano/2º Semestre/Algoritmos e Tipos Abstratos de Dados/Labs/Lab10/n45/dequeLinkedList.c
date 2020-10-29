/**
 * @file dequeLinkedList.c
 * 
 * @brief Provides an implementation of the ADT Deque with a 
 * doubly-linked list with sentinels as the underlying 
 * data structure.
 * 
 * @author Patricia Macedo
 * @bug No known bugs.
 */

#include "deque.h" 
#include <stdio.h>
#include <stdlib.h>

struct node;
typedef struct node* PtNode;

typedef struct node {
	DequeElem element;
	PtNode prev;
	PtNode next;
} Node;

typedef struct dequeImpl {
	PtNode header;
	PtNode trailer;
	
} DequeImpl;


PtDeque dequeCreate(unsigned int initialCapacity) {
	PtDeque newDeque = (PtDeque)malloc(sizeof(DequeImpl));
	if (newDeque == NULL) return NULL;

	newDeque->header = (PtNode)malloc(sizeof(Node));
	if (newDeque->header == NULL) {
		free(newDeque);
		return NULL;
	}
	newDeque->trailer = (PtNode)malloc(sizeof(Node));
	if (newDeque->trailer == NULL) {
		free(newDeque->header);
		free(newDeque);
		return NULL;
	}

	newDeque->header->prev = NULL;
	newDeque->header->next = newDeque->trailer;

	newDeque->trailer->next = NULL;
	newDeque->trailer->prev = newDeque->header;

	return newDeque;
}

int dequeDestroy(PtDeque *ptDeque) {
	PtDeque deque = *ptDeque;
	if (deque == NULL) { return DEQUE_NULL;	}

	PtNode current = deque->header;
	while (current != NULL) {
		PtNode remove = current;
		current = current->next;
		free(remove);
	}
	free(deque);

	//outras alternativas: percorrer a lista ligada no sentido inverso

	*ptDeque = NULL;

	return DEQUE_OK;
}

int dequeEnqueueFront(PtDeque deque, DequeElem elem) {
	if (deque == NULL) {return DEQUE_NULL;	}

	PtNode newNode = (PtNode)malloc(sizeof(Node));
	if (newNode == NULL) return DEQUE_NO_MEMORY;

	PtNode curStart = deque->header->next;

	newNode->element = elem;
	newNode->prev = deque->header;
	newNode->next = curStart;

	deque->header->next = newNode;
	curStart->prev = newNode;

	return DEQUE_OK;
}
	
int dequeEnqueueEnd(PtDeque deque, DequeElem elem) {
	if (deque == NULL) {return DEQUE_NULL;	}

	PtNode newNode = (PtNode)malloc(sizeof(Node));
	if (newNode == NULL) return DEQUE_NO_MEMORY;

	PtNode curEnd = deque->trailer->prev;

	newNode->element = elem;
	newNode->next = deque->trailer;
	newNode->prev = curEnd;

	deque->trailer->prev = newNode;
	curEnd->next = newNode;


	return DEQUE_OK;
}

int dequeDequeueFront(PtDeque deque, DequeElem *ptElem) {
	if (deque == NULL) {return DEQUE_NULL;	}

	if (dequeIsEmpty(deque)) {return DEQUE_EMPTY;	}

	PtNode curStart = deque->header->next;
	*ptElem = curStart->element;

	PtNode newStart = curStart->next; //ou deque->header->next->next;

	deque->header->next = newStart;
	newStart->prev = deque->header;
	
	free(curStart);

	return DEQUE_OK;
}

int dequeDequeueEnd(PtDeque deque, DequeElem *ptElem) {
	if (deque == NULL) {return DEQUE_NULL;	}

	if (dequeIsEmpty(deque)) {return DEQUE_EMPTY;	}

	PtNode curEnd = deque->trailer->prev;
	*ptElem = curEnd->element;

	PtNode newEnd = curEnd->prev;

	deque->trailer->prev = newEnd;
	newEnd->next = deque->trailer;
	
	free(curEnd);

	return DEQUE_OK;
}

int dequeFront(PtDeque deque, DequeElem *ptElem) {
	if (deque == NULL) {return DEQUE_NULL;	}

	if (dequeIsEmpty(deque)) {	return DEQUE_EMPTY;	}

	
	PtNode curStart = deque->header->next;
	*ptElem = curStart->element;
	
	return DEQUE_OK;
}

int dequeSize(PtDeque deque, int *ptSize) {
	if (deque == NULL) return DEQUE_NULL;

	int count = 0;
	PtNode current = deque->header->next;
	while (current != deque->trailer) {
		count++;
		current = current->next;
	}
	*ptSize = count;
	return DEQUE_OK;
}

bool dequeIsEmpty(PtDeque deque) {
	if (deque == NULL) return 1;
	return (deque->header->next == deque->trailer) ? true : false;
}

int dequeClear(PtDeque deque) {
	if (deque == NULL) return DEQUE_NULL;
	

	DequeElem elem;
	while (!dequeIsEmpty(deque)) {
		dequeDequeueFront(deque, &elem);
	}

	return DEQUE_OK;
}

void dequePrint(PtDeque deque) {
	if (deque == NULL) {
		printf("(Deque NULL) \n");
	}
	else if (deque->header->next == deque->trailer) {
		printf("(Deque Empty) \n");
	}
	else {
		//imprimir do inicio para o fim da fila:
		printf("Deque contents (begin to end): \n");
		PtNode current = deque->header->next;
		while (current != deque->trailer) {
			dequeElemPrint(current->element);
			current = current->next;
		}
		printf("------------------------------ \n");
	}
}

