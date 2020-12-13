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

// Obtains the values from the text file
void getValuesFromFile(char filename[30]){
  int n, max_weight, otimo;
  int temp, temp2, num;
  int isOtimo = 0;
  FILE *in_file;
  in_file = fopen(filename, "r");

  if (in_file == NULL)
  {
      printf("Can't open file for reading.\n");
  }
  else
  {
      fscanf(in_file, "%d", &n);
      fscanf(in_file, "%d", &max_weight);

      int countNumber = 0;
      while(fscanf(in_file, "%d", &temp) == 1){
        countNumber++;
      }
      countNumber--;
      if(countNumber % 2 == 0) isOtimo = 1;

      rewind(in_file);
      fscanf(in_file, "%d %d", &temp2, &temp2); //skip 2 positions

      printf("%d\n", n);
      printf("%d\n", max_weight);

      int value[n];
      int weight[n];

      int countLine = 2;
      int countValue = 0;
      int countWeight = 0;
      for(int i = 0; i < n*2; i++){
        if(countLine == i){
         countLine += 2;
         printf("\n");
        }

        if(i % 2 == 0){
          fscanf(in_file, "%d", &num);
          value[countValue] = num;
          printf("%d ", value[countValue]);
          countValue++;
        } else {
          fscanf(in_file, "%d", &num);
          weight[countWeight] = num;
          printf("%d ", weight[countWeight]);
          countWeight++;
        }
      }

      if(isOtimo == 1) {
        fscanf(in_file, "%d", &otimo);
        printf("\n%d", otimo);
      }

      printf("\nNUMEROS: %d", countNumber);

      printf("\n----------------------\n");

      for(int i = 0; i < n; i++){
        printf("%d ", value[i]);
      }

      printf("\n----------------------\n");

      for(int i = 0; i < n; i++){
        printf("%d ", weight[i]);
      }

      printf("\n");

      int totalWeight = 0;
      for(int i = 0; i < n; i++){
        totalWeight += weight[i];
      }
      printf("\nTOTAL WEIGHT: %d", totalWeight);

      fclose(in_file);
  }
}

// CALCULATES THE VALUE
int calc_value (int *values, int bag_values[], int n, int in) {
  //printf("BAG: %d - %d/%d/%d/%d \n", pid, bag_values[0], bag_values[1], bag_values[2], bag_values[3]);

  int value_sum = 0;
  int weight_sum = 0;
  int knapVal = 0;
    for (int i=0;i<n;i++) {
      knapVal = bag_values[i];
      value_sum = value_sum  + (values[i] * knapVal);
      //weight_sum = weight_sum  + (weights[i] * knapVal);
      if (value_sum == 29) {
          //printf("CHECK: %d - %d - %d\n", weight_sum, value_sum, n);
          //printf("CHECK: %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", (weights[0] * bag_values[0]), (weights[1] * bag_values[1]), (weights[2] * bag_values[2]), (weights[3] * bag_values[3]), weight_sum, value_sum, (values[0] * bag_values[0]), (values[1] * bag_values[1]), (values[2] * bag_values[2]), (values[3] * bag_values[3]), bag_values[0], bag_values[1], bag_values[2], bag_values[3]);
          //printf("CHECK: %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", (weights[0] * knapsack[0]), (weights[1] * knapsack[1]), (weights[2] * knapsack[2]), (weights[3] * knapsack[3]), weight_sum, value_sum, (values[0] * 0), (values[1] * 0), (values[2] * 0), (values[3] * 0), knapsack[0], knapsack[1], knapsack[2], knapsack[3]);
          //printf("CHECK: %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", (weights[0] * bag_values[0]), (weights[1] * bag_values[1]), (weights[2] * bag_values[2]), (weights[3] * bag_values[3]), weight_sum, value_sum, (values[0] * bag_values[0]), (values[1] * bag_values[1]), (values[2] * bag_values[2]), (values[3] * bag_values[3]), bag_values[0], bag_values[1], bag_values[2], bag_values[3]);
          //printf("CHECK_VALUE: %d - %d/%d/%d/%d\n", in, bag_values[0], bag_values[1], bag_values[2], bag_values[3]);

      }


    }

    return value_sum;
}


