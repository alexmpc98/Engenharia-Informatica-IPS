package patterns.mvc.controller;

import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import patterns.Statistics.StatisticsFactory;
import patterns.Statistics.StrategyStatistics;
import patterns.factory.ExportFactory;
import patterns.mvc.model.Relation;
import patterns.mvc.model.SocialNetwork;
import patterns.mvc.model.User;
import patterns.mvc.view.SocialNetworkUI;
import smartgraph.view.graphview.SmartGraphEdge;
import smartgraph.view.graphview.SmartGraphVertex;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of the controller of the pattern MVC
 */
public class SocialNetworkController {
    private SocialNetworkUI view;
    private SocialNetwork model;
    private ArrayList<User> users;
    private StrategyStatistics strategyStatistics;

    /**
     * Method to initialize the SocialNetworkController
     * @param view view of the MVC pattern
     * @param model model of the MVC pattern
     */
    public SocialNetworkController(SocialNetworkUI view, SocialNetwork model) {
        this.view = view;
        this.model = model;
        model.addObservers(view);
        this.users = new ArrayList<>();
    }

    /**
     * Selector method to return the users of the social network
     * @return users of the social network
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Method to undo the last made action on the program
     */
    public void undo() {
        if (!this.model.isUsersEmpty()) {
            this.view.resetText();
            this.view.enableGlobalButton();
            this.model.undo();
        } else {
            this.view.createAlert("Undo error!", "You can't undo an empty Social Network!", Alert.AlertType.ERROR).show();
        }
    }

    /**
     * Method to load the global import of the program
     */
    public void loadGlobal() {
        if (!this.model.isUsersEmpty()) {
            this.view.createAlert("Global Import Error!",
                    "You can't do a global import when there is already users in Social Network!",
                    Alert.AlertType.ERROR).show();
        } else {
            this.model.globalDataLoad();
            this.view.disableGlobalButton();
        }
    }

    /**
     * Method to load the iterative import of the program
     */
    public void loadIterative() {
        if (this.view.getUserId() != null) {
            this.model.iterativeDataLoad(this.view.getUserId(), false);
        }
    }

    /**
     * Method to load the interests of the program
     */
    public void loadInterests() {
        if (this.view.getUserId() != null) {
            this.model.insertIndirectRelations(this.view.getUserId());
        }
    }

    public void setSelectedUser(SmartGraphVertex<User> vertex) {
        this.view.setVertexStyleClass(vertex, "myVertex");
        this.view.setTextFieldIterative(Integer.toString(vertex.getUnderlyingVertex().element().getId()));
    }

    public void showInterestsRelationInfo(SmartGraphEdge<Relation, User> edge) {
        this.view.setEdgeStyleClass(edge, "myEdge");
        Relation relation = edge.getUnderlyingEdge().element();
        if (relation.isIndirect()) {
            Alert a = this.view.createAlert(
                    "List of Interests",
                    "List of interests of the relation " + relation.getName(),
                    Alert.AlertType.INFORMATION
            );
            TextArea area = this.view.createTextArea(
                    getListOfInterestsInRelationFormat(relation),
                    true,
                    false
            );
            a.getDialogPane().setContent(area);
            a.setResizable(true);
            a.show();
        }
    }

    public void showInterests() {
        Integer userID = this.view.getUserId();
        if (userID == null) {
            this.view.createAlert(
                    "List of Interests",
                    "Choose an user",
                    Alert.AlertType.ERROR
            ).show();
        } else {
            this.view.displayContentAlert(
                    "List of Interests",
                    "List of interests of the user " + userID,
                    Alert.AlertType.INFORMATION,
                    getListOfInterestsFormat(userID)
            );
        }
        this.view.resetSelect();
    }


    /**
     * Method to load a state of the program by a file
     * @param file file being loaded
     */
    public void load(File file) {
        if (file != null) {
            this.model.setExportStrategy(ExportFactory.create(this.view.getFileType(file)));
            SocialNetwork model = this.model.load(file);
            this.model = model;
            this.model.addObservers(view);
            this.view.restartGraphView(this.model);
            this.view.update(this.model);
        }
    }

    /**
     * Method to save a state of the program to a file
     * @param file file in which the data is going to be saved
     */
    public void save(File file) {
        if (file != null) {
            this.model.setExportStrategy(ExportFactory.create(this.view.getFileType(file)));
            this.model.save(file);
        }
    }


    /**
     * Method to get an user from a vertex
     * @param vertex vertex which contains the user
     * @return user of the vertex
     */
    public User getUser(Vertex<User> vertex) {
        return vertex.element();
    }

    /**
     * Method to get the minimum cost between two users ids
     * @param origId ID of the user of origin
     * @param dstId ID of the user destination
     * @return int minimum cost between two users
     */
    public int minCost(int origId, int dstId) {
        return model.minimumCostPath(origId, dstId, users);
    }

    /**
     * Method to get the edges path of the minimum cost path algorithm
     * @return list of the edges which indicate the path
     */
    public ArrayList<Edge<Relation, User>> getEdgesPath() {
        ArrayList<Edge<Relation, User>> relations = new ArrayList<>();
        for (int i = 0; i < users.size() - 1; i++) {
            Vertex<User> auxUser = model.returnVertex(users.get(i));
            Vertex<User> auxUser1 = model.returnVertex(users.get(i + 1));

            for (Edge<Relation, User> edges : model.getRelationships().outboundEdges(auxUser)) {
                if (model.getRelationships().opposite(auxUser, edges).equals(auxUser1)) {
                    relations.add(edges);
                }
            }
        }
        return relations;
    }

    /**
     * Method to get the list of interests in a relation formatted
     * @param relation relation whose list of interests is wanted
     * @return list of interests of a relation
     */
    public String getListOfInterestsInRelationFormat(Relation relation) {
        return this.model.getListOfInterestsInRelationFormat(relation);
    }

    /**
     * Method to get the list of interests from a user formatted
     * @param userID user whose list of interests is wanted
     * @return list of interests of an user
     */
    public String getListOfInterestsFormat(int userID) {
        return this.model.getListOfInterestsFormat(userID);
    }

    /**
     * Method to return the node of a user
     * @param user user whose node is wanted
     * @return Vertex which contains the user
     */
    public Vertex<User> getUserNode(User user){
        return this.model.returnVertex(user);
    }


    /**
     * Method to set the strategy for the statistic wanted
     * @param type type of the strategy wanted
     */
    public void setStrategyStatistics(String type){
        this.strategyStatistics = StatisticsFactory.create(type);
    }

    /**
     * Method to apply the strategy wanted
     * @return List of Strings with the information from the strategy made
     */
    public List<String> applyStrategy(){
        return this.strategyStatistics.applyStatistic(model.getRelationships());
    }
    /**
     * Method to apply the strategy wanted
     * @param userID user id needed for the strategy
     * @return List of Strings with the information from the strategy made
     */
    public List<String> applyStrategy(int userID){
        return this.strategyStatistics.applyStatistic(model.getRelationships(), userID);
    }
}
