#include "hanoiGame.h"
#include <stdio.h>
#include <stdlib.h>


int main(){

    
    PtHanoiGame ptGame= hanoiGameCreate();
    int errorcode=hanoiGameinit(ptGame,4);
    hanoiGamePrint(ptGame);
    bool gameOver=false;
    int x,y,error;
    while (gameOver==false){
        printf("move from  (1,2,3, 0-quit)>" );
        scanf("%d",&x);
        if(x==0) break;
        printf("move To > (1,2,3, 0-quit" );
        scanf("%d",&y);
        if( y==0) break;
        if(hanoiGameValidatePos(x) && hanoiGameValidatePos(y)){
             
             error=hanoiGameMakeMove(ptGame,createMove(x,y));
            
             if(error==HANOI_OK)
                hanoiGamePrint(ptGame);
            else
                {
                    printf("move not allowed\n");
                }
        }     

    gameOver=hanoiGameCheckGameOver(ptGame);
    }
    if(gameOver)
        printf("CONGRATULATIONS ");
    hanoiGameDestroy(&ptGame);
        return EXIT_SUCCESS;
}