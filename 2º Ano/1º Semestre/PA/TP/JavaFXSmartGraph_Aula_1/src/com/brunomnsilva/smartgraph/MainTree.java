package com.brunomnsilva.smartgraph;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graph.*;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.tree.Tree;
import com.brunomnsilva.smartgraph.tree.TreeImpl;
import com.brunomnsilva.smartgraph.tree.TreePosition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.*;

public class MainTree extends Application {

    @Override
    public void start(Stage ignored) throws Exception {
        Tree<String> tree = build_tree();

        System.out.println(tree);
        System.out.println("Size: " + tree.size());
        System.out.println("Height: " + tree.height());

        Digraph<String, Integer> digraph = tree2digraph(tree);
        System.out.println(digraph);

        SmartGraphPanel<String, Integer> graphView = new SmartGraphPanel(digraph);
        Scene scene = new Scene(new SmartGraphDemoContainer(graphView), 1024, 768);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("JavaFX SmartGraph Visualization (Tree)");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();
        graphView.init();

        graphView.getStylableVertex(tree.root().element()).setStyle("-fx-fill: gold; -fx-stroke: brown;");

    }

    private static Tree<String> build_tree() {
        Tree<String> tree = new TreeImpl<>();

        TreePosition<String> ceo = tree.insert(null, "CEO");
        TreePosition<String> production_manager = tree.insert(ceo, "Production Manager");
        TreePosition<String> personnel_manager = tree.insert(ceo, "Personnel Manager");
        TreePosition<String> sales_manager = tree.insert(ceo, "Sales Manager");
        tree.insert(production_manager, "Purchasing Supervisor");
        tree.insert(production_manager, "Warehouse Supervisor");
        tree.insert(sales_manager, "Shipping Supervisor");

        return tree;
    }

    public static <V> Digraph<V,Integer> tree2digraph(Tree<V> tree) {
        if(tree == null) throw new NullPointerException("Tree cannot be null.");

        Digraph<V, Integer> digraph = new DigraphEdgeList<>();
        if(tree.isEmpty()) return digraph;

        Queue<TreePosition<V>> queueTree = new LinkedList<>();
        Queue<Vertex<V>> queueGraph = new LinkedList<>();

        queueTree.offer(tree.root());

        Vertex<V> vertex = digraph.insertVertex(tree.root().element());

        queueGraph.offer(vertex);

        int edgeId = 1;
        while(!queueTree.isEmpty()) {
            TreePosition<V> nodeTree = queueTree.poll();
            Vertex<V> currentThis = queueGraph.poll();

            for(TreePosition<V> childNode : tree.children(nodeTree)) {
                queueTree.offer(childNode);

                Vertex<V> inserted = digraph.insertVertex(childNode.element());
                digraph.insertEdge(currentThis, inserted, edgeId++);

                queueGraph.offer(inserted);
            }
        }

        return digraph;
    }
}
