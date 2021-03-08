package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.Relation;
import patterns.mvc.model.RelationType;
import patterns.mvc.model.User;
import patterns.mvc.model.UserType;
import java.util.*;

/**
 * Class of the pattern strategy used for calculate the top 5 users with the most included users by them
 */
public class StrategyStatisticsTop5UsersWithMostIncludedUsers implements StrategyStatistics {

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        return top5AddedUsersWithMostIncludedUsersFormat(relationships);
    }

    /**
     * Method to calculate the top 5 users with most included users
     * @param relationships state of the digraph
     * @return map containing the users and the respective number of included users by them
     */
    private static Map<String, Integer> top5AddedUsersWithMostIncludedUsers(Digraph<User,Relation> relationships){
        int counter = 0;
        Map<User,Integer> mapAux = new HashMap<>();
        LinkedHashMap<User, Integer> sortedMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> mapFinal = new LinkedHashMap<>();

        for(Vertex<User> user : relationships.vertices()){
            try{
                mapAux.put(user.element(),getIncludedUsers(relationships,user.element().getId()).size());
            }catch (NullPointerException e){
                continue;
            }
        }

        mapAux.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));


        for(User u : sortedMap.keySet()){
            if(counter < 5){
                mapFinal.put((u.getId() + " - " + u.getName()),sortedMap.get(u));
                counter++;
            }else{
                break;
            }
        }

        return mapFinal;
    }

    /**
     * Method to calculate the top 5 users with most included users formatted
     * @param relationships state of the digraph
     * @return list containing the users and the respective number of included users by them formatted
     */
    public static List<String> top5AddedUsersWithMostIncludedUsersFormat(Digraph<User,Relation> relationships){
        List<String> list = new ArrayList<>();
        LinkedHashMap<String, Integer> mapAux = new LinkedHashMap<>(top5AddedUsersWithMostIncludedUsers(relationships));

        for(String s : mapAux.keySet()){
            list.add(mapAux.get(s).toString());
        }
        for(String s : mapAux.keySet()){
            list.add(s);
        }

        return list;
    }

    /**
     * Method to get the all the included users by an added user
     * @param relationships state of the digraph
     * @param userID id of the user
     * @return list of users included by the user
     */
    private static List<User> getIncludedUsers(Digraph<User, Relation> relationships, int userID){
        List<User> includedUsers = new ArrayList<>();
        Vertex<User> userAux = null;

        for(Vertex<User> u : relationships.vertices()){
            if(u.element().getId() == userID && u.element().getType().equals(UserType.ADDED)){
                userAux = u;
            }
        }

        if(userAux == null) return null; //Either the user does not exist or it's not an added one

        for(Edge<Relation, User> rel : relationships.outboundEdges(userAux)){
            if(rel.element().getType() == RelationType.DIRECT_INTERESTS || rel.element().getType() == RelationType.DIRECT_NORMAL){
                if(relationships.opposite(userAux,rel).element().getType() != UserType.ADDED){
                    includedUsers.add(relationships.opposite(userAux, rel).element());
                }
            }
        }

        return includedUsers;
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        return null;
    }
}
