/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo;

import Objetos.Chromosome;
import Objetos.Population;
import java.util.Random;
import java.util.concurrent.*;

/**
 *
 * @author hugomodesto
 */
public class GeneticAlgorithm{
    //Semaphores
    private Semaphore semaphoreSetSolution;
    private Semaphore s2;
    private Semaphore s3;
    
    //Variables that are going to be changed by the other threads
    private int iterations;
    private Population auxPopulation;
    private Population auxBestPopulation;
    private Population bestPopulation;
    private double timeSolution;
    private int s2Cont, s3Cont;
    
    //Variables that arent going to be changed by the other threads
    private final int popSize; 
    private final int mutationProbability;
    private final int timeTotal; //in seconds
    private final int perTotalTime;
    private final int[][] distances;
    private final int numberOfThreads;
    private final Random random;

    public GeneticAlgorithm(int popSize, int mutationProbability, int timeTotal, int[][] distances, int numberOfThreads, int perTotalTime) {
        this.popSize = popSize;
        this.mutationProbability = mutationProbability;
        this.timeTotal = timeTotal;
        this.distances = distances;
        this.numberOfThreads = numberOfThreads;
        this.perTotalTime = perTotalTime;
        iterations = 0;
        s2Cont = 0;
        s3Cont = 0;
        timeSolution = Integer.MAX_VALUE;
        bestPopulation = null;
        auxPopulation = new Population(popSize * numberOfThreads);
        random = new Random();
        semaphoreSetSolution = new Semaphore(1);
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }
    
    public void run() throws InterruptedException{
        Thread[] threads;
        threads = new Thread[numberOfThreads];
        
        for(int i = 0 ; i < numberOfThreads ; i++) {
            threads[i] = new Thread("Thread "+i){
                @Override
                public void run(){
                    try{
                        runAlgorithm();
                    } catch(InterruptedException e){
                        throw new RuntimeException(e.getMessage());
                    }
                }
            };
        }
        
        for(int i = 0 ; i < numberOfThreads ; i++){
            threads[i].start();
        }
        
        for(int i = 0 ; i < numberOfThreads ; i++){
            threads[i].join();
        }
        printResults();
    }
    
    private int[] getCities(){
        int size = distances[0].length;
        int[] cities = new int[size];
        for(int i = 0 ; i < size ; i++)
            cities[i] = i+1;
        return cities;
    }
    
    private void runAlgorithm() throws InterruptedException{
        Chromosome chromosome1;
        Chromosome chromosome2;
        Chromosome mostFit;
        Chromosome secondMostFit;
        int size = distances[0].length;
        
        int[] cities = getCities();
        
        Population threadOldPopulation = null;
        Population threadPopulation = new Population(popSize);        
        threadPopulation.randomPopulate(cities, distances);
        
        Population threadBestPopulation = threadPopulation.deepCopy();
        double threadTimeSolution = 0;
        int threadSolutionIterations = 0;
        
        long threadTimeRunning = System.currentTimeMillis();
        long threadTimeRunningSeconds = 0;
        int threadIterations = 0;
        
        long time = System.currentTimeMillis();
        long endTime = time + (timeTotal*1000);
        
        while(threadIterations < Integer.MAX_VALUE && threadTimeRunning < endTime){
            mostFit = threadPopulation.getMostFit();
            secondMostFit = threadPopulation.getSecondMostFit();
            
            chromosome1 = new Chromosome(size);
            chromosome2 = new Chromosome(size);
            
            PMX(mostFit, secondMostFit, chromosome1, chromosome2);
            chromosome1.calculateDistance(distances);
            chromosome2.calculateDistance(distances);
            mutation(chromosome1, chromosome2, mutationProbability, size);
            
            threadTimeRunning = System.currentTimeMillis();
            
            threadPopulation.removeTwoWorstFits();
            threadPopulation.add(chromosome1);
            threadPopulation.add(chromosome2);
            
            if(threadOldPopulation != null && threadPopulation.getMostFit().compareTo(threadOldPopulation.getMostFit()) < 0){
                threadSolutionIterations = threadIterations;
                threadBestPopulation = threadPopulation.deepCopy();
                threadTimeSolution = (double)(threadTimeRunning - time)/(1000);
            }
            
            //threadTimeRunningSeconds = (threadTimeRunning - time)/(1000);
            
            /*if(threadTimeRunningSeconds % perTotalTime == 0 && threadTimeRunningSeconds < getLastMultiple()){
                System.out.println("s2: "+s2.getQueueLength());
                if(s2.getQueueLength() < numberOfThreads-1)
                    s2.acquire();
                addPopulationToAuxPopulation(threadBestPopulation);
                if(s2.getQueueLength() > 0)
                    s2.release();
                
                System.out.println("s3: "+s3.getQueueLength());
                if(s3.getQueueLength() < numberOfThreads-1)
                    s3.acquire();
                else{
                    auxBestPopulation = auxPopulation.getXNumberOfBests(popSize).deepCopy();
                    auxPopulation.clear();
                }
                if(s3.getQueueLength() > 0)
                    s3.release();
                
                threadBestPopulation = auxBestPopulation.deepCopy();
                threadTimeSolution = (double)(threadTimeRunning - time)/(1000);

            }*/
            
            threadOldPopulation = threadPopulation.deepCopy();
            
            threadIterations++;
        }
        semaphoreSetSolution.acquire();
            setBestSolution(threadSolutionIterations, threadBestPopulation, threadTimeSolution);
        semaphoreSetSolution.release();
    }
    
