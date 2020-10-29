// Este main tem o proposito de serem efetuados testes individuais - Será apagado mais tarde

#include <stdlib.h>
#include <stdio.h>
#include "structs.h"
#include "list.h"
#include "listElem.h"
#include "map.h"
#include "mapElem.h"
#include "comandosa.h"
#include "comandosb.h"
#include "comandosc.h"

int main(){
    PtList listPatientsCSV = NULL; 
    PtMap mapPatientsCSV = NULL; 
    loadP("patients.csv",&listPatientsCSV);
    loadR("regions.csv",&mapPatientsCSV);
    //listPrint(listPatientsCSV);
    //average(&listPatientsCSV);
    //long int id = 1000000003;
    //follow(&listPatientsCSV,id,false);
    //sex(&listPatientsCSV);
    //show(&listPatientsCSV,1000000020);
    //topFive(&listPatientsCSV);
    //oldest(&listPatientsCSV);
    //Date dateToSearch = createDate(25,02,2020);
    //growth(&listPatientsCSV,dateToSearch);
    //matrix(&listPatientsCSV);

    //clear(&listPatientsCSV, &mapPatientsCSV);
    //mapPrint(mapPatientsCSV);
    //regions(&mapPatientsCSV,&listPatientsCSV);
    report(&mapPatientsCSV, &listPatientsCSV);
    
    //mapPrint(mapPatientsCSV);
    return EXIT_SUCCESS;
}
