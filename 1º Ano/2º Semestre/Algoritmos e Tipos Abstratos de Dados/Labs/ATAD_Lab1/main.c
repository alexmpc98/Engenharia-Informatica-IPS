#include <stdio.h> 
#include <stdlib.h>
#include "time.h"

int main(){
    Time NewTime;
    Time updatedTime;
    Time SecondsInTime;
    Time Diff;
    Time ToCompare1;
    Time ToCompare2;

    NewTime = timeCreate(5,34,59);
    updatedTime = timeUpdate(NewTime);
    timePrint(updatedTime);

    /* Seconds to Time */
    SecondsInTime = convertSecondsToTime(4600);
    timePrint(SecondsInTime);

    printSecondsAndTime(90000);

    /* Nivel 5 */
    ToCompare1 = timeCreate(5,34,59);
    ToCompare2 = timeCreate(7,35,50);
    Diff = diffTime(ToCompare1,ToCompare2);
    timePrint(Diff);

}