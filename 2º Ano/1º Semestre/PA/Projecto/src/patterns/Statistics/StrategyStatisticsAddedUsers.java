package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.Relation;
import patterns.mvc.model.User;
import patterns.mvc.model.UserType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class of the pattern strategy used for calculate the added users
 */
public class StrategyStatisticsAddedUsers implements StrategyStatistics{

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        List<String> finalOutput = new ArrayList<>();
        finalOutput.add(getAddedUsersFormat(relationships) + getNumberOfUsersAddedFormat(relationships));
        return finalOutput;
    }

    /**
     * Method to get the added users in a digraph relationships
     * @param relationships state of the digraph
     * @return List of users added in the digraph
     */
    private static List<User> getAddedUsers(Digraph<User, Relation> relationships){
        List<Vertex<User>> allUsers = new ArrayList<>(relationships.vertices());
        List<User> addedUsers = new ArrayList<>();
        for(Vertex<User> u : allUsers){
            if(u.element().getType().equals(UserType.ADDED)){
                addedUsers.add(u.element());
            }
        }
        Collections.sort(addedUsers, new StrategyStatisticsAddedUsers.sortByUserId() {
        });
        return addedUsers;
    }

    /**
     * Method to get the added users in a digraph formatted
     * @param relationships state of the digraph
     * @return String with the formatted information
     */
    private static String getAddedUsersFormat(Digraph<User, Relation> relationships){
        List<User> users = getAddedUsers(relationships);
        String format = "Users added:\n";

        for(User u : users){
            format += "ID: " + u.getId() + " Name: " + u.getName() + "\n";
        }
        return format;
    }

    /**
     * Method to get the number of added users in a digraph
     * @param relationships state of the digraph
     * @return int number of added users in a digraph
     */
    private static int getNumberOfUsersAdded(Digraph<User, Relation> relationships){
        return  getAddedUsers(relationships).size();
    }

    /**
     * Method to get the number of added users in a digraph formatted
     * @param relationships state of the digraph
     * @return String number of added users in a digraph formatted
     */
    private static String getNumberOfUsersAddedFormat(Digraph<User, Relation> relationships){
        return "Number of Added Users: " + getNumberOfUsersAdded(relationships);
    }

    /**
     * Class used to sort the users by id (implements Comparator)
     */
    abstract static class sortByUserId implements Comparator<User> {
        public int compare(User a, User b) {
            return a.getId() - b.getId();
        }
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        return null;
    }
}
