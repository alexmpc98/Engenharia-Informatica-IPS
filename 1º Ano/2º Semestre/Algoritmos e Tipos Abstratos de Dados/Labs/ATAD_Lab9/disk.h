#pragma once
/**
 * @file disk.h
 * @brief Definition of the Type Disk of Hanoi Game.
 * 
 * Defines the type Diskand associated operations.
 * 
 * @author Patricia Macedo
 * @bug No known bugs.
 */

typedef struct disk{
  int value;
}Disk;

/**
 * @brief Prints a disk.
 * 
 * Print the value associated with the disk
 * 
 * @param disk to print
 */
void printDisk(Disk disk);

/**
 * @brief Creates a new Disk
 * 
 * @param value    The value of the disk
 
 * @return The new created disk.
 */
Disk createDisk(int v);


