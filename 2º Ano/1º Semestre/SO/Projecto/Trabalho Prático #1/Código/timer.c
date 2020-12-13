#include <stdio.h>
#include <time.h>   	// for time()
#include <unistd.h> 	// for sleep()

int main()
{
	time_t begin = time(NULL);

	sleep(3);

	time_t end = time(NULL);

	printf("Time elapsed is %ld seconds", (end - begin));
}
