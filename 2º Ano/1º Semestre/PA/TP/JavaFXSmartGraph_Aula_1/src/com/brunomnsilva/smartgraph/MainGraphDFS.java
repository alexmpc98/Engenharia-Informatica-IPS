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
package com.brunomnsilva.smartgraph;

import com.brunomnsilva.smartgraph.MainTree;
import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graph.*;
import com.brunomnsilva.smartgraph.graphview.*;
import com.brunomnsilva.smartgraph.tree.Tree;
import com.brunomnsilva.smartgraph.tree.TreeImpl;
import com.brunomnsilva.smartgraph.tree.TreePosition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brunomnsilva
 */
public class MainGraphDFS extends Application {

    private volatile boolean running;

    @Override
    public void start(Stage ignored) {

        Graph<String, String> g = build_sample_graph();
        //Graph<String, String> g = build_flower_graph();
        System.out.println(g);
        
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        //SmartPlacementStrategy strategy = new SmartRandomPlacementStrategy();
        SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(g, strategy);

        Scene scene = new Scene(new SmartGraphDemoContainer(graphView), 1024, 768);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("JavaFX SmartGraph Visualization");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();

        graphView.init();

        graphView.setVertexDoubleClickAction(graphVertex -> {
            Vertex<String> selectedVertex = graphVertex.getUnderlyingVertex();
            System.out.println("Converting to tree starting at: " + selectedVertex.element());

            Tree<String> treeDFS = graph2tree(g, selectedVertex);
            SmartTreePanel<String> treeView = new SmartTreePanel<>(treeDFS);
            Scene scene2 = new Scene(new SmartGraphDemoContainer(treeView), 1024, 768);

            Stage stage2 = new Stage(StageStyle.DECORATED);
            stage2.setTitle("JavaFX SmartGraph Visualization (Tree)");
            stage2.setMinHeight(500);
            stage2.setMinWidth(800);
            stage2.setScene(scene2);
            stage2.show();
            stage2.toFront();
            treeView.init();
            treeView.setAutomaticLayout(true);

            treeView.getStylableVertex(treeDFS.root().element()).setStyle("-fx-fill: gold; -fx-stroke: brown;");
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Graph<String, String> build_sample_graph() {

        Graph<String, String> g = new GraphEdgeList<>();

        g.insertVertex("0");
        g.insertVertex("1");
        g.insertVertex("2");
        g.insertVertex("3");
        g.insertVertex("4");
        g.insertVertex("5");
        g.insertVertex("6");
        g.insertVertex("7");

        g.insertEdge("1", "0", "10");
        g.insertEdge("1", "2", "12");
        g.insertEdge("1", "3", "13");
        g.insertEdge("3", "5", "35");
        g.insertEdge("3", "7", "37");
        g.insertEdge("5", "6", "56");
        g.insertEdge("6", "7", "67");
        g.insertEdge("6", "2", "62");
        g.insertEdge("6", "4", "64");

        return g;
    }

    public static <V> Tree<V> graph2tree(Graph<V,?> graph , Vertex<V> vertex) {
        Tree<V> tree = new TreeImpl(vertex.element());

        Stack<Vertex<V>> stack = new Stack();
        HashMap<V, TreePosition<V>> parentsMap = new HashMap<>();
        Set<Vertex<V>> visited= new HashSet();

        stack.push(vertex);
        visited.add(vertex);
        parentsMap.put(vertex.element(), tree.root());

        Vertex<V> v,w;
        while(!stack.empty())
        {
            v = stack.pop();
            System.out.println("DFS (visited): " + v.element());
            TreePosition<V> parent = parentsMap.get(v.element());
            for(Edge edge : graph.incidentEdges(v))
            {
                w = graph.opposite(v, edge);
                if(!visited.contains(w))
                {
                    visited.add(w);
                    stack.push(w);
                    TreePosition<V> pos = tree.insert(parent, w.element());
                    parentsMap.put(w.element(),pos);
                }

            }
        }
        return tree;
    }

}
