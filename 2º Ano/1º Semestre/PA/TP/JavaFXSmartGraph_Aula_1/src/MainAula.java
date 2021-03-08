import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graph.*;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.*;

public class MainAula extends Application {

    @Override
    public void start(Stage ignore) throws Exception {
        Graph<Integer, String> g = new GraphEdgeList<>();
        Vertex<Integer> v1 = g.insertVertex(1);
        Vertex<Integer> v2 = g.insertVertex(2);
        Vertex<Integer> v3 = g.insertVertex(3);
        Vertex<Integer> v4 = g.insertVertex(4);
        Vertex<Integer> v5 = g.insertVertex(5);
        Vertex<Integer> v6 = g.insertVertex(6);

        g.insertEdge(v1,v6,"16");
        g.insertEdge(v1,v5,"15");
        g.insertEdge(v1,v2,"12");
        g.insertEdge(v2,v3,"23");
        g.insertEdge(v2,v5,"25");
        g.insertEdge(v5,v4,"54");
        g.insertEdge(v3,v4,"34");

        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        //SmartPlacementStrategy strategy = new SmartRandomPlacementStrategy();
        SmartGraphPanel<Integer, String> graphView = new SmartGraphPanel<>(g, strategy);

        Scene scene = new Scene(new SmartGraphDemoContainer(graphView), 1024, 768);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("JavaFX SmartGraph Visualization");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();

        graphView.init();

        //BFS(g, v4);
        DFS(g, v1);
    }

    public static <V,E> void BFS(Graph<V, E> g, Vertex<V> vertex_root) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        List<Vertex<V>> visited = new ArrayList<>();

        visited.add(vertex_root);
        queue.offer(vertex_root);

        while(!queue.isEmpty()) {
            Vertex<V> v = queue.poll();
            /* Processar v */
            System.out.println(v.element());

            for(Vertex<V> w : g.vertices()) {
                if(g.areAdjacent(v,w)) {
                    if(!visited.contains(w)) {
                        visited.add(w);
                        queue.offer(w);
                    }
                }
            }
//            Collection<Edge<E, V>> incidentEdges = g.incidentEdges(v);
//            for(Edge<E,V> edge : incidentEdges) {
//                Vertex<V> w = g.opposite(v, edge);
//                if(!visited.contains(w)) {
//                    visited.add(w);
//                    queue.offer(w);
//                }
//            }

        }
    }

    public static <V,E> void DFS(Graph<V, E> g, Vertex<V> vertex_root) {
        Stack<Vertex<V>> queue = new Stack<>();
        List<Vertex<V>> visited = new ArrayList<>();

        visited.add(vertex_root);
        queue.push(vertex_root);

        while(!queue.isEmpty()) {
            Vertex<V> v = queue.pop();
            /* Processar v */
            System.out.println(v.element());

            Collection<Edge<E, V>> incidentEdges = g.incidentEdges(v);
            for(Edge<E,V> edge : incidentEdges) {
                Vertex<V> w = g.opposite(v, edge);
                if(!visited.contains(w)) {
                    visited.add(w);
                    queue.push(w);
                }
            }

        }
    }
}
