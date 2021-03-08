package patterns.command;

import patterns.mvc.model.SocialNetwork;
import patterns.mvc.model.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that will insert a user that extends CommandHandler and implements Serializable
 */
public class CommandInsertUser extends CommandHandler implements Serializable {

    private User user;
    private boolean exist;

    /**
     *
     * @param socialNetwork represents a Social Network
     * @param user represents a user
     * @param exist represents a boolean
     */
    public CommandInsertUser(SocialNetwork socialNetwork, User user, boolean exist) {
        super(socialNetwork);
        this.user = user;
        this.exist = exist;
    }

    /**
     * Mehtod that will check if user already exists and will change the type if the user exists otherwise will just insert the user
     */
    @Override
    public void execute() {
        if(this.exist) {
            socialNetwork.changeUserType(this.user);
        } else {
            socialNetwork.insertUser(this.user);
        }
    }

    /**
     * Mehtod that will remove the user if he exists
     */
    @Override
    public void unExecute() {
        socialNetwork.removeUsers(this.user, this.exist);
    }

    /**
     * Will return a user
     *
     * @return a user
     */
    public User getUser() {
        return user;
    }

    /**
     * Will return true or false
     *
     * @return true or false
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * Method that will return a text of the inserted user
     *
     * @return a text with the user
     */
    @Override
    public String toString() {
        return "CommandInsertUser{" +
                "user=" + user +
                '}';
    }
}
