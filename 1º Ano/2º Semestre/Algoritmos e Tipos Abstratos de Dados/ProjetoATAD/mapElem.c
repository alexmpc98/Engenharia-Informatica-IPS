/**
 * @file mapElem.c
 * @brief Implements operations for types MapKey and MapValue.
 * 
 * @author Bruno Silva (brunomnsilva@gmail.com)
 * @bug No known bugs.
 */

#include "mapElem.h"
#include <stdio.h>
#include <string.h>

void mapKeyPrint(MapKey key) {
	printf("\nKey Region - %s \n", key.region);
}

void mapValuePrint(MapValue value) {
	printf("Region - %s \nCapital - %s \nArea - %.3f Km2 \nPopulation - %d \n", value.name,value.capital, value.area,value.population);
}
bool mapKeyEquals(MapKey key1, MapKey key2) {
	// in case of integer keys:
	return strcmp(key1.region,key2.region) == 0; 
}