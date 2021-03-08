package patterns.mvc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to represent backup data from the program in order to save data in JSON
 */
public class BackupData implements Serializable {
    private boolean interests;
    private User user;
    private ArrayList<User> userList;

    /**
     * Method to initialize the back up data of the program
     * @param user User who's being saved
     * @param userList List of Included Users of the user who's being saved
     * @param interests Boolean to check if the user has interests or not
     */
    public BackupData(User user, ArrayList<User> userList, boolean interests) {
        this.user = user;
        this.userList = userList;
        this.interests = interests;
    }

    /**
     * Selector method to get the user
     *
     * @return user User of the BackupData
     */
    public User getUser() {
        return user;
    }

    /**
     * Selector method to get the list of included users by the user
     *
     * @return userList List of the included users by the user
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Method to verify if the user has interests
     *
     * @return true if the user has interests, false otherwise
     */
    public boolean getInterest(){
        return this.interests;
    }

    /**
     * Method to show the information of the Backup Data
     *
     * @return String with the information of the Backup Data
     */
    @Override
    public String toString() {
        return "BackupData{" +
                "interests=" + interests +
                ", user=" + user +
                ", userList=" + userList +
                '}';
    }
}
