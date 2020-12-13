import java.util.*;

public class Main extends Thread {

    @Override
    public void run() {
        // CALLS THE FUNCTION THAT MODIFIES KNAPSACK
        modifyKnapsack(getName());
    }

    public static void main(String[] args) {
        // INITIALIZES THE NUMBER OF THREADS TO CREATE
        int iterationNumber = 1;

        // CREATES N THREADS
        for (int i = 0; i < iterationNumber; i++) {
            Main newThread = new Main();
            newThread.start();
        }
    }

    // FUNCTION THAT MODIFIES THE KNAPSACK
    void modifyKnapsack(String threadName) {
        // INITIALIZATES THE FILES VALUES V | W
        int knapsackVW[][] = {
                {60, 30},
                {40, 40},
                {90, 20},
                {15, 2},
                {100, 20},
                {15, 30},
                {1, 10},
                {10, 60}
        };
        int n = 8;
        int bestWeight = 102;
        ArrayList<Integer> lowerBound = new ArrayList<Integer>(n);
        int upperBound = 0;
        ArrayList<Integer> temporaryKnapsack = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                temporaryKnapsack.add(0);
            } else if (i < 3) {
                temporaryKnapsack.add(1);
            } else {
                temporaryKnapsack.add(0);
            }
        }
        System.out.println(temporaryKnapsack);

        // INITIALIZES THE SORTED ARRAYS
        ArrayList<Integer> sortedValues = new ArrayList<Integer>(n);
        ArrayList<Integer> sortedWeights = new ArrayList<Integer>(n);

        // INITIALIZES THE KNAPSACK SORT ARRAY LIST
        ArrayList<KnapsackSort> knapsackSort = new ArrayList<KnapsackSort>(n);

        // INITIALIZES THE SHARED MEMORY
        SharedMemory sharedMemory = new SharedMemory();

        for (int i = 0; i < n; i++) {
            // INITIALIZES THE KNAPSACK CLASS
            KnapsackSort knapsackSortClass = new KnapsackSort();
            knapsackSortClass.setKnapsackSort(knapsackVW[i][0], knapsackVW[i][1], (double) knapsackVW[i][0] / knapsackVW[i][1]);
            knapsackSort.add(knapsackSortClass);
        }

        // SORTS THE KNAPSACK ARRAY LIST
        Collections.sort(knapsackSort);

        // SETS THE VALUES FOR THE SORTED ARRAY LISTS
        for (int i = 0; i < n; i++) {
            sortedValues.add(knapsackSort.get(i).knapsackSortValues);
            sortedWeights.add(knapsackSort.get(i).knapsackSortWeights);
        }

        // INITIALIZES THE KNAPSACK SORT ARRAY LIST
        ArrayList<Knapsack> knapsack = new ArrayList<Knapsack>(n);

        // INITIALIZES THE KNAPSACK SORT ARRAY LIST
        ArrayList<Bag> knapsackChilds = new ArrayList<Bag>(n);

        ArrayList<Integer> bagLower = new ArrayList<Integer>(n);
        /*for (int i = 0; i < n; i++) {
            if (i == 0) {
                bagLower.add(1);
            } else if (i == 1) {
                bagLower.add(0);
            }

        }*/

        bagLower.add(1);
        bagLower.add(0);
        System.out.println("BL: " + bagLower);
        //lowerBound = calcLowerBound(n, knapsackSort, bagLower, bestWeight) ;

        calcUpperBound(knapsackSort, bagLower, bestWeight, n);
        //calcValue(n, knapsackSort, temporaryKnapsack);

        /*for (int i = 0; i < n; i++) {
            int lastIndex = temporaryKnapsack.lastIndexOf(1);
            for (int u = lastIndex; u < n; u++) {
                temporaryKnapsack.set(u, 1);
                Bag obj = new Bag();
                obj.setKnapsack(temporaryKnapsack);
                knapsackChilds.add(obj);
                System.out.println(obj.getKnapsackBin());
                upperBound = calcUpperBound(n, knapsackSort, temporaryKnapsack, bestWeight);
                if (upperBound >= calcValue(n, knapsackSort, lowerBound)) {
                    if (calcValue(n, knapsackSort, temporaryKnapsack) > calcValue(n, knapsackSort, lowerBound)) {
                        lowerBound = temporaryKnapsack;
                    }
                }
            }
        }*/
    }

    public int calcUpperBound(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bagUpper, int bestWeight, int n) {
        int weightSumUpper = 0;
        int weightSumUpperTemporary = 0;
        int cUpper = 0;
        int result = 0;
        int outWeight = 0;
        int maxWeight = 0;
        int weightMaxCalc = 0;
        int valueSum = 0;
        int atualSize = bagUpper.size();
        ArrayList<Integer> temporaryBagUpper = new ArrayList<Integer>(bagUpper);
        for (int i = 0; i < n; i++) {
            if ((weightSumUpperTemporary + knapsackSort.get(i).knapsackSortWeights) < bestWeight) {
                weightSumUpperTemporary += temporaryBagUpper.get(i) * knapsackSort.get(i + 1).knapsackSortWeights;
                if ((atualSize += -1) >= 0) {
                    weightSumUpper += temporaryBagUpper.get(atualSize) * knapsackSort.get(atualSize).knapsackSortWeights;
                }
                temporaryBagUpper.add(1);
            } else {
                outWeight = weightSumUpper + knapsackSort.get(i).knapsackSortWeights;
                cUpper = i + bagUpper.size();
                break;
            }
        }
        weightMaxCalc = bestWeight - weightSumUpperTemporary - weightSumUpper;
        System.out.println(bestWeight + " - " + weightSumUpper + " - " + bagUpper + " - " + weightSumUpperTemporary + " - " + temporaryBagUpper + " - " + weightMaxCalc);
        valueSum = calcValue(knapsackSort, bagUpper);
        //result = Math.max(valueSum + (bagUpper.size()+1) + int(weightMaxCalc * (knapsackSort.get((cUpper - 1)).knapsackSortValues / knapsackSort.get((cUpper - 1)).knapsackSortWeights)), valueSum + + int(knapsackSort.get(cUpper).knapsackSortValues-(knapsackSort.get(cUpper).knapsackSortWeights - weightMaxCalc)*(knapsackSort.get(cUpper-1).knapsackSortValues/knapsackSort.get(cUpper-1).knapsackSortWeights)));
        System.out.println("UB: " + weightSumUpper + " - " + cUpper + " - " + bagUpper + " - " + outWeight + " - " + maxWeight + " - " + result + " - " + weightMaxCalc);
        return result;
    }


    public ArrayList<Integer> calcLowerBound(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bagLower, int bestWeight) {
        int weightSumLower = 0;
        int valueSumLower = 0;
        int cLower = 0;
        int lastIndex = bagLower.lastIndexOf(1);
        if (lastIndex == -1) {
            lastIndex = 0;
        }
        for (int i = lastIndex; i < bagLower.size(); i++) {
            if ((weightSumLower + knapsackSort.get(i).knapsackSortWeights) < bestWeight) {
                weightSumLower += knapsackSort.get(i).knapsackSortWeights;
                valueSumLower += knapsackSort.get(i).knapsackSortValues;
                bagLower.set(i, 1);
            } else {
                cLower = i + 1;
                break;
            }
        }
        Knapsack obj = new Knapsack();
        obj.setKnapsack(valueSumLower, weightSumLower, cLower, bagLower);
        //System.out.println("LB: " + weightSumLower + " - " + valueSumLower + " - " + cLower + " - " + bagLower);
        return bagLower;
    }

    public int calcValue(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bag) {
        int valueSum = 0;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i) == 1) {
                valueSum += knapsackSort.get(i).knapsackSortValues;
            }
        }
        //System.out.println("VS: " + valueSum + " - " + bag);
        return valueSum;
    }

}