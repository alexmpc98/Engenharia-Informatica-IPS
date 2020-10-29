#pragma once
#include "time.h"


typedef struct webAccess{
   time_t timestamp;
   char url[100]; 
}WebAccess;


WebAccess webAccessCreate(char *url);
void webAccessPrint(WebAccess web);