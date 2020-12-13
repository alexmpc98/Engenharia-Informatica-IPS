public class KnapsackValues {
        int knapsackValues[];

        public void setKnapsackValue(int knapsackValues[]) {
            this.knapsackValues = knapsackValues;
        }

        public int[] getKnapsackValues() {
            return this.knapsackValues;
        }

        // CACULATES THE VALUE
        int calculate_value(int knapsackValues[], int bag_values[], int n) {
            int value_sum = 0;
            for (int i = 0; i < n; i++) {
                value_sum = value_sum + (knapsackValues[i] * bag_values[i]);
            }
            return value_sum;
        }
}
