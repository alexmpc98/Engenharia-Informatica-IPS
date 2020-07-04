#include <stdio.h>
#include <stdlib.h>
#include "kahoot.h"

int main(){
    KahootReport kr = KahootReportCreate(1,3,"Alex",3956,5,1);
    KahootReportPrint(kr);
    return EXIT_SUCCESS;
}