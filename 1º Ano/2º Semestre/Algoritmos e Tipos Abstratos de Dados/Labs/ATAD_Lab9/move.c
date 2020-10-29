#include "move.h"
#include <stdio.h>

void printMove(Move move){
    printf("(%d,%d)\n",move.x,move.y);

}

Move createMove(int x, int y){
    Move m={x,y};    
    return m;
}