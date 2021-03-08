package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.Relation;
import patterns.mvc.model.User;

import java.util.*;

/**
 * Class of the pattern strategy used for calculate the top 5 users with the most relations
 */
public class StrategyStatisticsTop5UsersWithMostRelations implements StrategyStatistics{

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        return top5Users(relationships);
    }

    /**
     * Method to get the top 5 users with the most relations
     * @param relationships state of the digraph
     * @return list with the top 5 users and the number of relations
     */
    public static List<String> top5Users(Digraph<User,Relation> relationships){
        List<String>  auxAll = new ArrayList<>();
        Map<User,Integer> auxMap = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        int count = 0;
        int count1 = 0;
        for(Vertex<User> user : relationships.vertices()){
            int counter = 0;
            for(Edge<Relation, User> edge : relationships.outboundEdges(user)){
                if(!relationships.incidentEdges(user).contains(edge)) {
                    counter++;
                }
            }
            for(Edge<Relation, User> edge2 : relationships.incidentEdges(user)){
                if(!relationships.outboundEdges(user).contains(edge2)) {
                    counter++;

                }
            }

            auxMap.put(user.element(),counter);
        }

        auxMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(String.valueOf(x.getKey()), x.getValue()));

        for(Integer a : sortedMap.values()){
            if(count<5){
                auxAll.add(a.toString());
                count++;
            }
        }

        for(String a : sortedMap.keySet()){
            if(count1<5){
                User user = getAnUser(relationships, Integer.parseInt(a));
                auxAll.add(user.getId() + " - " + user.getName());
                count1++;
            }
        }

        return auxAll;
    }

    /**
     * Method to get a user
     * @param relationships state of the digraph
     * @param userID user id
     * @return user corresponding to the user id
     */
    private static User getAnUser(Digraph<User, Relation> relationships, int userID){
        for(Vertex<User> u : relationships.vertices()){
            if(u.element().getId() == userID){
                return  u.element();
            }
        }
        return null;
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        return null;
    }
}
