import java.util.ArrayList;

public class SharedMemory {
    ArrayList<Integer> bestKnapsack;
    int bestWeight;
    int bestValue;
    int threadNumber;
    int testNumber;
    String testName;
    int numberOfNumbers;

    public int getNumberOfNumbers() {
        return numberOfNumbers;
    }

    public void setNumberOfNumbers(int numberOfNumbers) {
        this.numberOfNumbers = numberOfNumbers;
    }

    public int getBestValue() {
        return bestValue;
    }

    public void setBestValue(int bestValue) {
        this.bestValue = bestValue;
    }

    public ArrayList<Integer> getBestKnapsack() {
        return bestKnapsack;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setBestKnapsack(ArrayList<Integer> bestKnapsack) {
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
                "bestKnapsack=" + bestKnapsack +
                ", bestWeight=" + bestWeight +
                ", bestValues=" + bestValue +
                ", threadNumber=" + threadNumber +
                ", testNumber=" + testNumber +
                ", testName='" + testName + '\'' +
                ", numberOfNumbers=" + numberOfNumbers +
                '}';
    }

}
