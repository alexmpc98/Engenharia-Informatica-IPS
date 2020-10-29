#include "hanoiGame.h"
#include <stdio.h>
#include <stdlib.h>

int main(){
   
    PtHanoiGame ptGame=  hanoiGameCreate();
    int errorcode=hanoiGameinit(ptGame,4);
    hanoiGamePrint(ptGame);
    Move m= createMove(1,2);
    printMove(m);
    int error=hanoiGameMakeMove(ptGame,m);
    printf("result %d \n", error);
    
    m= createMove(1,2);
    printMove(m);
    error=hanoiGameMakeMove(ptGame,m);
    printf("result %d \n", error);
    m= createMove(1,3);
    printMove(m);
    error=hanoiGameMakeMove(ptGame,m);
    printf("result %d \n", error);
    m= createMove(2,3);
    printMove(m);
    error=hanoiGameMakeMove(ptGame,m);
    printf("result %d \n", error);
    hanoiGameDestroy(&ptGame);
    return EXIT_SUCCESS;
}
