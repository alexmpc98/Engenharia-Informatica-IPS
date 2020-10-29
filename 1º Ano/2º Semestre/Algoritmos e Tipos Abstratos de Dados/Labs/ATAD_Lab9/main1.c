#include "disk.h"
#include "move.h"
#include <stdio.h>
#include <stdlib.h>

int main(){

 Disk d= createDisk(1);
 printDisk(d);
 printf("\n");
 Move m=createMove(2,3);
 printMove(m);
 return EXIT_SUCCESS;
}