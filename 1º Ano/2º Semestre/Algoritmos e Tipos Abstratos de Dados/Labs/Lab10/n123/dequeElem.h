/**
 * @file dequeElem.h
 * @brief Defines the type QueueElem.
 * 
 * The QueueElem is an alias to the type of elements 
 * held by an instance of the ADT Queue (PtQueue).
 * 
 * This alias just be changed according to the use-case.
 * 
 * @author Patricia Macedo
 * @bug No known bugs.
 */

#pragma once

/** Type definition. Change according to the use-case. */

typedef int DequeElem;

/**
 * @brief Prints an element.
 * 
 * Must be implemented according to type
 * of defined for DequeElem.
 * 
 * @param elem [in] element to print
 */
void dequeElemPrint(DequeElem elem);
