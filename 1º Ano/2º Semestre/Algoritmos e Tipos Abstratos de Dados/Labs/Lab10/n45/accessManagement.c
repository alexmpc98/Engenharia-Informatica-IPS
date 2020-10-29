#include "accessManagement.h"
#include "webAccess.h"
#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>

PtWebAcessManagement accessManagementCreate (int max) {
    PtWebAcessManagement webManagement= (PtWebAcessManagement) malloc (sizeof(WebAcessManagement));
    webManagement->webList= dequeCreate(max);
    webManagement->maxSize=max;
    return webManagement;

}
void accessManagementDestroy(PtWebAcessManagement* webManagement){     
        PtDeque deque= (*webManagement)->webList;
        dequeDestroy(&deque);
        free(*webManagement);
}
void accessManagementClear(PtWebAcessManagement webManagement){
    dequeClear(webManagement->webList);
}
void accessManagementPrint(PtWebAcessManagement webManagement){
    printf("Web Acess list -> ");
    dequePrint(webManagement->webList);
}
void accessManagementSave(PtWebAcessManagement webManagement, char* url){
    int size;
    WebAccess elem;
    dequeSize(webManagement->webList,&size);
    if( size == webManagement->maxSize)
        dequeDequeueEnd(webManagement->webList,&elem);
    WebAccess web= webAccessCreate(url);
    dequeEnqueueFront(webManagement->webList,web);


}

int accessManagementUndo(PtWebAcessManagement webManagement, WebAccess* ptElem){
    int erro= dequeDequeueFront(webManagement->webList,ptElem);
    if(erro!=DEQUE_OK)
        return ACCESS_ERROR;
    return ACCESS_OK;
}