package pt.pa.adts.graph;


import pt.pa.adts.tree.Position;
import pt.pa.adts.tree.Tree;
import pt.pa.adts.tree.TreeLinked;
import pt.pa.model.Bridge;


import java.util.*;

public class GraphUtils {


    /**
     *
     * @param graph -Graph to transverse in Depth order
     * @param vertex - Start point
     * @param <V> -Type of Vertex Elements
     * @return - A Tree resulting from tranverse the graph using depth first strategy, starting from vertex
     * @throws InvalidVertexException if the vertex is null or not belongs to the graph
     */
    public static <V> Tree<V> treeDFS(Graph<V,?> graph , Vertex<V> vertex)throws InvalidVertexException{
        Vertex<V> v,w;
        TreeLinked<V> tree = new TreeLinked(vertex.element());
        Stack<Vertex<V>> stack = new Stack();
        HashMap<V, Position<V>> parentsMap= new HashMap<>();
        stack.push(vertex);
        Set<Vertex<V>> visited= new HashSet();
        visited.add(vertex);
        parentsMap.put(vertex.element(), tree.root());
        while(!stack.empty())
        {
            v=stack.pop();
            Position<V> parent = parentsMap.get(v.element());
            for(Edge edge: graph.incidentEdges(v))
            {
                w=graph.opposite(v,edge);
                if(!visited.contains(w))
                {
                    visited.add(w);
                    stack.push(w);
                    Position<V> pos = tree.insert(parent, w.element());
                    parentsMap.put(w.element(),pos);
                }

            }
        }
        return tree;


    }

    /**
     *
     * @param graph -Graph to transverse in Breadth first Order
     * @param vertex - Start point
     * @param <V> -Type of Vertex Elements
     * @return - A Tree resulting from tranverse the graph using Braedth first strategy, starting from vertex
     * @throws InvalidVertexException if the vertex is null or not belongs to the graph
     */
    public static <V> Tree<V> treeBFS(Graph<V,?> graph , Vertex<V> vertex)throws InvalidVertexException{
        Vertex<V> v,w;
        TreeLinked<V> tree = new TreeLinked(vertex.element());
        Queue<Vertex<V>> queue = new LinkedList();
        HashMap<V, Position<V>> parentsMap= new HashMap<>();
        queue.offer(vertex);
        Set<Vertex<V>> visited= new HashSet();
        visited.add(vertex);
        parentsMap.put(vertex.element(), tree.root());
        while(!queue.isEmpty())
        {
            v=queue.offer();
            Position<V> parent = parentsMap.get(v.element());
            for(Edge edge: graph.incidentEdges(v))
            {
                w=graph.opposite(v,edge);
                if(!visited.contains(w))
                {
                    visited.add(w);
                    queue.offer(w);
                    Position<V> pos = tree.insert(parent, w.element());
                    parentsMap.put(w.element(),pos);
                }

            }
        }
        return tree;


    }


}
