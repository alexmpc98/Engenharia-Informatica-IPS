/**
 * @file hanoiGame.c
 * 
 * @brief Provides an implementation of the HanoiGame with n disks, where each tower is implemented as a Stack
 * 
 * 
 * @author Patricia Macedo
 * @bug No known bugs.
 */
#include "hanoiGame.h"
#include "stack.h"
#include <stdlib.h>
#include <stdio.h>
#include "queue.h"

int validateMove(PtStack towerFrom, PtStack towerTo );
void initTower1(PtHanoiGame ptGame);

struct hanoiGame{
    PtStack towers[TOWERS];
    int numberDisks;
    PtQueue moves;
};

PtHanoiGame hanoiGameCreate(){
    PtHanoiGame ptGame= (PtHanoiGame) malloc(sizeof(struct hanoiGame));
    ptGame->numberDisks=0;
    ptGame->towers[0]= stackCreate(10);
    ptGame->towers[1]= stackCreate(10);
    ptGame->towers[2]= stackCreate(10); 
    ptGame->moves=queueCreate(20);
    return ptGame;
}

int hanoiGameinit(PtHanoiGame ptGame,int n){
    if (ptGame==NULL) return HANOI_NULL;
    ptGame->numberDisks=n;
    for(int i=0;i<ptGame->numberDisks;i++){
        stackPush(ptGame->towers[0], createDisk(ptGame->numberDisks-i));
    }
    return HANOI_OK;   
}
int hanoiGamePrint(PtHanoiGame ptGame){
    printf("--------------------------------------\n");
    printf("TOWER 1 ");
    stackPrint(ptGame->towers[0]);
    printf("TOWER 2 ");
    stackPrint(ptGame->towers[1]);
    printf("TOWER 3 ");
    stackPrint(ptGame->towers[2]);
    printf("--------------------------------------\n");
}
int hanoiGameMakeMove(PtHanoiGame ptGame, Move move){
    PtStack towerFrom=ptGame->towers[move.x-1];
    PtStack towerTo=ptGame->towers[move.y-1];
    Disk disk,diskTo;
    int error=validateMove(towerFrom,towerTo);
    if(!error){
        stackPop(towerFrom, &disk);
        stackPush(towerTo,disk);
        hanoiGamePrint(ptGame);
        queueEnqueue(ptGame->moves,move); //nivel 5
    }
    
    return error;
}
/**
 * @brief validate the identification of a tower to build a move
 * 
 * @param t the numeber of the tower to play
 * @return true if t >0 or t<=TOWERS
 * @return false 
 */
bool hanoiGameValidatePos(int t){
  return t>0 && t <= TOWERS;
}
/**
 * @brief 
 * 
 * @param towerFrom number of the tower to take the disk
 * @param towerTo number of the tower to put the disk
 * @return int HANOI_INVALID if the move is not valid HANOI_OK otherwise
 */
int validateMove(PtStack towerFrom, PtStack towerTo ){
    Disk diskFrom, diskTo;
    int error=stackPeek(towerFrom,&diskFrom);
    if(error!=STACK_OK)
        return HANOI_INVALID;
    
    int size;
    stackSize(towerTo,&size);
    if(size!=0)
    {
        stackPeek(towerTo, &diskTo);
        if(diskFrom.value<diskTo.value)
            return HANOI_OK;
        else
        {
            return HANOI_INVALID;
        }
        
    } 
    return HANOI_OK;
}

int  numberOfMovementsDone(PtHanoiGame ptGame){
  int size;
  queueSize(ptGame->moves,&size);
  return size;

}

bool hanoiGameCheckGameOver(PtHanoiGame ptGame){
    PtStack tower2=ptGame->towers[1];
    int size2;
    stackSize(tower2,&size2);
    return (size2==ptGame->numberDisks);
}
void hanoiGameClear(PtHanoiGame ptGame){
    stackClear(ptGame->towers[0]);
    stackClear(ptGame->towers[1]);
    stackClear(ptGame->towers[2]);    
     for(int i=0;i<ptGame->numberDisks;i++){
        stackPush(ptGame->towers[0], createDisk(ptGame->numberDisks-i));
    }
}


void hanoiReplayGame(PtHanoiGame ptGame){
    Move move;
    hanoiGameClear(ptGame);
    hanoiGamePrint(ptGame);
    PtQueue movesToReplay=ptGame->moves;
    ptGame->moves=queueCreate(10);
    while(!queueIsEmpty(movesToReplay)){
        queueDequeue(movesToReplay,&move);
        printMove(move);
        hanoiGameMakeMove(ptGame,move);        
    }
    queueDestroy(&movesToReplay);
}  


void hanoiGameDestroy(PtHanoiGame* ptGame){
    stackDestroy(&(*ptGame)->towers[0]);
    stackDestroy(&(*ptGame)->towers[1]);
    stackDestroy(&(*ptGame)->towers[2]);
    queueDestroy(&(*ptGame)->moves);
    free(*ptGame);
    ptGame=NULL;
}