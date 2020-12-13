public class KnapsackWeights {
    int knapsackWeights[];

    public void setKnapsackWeight(int knapsackWeights[]) {
        this.knapsackWeights = knapsackWeights;
    }

    public int[] getKnapsackWeights() {
        return this.knapsackWeights;
    }

    // CALCULATES THE WEIGHT
    int calculate_weight(int knapsackWeights[], int bag_values[], int n) {
        int weight_sum = 0;
        for (int i = 0; i < n; i++) {
            weight_sum = weight_sum + (knapsackWeights[i] * bag_values[i]);
        }
        return weight_sum;
    }
}
