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
package com.pa.proj2020.adts.graph;

import java.io.Serializable;
import java.util.*;

/**
 * ADT Graph implementation that stores a collection of edges (and vertices) and
 * where each edge contains the references for the vertices it connects.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 * @author brunomnsilva
 */
public class DigraphAdjacentList<V, E> implements Digraph<V, E>, Serializable {

    /* inner classes are defined at the end of the class, so are the auxiliary methods
     */

    private Map<V, Vertex<V>> vertices;

    /**
     * Creates a empty graph.
     */
    public DigraphAdjacentList() {
        this.vertices = new HashMap<>();
    }

    /**
     * Returns the total number of vertices of the graph.
     *
     * @return      total number of vertices
     *
     */
    @Override
    public int numVertices() {
        return vertices.size();
    }

    /**
     * Returns the total number of edges of the graph.
     *
     * @return      total number of vertices
     */
    @Override
    public int numEdges() {
        return edges().size();
    }

    /**
     * Returns the vertices of the graph as a collection.
     *
     * If there are no vertices, returns an empty collection.
     *
     * @return      collection of vertices
     */
    @Override
    public Collection<Vertex<V>> vertices() {
        List<Vertex<V>> list = new ArrayList<>();
        for (Vertex<V> v : vertices.values()) {
            list.add(v);
        }
        return list;
    }

