/* 
 * The MIT License
 *
 * Copyright 2019 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pt.pa.model;

import java.util.*;

import pt.pa.model.Local;
import pt.pa.model.MapManager;

/**
 *
 * @author brunomnsilva
 */
public class ModelMain {

    private volatile boolean running;

       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MapManager map = new MapManager();
        map.build_map_example();

        ArrayList<Local> path= new ArrayList();
        double cost = map.minimumCostPath( "A","E",path);

        System.out.println(" \n\n Custo minimo " + cost+ " Shortest Path " + path);
        System.out.println(map.toStringLocals(MapManager.MODE.BREADTH,"A"));
        System.out.println(map.toStringLocals(MapManager.MODE.DEPTH,"A"));
        
    }




}
