package pt.pa.model;

import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Graph;
import pt.pa.adts.graph.GraphEdgeList;
import pt.pa.adts.graph.Vertex;

import java.util.*;

public class MapManager {
    
    private Graph<Local,Bridge> graph;
    public enum MODE {DEPTH,BREADTH};


    public MapManager() {
        graph = new GraphEdgeList<>();
    }

    public void  build_map_example() {

        graph = new GraphEdgeList<>();

        Vertex<Local> localA = graph.insertVertex(new Local("A"));
        Vertex<Local> localB = graph.insertVertex(new Local("B"));
        Vertex<Local> localC = graph.insertVertex(new Local("C"));
        Vertex<Local> localD = graph.insertVertex(new Local("D"));
        Vertex<Local> localH = graph.insertVertex(new Local("H"));
        Vertex<Local> localI = graph.insertVertex(new Local("I"));
        Vertex<Local> localE = graph.insertVertex(new Local("E"));

        graph.insertEdge(localA, localB, new Bridge("ponteAB", 20));
        graph.insertEdge(localC, localA, new Bridge("ponteCA", 1));
        graph.insertEdge(localC, localE, new Bridge("ponteEH", 100));
        graph.insertEdge(localA, localH, new Bridge("ponteAH", 100));
        graph.insertEdge(localA, localD, new Bridge("ponteAD", 20));
        graph.insertEdge(localD, localC, new Bridge("ponteDC", 100));
        graph.insertEdge(localB, localH, new Bridge("ponteBH", 1000));
        graph.insertEdge(localI, localH, new Bridge("ponteIH", 1000));
    }

    
    public int minimumCostPath(String origName,
                                       String dstName, List<Local> locals) {

        Vertex<Local> vOrig = findLocal(origName);
        Vertex<Local> vDst= findLocal(dstName);

        if( vOrig==null) throw new IllegalArgumentException("orig does not exist");
        if(vDst==null) throw new IllegalArgumentException("dst does not exist");
        if(locals == null ) throw new IllegalArgumentException("locals list reference is null");


        //Create auxiliary strucutures and run the dijsktra algorithm
        java.util.Map<Vertex<Local>, Double> costs = new HashMap<>();
        java.util.Map<Vertex<Local>, Vertex<Local>> predecessors = new HashMap<>();


        dijkstra(vOrig, costs, predecessors);

        //extract the path, making sure it starts out empty
        locals.clear();

        //flag to indicate if path was complete
        boolean complete = true;
        Vertex<Local> actual = vDst;
        while( actual != vOrig) {
            locals.add(0, actual.element());
            actual = predecessors.get(actual);

            if( actual == null) {
                complete = false;
                break;
            }
        }
        locals.add(0, vOrig.element());

        if(!complete) {
            locals.clear();
            return -1;
        }

        return costs.get(vDst).intValue();
    }

    /**
     * Performs the Dijkstra algorithm starting from 'orig'
     * @param orig the initial vertex
     * @param costs minimum cost from 'orig' to all the other vertex
     * @param predecessors predecessors along the paths
     */
    private void dijkstra(Vertex<Local> orig,
                          Map<Vertex<Local>, Double> costs,
                          Map<Vertex<Local>, Vertex<Local>> predecessors) {

        costs.clear();
        predecessors.clear();
        List<Vertex<Local>> unvisited = new ArrayList<>();

        for (Vertex<Local> v : graph.vertices()) {
            costs.put(v, Double.POSITIVE_INFINITY);
            predecessors.put(v, null);

            unvisited.add(v);
        }
        costs.put(orig, 0.0);
        while(!unvisited.isEmpty()) {
            Vertex<Local> lowerCostVertex = findLowerCostVertex(unvisited, costs);
            unvisited.remove(lowerCostVertex);
            for (Edge<Bridge, Local> incidentEdge : graph.incidentEdges(lowerCostVertex)) {
                Vertex<Local> opposite = graph.opposite(lowerCostVertex, incidentEdge);
                if( unvisited.contains(opposite) ) {
                    double cost = incidentEdge.element().getCost();
                    double pathCost = costs.get(lowerCostVertex) + cost;
                    if( pathCost < costs.get(opposite) ) {
                        costs.put(opposite, pathCost);
                        predecessors.put(opposite, lowerCostVertex);
                    }
                }
            }
        }

    }

    private Vertex<Local> findLowerCostVertex(List<Vertex<Local>> unvisited,
                                              Map<Vertex<Local>, Double> costs) {

        double min = Double.MAX_VALUE;
        Vertex<Local> lowerCostVertex = null;

        for (Vertex<Local> v : unvisited) {
            double cost = costs.get(v);
            if( cost < min ) {
                min = cost;
                lowerCostVertex = v;
            }
        }

        return lowerCostVertex;

    }

    public Vertex<Local> findLocal(String localName)  {
        if( localName == null) return null;
        Vertex<Local> find = null;
        for (Vertex<Local> v : graph.vertices()) {
            if( v.element().getName().equals(localName)) { //equals was overriden in Local!!
                return v;
            }
        }
        return find;
    }

    public String toStringLocals(MODE mode, String l)
    {
        String str="Map Transversal ";
        Vertex<Local> vLocal=findLocal(l);
        Collection<Vertex<Local>> localsVertices;
        if(mode==MODE.BREADTH)
        {
            localsVertices= graph.bfs_order(vLocal);
            str+=" Breadth first\n";
        }
        else {
            localsVertices = graph.dfs_order(vLocal);
            str += " Depth first\n";
        }
        for(Vertex<Local> local:localsVertices){
            str+=local.element().getName() + " ";
        }
        return str;



    }
}
