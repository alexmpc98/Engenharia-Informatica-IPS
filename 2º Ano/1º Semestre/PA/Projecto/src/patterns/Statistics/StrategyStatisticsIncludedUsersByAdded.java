package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the pattern strategy used for calculate the included users by an added user
 */
public class StrategyStatisticsIncludedUsersByAdded implements StrategyStatistics{

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        List<String> finalOutput = new ArrayList<>();
        finalOutput.add(getIncludedUsersFormat(relationships, userID) + getNumberOfIncludedUsersFormat(relationships, userID));
        return finalOutput;
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

    /**
     * Method to get the all the included users by an added user formatted
     * @param relationships state of the digraph
     * @param userID id of the user
     * @return list of users included by the user formatted
     */
    public static String getIncludedUsersFormat(Digraph<User, Relation> relationships, int userID){
        List<User> includedUsers = getIncludedUsers(relationships,userID);
        String includedUsersFormat = "The included users from the user " + userID + " are: \n";
        if(includedUsers == null){
            return "The user " + userID + " does not exist or it's not an added one.\n";
        }
        if(includedUsers.isEmpty()){
            return "There are no Included Users by the user " + userID + "\n";
        }

        for(User u :includedUsers){
            includedUsersFormat += "ID: " + u.getId() + " Name: " + u.getName() + "\n";
        }
        return includedUsersFormat;
    }

    /**
     * Method to get the number of included users by an added user
     * @param relationships state of the digraph
     * @param userID id of the user
     * @return int number of added users by the included user
     */
    private static int getNumberOfIncludedUsers(Digraph<User,Relation> relationships, int userID){
        return getIncludedUsers(relationships,userID).size();
    }

    /**
     * Method to get the number of included users by an added user formatted
     * @param relationships state of the digraph
     * @param userID id of the user
     * @return int number of added users by the included user formatted
     */
    public static String getNumberOfIncludedUsersFormat(Digraph<User,Relation> relationships, int userID){
        return "Number of Included Users: " + getNumberOfIncludedUsers(relationships,userID);
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        return null;
    }
}
