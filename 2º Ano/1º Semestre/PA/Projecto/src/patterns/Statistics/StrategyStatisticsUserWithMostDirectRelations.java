package patterns.Statistics;

import com.pa.proj2020.adts.graph.Digraph;
import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import patterns.mvc.model.Relation;
import patterns.mvc.model.RelationType;
import patterns.mvc.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the pattern strategy used for calculate the user with the most direct relations
 */
public class StrategyStatisticsUserWithMostDirectRelations implements StrategyStatistics{

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships) {
        List<String> finalOutput = new ArrayList<>();
        finalOutput.add(getUserWithMostRelationsFormat(relationships));
        return finalOutput;
    }

    /**
     * Method to calculate the user with the most relations
     * @param relationships state of the digraph
     * @return user with the most relations
     */
    private static User getUserWithMostRelations(Digraph<User,Relation> relationships){
        int maxValue = 0;
        User maxUser = null;
        for(Vertex<User> user : relationships.vertices()){
            int counter = 0;
            for(Edge<Relation, User> edge : relationships.outboundEdges(user)){
                if(!relationships.incidentEdges(user).contains(edge)) {
                    if (edge.element().getType() == RelationType.DIRECT_INTERESTS ||
                            edge.element().getType() == RelationType.DIRECT_NORMAL){
                        counter++;
                    }
                }
            }
            for(Edge<Relation, User> edge2 : relationships.incidentEdges(user)){
                if(!relationships.outboundEdges(user).contains(edge2)) {
                    if (edge2.element().getType() == RelationType.DIRECT_INTERESTS ||
                            edge2.element().getType() == RelationType.DIRECT_NORMAL){
                        counter++;
                    }
                }
            }
            if(maxValue < counter){
                maxValue = counter;
                maxUser = user.element();
            }
        }
        return maxUser;
    }

    /**
     * Method to calculate the user with the most relations formatted
     * @param relationships state of the digraph
     * @return user with the most relations formatted
     */
    private static String getUserWithMostRelationsFormat(Digraph<User,Relation> relationships){
        if(getUserWithMostRelations(relationships) == null) return "Nao existem utilizadores";
        return "" + "ID: " + getUserWithMostRelations(relationships).getId() + "    Name: " + getUserWithMostRelations(relationships).getName() + "\n";
    }

    @Override
    public List<String> applyStatistic(Digraph<User, Relation> relationships, int userID) {
        return null;
    }
}
