#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include "kahoot.h"
#include "utils.h"
#include "list.h"
#include "listElem.h"

typedef unsigned long long timestamp_t;

static timestamp_t get_timestamp();

int main(){
    //Calculate time taken by a code segment
    printf("Timer starts\n");

    timestamp_t t0 = get_timestamp();

    // CODE TO TESTE <---- HERE ---->
    PtList kr = NULL; 
    importKahootFromFile("kahootReports.csv",&kr);
    listPrint(kr);

    printf("Timer ends\n");

    // caculate the elapsed time
    timestamp_t t1 = get_timestamp();
    double secs = (t1-t0) / 1000000.0L;

    printf("The program took %f microseconds to execute\n",secs);
    listDestroy(&kr);
    return EXIT_SUCCESS;
}


/**
 * @brief Este algoritmo calcula e devolve o timestamp em microssegundos
 * 
 * @return double (microssegundos)
 */
static timestamp_t get_timestamp(){
    struct timeval now;
    gettimeofday(&now,NULL);
    return now.tv_usec + (timestamp_t)now.tv_sec * 1000000;
}