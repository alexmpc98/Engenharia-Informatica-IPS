package patterns.command;

import com.pa.proj2020.adts.graph.Edge;
import patterns.mvc.model.Relation;
import patterns.mvc.model.SocialNetwork;
import patterns.mvc.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will insert indirect relations extends CommandHandler and implements Serializable
 */
public class CommandInsertIndirectRelation extends CommandHandler implements Serializable {

    private User user;
    private List<Edge<Relation, User>> edgeList;

    /**
     * Method to inicalize the social network and the user
     *
     * @param socialNetwork represents a Social Network
     * @param user represents a user to insert
     */
    public CommandInsertIndirectRelation(SocialNetwork socialNetwork, User user) {
        super(socialNetwork);
        this.user = user;
        this.edgeList = new ArrayList<>();
    }

    /**
     * Method that will insert the indirect relations of the user that was inicialized previously
     */
    @Override
    public void execute() {
        this.edgeList = socialNetwork.insertIndirects(this.user);
    }

    /**
     * Method that will remove the indirect relations of the user that was inicialized previously
     */
    @Override
    public void unExecute() {
        socialNetwork.removeIndirects(this.edgeList);
    }
}
