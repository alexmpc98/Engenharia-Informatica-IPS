package com.brunomnsilva.smartgraph.graphview;

import com.brunomnsilva.smartgraph.graph.Digraph;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.tree.Tree;
import com.brunomnsilva.smartgraph.tree.TreePosition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class SmartTreePanel<E> extends SmartGraphPanel<E, Integer> {

    private final Tree<E> tree;


    public SmartTreePanel(Tree<E> tree) {
        super(tree2digraph(tree), new SmartGraphProperties(), null);
        this.tree = tree;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    @Override
    public void updateAndWait() {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    @Override
    public void setEdgeDoubleClickAction(Consumer<SmartGraphEdge<Integer, E>> action) {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    @Override
    public SmartStylableNode getStylableVertex(Vertex<E> v) {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    @Override
    public SmartStylableNode getStylableEdge(Edge<Integer, E> edge) {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    @Override
    public SmartStylableNode getStylableEdge(Integer edgeElement) {
        throw new UnsupportedOperationException("This panel does not support this operation.");
    }

    private static <V> Digraph<V,Integer> tree2digraph(Tree<V> tree) {
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
