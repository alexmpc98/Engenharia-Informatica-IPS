typedef struct {
     int hours;
     int minutes;
     int seconds;
} Time;

void timePrint(Time t);
Time timeCreate(int h,int m,int s);
Time timeUpdate(Time t);
Time convertSecondsToTime(int s);
void printSecondsAndTime(int n);
Time diffTime(Time t1,Time t2);