    private long getLastMultiple(){
        long division = timeTotal/perTotalTime;
        return division * perTotalTime;
    }
    
    private void addPopulationToAuxPopulation(Population population){
        for(Chromosome c : population){
            auxPopulation.add(c);
        }
    }
    
    private void setBestSolution(int threadSolutionIterations, Population threadBestPopulation, double threadTimeSolution) throws InterruptedException{
            iterations += threadSolutionIterations;
            int compare;
            
            if(bestPopulation != null)
                compare = threadBestPopulation.getMostFit().compareTo(bestPopulation.getMostFit());
            else
                compare = -1;
            
            if(compare < 0){
                bestPopulation = threadBestPopulation.deepCopy();
                timeSolution = threadTimeSolution;
                iterations = threadSolutionIterations;
            } else if(compare == 0 && threadTimeSolution < timeSolution){
                bestPopulation = threadBestPopulation.deepCopy();
                timeSolution = threadTimeSolution;
                iterations = threadSolutionIterations;
            }
    }
    
    
    private void PMX(Chromosome mostFit, Chromosome secondMostFit, Chromosome chromosome1, Chromosome chromosome2){
        int crossPoint1;
        int crossPoint2;
        int mostFitVal;
        int secondMostFitVal;
        int aux1Val;
        int aux2Val;
        
        int swap;
        int size = mostFit.getArray().length;
        
        int[] aux1 = new int[size+1];
        int[] aux2 = new int[size+1];
        int[] parent1 = mostFit.getArray();
        int[] parent2 = secondMostFit.getArray();
        int[] child1 = new int[size];
        int[] child2 = new int[size];
        
        crossPoint1 = mostFit.getRandomIndex();        
        do{
            crossPoint2 = secondMostFit.getRandomIndex();
        }while(crossPoint1 == crossPoint2);
        
        if(crossPoint1 > crossPoint2){
            swap = crossPoint1;
            crossPoint1 = crossPoint2;
            crossPoint2 = swap;
        }
        
        for(int i = 0 ; i <= size ; i++){
            aux1[i] = -1;
            aux2[i] = -1;
        }
        
        for(int i = crossPoint1 ; i <= crossPoint2 ; i++){
            child1[i] = parent2[i];
            child2[i] = parent1[i];
            aux1[parent2[i]] = parent1[i];
            aux2[parent1[i]] = parent2[i];
        }
        
        for(int i = 0 ; i < size ; i++){
            if((i < crossPoint1) || (i > crossPoint2)){
                mostFitVal = parent1[i];
                aux1Val = aux1[mostFitVal];
                
                secondMostFitVal = parent2[i];
                aux2Val = aux2[secondMostFitVal];
                
                while(aux1Val != -1){
                    mostFitVal = aux1Val;
                    aux1Val = aux1[aux1Val];
                }

                while(aux2Val != -1){
                    secondMostFitVal = aux2Val;
                    aux2Val = aux2[aux2Val];
                }
                
                child1[i] = mostFitVal;
                child2[i] = secondMostFitVal;
            }
        }
        
        chromosome1.setPath(child1);
        chromosome2.setPath(child2);
    }
    
    private void mutation(Chromosome child1, Chromosome child2, int mutationProbability, int size){
        int probability = random.nextInt(100+1);
        if(probability < mutationProbability){
            swapRandomCities(child1, size);
            swapRandomCities(child2, size);
        }
    }
    
    private void swapRandomCities(Chromosome chromosome, int size){
        int randomIndex1;
        int randomIndex2;
        int swap;
        randomIndex1 = random.nextInt(size);
        do{
            randomIndex2 = random.nextInt(size);
        }while(randomIndex1 == randomIndex2);
        swap = chromosome.getCity(randomIndex1);
        chromosome.setCity(randomIndex1, chromosome.getCity(randomIndex2));
        chromosome.setCity(randomIndex2, swap);
    }
    
    public void printResults(){
        System.out.println("*-*-*-* INFO *-*-*-*");
        System.out.println(" - User inputs:");
        System.out.println("\t Population Size: "+popSize);
        System.out.println("\t Mutation probability: "+mutationProbability+"%");
        System.out.println("\t Time total: "+timeTotal+"s");
        System.out.println("\t Number of threads running simultaneously: "+numberOfThreads);
        System.out.println(" ");
        System.out.println(" - Final results:");
        System.out.println("\t Number of iterations: "+iterations);
        System.out.println("\t Time for solution: "+timeSolution+"s");
        System.out.println("\t Best result: ");
        System.out.println(bestPopulation.getMostFit().toString());
    }
}
