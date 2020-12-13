import java.util.Arrays;

public class SharedMemory {
        String threadName;
        int bestKnapsack[];
        int bestWeight;

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }

        public int[] getBestKnapsack() {
            return bestKnapsack;
        }

        public void setBestKnapsack(int[] bestKnapsack) {
            this.bestKnapsack = bestKnapsack;
        }

        public int getBestWeight() {
            return bestWeight;
        }

        public void setBestWeight(int bestWeight) {
            this.bestWeight = bestWeight;
        }

        @Override
        public String toString() {
            return "SharedMemory{" +
                    "threadName='" + threadName + '\'' +
                    ", bestKnapsack=" + Arrays.toString(bestKnapsack) +
                    ", bestWeight=" + bestWeight +
                    '}';
        }
}
