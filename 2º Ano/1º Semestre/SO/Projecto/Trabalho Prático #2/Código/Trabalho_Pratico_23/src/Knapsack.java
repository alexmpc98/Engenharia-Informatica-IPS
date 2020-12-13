import java.util.ArrayList;

public class Knapsack {
    int knapsackValuesSum;
    int knapsackWeightsSum;
    int knapsackIndex;
    ArrayList<Integer> knapsackBin;

    public void setKnapsack(int knapsackValues, int knapsackWeights, int knapsackIndex, ArrayList<Integer> knapsackBin) {
        this.knapsackValuesSum = knapsackValues;
        this.knapsackWeightsSum = knapsackWeights;
        this.knapsackIndex = knapsackIndex;
        this.knapsackBin = knapsackBin;
    }

    public int getKnapsackValuesSum() {
        return knapsackValuesSum;
    }

    public void setKnapsackValuesSum(int knapsackValuesSum) {
        this.knapsackValuesSum = knapsackValuesSum;
    }

    public int getKnapsackWeightsSum() {
        return knapsackWeightsSum;
    }

    public void setKnapsackWeightsSum(int knapsackWeightsSum) {
        this.knapsackWeightsSum = knapsackWeightsSum;
    }

    public int getKnapsackIndex() {
        return knapsackIndex;
    }

    public ArrayList<Integer> getKnapsackBin() {
        return knapsackBin;
    }

    public void setKnapsackBin(ArrayList<Integer> knapsackBin) {
        this.knapsackBin = knapsackBin;
    }

    public void setKnapsackIndex(int knapsackIndex) {
        this.knapsackIndex = knapsackIndex;
    }
}
