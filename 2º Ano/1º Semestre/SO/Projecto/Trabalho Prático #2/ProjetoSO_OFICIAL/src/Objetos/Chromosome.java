/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author hugomodesto
 */
public class Chromosome implements Comparable<Chromosome> {
    private int[] path;
    private int distance = -1; // Calculated once then cached.
    
    public Chromosome (int numberOfCities){
        this.path = new int[numberOfCities];
    }

    public Chromosome (int[] cities, int[][] distances) {
        this.path = cities.clone();
        shuffle();
        calculateDistance(distances);
    }

    private void shuffle () {
        Random random = new Random();
        for (int i = 0; i < path.length; i++) {
            swapints(i, random.nextInt(path.length));
        }
    }

    private void swapints (int i, int j) {
        int temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }

    public int[] getArray () {
        return path.clone();
    }
    
    public void setPath(int[] path){
        for(int i = 0 ; i < path.length ; i++){
            for(int j = 0 ; j < path.length ; j++){
                if(i != j && path[i] == path[j]){
                    throw new RuntimeException("Houve problema");
                }
            }
        }
        this.path = path.clone();
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return getDistance() - chromosome.getDistance();
    }
    
    public int getDistance(){
        return distance;
    }
    
    public int calculateDistance (int[][] distances) {

        distance = 0;

        for (int i = 1; i < path.length; i++) {
            distance += distances[path[i-1]-1][path[i]-1];
        }
        distance += distances[path[path.length-1]-1][path[0]-1];
        return distance;
    }
    
    public int getCity(int index){
        return path[index];
    }
    
    public void setCity(int index, int city){
        path[index] = city;
    }
    
    public int getRandomIndex(){
        Random random = new Random();
        return random.nextInt(path.length);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chromosome)) {
            return false;
        }

        Chromosome c = (Chromosome) o;

        return Arrays.equals(c.path, path);
    }

    @Override
    public String toString () {
        StringBuilder sb;
        sb = new StringBuilder("\t\tPath: ");
        for (int item : path) 
            sb.append(item).append("-");
        sb.append("\b");
        sb.append("\n\t\tDistance: ").append(distance);
        return new String(sb);
    }
}
