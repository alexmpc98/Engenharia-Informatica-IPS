import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.Semaphore;

/**
 * @authors Alexandre, Sérgio e Tim
 **/

public class Main extends Thread {

    static int bestWeight;
    static int n;

    static int[][] tableVW;

    // INITIALIZES THE SHARED MEMORY
    static SharedMemory sharedMemory = new SharedMemory();

    static int testNumber;

    static String testName;

    static String fileName;

    static int threadNumber;

    public static void main(String[] args) throws InterruptedException {
        // INITIALIZES THE KEYBOARD READER
        Scanner reader = new Scanner(System.in);

        // READS THE INSERTED TEST NUMBER
        System.out.print("Insert test number: ");
        testNumber = reader.nextInt();

        // READS THE INSERTED TEST NAME
        System.out.print("Insert test name: ");
        testName = reader.next();

        // READS THE INSERTED FILE NAME
        System.out.print("Insert file name: ");
        fileName = reader.next();

        // READS THE INSERTED NUMBER OF THREADS
        System.out.print("Thread Number: ");
        threadNumber = reader.nextInt();

        // SETS THREAD ARRAY
        Thread[] threads = new Thread[threadNumber];

        // CREATES N THREADS
        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new Thread("Thread " + i) {
                @Override
                public void run() {
                    // CALLS THE FUNCTION THAT MODIFIES KNAPSACK
                    try {
                        modifyKnapsack(getName(), fileName);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

        }

        // STARTS THE THREADS
        for (int i = 0; i < threadNumber; i++) {
            threads[i].start();
        }

        // JOINS THE THREADS
        for (int i = 0; i < threadNumber; i++) {
            threads[i].join();
        }
    }

    // FUNCTION THAT MODIFIES THE KNAPSACK
    static void modifyKnapsack(String threadName, String fileName) throws InterruptedException {
        // DEFINE THE LOWER AND UPPER BOUND
        ArrayList<Integer> lowerBound = new ArrayList<Integer>(n);
        int upperBound = 0;

        Semaphore semaphore = new Semaphore(1);

        // INITIALIZES THE SORTED ARRAYS
        ArrayList<Integer> sortedValues = new ArrayList<Integer>(n);
        ArrayList<Integer> sortedWeights = new ArrayList<Integer>(n);

        // INITIALIZES THE KNAPSACK SORT ARRAY LIST
        ArrayList<KnapsackSort> knapsackSort = new ArrayList<KnapsackSort>(n);

        // IMPORTS THE VALUES FROM THE INSERTED FILE
        importFile(fileName);
        //System.out.println(Arrays.deepToString(tableVW));

        // PREPARES THE VALUES TO BE SORTED
        for (int i = 0; i < n; i++) {
            // INITIALIZES THE KNAPSACK CLASS
            KnapsackSort knapsackSortClass = new KnapsackSort();
            knapsackSortClass.setKnapsackSort(tableVW[i][0], tableVW[i][1], (double) tableVW[i][0] / tableVW[i][1]);
            knapsackSort.add(knapsackSortClass);
        }

        // SORTS THE KNAPSACK ARRAY LIST
        Collections.sort(knapsackSort);

        // SETS THE VALUES FOR THE SORTED ARRAY LISTS
        for (int i = 0; i < n; i++) {
            sortedValues.add(knapsackSort.get(i).knapsackSortValues);
            sortedWeights.add(knapsackSort.get(i).knapsackSortWeights);
        }
        /*System.out.println("V: " + sortedValues);
        System.out.println("W: " + sortedWeights);*/

        // INITIALIZES A NEW "BLANK" SOLUTION AND ADDS IT TO THE NEW LIST
        ArrayList<Solution> listSol = new ArrayList<Solution>();
        Solution s = new Solution(n);
        listSol.add(s);
        for (int i = 0; i < n; i++) {
            /*System.out.println("\n[ Level " + (i+1) + " ]");
            System.out.println("-----------------------------------");*/
            getChilds(listSol, i, n);
            for (int u = 0; u < listSol.size(); u++) {
                upperBound = calcUpperBound(knapsackSort, listSol.get(u).get(), bestWeight, n);
                if (upperBound >= calcValue(knapsackSort, lowerBound)) {
                    if (calcValue(knapsackSort, listSol.get(u).get()) > calcValue(knapsackSort, lowerBound)) {
                        lowerBound = listSol.get(u).get();
                    } else {
                        listSol.remove(listSol.get(u));
                    }
                }
            }
            listSol = (ArrayList<Solution>)selectSolutions(n/2, listSol).clone();
            int tempBestValue = 0;
            int tempBestWeight = 0;
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            for (int x  = 0; x< listSol.size(); x++) {
                if (calcValue(knapsackSort, listSol.get(x).get() ) > tempBestValue) {
                    tempBestValue = calcValue(knapsackSort, listSol.get(x).get());
                    tempBestWeight = calcWeight(knapsackSort, listSol.get(x).get());
                    tempList=listSol.get(x).get();
                }
            }
            semaphore.acquire();
            sharedMemory.setTestNumber(testNumber);
            sharedMemory.setTestName(testName);
            sharedMemory.setThreadNumber(threadNumber);
            sharedMemory.setNumberOfNumbers(n);
            sharedMemory.setBestKnapsack(tempList);
            sharedMemory.setBestValue(tempBestValue);
            sharedMemory.setBestWeight(tempBestWeight);
            semaphore.release();
        }
        System.out.println("\nBest Knapsack: " + sharedMemory.getBestKnapsack());
        System.out.println("Test Number: " + sharedMemory.getTestNumber());
        System.out.println("Test Name: " + sharedMemory.getTestName());
        System.out.println("Number of Values: " + sharedMemory.getNumberOfNumbers());
        System.out.println("ThreadNumber: " + sharedMemory.getThreadNumber());
        System.out.println("Best Value: " + sharedMemory.getBestValue());
        System.out.println("Best Weight: " + sharedMemory.getBestWeight());
    }


    // CALCULATES THE UPPER BOUND
    static public int calcUpperBound(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bagUpper, int bestWeight, int n) {
        int weightSumUpper = 0;
        int weightSumUpperTemporary = 0;
        int cUpper = 0;
        int bagValue = 1;
        ArrayList<Integer> temporaryBagUpper = new ArrayList<Integer>(bagUpper);
        for (int i = 0; i < n; i++) {
            if (i < bagUpper.size()) {
                bagValue = temporaryBagUpper.get(i);
            } else {
                bagValue = 1;
            }
            if ((weightSumUpper + (bagValue * knapsackSort.get(i).knapsackSortWeights)) < bestWeight) {
                weightSumUpper += bagValue * knapsackSort.get(i).knapsackSortWeights;
                if (i > (bagUpper.size() - 1)) {
                    temporaryBagUpper.add(1);
                } else {
                    weightSumUpperTemporary += bagValue * knapsackSort.get(i).knapsackSortWeights;
                }
            } else {
                cUpper = i;
                break;
            }
        }
        int result = 0;
        if (cUpper > 0 && cUpper < n && (cUpper + 1) < n && (bagUpper.size()+2) < n && (bagUpper.size()+1) < n && (cUpper - 1) >= 0) {
            int sigmaSum = knapsackSort.get((bagUpper.size())).knapsackSortValues + knapsackSort.get((bagUpper.size() + 1)).knapsackSortValues + knapsackSort.get((bagUpper.size() + 2)).knapsackSortValues;
            int weightMaxCalc = bestWeight - (weightSumUpper - weightSumUpperTemporary) - weightSumUpperTemporary;
            int valueSum = calcValue(knapsackSort, bagUpper);
            int leftCalc = (int) (weightMaxCalc * ((double) knapsackSort.get((cUpper + 1)).knapsackSortValues / (double) knapsackSort.get((cUpper + 1)).knapsackSortWeights));
            int rightCalc = (int) ((double) knapsackSort.get(cUpper).knapsackSortValues - ((double) knapsackSort.get(cUpper).knapsackSortWeights - weightMaxCalc) * ((double) knapsackSort.get(cUpper - 1).knapsackSortValues / (double) knapsackSort.get(cUpper - 1).knapsackSortWeights));
            result = Math.max(valueSum + sigmaSum + leftCalc, valueSum + sigmaSum + rightCalc);
            //System.out.println("UB: " + bestWeight + " - " + weightSumUpper + " - " + bagUpper + " - " + weightSumUpperTemporary + " - " + temporaryBagUpper + " - " + weightMaxCalc + " - " + result);
        }
        return result;
    }


    // CALCULATES THE LOWER BOUND
    static public ArrayList<Integer> calcLowerBound(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bagLower, int bestWeight) {
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
        return bagLower;
    }


    // SUMS THE VALUES OF THE KNAPSACK
    static public int calcValue(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bag) {
        int valueSum = 0;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i) == 1) {
                valueSum += knapsackSort.get(i).knapsackSortValues;
            }
        }
        return valueSum;
    }


    // SUMS THE WEIGHTS OF THE KNAPSACK
    static public int calcWeight(ArrayList<KnapsackSort> knapsackSort, ArrayList<Integer> bag) {
        int weightSum = 0;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i) == 1) {
                weightSum += knapsackSort.get(i).knapsackSortWeights;
            }
        }
        return weightSum;
    }


    // RECEIVES THE LIST OF SOLUTIONS AND THE LEVEL AND UPDATES
    // THE LIST OF SOLUTIONS WITH THEIR CHILD SOLUTIONS
    static void getChilds(ArrayList<Solution> listSol, int level, int n) {
        Solution sol;
        ArrayList<Solution> newListSol = new ArrayList<Solution>();
        if (level < n) {
            for (int i = 0; i < listSol.size(); i++) {
                // If is possible
                // Add a child with the next element inside knapsack
                sol = listSol.get(i);
                if (sol.sumWeight + tableVW[level][1] <= bestWeight) {
                    Solution newSol = sol.clone(sol);
                    newSol.setListPosition(level, 1);
                    newSol.size += 1;
                    newSol.eval += tableVW[level][0];
                    newSol.sumWeight += tableVW[level][1];
                    newListSol.add(newSol);
                }
                // Add a child without the next element inside knapsack
                // Just add a 0 in the permutation of the solution
                sol.setListPosition(level, 0);
                sol.size += 1;
            }
            listSol.addAll(newListSol);
        } else {
            listSol.clear();
        }
    }


    // SELECTS RANDOMLY SOME OF THE SOLUTIONS
    static ArrayList<Solution> selectSolutions(int alpha, ArrayList<Solution> listSol) {
        ArrayList<Solution> newList = new ArrayList<Solution>();
        // CREATES A RANDOM OBJECT
        Random generator = new Random();
        for (int i = 0; i < alpha;i++) {
            //System.out.println("G: " + listSol.get(generator.nextInt(listSol.size())).get());
            newList.add(listSol.get(generator.nextInt(listSol.size())));
        }
        return newList;
    }


    // IMPORTS THE VALUES FROM THE FILE
    static void importFile(String nameFile) {
        File file = new File(nameFile);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("You must insert a valid file");
        }
        if (sc.hasNextLine()) {
            n = Integer.parseInt(sc.nextLine());
        }
        if (sc.hasNextLine()) {
            bestWeight = Integer.parseInt(sc.nextLine());
        }
        tableVW = new int[n][2];
        int i = 0;
        int j = 0;
        while (sc.hasNextLine() && i < n) {
            for (j = 0; j < 2; j++) {
                int a = sc.nextInt();
                tableVW[i][j] = a;
            }
            i++;
        }
    }
}
