#pragma once

#include "item.h"
#include <stdbool.h>

typedef struct menuItem{
    char key[100];
} MenuItem;

typedef int MapKey;
typedef Item MapValue;

void mapKeyPrint(MapKey key);
void mapValuePrint(MapValue value);

bool mapKeyEquals(MapKey key1, MapKey key2);
