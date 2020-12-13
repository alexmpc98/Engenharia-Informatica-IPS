/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Algoritmo.GeneticAlgorithm;
import IO.InputData;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hugomodesto
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        try {
            int[][] distances;
            int popSize;
            int mutationProbability;
            int maxTimeRunning;
            int nThreads;
            int perTotalTime;
            Scanner reader = new Scanner(System.in);

            distances = InputData.inputFileName(reader);

            System.out.println("Population size:");
            popSize = InputData.inputInt(reader);

            System.out.println("Mutation probability:");
            mutationProbability = InputData.inputInt(reader);

            System.out.println("Maximum time running:");
            maxTimeRunning = InputData.inputInt(reader);
            
            System.out.println("Number of threads running simultaneously:");
            nThreads = InputData.inputInt(reader);
            
            //System.out.println("Percentagem do tempo total:");
            //perTotalTime = InputData.inputInt(reader);
            perTotalTime = 1;
            GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutationProbability, maxTimeRunning, distances, nThreads, perTotalTime);
            
            ga.run();
        } catch (Throwable ex) {
            System.err.println("Uncaught exception - " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }
    
    
}
