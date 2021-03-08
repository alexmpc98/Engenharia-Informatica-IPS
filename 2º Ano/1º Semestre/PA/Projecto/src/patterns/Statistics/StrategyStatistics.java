package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import patterns.mvc.model.Relation;
import patterns.mvc.model.User;

import java.util.List;

/**
 * Class Strategy for the statistics
 */
public interface StrategyStatistics {

    /**
     * Method to apply a statistic based on a relationship digraph
     * @param relationships state of the digraph
     * @return list of string with the information of the statistic calculated
     */
    List<String> applyStatistic(Digraph<User, Relation> relationships);

    /**
     * Method to apply a statistic based on a relationship digraph
     * @param relationships state of the digraph
     * @param userID id of the user
     * @return list of string with the information of the statistic calculated
     */
    List<String> applyStatistic(Digraph<User, Relation> relationships, int userID);
}
