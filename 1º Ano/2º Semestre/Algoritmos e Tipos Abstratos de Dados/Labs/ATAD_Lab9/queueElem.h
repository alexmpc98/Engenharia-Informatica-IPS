/**
 * @file queueElem.h
 * @brief Defines the type QueueElem.
 * 
 * The QueueElem is an alias to the type of elements 
 * held by an instance of the ADT Queue (PtQueue).
 * 
 * This alias just be changed according to the use-case.
 * 
 * @author Bruno Silva (brunomnsilva@gmail.com)
 * @bug No known bugs.
 */

#pragma once
#include "move.h"
/** Type definition. Change according to the use-case. */
typedef Move QueueElem;

/**
 * @brief Prints an element.
 * 
 * Must be implemented according to type
 * of defined for QueueElem.
 * 
 * @param elem [in] element to print
 */
void queueElemPrint(QueueElem elem);
