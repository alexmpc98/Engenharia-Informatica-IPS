
public class KnapsackSort implements Comparable<KnapsackSort>{
    int knapsackSortValues;
    int knapsackSortWeights;
    double knapsackSortCalc;

    public void setKnapsackSort(int knapsackValues, int knapsackWeights, double knapsackCalc) {
        this.knapsackSortValues = knapsackValues;
        this.knapsackSortWeights = knapsackWeights;
        this.knapsackSortCalc = knapsackCalc;
    }

    public int getKnapsackSortValues() {
        return knapsackSortValues;
    }

    public int getKnapsackSortWeights() {
        return knapsackSortWeights;
    }

    public double getKnapsackSortCalc() {
        return knapsackSortCalc;
    }

    public int compareTo(KnapsackSort knap){
        if(this.knapsackSortCalc<knap.knapsackSortCalc)
            return 1;
        else if(knap.knapsackSortCalc<this.knapsackSortCalc)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Knapsack{" +
                "knapsackValues=" + knapsackSortValues +
                ", knapsackWeights=" + knapsackSortWeights +
                ", knapsackCalc=" + knapsackSortCalc +
                '}';
    }


    public String toStringCalc() {
        return "Knapsack{" +
                "knapsackCalc=" + knapsackSortCalc +
                '}';
    }
}
