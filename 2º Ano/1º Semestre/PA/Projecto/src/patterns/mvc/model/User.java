package patterns.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A class that represents a User of the Social Network
 *
 */
public class User implements Serializable {
    private int id;
    private String name;
    private UserType type;
    private Collection<Interest> listOfInterest;

    /**
     * Method to initialize a User
     * @param id id of the user
     * @param type type of the user
     */
    public User(int id, UserType type) {
        this.id = id;
        this.name = "";
        this.type = type;
        this.listOfInterest = new ArrayList<>();
    }

    /**
     * Method to initialize a User
     * @param id id of the user
     * @param name name of the user
     * @param type type of user
     */
    public User(int id, String name, UserType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.listOfInterest = new ArrayList<>();
    }


    /**
     * Selector method to return the id of the user
     * @return id Id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method to set the id of a user
     * @param id Id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Selector method to return the name of the user
     * @return name Name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method to set the name of a user
     * @param name Name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Selector method to return the type of the user
     * @return type Type of the user
     */
    public UserType getType() {
        return type;
    }

    /**
     * Selector method to return the list of interest of the user
     * @return listOfInterests List of the interests of the user
     */
    public Collection<Interest> getListOfInterest(){
        return listOfInterest;
    }

    /**
     * Setter method to set the list of interests of the user
     * @param newListOfInterest List of interests of the user
     */
    public void setListOfInterest(Collection<Interest> newListOfInterest){
        this.listOfInterest = newListOfInterest;
    }

    /**
     * Method to change the type of user to it's opposite
     *
     */
    public void changeType(){
        switch(getType()){
            case ADDED:
                setType(UserType.INCLUDED);
                break;
            case INCLUDED:
                setType(UserType.ADDED);
                break;
        }
    }

    /**
     * Method to verify is a user is of the type Added
     * @return true case added user, false otherwise
     */
    public boolean isAdded(){
        if(getType() == UserType.ADDED){
            return true;
        }
        return false;
    }

    /**
     * Method to verify is a user is of the type Included
     * @return true case included user, false otherwise
     */
    public boolean isIncluded(){
        if(getType() == UserType.INCLUDED){
            return true;
        }
        return false;
    }

    /**
     * Setter method to set the type of a user
     * @param type Type of the user
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * Method to show the user
     * @return String with the information of the user
     */
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
