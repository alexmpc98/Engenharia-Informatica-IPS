package Objetos;

import java.nio.BufferOverflowException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Represents a Population of chromosomes.
 */
public class Population implements Iterable<Chromosome> {

    private PriorityQueue<Chromosome> chromosomes;
    private int maxSize;

    public Population (int maxSize) {
        this.maxSize = maxSize;
        chromosomes = new PriorityQueue<>();
    }

    public void add (Chromosome chromosome) {
        if (chromosomes.size() == maxSize) {
            throw new BufferOverflowException();
        }
        chromosomes.add(chromosome);
    }
    
    public void removeTwoWorstFits (){
        Population newPop = new Population(maxSize);
     
        while(chromosomes.size() > 2){
            newPop.add(chromosomes.poll());
        }
        PriorityQueue newChromosomes = newPop.getChromosomes();
        chromosomes.clear();
        chromosomes = newChromosomes;
        
    }

    public void randomPopulate (int[] cities, int[][] distances) {

        HashSet<Chromosome> hashSet = new HashSet<>();
        chromosomes.clear();

        while (chromosomes.size() < maxSize) {
            Chromosome chromo = new Chromosome(cities, distances);
            if (!hashSet.contains(chromo)) {
                hashSet.add(chromo);
                add(chromo);
            }
        }

    }

    public void clear () {
        chromosomes.clear();
    }

    public int[] getCities () {
        return chromosomes.peek().getArray().clone();
    }
    
    public PriorityQueue<Chromosome> getChromosomes (){
        return chromosomes;
    }

    public Chromosome[] getArrayChromosomes () {
        Chromosome[] array = new Chromosome[chromosomes.size()];

        int i = 0;
        for (Chromosome chromo : chromosomes) {
            array[i++] = chromo;
        }

        return array;
    }

    public int size () {
        return chromosomes.size();
    }
    
    public int getAverageDistance () {

        int averageDistance = 0;

        for (Chromosome chromosome : chromosomes) {
            averageDistance += chromosome.getDistance();
        }

        return averageDistance / chromosomes.size();
    }

    public Chromosome getMostFit () {
        return chromosomes.peek();
    }
    
    public Chromosome getSecondMostFit() {
        Chromosome removed = chromosomes.poll();
        Chromosome secondMostFit = chromosomes.peek();
        chromosomes.add(removed);
        return secondMostFit;
    }
    
    public Population getXNumberOfBests(int popSize){
        Population newPopulation = new Population(popSize);
        while(newPopulation.getChromosomes().size() < popSize){
            newPopulation.add(chromosomes.poll());
        }
        return newPopulation;
    }

    public Iterator<Chromosome> iterator () {
        return chromosomes.iterator();
    }

    public Population deepCopy () {
        Population population = new Population(maxSize);
        chromosomes.forEach((chromosome) -> population.add(chromosome));
        return population;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder("Population:");

        for (Chromosome chromosome : chromosomes) {
            sb.append("\n");
            sb.append(chromosome);
            sb.append(" Value: ");
            sb.append(chromosome.getDistance());
        }

        return new String(sb);
    }

}
