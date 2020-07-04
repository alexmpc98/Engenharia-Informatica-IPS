#include "time.h"
#include <stdio.h> 
#include <stdlib.h>

 /**
    * @brief Time Print
    *
    * @param t
    * 
    */
void timePrint(Time t){  
    printf("%d:%d:%d\n",t.hours,t.minutes,t.seconds);
}

  /**
    * @brief Time Creation
    *
    * @param h
    * @param m
    * @param s
    * 
    * @return NewTime
    */

Time timeCreate(int h, int m, int s){
    Time NewTime;
    NewTime.hours = h;
    NewTime.minutes = m;
    NewTime.seconds = s;
    return NewTime;
}

 /**
    * @brief Time Update
    *
    * @param t
    * 
    * @return t
    */

Time timeUpdate(Time t){ 
    t.seconds = t.seconds + 1;
    if(t.seconds == 59){
        t.seconds = 0;
        t.minutes = t.minutes + 1;
    }
    if(t.minutes == 59){
        t.minutes = 0;
        t.hours = t.hours + 1;
    }
    return t;
}

 /**
    * @brief Seconds To Time
    *
    * @param s
    * 
    * @return NewTime
    */
Time convertSecondsToTime(int s){
    Time NewTime;

    NewTime.hours = (s/3600); 
	NewTime.minutes = (s -(3600*NewTime.hours))/60;
	NewTime.seconds = (s -(3600*NewTime.hours)-(NewTime.minutes*60));
	
    return NewTime;
}

 /**
    * @brief Seconds to Time with Days
    *
    * @param n
    * 
    */
void printSecondsAndTime(int n){
    int day = n/(24*3600);

    n = n % (24 * 3600);
    int hour = n/3600;

    n %= 3600;
    int minutes = n/60;

    n %= 60;
    int seconds = n;

    printf("%d days %d hours %d minutes %d seconds\n", day, hour, minutes, seconds);
}
  /**
    * @brief Time Differential
    *
    * @param t1
    * @param t2
    * 
    * @return Diff
    */
Time diffTime(Time t1,Time t2){
    Time Diff;
    if(t1.hours > t2.hours){
        Diff.hours = t1.hours - t2.hours;
    }
    else{
        Diff.hours = t2.hours - t1.hours;
    }

    if(t1.minutes > t2.minutes){
        Diff.minutes = t1.minutes - t2.minutes;
    }
    else{
        Diff.minutes = t2.minutes - t1.minutes;
    }
    if(t1.seconds > t2.seconds){
        Diff.seconds = t1.seconds - t2.seconds;
    }
    else{
        Diff.seconds = t2.seconds - t1.seconds;
    }

    return Diff;
}    