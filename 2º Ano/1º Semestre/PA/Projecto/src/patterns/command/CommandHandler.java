package patterns.command;

import patterns.mvc.model.SocialNetwork;

import java.io.Serializable;

/**
 * Abstract class that will handle the commands using the Social Network that implements Command and Serializable
 */
public abstract class CommandHandler implements Command, Serializable {

    protected SocialNetwork socialNetwork;

    /**
     * Method that recieves a Social Network and return that network
     *
     * @param socialNetwork represents a Social Network
     */
    public CommandHandler(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

}
