public class Knapsack {
    int knapsack[];

    public void setKnapsack(int knapsack[]) {
        this.knapsack = knapsack;
    }

    public int[] getKnapsack() {
        return this.knapsack;
    }

    public void changeKnapsack(int knapsack, int position) {
        this.knapsack[position] = knapsack;
    }

    public int getChangedValueKnapsack(int position) {
        return this.knapsack[position];
    }
}
