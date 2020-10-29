#include "disk.h"
#include <stdio.h>

void printDisk(Disk disk){
    printf("D%d ",disk.value);

}

Disk createDisk(int v)
{
    Disk d;
    d.value=v;
    return d;
}