    /**
     * Returns the edges of the graph as a collection.
     *
     * If there are no edges, returns an empty collection.
     *
     * @return      collection of edges
     */
    @Override
    public Collection<Edge<E, V>> edges() {
        List<Edge<E, V>> list = new ArrayList<>();
        for (Vertex<V> v : this.vertices.values()) {
            for (Edge<E, V> e : checkVertex(v).edgesInbound()) {
                if (!list.contains(e)) {
                    list.add(e);
                }
            }
            for (Edge<E, V> e : checkVertex(v).edgesOutbound()) {
                if (!list.contains(e)) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    /**
     * Returns a vertex's <i>incident</i> edges as a collection.
     *
     * Incident edges are all edges that have vertex <code>inbound</code> as the
     * <i>inbound vertex</i>, i.e., the edges "entering" vertex <code>inbound</code>.
     * If there are no incident edges, e.g., an isolated vertex,
     * returns an empty collection.
     *
     * @param v     vertex for which to obtain the incident edges
     *
     * @return            collection of edges
     */
    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        return checkVertex(v).edgesInbound();
    }


    /**
     * Returns a vertex's <i>outbound</i> edges as a collection.
     * <p>
     * Incident edges are all edges that have vertex <code>outbound</code> as the
     * <i>outbound vertex</i>, i.e., the edges "leaving" vertex <code>outbound</code>.
     * If there are no outbound edges, e.g., an isolated vertex,
     * returns an empty collection.
     *
     * @param v vertex for which to obtain the outbound edges
     * @return collection of edges
     */
    @Override
    public Collection<Edge<E, V>> outboundEdges(Vertex<V> v) throws InvalidVertexException {
        return checkVertex(v).edgesOutbound();
    }

    /**
     * Given vertex <code>v</code>, return the opposite vertex at the other end
     * of edge <code>e</code>.
     *
     * If both <code>v</code> and <code>e</code> are valid, but <code>e</code>
     * is not connected to <code>v</code>, returns <i>null</i>.
     *
     * @param v         vertex on one end of <code>e</code>
     * @param e         edge connected to <code>v</code>
     * @return          opposite vertex along <code>e</code>
     *
     * @exception InvalidVertexException    if the vertex is invalid for the graph
     * @exception InvalidEdgeException      if the edge is invalid for the graph
     */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        MyVertex myVertex = checkVertex(v);
        Vertex<V> tempVertex = null;
        List<Vertex<V>> tempVertexList = new ArrayList<>();
        if (myVertex.edgesInbound().contains(e) || myVertex.edgesOutbound().contains(e)) {
            for (Vertex<V> vertex : this.vertices.values()) {
                MyVertex currMyVertex = checkVertex(vertex);
                if(currMyVertex.edgesInbound().contains(e) || currMyVertex.edgesOutbound().contains(e)){
                    tempVertexList.add(vertex);
                }
            }
        }
        if(tempVertexList.size() > 1 ) {
            for (Vertex<V> vertex : tempVertexList) {
                if (!vertex.equals(myVertex)) {
                    tempVertex = vertex;
                }
            }
        } else if(tempVertexList.size() == 1) {
            tempVertex = tempVertexList.get(0);
        }

        return tempVertex;
    }

    /**
     * Evaluates whether two vertices are adjacent, i.e., there exists some
     * directed-edge connecting <code>outbound</code> and <code>inbound</code>.
     *
     * The existing edge must be directed as <code>outbound --&gt; inbound</code>.
     *
     * If, for example, there exists only an edge <code>outbound &lt;-- inbound</code>,
     * they are not considered adjacent.
     *
     * @param outbound    outbound vertex
     * @param inbound     inbound vertex
     *
     * @return      true if they are adjacent, false otherwise.
     *
     * @exception InvalidVertexException    if <code>outbound</code> or
     *                                      <code>inbound</code>
     *                                      are invalid vertices for the graph
     */
    @Override
    public synchronized boolean areAdjacent(Vertex<V> outbound, Vertex<V> inbound) throws InvalidVertexException {
        MyVertex vertex1 = checkVertex(outbound);
        MyVertex vertex2 = checkVertex(inbound);

        for (Edge<E, V> edge : vertex1.edgesOutbound()) {
            for (Edge<E, V> edge2 : vertex2.edgesInbound()) {
                if (edge.equals(edge2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Inserts a new vertex with a given element, returning its reference.
     *
     * @param vElement      the element to store at the vertex
     *
     * @return              the reference of the newly created vertex
     *
     * @exception InvalidVertexException    if there already exists a vertex
     *                                      containing <code>vElement</code>
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object) }
     *                                      method.
     *
     */
    @Override
    public synchronized Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        if (existsVertexWith(vElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }
        MyVertex newVertex = new MyVertex(vElement);

        vertices.put(vElement, newVertex);

        return newVertex;
    }

    /**
     * Inserts a new edge with a given element between two existing vertices and
     * return its (the edge's) reference.
     *
     * @param outbound      outbound vertex
     * @param inbound       inbound vertex
     * @param edgeElement   the element to store in the new edge
     *
     * @return              the reference for the newly created edge
     *
     * @exception InvalidVertexException    if <code>outbound</code> or
     *                                      <code>inbound</code>
     *                                      are invalid vertices for the graph
     *
     * @exception InvalidEdgeException    if there already exists an edge
     *                                      containing <code>edgeElement</code>
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object)}
     *                                      method.
     */
    @Override
    public synchronized Edge<E, V> insertEdge(Vertex<V> outbound, Vertex<V> inbound, E edgeElement)
            throws InvalidVertexException, InvalidEdgeException {

        return insertEdge(outbound.element(),inbound.element(),edgeElement);

    }

    /**
     * Inserts a new edge with a given element between two existing vertices and
     * return its (the edge's) reference.
     *
     * @param vElement1  outbound vertex's stored element
     * @param vElement2   inbound vertex's stored element
     * @param edgeElement      element to store in the new edge
     *
     * @return              the reference for the newly created edge
     *
     * @exception InvalidVertexException    if <code>outboundElement</code> or
     *                                      <code>inboundElement</code>
     *                                      are not found in any vertices of the graph
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object) }
     *                                      method.
     *
     * @exception InvalidEdgeException    if there already exists an edge
     *                                      containing <code>edgeElement</code>
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object) }
     *                                      method.
     */
    @Override
    public synchronized Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement)
            throws InvalidVertexException, InvalidEdgeException {
        /*System.out.println(vElement1+"|"+vElement2);
        System.out.println(vertices + " - " + vertices.containsKey(vElement1)+"|"+vertices.containsKey(vElement2));*/
        if (existsEdgeWith(edgeElement)) {
            throw new InvalidEdgeException("There's already an edge with this element.");
        }

        if (!existsVertexWith(vElement1)) {
            throw new InvalidVertexException("No vertex contains " + vElement1);
        }
        if (!existsVertexWith(vElement2)) {
            throw new InvalidVertexException("No vertex contains " + vElement2);
        }

        MyVertex outVertex = vertexOf(vElement1);
        MyVertex inVertex = vertexOf(vElement2);


        MyEdge newEdge = new MyEdge(edgeElement);

        outVertex.edgesOutbound().add(newEdge);
        inVertex.edgesInbound().add(newEdge);
        return newEdge;

    }

    /**
     * Removes a vertex, along with all of its incident edges, and returns the element
     * stored at the removed vertex.
     *
     * @param v     vertex to remove
     *
     * @return      element stored at the removed vertex
     *
     * @exception InvalidVertexException if <code>v</code> is an invalid vertex for the graph
     */
    @Override
    public synchronized V removeVertex(Vertex<V> v) throws InvalidVertexException {
        MyVertex myVertex = checkVertex(v);
        V element = myVertex.element();
        List<Edge<E, V>> edgeInboundList = new ArrayList<>(myVertex.edgesInbound());
        List<Edge<E, V>> edgeOutboundList = new ArrayList<>(myVertex.edgesOutbound());
        //Inbound
        for(Edge<E, V> edge : edgeInboundList){
            removeEdge(edge);
        }
        //Outbound
        for(Edge<E, V> edge : edgeOutboundList){
            removeEdge(edge);
        }
        vertices.remove(v.element());
        return element;
    }


    /**
     * Removes an edge and return its element.
     *
     * @param e     edge to remove
     *
     * @return      element stored at the removed edge
     *
     * @exception InvalidEdgeException if <code>e</code> is an invalid edge for the graph.
     */
    @Override
    public synchronized E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        List<Vertex<V>> newVerticesOut = new ArrayList<>();
        List<Vertex<V>> newVerticesIn = new ArrayList<>();
        for (Vertex<V> v1 : vertices()) {
            if (checkVertex(v1).edgesInbound().contains(e)) {
                newVerticesIn.add(v1);
            }
            if (checkVertex(v1).edgesOutbound().contains(e)) {
                newVerticesOut.add(v1);
            }
        }
        for(Vertex<V> v2: newVerticesOut){
            checkVertex(v2).edgesOutbound().remove(e);
        }
        for(Vertex<V> v2: newVerticesIn){
            checkVertex(v2).edgesInbound().remove(e);
        }
        E oldElement = e.element();
        return oldElement;
    }

    /**
     * Replaces the element of a given vertex with a new element and returns the
     * previous element stored at <code>v</code>.
     *
     * @param v             vertex to replace its element
     * @param newElement    new element to store in <code>v</code>
     *
     * @return              previous element previously stored in <code>v</code>
     *
     * @exception InvalidVertexException    if the vertex <code>v</code> is invalid for the graph, or;
     *                                      if there already exists another vertex containing
     *                                      the element <code>newElement</code>
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object) }
     *                                      method.
     */
    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        if (existsVertexWith(newElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }

        List<Edge<E, V>> EdgesOut = new ArrayList<>(checkVertex(v).edgesOutbound());
        List<Edge<E, V>> EdgesIn = new ArrayList<>(checkVertex(v).edgesInbound());
        removeVertex(v);
        MyVertex v1 = new MyVertex(newElement);
        v1.listInbound = new ArrayList<>(EdgesIn);
        v1.listOutbound = new ArrayList<>(EdgesOut);
        vertices.put(newElement,v1);

        return v.element();
    }

    /**
     * Replaces the element of a given edge with a new element and returns the
     * previous element stored at <code>e</code>.
     *
     * @param e             edge to replace its element
     * @param newElement    new element to store in <code>e</code>
     *
     * @return              previous element previously stored in <code>e</code>
     *
     * @exception InvalidVertexException    if the edge <code>e</code> is invalid for the graph, or;
     *                                      if there already exists another edge containing
     *                                      the element <code>newElement</code>
     *                                      according to the equality of
     *                                      {@link Object#equals(java.lang.Object)}
     *                                      method.
     */
    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        if (existsEdgeWith(newElement)) {
            throw new InvalidEdgeException("There's already an edge with this element.");
        }
        MyEdge edge = checkEdge(e);
        E oldElement = edge.element;
        for (Vertex<V> v1 : this.vertices.values()) {
            if (checkVertex(v1).edgesInbound().contains(e) || checkVertex(v1).edgesOutbound().contains(e)) {
                edge.element = newElement;
            }

        }
        return oldElement;
    }

    /**
     * This method will convert a V element into MyVertex element.
     *
     * @param vElement     element to transform into MyVertex
     *
     * @return      element of type MyVertex if it works or null otherwise
     *
     */
    private MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : vertices.values()) {
            if (v.element().equals(vElement)) {
                return checkVertex(v);
            }
        }
        return null;
    }

    /**
     * This method check if a certain V element exists.
     *
     * @param vElement     element to check
     *
     * @return     true if it exists otherwise returns false
     *
     */
    private boolean existsVertexWith(V vElement) {
        return vertices.containsKey(vElement);
    }

    /**
     * This method check if a certain E element exists.
     *
     * @param edgeElement     element to check
     *
     * @return     true if it exists otherwise returns false
     *
     */
    private boolean existsEdgeWith(E edgeElement) {
        for (Vertex<V> v1 : this.vertices.values()) {
            for (Edge<E, V> edge : incidentEdges(v1)) {
                if (edge.element().equals(edgeElement)) {
                    return true;
                }
            }
            for (Edge<E, V> edge : outboundEdges(v1)) {
                if (edge.element().equals(edgeElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the graph in string format
     *
     * @return the graph in string format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("Graph with %d vertices and %d edges:\n", numVertices(), numEdges())
        );

        sb.append("--- Vertices: \n");
        for (Vertex<V> v : vertices.values()) {
            sb.append("\t").append(v.toString()).append("\n");
        }
        sb.append("\n--- Edges: \n");
        for (Edge<E, V> e : edges()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Inner class implementation that stores an element of a vertex and their inbounds and outbounds edges.
     *
     */
    public class MyVertex implements Vertex<V>, Serializable {

        V element;
        ArrayList<Edge<E, V>> listInbound;
        ArrayList<Edge<E, V>> listOutbound;

        /**
         * Creates a graph.
         *
         * @param element     to inicialize the V element
         */
        public MyVertex(V element) {
            this.element = element;
            this.listInbound = new ArrayList<>();
            this.listOutbound = new ArrayList<>();
        }

        /**
         * This method will return the V element.
         *
         *
         * @return      element of type V
         *
         */
        @Override
        public V element() {
            return this.element;
        }

        /**
         * This method will return a list of inbound edges.
         *
         *
         * @return      list of inbound edges.
         *
         */
        public ArrayList<Edge<E, V>> edgesInbound() {
            return this.listInbound;
        }

        /**
         * This method will return a list of outbound edges.
         *
         *
         * @return      list of outbound edges.
         *
         */
        public ArrayList<Edge<E, V>> edgesOutbound() {
            return this.listOutbound;
        }

        /**
         * Returns the vertex in string format
         *
         * @return vertex in string format
         */
        @Override
        public String toString() {
            return "Vertex{" + element + '}';
        }
    }

    /**
     * Inner class implementation that stores an element of a edge.
     *
     */
    class MyEdge implements Edge<E, V>, Serializable {

        E element;

        /**
         * Creates a graph.
         *
         * @param element    to inicialize the E element
         */
        public MyEdge(E element) {
            this.element = element;
        }

        /**
         * This method will return the E element.
         *
         *
         * @return      element of type E
         *
         */
        @Override
        public E element() {
            return this.element;
        }

        /**
         * Returns the edge in string format
         *
         * @return edge in string format
         */
        @Override
        public String toString() {
            return "Edge{" + element + "}";
        }

        /**
         * Returns the outbound and inbound vertices of the edge
         *
         * @return array with the outbounnd and inbound vertices
         */
        @Override
        public Vertex<V>[] vertices() {
            Vertex[] verticesInOut = new Vertex[2];
            for (Vertex<V> v : vertices.values()) {
                if (checkVertex(v).edgesOutbound().contains(this)) {
                    verticesInOut[0] = v;
                }
                if(checkVertex(v).edgesInbound().contains(this)) {
                    verticesInOut[1] = v;
                }
            }
            return verticesInOut;
        }
    }

    /**
     * Checks whether a given vertex is valid and belongs to this graph
     *
     * @param v the Vertex to be checked
     * @return MyVertex
     * @throws InvalidVertexException if the vertex is invalid or don't exist
     */
    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {
        if (v == null) throw new InvalidVertexException("Null vertex.");

        MyVertex vertex;
        try {
            vertex = (MyVertex) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Not a vertex.");
        }

        if (!vertices.containsKey(vertex.element)) {
            throw new InvalidVertexException("Vertex does not belong to this graph.");
        }

        return vertex;
    }

    /**
     * Checks whether a given edge is valid and belongs to this graph
     *
     * @param e the Edge to be checked
     * @return MyEdge
     * @throws InvalidVertexException if the edge is invalid or don't exist
     */
    private MyEdge checkEdge(Edge<E, V> e) throws InvalidEdgeException {
        if (e == null) throw new InvalidEdgeException("Null edge.");

        MyEdge edge;
        try {
            edge = (MyEdge) e;
        } catch (ClassCastException ex) {
            throw new InvalidVertexException("Not an edge.");
        }

        if (!existsEdgeWith(edge.element)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }

        return edge;
    }
}
