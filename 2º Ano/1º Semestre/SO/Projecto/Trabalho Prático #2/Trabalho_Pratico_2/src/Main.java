import sun.awt.SunHints;
import java.util.Arrays;
import java.util.Random;

public class Main extends Thread{

    @Override
    public void run() {
        modifyKnapsack(getName());
    }

    public static void main(String[] args) {
        int iterationNumber = 2;

        for (int i = 0; i < iterationNumber; i++) {
            Main newThread = new Main();
            newThread.start();
        }
    }


    void modifyKnapsack(String threadName)  {
        int valuesArray[] = {6, 10, 12, 13};
        int weightsArray[] = {2, 4, 6, 7};
        int n = 4;
        int bestWeight = 11;

        // INITIALIZES THE SUM VARIABLES
        int valueSum = 0;
        int weightSum = 0;
        int bestWeightSum = 0;

        // INITIALIZES THE KNAPSACK
        int temporary_knapsack[] = new int[n];
        for (int i = 0; i < n; i++) {
            temporary_knapsack[i] = 0;
        }

        // INITIALIZES THE KNAPSACK
        Knapsack knapsack = new Knapsack();

        //SET THE KNAPSACK TO 0
        knapsack.setKnapsack(temporary_knapsack);

        // INITIALIZES THE KNAPSACK VALUES
        KnapsackValues knapsackValues = new KnapsackValues();
        knapsackValues.setKnapsackValue(valuesArray);

        // INITIALIZES THE KNAPSACK WEIGHTS
        KnapsackWeights knapsackWeights = new KnapsackWeights();
        knapsackWeights.setKnapsackWeight(weightsArray);

        // CREATES A RANDOM OBJECT
        Random generator = new Random();
        for (int i = 0; i < 10; i++) {
            int random_number = generator.nextInt(4);
            knapsack.changeKnapsack(1 - knapsack.getChangedValueKnapsack(random_number), random_number);
            valueSum = knapsackValues.calculate_value (knapsackValues.getKnapsackValues(), knapsack.getKnapsack(), n);
            weightSum = knapsackWeights.calculate_weight(knapsackWeights.getKnapsackWeights(), knapsack.getKnapsack(), n);
            if (weightSum > bestWeightSum && weightSum <= bestWeight) {
                bestWeightSum = weightSum;
            }
            System.out.println("-----------------------------------");
            System.out.println("Thread: " + threadName + " | KS: " + Arrays.toString(knapsack.getKnapsack()) + " | Value: " + valueSum + " | Weight: "+ weightSum);
            //System.out.println("Best Weight Sum: " + bestWeightSum);
            System.out.println("-----------------------------------\n");
        }
        System.out.println("BEST WEIGHT: " + bestWeightSum);
    }




}