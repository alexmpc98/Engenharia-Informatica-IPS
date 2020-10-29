#include "deque.h"
#include "webAccess.h"
#define ACCESS_OK 0
#define ACCESS_ERROR 1
typedef struct  webAcessManagement{
   PtDeque webList;
   int maxSize;

}WebAcessManagement;
typedef WebAcessManagement* PtWebAcessManagement;

PtWebAcessManagement accessManagementCreate (int max) ;
void accessManagementDestroy(PtWebAcessManagement* webManagement);
void accessManagementClear(PtWebAcessManagement webManagement);
void accessManagementPrint(PtWebAcessManagement webManagement);
void accessManagementSave(PtWebAcessManagement webManagement, char* url);
int accessManagementUndo(PtWebAcessManagement webManagement, WebAccess* ptElem);