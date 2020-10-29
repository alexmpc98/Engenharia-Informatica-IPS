#include "deque.h"
#include "accessManagement.h"
#include <stdio.h>
#include <time.h>


int main(){
    WebAccess web= webAccessCreate("http://www.ips.pt");
    webAccessPrint(web);
    PtWebAcessManagement webM= accessManagementCreate(3);
    accessManagementSave(webM,"http://www.ips.pt");
    accessManagementSave(webM,"http://www.google.pt");
    accessManagementSave(webM,"http://www.estsetubal.pt");
    accessManagementPrint(webM);
    accessManagementSave(webM,"http://www.audi.pt");
    accessManagementPrint(webM);
    int error=accessManagementUndo(webM,&web);
    if(error==ACCESS_ERROR)
        printf("undo is not avaiable");
    else {
        webAccessPrint(web);
    }
    accessManagementPrint(webM);
    accessManagementClear(webM);
    accessManagementPrint(webM);
    accessManagementDestroy(&webM) ;                   ;
}