void calc_value_weight (int *values, int *weights, int *bag_values, int n, int in) {
  //printf("BAG: %d - %d/%d/%d/%d \n", pid, bag_values[0], bag_values[1], bag_values[2], bag_values[3]);

  int value_sum = 0;
  int weight_sum = 0;
  int knapVal = 0;
    for (int i=0;i<n;i++) {
      knapVal = bag_values[i];
      value_sum = value_sum  + (values[i] * knapVal);
      weight_sum = weight_sum  + (weights[i] * knapVal);
      //weight_sum = weight_sum  + (weights[i] * knapVal);
      if (value_sum == 29) {
          //printf("CHECK: %d - %d - %d\n", weight_sum, value_sum, n);
          //printf("CHECK: %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", (weights[0] * bag_values[0]), (weights[1] * bag_values[1]), (weights[2] * bag_values[2]), (weights[3] * bag_values[3]), weight_sum, value_sum, (values[0] * bag_values[0]), (values[1] * bag_values[1]), (values[2] * bag_values[2]), (values[3] * bag_values[3]), bag_values[0], bag_values[1], bag_values[2], bag_values[3]);
          //printf("CHECK: %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", (weights[0] * knapsack[0]), (weights[1] * knapsack[1]), (weights[2] * knapsack[2]), (weights[3] * knapsack[3]), weight_sum, value_sum, (values[0] * 0), (values[1] * 0), (values[2] * 0), (values[3] * 0), knapsack[0], knapsack[1], knapsack[2], knapsack[3]);
          printf("CHECK: %d/%d/%d/%d - %d/%d/%d/%d - %d - %d - %d/%d/%d/%d - %d/%d/%d/%d\n", bag_values[0], bag_values[1], bag_values[2], bag_values[3], (weights[0] * (bag_values[0])), (weights[1] * bag_values[1]), (weights[2] * bag_values[2]), (weights[3] * bag_values[3]), weight_sum, value_sum, (values[0] * bag_values[0]), (values[1] * bag_values[1]), (values[2] * bag_values[2]), (values[3] * bag_values[3]), bag_values[0], bag_values[1], bag_values[2], bag_values[3]);
          //printf("CHECK_VALUE: %d - %d/%d/%d/%d\n", in, bag_values[0], bag_values[1], bag_values[2], bag_values[3]);

      }


    }

    //return value_sum;
}


// CALCULATES THE WEIGHT
int calc_weight (int *weights, int bag_values [], int n, int in) {
  int weight_sum = 0;

    for (int i=0;i<n;i++) {
      weight_sum = weight_sum  + (weights[i] * bag_values[i]);
      //printf("PID: %d - %d - %d\n", knapsack[i], weight_sum, n);
    }
    if (weight_sum == 11) {
    printf("CHECK_WEIGHT: %d - %d/%d/%d/%d\n", in, bag_values[0], bag_values[1], bag_values[2], bag_values[3]);
}

    return weight_sum;
}

// MODIFIES THE KNAPSACK
void modify_knapsack (
    int *knapsack,
    int *weights,
    int *values,
    int n,
    int process_id,
    int *sm_bestVal,
    int *sm_pid,
    sem_t *mutex

  ){
  int random = 0;
  int x = 0;
  int value = 0;
  int weight = 0;
  int in = 0;
  while(1){
    random = rand() % n;
    x = knapsack[random];
    x = 1 - x;
    knapsack[random] = x;
    //sem_wait(mutex);
    /*value = calc_value (values, knapsack, n, in);
    weight = calc_weight (weights, knapsack, n, in);*/
    calc_value_weight (values, weights, knapsack, n, in);
    /*if (weight <= 11 && value > *sm_bestVal) {
      *sm_bestVal = value;
      *sm_pid = process_id;
      printf("PID: %d\n", process_id);
      printf("Values Sum: %d\n", value);
      printf("Weight Sum %d\n", weight);
    }*/
      //sem_post(mutex);
      in++;

  }
}

int main(int argc, char *argv[]) {
  // CREATES THE SEMAPHORE
  sem_unlink("mymutex");
  sem_t *mutex = sem_open("mymutex", O_CREAT, 0644, 1);

  // INICIATES RANDOM NUMBERS
  srand(time(NULL));
  // INICIATES THE SIGNAL
  // INICIATES THE ARRAYS AND OTHER VARIABLES
  int weights[] = {2,4,6,7};
  int values[] = {6,10,12,13};
  int n = 4;
  int max_weight = 11;
  int knapsack[] = {0,0,0,0};
  int test_number = 0;
  char filename[30] = {};
  int fork_number = 0;
  int forks_id [] = {};
  int run_time = 0; //SECONDS
  // CREATES THE SHARED MEMORY
  int size = 64;
  int protection = PROT_READ | PROT_WRITE;
  int visibility = MAP_ANONYMOUS | MAP_SHARED;
  int *sm_bestVal = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  int *sm_pid = mmap(NULL, sizeof(int), protection, visibility, 0, 0);
  
  if (argc = 3) {
    test_number = atoi(argv[1]);
    strcpy(filename,argv[2]);
    fork_number = atoi(argv[3]);
    run_time = atoi(argv[4]);
    printf("TN: %d\n", test_number);
    printf("F: %s\n", filename);
    printf("FN: %d\n", fork_number);
    printf("RT: %d\n", run_time);
  }
 // getValuesFromFile(filename);
  
  for (int i =0;i<fork_number;i++) {
    forks_id[i] = fork();
    if (forks_id[i] == 0) {
      //sem_wait(mutex);
      printf("--------  PID: %d -------- \n", getpid());
      modify_knapsack(knapsack, weights, values, n, getpid(), sm_bestVal, sm_pid, mutex);
      //  sem_post(mutex);
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
  sem_close(mutex);
  printf("MEMORY PID: %d\n", *sm_pid);
  printf("MEMORY VALUE %d\n", *sm_bestVal);
  return 0;
}
