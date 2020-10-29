#include "hanoiGame.h"
#include <stdlib.h>
int main(){

 PtHanoiGame ptGame= hanoiGameCreate();
 int errorcode=hanoiGameinit(ptGame,4);
 hanoiGamePrint(ptGame);
 hanoiGameDestroy(&ptGame);
 return EXIT_SUCCESS;
}