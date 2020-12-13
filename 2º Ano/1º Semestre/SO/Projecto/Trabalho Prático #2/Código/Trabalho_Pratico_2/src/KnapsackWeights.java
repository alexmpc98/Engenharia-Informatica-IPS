public class KnapsackWeights {
    int knapsackWeights[];

    // SETS THE KNAPSACK WEIGHTS
    public void setKnapsackWeights(int knapsackWeights[]) {
        this.knapsackWeights = knapsackWeights;
    }

    // GETS THE KNAPSACK WEIGHTS
    public int[] getKnapsackWeights() {
        return this.knapsackWeights;
    }

    // CALCULATES THE KNAPSACK WEIGHT
    int calculate_weight(int knapsackWeights[], int bag_values[], int n) {
        int weight_sum = 0;
        for (int i = 0; i < n; i++) {
            weight_sum = weight_sum + (knapsackWeights[i] * bag_values[i]);
        }
        return weight_sum;
    }
}
