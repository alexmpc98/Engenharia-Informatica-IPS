#include "stringCode.h"
#include <string.h>
#include <stdio.h>

StringCode stringCodeCreate(char *str) {
    StringCode strCode;
    strcpy(strCode.code,str);
    return strCode;


}

void stringCodePrint(StringCode str) {
 printf(" %s ", str.code);

}