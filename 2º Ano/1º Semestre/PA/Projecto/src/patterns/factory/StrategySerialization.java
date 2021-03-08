package patterns.factory;

import exceptions.LoadException;
import exceptions.SaveException;
import patterns.mvc.model.SocialNetwork;

import java.io.*;

/**
 * Class that will save and load a file .bin
 */
public class StrategySerialization implements Strategy, Serializable {


    /**
     * Method that will load a social network, recieving a file of .bin
     *
     * @param file file that will be loaded
     * @return the social network from the file
     * @throws LoadException error if the load will fail
     */
    @Override
    public SocialNetwork load(File file) throws LoadException {
        SocialNetwork readSocialNetwork;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            readSocialNetwork = (SocialNetwork) ois.readObject();
            ois.close();
            return readSocialNetwork;
        } catch (IOException ex) {
            System.out.println(ex);
            throw new LoadException("Can't load the selected file!");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            throw new LoadException("Can't load the selected file!");
        }
    }

    /**
     * Method that will save a social network in a file .bin
     *
     * @param socialNetwork the social network that will be loaded
     * @param file the file where social network will be saved
     * @throws SaveException if appears a error while trying to save the file
     */
    @Override
    public void save(SocialNetwork socialNetwork, File file) throws SaveException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(socialNetwork);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println(ex);
            throw new SaveException("Can't save the digraph!");
        }
    }
}
