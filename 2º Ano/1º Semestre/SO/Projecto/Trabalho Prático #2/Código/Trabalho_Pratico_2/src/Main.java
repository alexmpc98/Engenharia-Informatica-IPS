import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authors Alex, Sérgio e Tim
 **/

public class Main extends Thread {

    static int W;
    static int n;

    static int[][] tableSorted;

    // INITIALIZES THE RUNNING SETTINGS
    public static RunningSettings runningSettings = new RunningSettings();

    @Override
    public void run() {
        // CALLS THE FUNCTION THAT MODIFIES KNAPSACK
        modifyKnapsack(getName(), runningSettings.getFileName());
    }

    public static void main(String[] args) {
        // INITIALIZES THE SHARED MEMORY
        SharedMemory sharedMemory = new SharedMemory();

        // INITALIZES THE KEYBOARD READER
        Scanner reader = new Scanner(System.in);

        System.out.println("Insert file name:");
        String fileName = reader.next();

        System.out.println("Thread Number:");
        int threadNumber = InputValues.inputInt(reader);

        runningSettings.setRunningSettings(threadNumber, fileName);

        // CREATES N THREADS
        for (int i = 0; i < 1; i++) {
            Main newThread = new Main();
            newThread.start();
        }
    }

    // FUNCTION THAT MODIFIES THE KNAPSACK
    void modifyKnapsack(String threadName, String file3Name) {
        String fileName = "ex08_ordered.txt";

        importTable(fileName);

        // Initial level
        int level = 0;

        // Initialize a new "blank" solution and add it to the new listSol
        ArrayList<Solution> listSol = new ArrayList<Solution>();
        Solution s = new Solution(n);
        listSol.add(s);

        /*for () {

        }*/

        System.out.println("Level 0");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++)
            listSol.get(i).show();


        // Generate the childs for the level 1
        getChilds(listSol, level);
        System.out.println("\nLevel 1");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++)
            listSol.get(i).show();

        // Generate the childs for the level 2
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 2");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 3
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 3");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 4
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 4");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 5
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 5");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 6
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 6");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 7
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 7");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }

        // Generate the childs for the level 8
        level++;
        getChilds(listSol, level);
        System.out.println("\nLevel 8");
        System.out.println("-----------------------------------");
        for (int i = 0; i < listSol.size(); i++) {
            listSol.get(i).show();
        }
    }

    // Receives a list of solutions and a level
    // Updates the list of solutions with their child solutions
    static void getChilds(ArrayList<Solution> listSol, int level) {
        Solution sol;
        ArrayList<Solution> newListSol = new ArrayList<Solution>();
        if (level < n) {
            for (int i = 0; i < listSol.size(); i++) {

                // If is possible
                // Add a child with the next element inside knapsac
                sol = listSol.get(i);
                if (sol.sumWeight + tableSorted[level][1] <= W) {
                    Solution newSol = sol.clone(sol);
                    newSol.setListPosition(level, 1);
                    newSol.size += 1;
                    newSol.eval += tableSorted[level][0];
                    newSol.sumWeight += tableSorted[level][1];
                    newListSol.add(newSol);
                }

                // Add a child without the next element inside knapsac
                // Just add a 0 in the permutation of the solution
                sol.setListPosition(level, 0);
                sol.size += 1;
            }
            listSol.addAll(newListSol);
        } else
            listSol.clear();
    }

    static void importTable(String nameFile) {
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
            W = Integer.parseInt(sc.nextLine());
        }
        tableSorted = new int[n][2];
        int i = 0;
        int j = 0;
        while (sc.hasNextLine() && i < n) {
            for (j = 0; j < 2; j++) {
                int a = sc.nextInt();
                tableSorted[i][j] = a;
            }
            i++;
        }
    }
}