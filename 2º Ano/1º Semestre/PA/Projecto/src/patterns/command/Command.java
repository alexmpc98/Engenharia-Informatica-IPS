package patterns.command;

import patterns.mvc.model.SocialNetwork;

/**
 * Interface that represents the command
 */
public interface Command {
    /**
     * Method that will execute the command
     */
    public void execute();
    /**
     * Method that will unexecute a command
     */
    public void unExecute();

}
