package patterns.factory;

import patterns.mvc.model.SocialNetwork;

import java.io.File;

/**
 *  Interface Strategy define the method that will be implemented in concreteStrategy
 */
public interface Strategy {

    /**
     * Method that will load a file
     *
     * @param file file that will be loaded
     * @return a Social Network
     */
    public SocialNetwork load(File file);

    /**
     * Method that will save a social network in a file
     *
     * @param socialNetwork the social network that will be loaded
     * @param file the file where social network will be saved
     */
    public void save(SocialNetwork socialNetwork, File file);
}
