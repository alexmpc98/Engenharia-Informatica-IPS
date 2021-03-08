package patterns.command;

import patterns.mvc.model.SocialNetwork;
import patterns.mvc.model.User;

import java.io.Serializable;

/**
 *Class that represents a Global Import using a CommandHandler and implementing Serializable
 */
public class CommandGlobalImport extends CommandHandler implements Serializable {

    /**
     * Method to inicialize the Global Import
     *
     * @param socialNetwork represents a Social Network
     */
    public CommandGlobalImport(SocialNetwork socialNetwork) {
        super(socialNetwork);
    }

    /**
     * Method that will insert all users to Social Network when executed
     */
    @Override
    public void execute() {
        socialNetwork.insertUsers();
    }

    /**
     * Method that will remove all users from Social Network
     */
    @Override
    public void unExecute() {
        socialNetwork.removeUsers();
    }

}
