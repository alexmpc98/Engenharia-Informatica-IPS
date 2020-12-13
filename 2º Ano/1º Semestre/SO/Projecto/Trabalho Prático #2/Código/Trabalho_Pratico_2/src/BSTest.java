

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */



public class BSTest {
    
    static int W;
    static int n;
    
    static int [][] tableSorted;    
    
    
    // Receives a list of solutions and a level
    // Updates the list of solutions with their child solutions
    static void getChilds(ArrayList<Solution> listSol,int level) {
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
                sol.setListPosition(level,0);
                sol.size += 1;
            }
            listSol.addAll(newListSol);
        }
        else
            listSol.clear();
        
    }
    
    static void importTable(String nameFile) {
        
        File file = 
          new File(nameFile); 
        Scanner sc=null; 
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
        }

        if (sc.hasNextLine())
            n =  Integer.parseInt(sc.nextLine());   
        if (sc.hasNextLine())
            W =  Integer.parseInt(sc.nextLine());
            
        tableSorted = new int[n][2];
        int i = 0;
        int j = 0;
        while (sc.hasNextLine() && i < n) {          
          for (j=0; j< 2; j++) {
            int a = sc.nextInt();
            tableSorted[i][j] = a;
          }
          i++;
        }        
    }
    
    
    public static void main(String [] args) {
        String fileName = "ex08_ordered.txt";
        
        importTable(fileName);
        
        // Initial level
        int level = 0;
        
        // Initialize a new "blank" solution and add it to the new listSol
        ArrayList<Solution> listSol = new ArrayList<Solution>();
        Solution s = new Solution(n);
        listSol.add(s);
        
        System.out.println("Level 0");
        System.out.println("-----------------------------------");
        for (int i=0; i < listSol.size(); i++)
            listSol.get(i).show();
        
        
        // Generate the childs for the level 1
        getChilds(listSol,level);
        System.out.println("\nLevel 1");
        System.out.println("-----------------------------------");
        for (int i=0; i < listSol.size(); i++)
            listSol.get(i).show();
               
        // Generate the childs for the level 2
        level++;
        getChilds(listSol,level);
        System.out.println("\nLevel 2");
        System.out.println("-----------------------------------");
        for (int i=0; i < listSol.size(); i++)
            listSol.get(i).show();
        
        // Generate the childs for the level 3
        level++;
        getChilds(listSol,level);
        System.out.println("\nLevel 3");
        System.out.println("-----------------------------------");
        for (int i=0; i < listSol.size(); i++)
            listSol.get(i).show();
    }
    
}
