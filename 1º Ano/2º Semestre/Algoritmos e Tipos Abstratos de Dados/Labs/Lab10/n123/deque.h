/**
 * @file deque.h
 * @brief Definition of the ADT deque in C.
 * 
 * Defines the type PtDeque and associated operations.
 * 
 * @author Patricia Macedo
 * @bug No known bugs.
 */

#pragma once

#define DEQUE_OK            0
#define DEQUE_NULL          1
#define DEQUE_NO_MEMORY     2
#define DEQUE_EMPTY         3
#define DEQUE_FULL          4

#include <stdbool.h>
#include "dequeElem.h"

/** Forward declaration of the data structure. */
struct dequeImpl;

/** Definition of pointer to the  data stucture. */
typedef struct dequeImpl *PtDeque;

/**
 * @brief Creates a new empty deque.
 * 
 * @param initialCapacity [in] The desired initial capacity (?)
 * 
 * @return PtDeque pointer to allocated data structure, or
 * @return NULL if unsufficient memory for allocation
 */
PtDeque dequeCreate(unsigned int initialCapacity);

/**
 * @brief Free all resources of a deque.
 * 
 * @param PtDeque [in] ADDRESS OF pointer to the deque
 * 
 * @return DEQUE_OK if success, or
 * @return DEQUE_NULL if '*PtDeque' is NULL 
 */
int dequeDestroy(PtDeque *PtDeque);

/**
 * @brief Endeque an element in a deque on the end.
 * 
 * @param deque [in] pointer to the deque
 * @param elem  [in] element to endeque
 * 
 * @return DEQUE_OK if successful, or
 * @return DEQUE_FULL if no capacity available, or
 * @return DEQUE_NO_MEMORY if unsufficient memory for allocation, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */
    
int dequeEnqueueEnd(PtDeque deque, DequeElem elem);

/**
 * @brief Endeque an element in a deque on the front.
 * 
 * @param deque [in] pointer to the deque
 * @param elem  [in] element to endeque
 * 
 * @return DEQUE_OK if successful, or
 * @return DEQUE_FULL if no capacity available, or
 * @return DEQUE_NO_MEMORY if unsufficient memory for allocation, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */
int dequeEnqueueFront(PtDeque deque, DequeElem elem);
/**
 * @brief Dequeue the element in front of a deque.
 * 
 * @param deque [in] pointer to the deque
 * @param ptElem [out] address of variable to hold the value
 * 
 * @return DEQUE_OK if successful and value in 'ptElem', or
 * @return DEQUE_EMPTY if the deque is empty, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */


int dequeDequeueFront(PtDeque deque, DequeElem *ptElem);
/**
 * @brief Dequeue the element in end of a deque.
 * 
 * @param deque [in] pointer to the deque
 * @param ptElem [out] address of variable to hold the value
 * 
 * @return DEQUE_OK if successful and value in 'ptElem', or
 * @return DEQUE_EMPTY if the deque is empty, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */
int dequeDequeueEnd(PtDeque deque, DequeElem *ptElem);

/**
 * @brief Retrieves the element in the front of a deque.
 * 
 * @param deque [in] pointer to the deque
 * @param ptElem [out] address of variable to hold the value
 * 
 * @return DEQUE_OK if successful and value in 'ptElem', or
 * @return DEQUE_EMPTY if the deque is empty, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */

int dequeFront(PtDeque deque, DequeElem *ptElem);

/**
 * @brief Retrieves the element in the end of a deque.
 * 
 * @param deque [in] pointer to the deque
 * @param ptElem [out] address of variable to hold the value
 * 
 * @return DEQUE_OK if successful and value in 'ptElem', or
 * @return DEQUE_EMPTY if the deque is empty, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */
int dequeEnd(PtDeque deque, DequeElem *ptElem);


/**
 * @brief Retrieves the size of a deque.
 * 
 * @param stack [in] pointer to the deque
 * @param ptSize [out] address of variable to hold the value
 * 
 * @return DEQUE_OK if successful and value in 'ptSize', or
 * @return DEQUE_NULL if 'deque' is NULL 
 */


 
int dequeSize(PtDeque deque, int *ptSize);

/**
 * @brief Checks whether the deque is empty.
 * 
 * @param deque [in] pointer to the deque
 * 
 * @return 'true' if empty or if 'deque' is NULL, or
 * @return 'false' it not empty
 */
bool dequeIsEmpty(PtDeque deque);

/**
 * @brief Clears the contents of a deque.
 * 
 * This operation will remove all current elements in the deque,
 * returning to an empty state.
 * 
 * @param deque [in] pointer to the deque
 * 
 * @return DEQUE_OK if successful, or
 * @return DEQUE_NULL if 'deque' is NULL 
 */
int dequeClear(PtDeque deque);

/**
 * @brief Prints the contents of a deque.
 * 
 * @param deque [in] pointer to the deque
 */
void dequePrint(PtDeque deque);


