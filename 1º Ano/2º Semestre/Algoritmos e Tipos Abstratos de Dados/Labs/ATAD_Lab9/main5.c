#include "hanoiGame.h"
#include <stdio.h>
#include <stdlib.h>
#include "queue.h"


void reviewHanoiGame(PtHanoiGame ptGame, PtQueue ptQueue);

int main(){

    PtHanoiGame ptGame=  hanoiGameCreate();
    printf("A\n");
    int errorCode=hanoiGameinit(ptGame,4);
    printf("b %d\n", errorCode);
    hanoiGamePrint(ptGame);
    bool gameOver=false;
    int x,y,error;
   
    while (gameOver==false)
    {
        printf("move from  (1,2,3, 0-quit)>" );
        scanf("%d",&x);
        if(x==0) break;
        printf("move To (1,2,3, 0-quit) > " );
        scanf("%d",&y);
        if( y==0) break;
        if(hanoiGameValidatePos(x) && hanoiGameValidatePos(y)){
             Move move=createMove(x,y);
             error=hanoiGameMakeMove(ptGame,move);
             if(error==HANOI_OK){
             
                hanoiGamePrint(ptGame);
            }
            else{
                    printf("move not allowed\n");
                }
        }     

    gameOver=hanoiGameCheckGameOver(ptGame);
    }
    if(gameOver){
        int moves;
        
        printf("CONGRATULATIONS  you have made %d moves\n", numberOfMovementsDone(ptGame));
    }
     char res;
    printf("Pretende rever as jogadas ? (S/N)" );
    scanf(" %c%*[^\n]",&res);
    if(res=='s' || res=='S'){
        printf(" \n\n -----REVIEWING THE MOVES-----\n\n");
        hanoiReplayGame(ptGame);
    }
    hanoiGameDestroy(&ptGame);
    return EXIT_SUCCESS;
}



