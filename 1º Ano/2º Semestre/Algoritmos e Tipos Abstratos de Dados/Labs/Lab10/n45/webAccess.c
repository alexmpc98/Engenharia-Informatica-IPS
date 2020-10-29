#include "webAccess.h"
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdint.h>

WebAccess webAccessCreate(char *url){
     WebAccess w;
     strcpy(w.url,url);
     time_t mytime;
     w.timestamp=time(NULL);;
     return w;

}
void webAccessPrint(WebAccess web){
     
     printf( " url:%s ", web.url);
     printf("%s", asctime(gmtime(&web.timestamp)));
}