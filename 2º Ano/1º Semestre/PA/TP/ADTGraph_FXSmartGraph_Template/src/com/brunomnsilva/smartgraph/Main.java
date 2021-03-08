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

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;


/**
 *
 * @author brunomnsilva
 */
public class Main extends Application {

    private volatile boolean running;

    @Override
    public void start(Stage ignored) {

        Graph<String, String> g = build_sample_graph(); //Build Graph
        System.out.println(g);
        
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(g, strategy);

        Scene scene = new Scene(graphView, 1024, 768);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("JavaFX SmartGraph Visualization");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();

        graphView.init();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Graph<String, String> build_sample_graph() {

        Graph<String, String> g = new GraphEdgeList<>();

        Vertex<String> a= g.insertVertex("A");
        Vertex<String> b=g.insertVertex("B");
        Vertex<String> c=g.insertVertex("C");
        Vertex<String> d=g.insertVertex("D");
        Vertex<String> e=g.insertVertex("E");
        Vertex<String> f=g.insertVertex("F");

        g.insertEdge(a, b, "AB");
        g.insertEdge(b  , a, "AB2");
        g.insertEdge(a, c, "AC");
        g.insertEdge(a, d, "AD");
        g.insertEdge(b, c, "BC");
        g.insertEdge(c, d, "CD");
        g.insertEdge(b, e, "BE");
        g.insertEdge(f, d, "DF");
        g.insertEdge(f, d, "DF2");

        //yep, its a loop!
        g.insertEdge(a, a, "Loop");

        return g;
    }

    private static final Random random = new Random(/* seed to reproduce*/);


}
