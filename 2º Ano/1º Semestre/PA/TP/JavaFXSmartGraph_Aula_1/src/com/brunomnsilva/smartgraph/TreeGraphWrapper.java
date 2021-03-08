package com.brunomnsilva.smartgraph;

import com.brunomnsilva.smartgraph.graph.Digraph;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.tree.InvalidTreePositionException;
import com.brunomnsilva.smartgraph.tree.Tree;
import com.brunomnsilva.smartgraph.tree.TreeImpl;
import com.brunomnsilva.smartgraph.tree.TreePosition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TreeGraphWrapper<E> implements Tree<E> {

    private final Tree<E> tree;
    private final Digraph<E, Integer> graph;

    private final Map<TreePosition<E>, Vertex<E>> treeVertexMap;

    private int graphEdgeId = 1;

    public TreeGraphWrapper() {
        tree = new TreeImpl<>();
        graph = new DigraphEdgeList<>();
        treeVertexMap = new HashMap<>();
    }

    public TreeGraphWrapper(E rootElem) {
        tree = new TreeImpl<>(rootElem);
        graph = new DigraphEdgeList<>();
        graph.insertVertex(rootElem);
        treeVertexMap = new HashMap<>();
    }

    public Digraph<E, Integer> getGraph() {
        return graph;
    }

    private boolean contains(E element) {
        for(E elem : tree.elements()) {
            if(elem.equals(element)) return true;
        }
        return false;
    }

    @Override
    public TreePosition<E> root() {
        return tree.root();
    }

    @Override
    public TreePosition<E> insert(TreePosition<E> parent, E elem) throws InvalidTreePositionException, NullPointerException {
        //TODO: duplicate code, but different behavior when inserting root on TreeImpl
        if(contains(elem)) throw new UnsupportedOperationException("This implementation does not allow duplicate elements.");

        TreePosition<E> position = tree.insert(parent, elem);
        Vertex<E> vertex = graph.insertVertex(elem);

        treeVertexMap.put(position, vertex);

        if(parent != null) { /* root insertion was performed */
            graph.insertEdge(treeVertexMap.get(parent), vertex, graphEdgeId++);
        }

        return position;
    }

    @Override
    public TreePosition<E> insert(TreePosition<E> parent, E elem, int order) throws InvalidTreePositionException, NullPointerException, IndexOutOfBoundsException {
        //TODO: duplicate code, but different behavior when inserting root on TreeImpl
        if(contains(elem)) throw new UnsupportedOperationException("This implementation does not allow duplicate elements.");

        TreePosition<E> position = tree.insert(parent, elem, order);
        Vertex<E> vertex = graph.insertVertex(elem);

        treeVertexMap.put(position, vertex);

        if(parent != null) { /* root insertion was performed */
            graph.insertEdge(treeVertexMap.get(parent), vertex, graphEdgeId++);
        }

        return position;
    }

    @Override
    public E replace(TreePosition<E> position, E elem) throws InvalidTreePositionException, NullPointerException {

        E old = tree.replace(position, elem);

        Vertex<E> vertex = treeVertexMap.get(position);
        graph.replace(vertex, elem);

        return old;
    }

    @Override
    public E remove(TreePosition<E> position) throws InvalidTreePositionException {
        E removed = tree.remove(position);

        Vertex<E> vertex = treeVertexMap.get(position);
        graph.removeVertex(vertex);

        return removed;
    }

    @Override
    public void move(TreePosition<E> position, TreePosition<E> newParent) throws InvalidTreePositionException {
        //TODO: implement method
        throw new UnsupportedOperationException("This implementation does not currently allow this operation.");
    }

    @Override
    public TreePosition<E> parent(TreePosition<E> position) throws InvalidTreePositionException {
        return tree.parent(position);
    }

    @Override
    public Collection<TreePosition<E>> children(TreePosition<E> position) throws InvalidTreePositionException {
        return tree.children(position);
    }

    @Override
    public boolean isRoot(TreePosition<E> position) throws InvalidTreePositionException {
        return tree.isRoot(position);
    }

    @Override
    public boolean isInternal(TreePosition<E> position) throws InvalidTreePositionException {
        return isInternal(position);
    }

    @Override
    public boolean isExternal(TreePosition<E> position) throws InvalidTreePositionException {
        return tree.isExternal(position);
    }

    @Override
    public Collection<E> elements() {
        return tree.elements();
    }

    @Override
    public Collection<TreePosition<E>> positions() {
        return tree.positions();
    }

    @Override
    public Tree<E> subTree(TreePosition<E> rootPosition) throws InvalidTreePositionException {
        return tree.subTree(rootPosition);
    }

    @Override
    public int height() {
        return tree.height();
    }

    @Override
    public int order() {
        return tree.order();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }
}
