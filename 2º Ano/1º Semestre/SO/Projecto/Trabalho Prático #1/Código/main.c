#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <semaphore.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>

// CACULATES THE VALUE
int calc_value (int *values, int bag_values[], int n) {
  int value_sum = 0;
    for (int i=0;i<n;i++) {
      value_sum = value_sum  + (values[i] * bag_values[i]);
    }
    return value_sum;
}

// CALCULATES THE WEIGHT
int calc_weight (int *weights, int bag_values[], int n) {
  int weight_sum = 0;
    for (int i=0;i<n;i++) {
      weight_sum = weight_sum  + (weights[i] * bag_values[i]);
    }
    return weight_sum;
}

// MODIFIES THE KNAPSACK
void modify_knapsack (
    int process_id,
    int* knapsack,
    int* values,
    int* weights,
    int n,
    int max_weight,
    int bestValue,
    int *sm_bestVal,
    int *sm_bestWei,
    int *sm_pid,
    double *sm_time,
    int *sm_in,
    int *sm_bestNum,
    clock_t begin,
    sem_t *mutex
  ){
  // INICIATES THE VARIABLES
  int random = 0;
  int x = 0;
  int value = 0;
  int weight = 0;
  int in = 1; // ITERATION NUMBER
  int bestSum = 0;
  while(1){
    // MODIFY THE KNAPSACK VALUES RANDOMLY
    random = rand() % n;
    x = knapsack[random];
    x = 1 - x;
    knapsack[random] = x;
    value = calc_value (values, knapsack, n);
    weight = calc_weight (weights, knapsack, n);
    sem_wait(mutex);
    if (bestValue == 0) {
      if (weight <= max_weight && value > *sm_bestVal) {
        *sm_bestWei = weight;
        *sm_bestVal = value;
        *sm_pid = process_id;
        clock_t end = clock();
        double bestTime = (double)(end - begin)/CLOCKS_PER_SEC;
        if ((double)(end - begin)/CLOCKS_PER_SEC < 0 ) {
          bestTime = bestTime * -1;
        }
        *sm_time = bestTime;
        *sm_in = in;
      }
    } else {
      if (value == bestValue) {
        bestSum++;
        *sm_bestNum = bestSum;
      }
      if (weight <= max_weight && value <= bestValue && value > *sm_bestVal) {
        *sm_bestWei = weight;
        *sm_bestVal = value;
        *sm_pid = process_id;
        clock_t end = clock();
        double bestTime = (double)(end - begin)/CLOCKS_PER_SEC;
        if ((double)(end - begin)/CLOCKS_PER_SEC < 0 ) {
          bestTime = bestTime * -1;
        }
        *sm_time = bestTime;
        *sm_in = in;
      }
    }
    sem_post(mutex);
    in++;
  }
}

int main(int argc, char *argv[]) {

  // START TIME
  clock_t begin = clock();

  // CREATES THE SEMAPHORE
  sem_unlink("mymutex");
  sem_t *mutex = sem_open("mymutex", O_CREAT, 0644, 1);

  // INICIATES RANDOM NUMBERS
  srand(time(NULL));

  // INICIATES THE ARRAYS AND OTHER VARIABLES
  int n, max_weight, bestValue;
  int temp, temp2, num;
  int hasBestValue = 0;
  int test_number = 0;
  char filename [30] = {};
  int fork_number = 0;
  int forks_id [] = {};
  int run_time = 0; //SECONDS

  // CREATES THE SHARED MEMORY
  int size = 64;
  int protection = PROT_READ | PROT_WRITE;
  int visibility = MAP_ANONYMOUS | MAP_SHARED;
  int *sm_bestVal = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  int *sm_bestWei = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  int *sm_pid = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  double *sm_time = mmap(NULL, sizeof(double), protection, visibility, 0, 0);
  int *sm_in = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  int *sm_bestNum = mmap(NULL, sizeof(int), protection, visibility, 0, 0);

  // READ RUNNING PARAMETERS
  if (argc = 3) {
    test_number = atoi(argv[1]);
    strcpy(filename, argv[2]);
    fork_number = atoi(argv[3]);
    run_time = atoi(argv[4]);
    printf("TEST NUMBER: %d\n", test_number);
    printf("FILE: %s\n", filename);
    printf("FORK NUMBER: %d\n", fork_number);
    printf("RUNNING TIME: %d Seconds\n", run_time);
  }

  // GETS THE FILE VALUES AND STORAGE THEM
  FILE *in_file;
  in_file = fopen(filename, "r");
  if (in_file == NULL){
      printf("Can't open file for reading.\n");
  } else {
      fscanf(in_file, "%d", &n);
      fscanf(in_file, "%d", &max_weight);
      int countNumber = 0;
      while(fscanf(in_file, "%d", &temp) == 1){
        countNumber++;
      }
      countNumber--;
      if(countNumber % 2 == 0)hasBestValue = 1;
      rewind(in_file);
      fscanf(in_file, "%d %d", &temp2, &temp2); //skip 2 positions
      int values[n];
      int weights[n];
      int knapsack[n];
      int countLine = 2;
      int countValue = 0;
      int countWeight = 0;
      for(int i = 0; i < n; i++){
        knapsack[i] = 0;
      }
      for(int i = 0; i < n*2; i++){
        if(i % 2 == 0){
          fscanf(in_file, "%d", &num);
          values[countValue] = num;
          countValue++;
        } else {
          fscanf(in_file, "%d", &num);
          weights[countWeight] = num;
          countWeight++;
        }
      }
      if(hasBestValue == 1) {
        fscanf(in_file, "%d", &bestValue);
      } else {
        bestValue = 0;
      }
      fclose(in_file);

      // CREATES THE FORKS AND START THE NUMBER ANALYZATION
      for (int i =0;i<fork_number;i++) {
        forks_id[i] = fork();
        if (forks_id[i] == 0) {
          modify_knapsack(
            getpid(),
            knapsack,
            values,
            weights,
            n,
            max_weight,
            bestValue,
            sm_bestVal,
            sm_bestWei,
            sm_pid,
            sm_time,
            sm_in,
            sm_bestNum,
            begin,
            mutex
          );
          exit(0);
        }
      }

      // RUNNING TIME
      sleep(run_time);

      // CLOSE THE FORKS
      for (int i =0;i<fork_number;i++) {
        kill(forks_id[i],SIGKILL);
        wait(NULL);
      }

      // VALUES STORED IN THE SHARED MEMORY
      printf("\n");
      printf("KNAPSACK ITEM NUMBER: %d\n", n);
      printf("MEMORY PID: %d\n", *sm_pid);
      printf("MEMORY BEST VALUE: %d\n", *sm_bestVal);
      printf("MEMORY BEST WEIGHT: %d\n", *sm_bestWei);
      printf("MEMORY BEST TIME: %f Seconds\n", *sm_time);
      printf("MEMORY ITERATION NUMBER: %d\n", *sm_in);
      printf("MEMORY NUMBER OF BEST SOLUTIONS FOUND: %d in %d Seconds\n", *sm_bestNum, run_time);
  }

  sem_close(mutex);
  return 0;
}
