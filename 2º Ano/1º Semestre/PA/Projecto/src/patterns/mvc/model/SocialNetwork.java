package patterns.mvc.model;

import com.pa.proj2020.adts.graph.*;
import patterns.command.*;
import patterns.factory.Strategy;
import patterns.observer.Subject;
import patterns.singleton.Logger;
import java.io.File;
import java.io.Serializable;
import java.util.*;


/**
 * Class that represents a Social Network using a DiagraphAdjacentList associating Users with their respective Relations
 */
public class SocialNetwork extends Subject implements Serializable {

    private Digraph<User, Relation> relationships;
    private FileReader fr;
    private CommandManager cmdManager;
    private Strategy exportStrategy;

    /**
     * Method to initialize a Social Network
     */
    public SocialNetwork() {
        this.relationships = new DigraphAdjacentList();
        this.fr = new FileReader();
        this.cmdManager = new CommandManager();
        this.exportStrategy = null;
        Logger.getInstance().resetLog();
    }

    /**
     * Setter method to set the strategy for the import of the data
     * @param strategy Strategy for the import of the data
     */
    public void setExportStrategy(Strategy strategy) {
        this.exportStrategy = strategy;
    }

    /**
     * Method to load the data from a file into the Social Network
     * @param file file with the data whose want to be loaded
     * @return SocialNetwork with the data loaded
     */
    public SocialNetwork load(File file) {
        return exportStrategy.load(file);
    }

    /**
     * Method to save a file with data loaded
     * @param file file in which the data is going to be saved
     */
    public void save(File file) {
        exportStrategy.save(this, file);
    }

    /**
     * Method to fully import the data from the files into the program
     */
    public void globalDataLoad() {
        cmdManager.executeCommand(new CommandGlobalImport(this));
        notifyObservers(this);
    }

    /**
     * Method to import a single user into the program or all the users into the program
     * @param userID user id which wants to be imported
     * @param global boolean to identify if it's going to be global import or iterative
     */
    public void iterativeDataLoad(int userID, boolean global) {
        fr.importRelationshipIterative(userID);
        if (exists(userID)) {
            return;
        }
        insertIterativeUsers(userID, global);
        insertIterativeRelations(userID);
        insertIterativeInterests(userID);
        notifyObservers(this);
    }

    /**
     * Method to know if there are users loaded
     * @return true if there are users loaded, false otherwise
     */
    public boolean isUsersEmpty() {
        return this.relationships.vertices().isEmpty();
    }

    /**
     * Method to load data from a backup data list
     * @param list list with the backup data information
     */
    public void loadFromBackup(List<BackupData> list) {
        for (BackupData backupData : list) {
            if (!backupData.getUserList().isEmpty()) {
                iterativeDataLoad(backupData.getUser().getId(), false);
            }
        }
        for (BackupData b : list) {
            if (b.getInterest()) {
                insertIndirects(b.getUser());
            }
        }
        notifyObservers(this);
    }

    /**
     * Method to insert an user into the relationships
     * @param user user who's wants to be inserted
     */
    public void insertUser(User user) {
        if (user == null) {
            return;
        }
        try {
            relationships.insertVertex(user);
        } catch (InvalidVertexException e) {
            System.out.println("ERROR");
        }
        notifyObservers(this);
    }

    /**
     * Method to remove an user from the relationships
     * @param user user who's wants to be removed
     */
    public void removeUser(User user) {
        if (user == null) {
            return;
        }
        Vertex<User> removeVertex = null;
        for (Vertex<User> v : relationships.vertices()) {
            if (user.getId() == v.element().getId()) {
                removeVertex = v;
            }
        }
        relationships.removeVertex(removeVertex);
        notifyObservers(this);
    }

    /**
     * Method to undo the last possible action made in the program
     */
    public void undo() {
        cmdManager.undo();
        notifyObservers(this);
    }

    /**
     * Method to insert all users into the program
     */
    public void insertUsers() {
        for (User u : fr.getSocialMap().keySet()) {
            iterativeDataLoad(u.getId(), true);
        }
    }

    /**
     * Method to remove all users from the program
     */
    public void removeUsers() {
        List<User> usersList = new ArrayList<>(fr.getSocialMap().keySet());
        for (User u : usersList) {
            removeUser(u);
        }
    }

