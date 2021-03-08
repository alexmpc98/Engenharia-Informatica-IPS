package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.Relation;
import patterns.mvc.model.User;
import java.util.ArrayList;
import java.util.List;



/**
 * Class of the pattern strategy used for calculate the Average number of relations
 */
public class StrategyStatisticsAvgNumberOfInterests implements StrategyStatistics{

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        List<String> finalOutput = new ArrayList<>();
        finalOutput.add(getAverageNumberOfInterestsPerUserFormat(relationships));
        return finalOutput;
    }

    /**
     * Method to get the average number of interests per user
     * @param relationships state of the digraph
     * @return double average number of interests per user
     */
    private static double getAverageNumberOfInterestsPerUser(Digraph<User,Relation> relationships) {
        int counter = 0;
        for(Vertex<User> user : relationships.vertices()){
            counter += user.element().getListOfInterest().size();
        }

        return counter / (double)relationships.vertices().size();
    }

    /**
     * Method to get the average number of interests per user formatted
     * @param relationships state of the digraph
     * @return double average number of interests per user formatted
     */
    private static String getAverageNumberOfInterestsPerUserFormat(Digraph<User,Relation> relationships){
        return "The average number of Interests per user is " + round(getAverageNumberOfInterestsPerUser(relationships),2);
    }

    /**
     * Method to round a double value
     * @param value value being rounded
     * @param places number of decimal cases wanted
     * @return double value rounded
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        return null;
    }
}