    /**
     * Method to remove a user from the program
     * @param user user who wants to be removed
     * @param exist boolean to verify if the user who's being removed exists
     */
    public void removeUsers(User user, boolean exist) {
        if (user == null) {
            return;
        }
        List<User> deleteList = new ArrayList<>(getIncluded(user));
        if (exist) {
            returnVertex(user).element().changeType();
            List<Edge<Relation, User>> relList = new ArrayList<>(relationships.outboundEdges(returnVertex(user)));
            for (Edge<Relation, User> edge : relList) {
                relationships.removeEdge(edge);
            }
        } else {
            deleteList.add(user);
        }
        verificationOfIncludedWithDoubleRelation(deleteList, user);
        System.out.println("DEL: " + deleteList);
        for (User u : deleteList) {
            relationships.removeVertex(returnVertex(u));
        }
        notifyObservers(this);
    }

    /**
     * Method to verify if a user exists in the program
     * @param u user who is being verified
     * @param exceptionUser user who is being compared to
     * @return true if user u already exists, false otherwise
     */
    public boolean verifyIfExistsAlready(User u, User exceptionUser) {
        if (u.isAdded()) {
            return false;
        }
        for (Vertex<User> user : relationships.vertices()) {
            if (user.element() != exceptionUser) {
                if (user.element().isAdded()) {
                    for (Edge<Relation, User> edge1 : relationships.incidentEdges(user)) {
                        if (relationships.opposite(user, edge1).element() == u) {
                            return true;
                        }
                    }
                    for (Edge<Relation, User> edge2 : relationships.outboundEdges(user)) {
                        if (relationships.opposite(user, edge2).element() == u) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to verify if an user is to be removed of a list
     * @param includedList list of Users
     * @param exceptionUser user being verified
     */
    public void verificationOfIncludedWithDoubleRelation(Collection<User> includedList, User exceptionUser) {
        List<User> finalManipulationList = new ArrayList<>();
        for (User includedUsers : includedList) {
            if (verifyIfExistsAlready(includedUsers, exceptionUser)) {
                finalManipulationList.add(includedUsers);
            }
        }
        for (User usersToNotRemove : finalManipulationList) {
            includedList.remove(usersToNotRemove);
        }
    }

    /**
     * Method to change the type of a user
     * @param user user who is going to get the type changed
     */
    public void changeUserType(User user) {
        returnVertex(user).element().changeType();
    }

    /**
     * Method to insert users iterative
     * @param userId userID being inserted
     * @param global boolean to know if it's a global import or not
     */
    public void insertIterativeUsers(int userId, boolean global) {
        User addedUser = null;
        if (fr.sizeOfUserMapKeySet() == 1) {
            addedUser = fr.getIterativeMap().keySet().iterator().next();
            addedUser.setListOfInterest(getListInterests(addedUser.getId()));
            for (Interest interest : addedUser.getListOfInterest()) {
                Logger.getInstance().writeToLog(addedUser.getId() + " | " + interest.getId());
            }
            if (addedUser.getId() == userId) {
                if (!global) {
                    if (!checkIfIdExistsVertices(addedUser.getId())) {
                        cmdManager.executeCommand(new CommandInsertUser(this, addedUser, false));
                    } else {
                        cmdManager.executeCommand(new CommandInsertUser(this, addedUser, true));
                    }
                } else {
                    if (!checkIfIdExistsVertices(addedUser.getId())) {
                        insertUser(addedUser);
                    } else {
                        changeUserType(addedUser);
                    }
                }
                Logger.getInstance().writeToLog(addedUser.getId() + " | " + fr.sizeOfValueFromUserMap(addedUser) + " | " + getTotalOcurrencesInInterests(addedUser.getId()));
            }
            for (User includedUsers : fr.getIterativeMap().get(addedUser)) {
                if (!checkIfIdExistsVertices(includedUsers.getId())) {
                    includedUsers.setListOfInterest(getListInterests(includedUsers.getId()));
                    relationships.insertVertex(includedUsers);
                }
            }
        }
    }

    /**
     * Method to insert relations iterative
     * @param userId id of the user who's getting his relations inserted
     */
    public void insertIterativeRelations(int userId) {
        User u = null;
        if (fr.sizeOfUserMapKeySet() == 1) {
            u = fr.getIterativeMap().keySet().iterator().next();
            if (u.getId() == userId) {
                //System.out.println(relationships);
                for (User u2 : fr.getIterativeMap().get(u)) {
                    String name = "e - " + u.getId() + " | " + u2.getId();
                    //System.out.println(name);
                    //System.out.println(u2 + "-"+returnVertex(u2).element());
                    if (returnVertex(u2) != null) {
                        //System.out.println("F"+returnVertex(u2).element()+"-"+returnVertex(u).element());
                        if (hasInterest(u.getId(), u2.getId())) {
                            relationships.insertEdge(returnVertex(u).element(), returnVertex(u2).element(), new Relation(name, RelationType.DIRECT_INTERESTS));
                        } else {
                            relationships.insertEdge(returnVertex(u).element(), returnVertex(u2).element(), new Relation(name, RelationType.DIRECT_NORMAL));
                        }
                    } else {
                        relationships.insertEdge(u, u2, new Relation(name, RelationType.DIRECT_NORMAL));
                    }
                    Logger.getInstance().writeToLog(u.getId() + " | " + u2.getId() + " | " + getTotalOcurrencesInInterests(u.getId(), u2.getId()));
                }
            }
        }
    }

    /**
     * Method to check if an vertice id already exists
     * @param id if being checked
     * @return true if exists, false otherwise
     */
    public boolean checkIfIdExistsVertices(int id) {
        for (Vertex<User> u : relationships.vertices()) {
            if (u.element().getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to verify if there is an interest between two users
     * @param userId user being compared to the user2
     * @param userId2 user being compared to the user1
     * @return true if there is an interest between them, false otherwise
     */
    private boolean hasInterest(int userId, int userId2) {
        Vertex<User> userVertex = returnVertexWithId(userId);
        Vertex<User> userVertex2 = returnVertexWithId(userId2);
        for (Interest interest : userVertex.element().getListOfInterest()) {
            if (userVertex2.element().getListOfInterest().contains(interest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Selector method to get a list of interests of a user
     * @param userId user whose interests are wanted
     * @return Collection<Interest> collection of interests of an user
     */
    private Collection<Interest> getListInterests(int userId) {
        List<Interest> listOfInterests = new ArrayList<>();
        for (Interest interest : fr.getInterestMap().keySet()) {
            if (fr.interestMapValuesContainsUser(interest,userId)){
                listOfInterests.add(interest);
            }
        }
        return listOfInterests;
    }

    /**
     * Selector method to get the list of interests of an user formatted
     * @param userID user whose interests are wanted
     * @return String with the list of interests from an user formatted
     */
    public String getListOfInterestsFormat(int userID) {
        String listOfInterests = "";

        for (Interest i : getListInterests(userID)) {
            listOfInterests += i.getId() + " - " + i.getName() + "\n";
        }
        return listOfInterests;
    }

    /**
     * Method to get the list of interests in a relation
     * @param relation who's list of interests is wanted
     * @return Collection<Interest> list of interests in a relation
     */
    private Collection<Interest> getListOfInterestInRelation(Relation relation) {
        if (!relation.isIndirect()) {
            return null;
        }
        Edge<Relation, User> edgeToUse = null;
        List<Interest> listOfInterests = new ArrayList<>();
        for (Edge<Relation, User> edge : relationships.edges()) {
            if (edge.element() == relation) {
                edgeToUse = edge;
            }
        }
        List<User> list = new ArrayList<>();
        for (Vertex<User> v : relationships.vertices()) {
            if (relationships.outboundEdges(v).contains(edgeToUse) || relationships.incidentEdges(v).contains(edgeToUse)) {
                list.add(v.element());
            }
        }
        for (Interest interest : list.get(0).getListOfInterest()) {
            if (list.get(1).getListOfInterest().contains(interest)) {
                listOfInterests.add(interest);
            }
        }
        return listOfInterests;
    }

    /**
     * Selector Method to get the list of interests in a relation formatted
     * @param relation relation whose interests are wanted
     * @return String with the list of interests from an relation formatted
     */
    public String getListOfInterestsInRelationFormat(Relation relation) {
        String listOfInterests = "";
        for (Interest i : getListOfInterestInRelation(relation)) {
            listOfInterests += i.getId() + " - " + i.getName() + "\n";
        }
        return listOfInterests;
    }

    /**
     * Method to insert interests of an user iterative way
     * @param userId user whose interests are wanted
     */
    public void insertIterativeInterests(int userId) {
        List<Interest> interestList = new ArrayList<>(fr.getInterestMap().keySet());
        Collections.sort(interestList, new sortByInterestId() {
        });
        for (Vertex<User> user : relationships.vertices()) {
            if (user.element().getId() == userId) {
                for (Edge<Relation, User> edge : relationships.outboundEdges(user)) {
                    Vertex<User> opp = relationships.opposite(user, edge);
                    if (hasInterest(userId, opp.element().getId())) {
                        User userWithId = returnVertexWithId(userId).element();
                        if (returnEdge(userWithId, opp.element()).element().isDirectNormal()) {
                            String name = returnEdge(userWithId, opp.element()).element().getName();
                            relationships.replace(returnEdge(userWithId, opp.element()),
                                    new Relation(name, RelationType.DIRECT_INTERESTS));
                        }
                    }
                }
                for (Edge<Relation, User> edge : relationships.incidentEdges(user)) {
                    Vertex<User> opp = relationships.opposite(user, edge);
                    if (hasInterest(userId, opp.element().getId())) {
                        User userWithId = returnVertexWithId(userId).element();
                        if (returnEdge(userWithId, opp.element()).element().isDirectNormal()) {
                            String name = returnEdge(userWithId, opp.element()).element().getName();
                            relationships.replace(returnEdge(userWithId, opp.element()),
                                    new Relation(name, RelationType.DIRECT_INTERESTS));
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to insert indirect relations of an user
     * @param id id of the user which the indirect relations are going to be imported
     */
    public void insertIndirectRelations(int id) {
        cmdManager.executeCommand(new CommandInsertIndirectRelation(this, returnVertexWithId(id).element()));
        System.out.println(cmdManager.listAll());
    }

    /**
     * Method to insert indirect relations of an user
     * @param user user which the relations are wanted
     * @return list with the relations from an user
     */
    public List<Edge<Relation, User>> insertIndirects(User user) {
        if (user.isIncluded()) {
            return null;
        }
        int userId = user.getId();
        List<Edge<Relation, User>> edgesList = new ArrayList<>();
        List<Interest> interestList = new ArrayList<>(fr.getInterestMap().keySet());
        Collections.sort(interestList, new sortByInterestId() {
        });
        for (Interest interest : interestList) {
            for (int i = 0; i < fr.sizeOfValueFromInterestMap(interest); i++) {
                if (fr.returnValueFromInterestMap(interest, i) == userId) {
                    for (int j = 0; j < fr.sizeOfValueFromInterestMap(interest); j++) {
                        int mapArrayValue = fr.returnValueFromInterestMap(interest, j);
                        if (mapArrayValue != userId) {
                            if (checkIfIdExistsVertices(mapArrayValue) &&
                                    checkIfIdExistsVertices(userId)) {
                                Logger.getInstance().writeToLog(userId + " | " + interest.getId());
                                if (!relationships.areAdjacent(returnVertexWithId(userId), returnVertexWithId(
                                        mapArrayValue))) {
                                    String relName = "" + getTotalOcurrencesInInterests(userId, mapArrayValue);
                                    relationships.insertEdge(returnVertexWithId(userId),
                                            returnVertexWithId(mapArrayValue),
                                            new Relation(relName, RelationType.INDIRECT));
                                    edgesList.add(returnEdgeInterest(user, getUserById(mapArrayValue)));
                                    Logger.getInstance().writeToLog(userId + " | " + mapArrayValue + " | " + interest.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
        notifyObservers(this);
        return edgesList;
    }

    /**
     * Method to remove indirect relations
     * @param edgeList list of edges being removed
     */
    public void removeIndirects(List<Edge<Relation, User>> edgeList) {
        for (Edge<Relation, User> e : edgeList) {
            relationships.removeEdge(e);
        }
        notifyObservers(this);
    }

    /**
     * Method to verify if a user exists by its id
     * @param userId user id
     * @return true if exists, false otherwise
     */
    public boolean exists(int userId) {
        if (relationships.vertices().contains(returnVertexWithId(userId))) {
            if (returnVertexWithId(userId).element().isAdded()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to count the number of ocurrences between two interests
     * @param id1 id of the interest being compared to the second
     * @param id2 id of the interest being compared to the first
     * @return int number of interest between the two interest
     */
    public int getTotalOcurrencesInInterests(int id1, int id2) {
        int counter = 0;
        for (Interest interest : fr.getInterestMap().keySet()) {
            if (fr.getInterestMap().get(interest).contains(id1) && fr.getInterestMap().get(interest).contains(id2)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method to count the number of ocurrences of an interest
     * @param id1 id of the interest
     * @return int number of interest in which the interest id1 participate
     */
    public int getTotalOcurrencesInInterests(int id1) {
        int counter = 0;
        for (Interest interest : fr.getInterestMap().keySet()) {
            if (fr.getInterestMap().get(interest).contains(id1)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method to return the Interest between two users
     * @param a user being compared to the second
     * @param b user being compared to the first
     * @return Interest between the two users, null if it doesnt exist one
     */
    private Edge<Relation, User> returnEdgeInterest(User a, User b) {
        for (Edge<Relation, User> e : relationships.incidentEdges(returnVertex(b))) {
            for (Edge<Relation, User> edge : relationships.outboundEdges(returnVertex(a))) {
                if (e.equals(edge)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Method to return the Edge between two users
     * @param a user being compared to the second
     * @param b user being compared to the first
     * @return Edge between the two users, null if it doesnt exist one
     */
    private Edge<Relation, User> returnEdge(User a, User b) {
        for (Edge<Relation, User> e : relationships.incidentEdges(returnVertex(a))) {
            for (Edge<Relation, User> edge : relationships.outboundEdges(returnVertex(b))) {
                if (e.equals(edge)) {
                    return e;
                }
            }
        }
        for (Edge<Relation, User> e : relationships.incidentEdges(returnVertex(b))) {
            for (Edge<Relation, User> edge : relationships.outboundEdges(returnVertex(a))) {
                if (e.equals(edge)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Method to return a vertex associated to the user a
     * @param a user which the vertex is wanted
     * @return Vertex if exists, null otherwise
     */
    public Vertex<User> returnVertex(User a) {
        for (Vertex<User> v : this.relationships.vertices()) {
            if (v.element().getId() == a.getId() && v.element().getName().equals(a.getName())) {
                return v;
            }
        }
        return null;
    }

    /**
     * Method to return a vertex associated to the user by its user id
     * @param userId user which the vertex is wanted
     * @return Vertex if exists, null otherwise
     */
    private Vertex<User> returnVertexWithId(int userId) {
        for (Vertex<User> v : this.relationships.vertices()) {
            if (v.element().getId() == userId) {
                return v;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        User user = null;
        for (Vertex<User> v : relationships.vertices()) {
            if (v.element().getId() == id) {
                user = v.element();
            }
        }
        return user;
    }

    /**
     * Method to return the list of included users by an user u
     * @param u user which the included users are wanted
     * @return list of included users by an user u
     */
    public Collection<User> getIncluded(User u) {
        List<User> listOfUsers = new ArrayList<>();
        if (u == null) {
            return null;
        }
        for (Vertex<User> v : relationships.vertices()) {
            if (v.element().getId() == u.getId() && v.element().getName().equals(u.getName())) {
                for (Edge<Relation, User> rel : relationships.outboundEdges(v)) {
                    if (rel.element().isDirectWithInterests()
                            || rel.element().isDirectNormal()) {
                        Vertex<User> opp = relationships.opposite(v, rel);
                        listOfUsers.add(opp.element());
                    }
                }
            }

        }
        System.out.println(listOfUsers.size());
        return manipulate(listOfUsers);
    }

    /**
     * Method to remove the added users from an included list
     * @param includedList which is going to be manipulated
     * @return List of users without the added users
     */
    public Collection<User> manipulate(Collection<User> includedList) {
        List<User> addedUsers = new ArrayList<>();
        for (User u : includedList) {
            if (returnVertex(u).element().isAdded()) {
                addedUsers.add(u);
            }
        }
        for (User added : addedUsers) {
            includedList.remove(added);
        }
        return includedList;
    }

    /**
     * Method to get the relationships
     * @return relationships of the program
     */
    public Digraph<User, Relation> getRelationships() {
        return relationships;
    }

    /**
     * Method to get the file reader
     * @return filereader
     */
    public FileReader getFileReader() {
        return this.fr;
    }

    /**
     * Method to find the lower cost vertex from a list a list of unvisited vertex and its costs
     * @param unvisited list of unvisited vertex
     * @param costs costs of each vertex
     * @return vertex with the lower cost
     */
    private Vertex<User> findLowerCostVertex(List<Vertex<User>> unvisited,
                                             Map<Vertex<User>, Integer> costs) {

        int min = Integer.MAX_VALUE;
        Vertex<User> lowerCostVertex = null;

        for (Vertex<User> v : unvisited) {
            int cost = costs.get(v);
            if (cost < min) {
                min = cost;
                lowerCostVertex = v;
            }
        }

        return lowerCostVertex;

    }

    /**
     * Method to calculate the dijkstra
     * @param orig Vertex in which the dijkstra algorithm starts
     * @param costs map with the costs of each vertex
     * @param predecessors map with the predecessors of each vertex
     */
    private void dijkstra(Vertex<User> orig,
                          Map<Vertex<User>, Integer> costs,
                          Map<Vertex<User>, Vertex<User>> predecessors) {
        costs.clear();
        predecessors.clear();
        List<Vertex<User>> unvisited = new ArrayList<>();
        for (Vertex<User> v : relationships.vertices()) {
            if (relationships.outboundEdges(v) != null) {
                costs.put(v, Integer.MAX_VALUE);
                predecessors.put(v, null);
                unvisited.add(v);
            }
        }
        costs.put(orig, 0);
        while (!unvisited.isEmpty()) {
            Vertex<User> lowerCostVertex = findLowerCostVertex(unvisited, costs);
            if (lowerCostVertex == null) {
                break;
            } else {
                unvisited.remove(lowerCostVertex);

                for (Edge<Relation, User> outboundEdges : relationships.outboundEdges(lowerCostVertex)) {
                    Vertex<User> opposite = relationships.opposite(lowerCostVertex, outboundEdges);
                    if (!outboundEdges.element().isIndirect()) {
                        if (unvisited.contains(opposite)) {
                            int pathCost = costs.get(lowerCostVertex) + 1;
                            if (pathCost < costs.get(opposite)) {
                                costs.put(opposite, pathCost);
                                predecessors.put(opposite, lowerCostVertex);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to calculate the minimum cost path between two verticies by its id's
     * @param origId Id of the start vertex
     * @param dstId id of the end vertex
     * @param path list of users in which the path is going to be inserted
     * @return int minimum cost path between the two ids, -1 if not possible
     */
    public int minimumCostPath(int origId, int dstId, List<User> path) {
        Vertex<User> vOrig = findLocal(origId);
        Vertex<User> vDst= findLocal(dstId);
        if(vOrig == null || vDst == null || path == null) return -1;

        Map<Vertex<User>, Integer> costs = new HashMap<>();
        Map<Vertex<User>, Vertex<User>> predecessors = new HashMap<>();
        dijkstra(vOrig, costs, predecessors);
        path.clear();
        boolean complete = true;
        Vertex<User> actual = vDst;
        while (actual != vOrig) {
            path.add(0, actual.element());
            actual = predecessors.get(actual);
            if (actual == null) {
                complete = false;
                break;
            }
        }
        path.add(0, vOrig.element());
        if (!complete) {
            path.clear();
            return -1;
        }

        return costs.get(vDst).intValue();
    }

    /**
     * Method to find a local a local vertex
     * @param localId local id being located
     * @return Vertex if exists, null otherwise
     */
    public Vertex<User> findLocal(int localId) {
        if (localId < 0) return null;
        for (Vertex<User> v : relationships.vertices()) {
            if (v.element().getId() == localId) { //equals was overriden in Local!!
                return v;
            }
        }
        return null;
    }

    /**
     * Class used to sort the users by id (implements Comparator)
     */
    abstract class sortByInterestId implements Comparator<Interest> {
        public int compare(Interest a, Interest b) {
            return a.getId() - b.getId();
        }
    }
}
