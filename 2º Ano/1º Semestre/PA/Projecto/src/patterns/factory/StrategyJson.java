package patterns.factory;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import exceptions.LoadException;
import exceptions.SaveException;
import patterns.mvc.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will save and load .json files
 */
public class StrategyJson implements Strategy, Serializable {

    /**
     * Method that will load a social network, recieving a file of .json
     *
     * @param file file that will be loaded
     * @return the social network from the file
     * @throws LoadException error if the load will fail
     */
    @Override
    public SocialNetwork load(File file) throws LoadException  {
        List<BackupData> backupData = null;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));

            backupData = new Gson().fromJson(reader, new TypeToken<List<BackupData>>() {
            }.getType());

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SocialNetwork socialNetwork = new SocialNetwork();
        if(backupData == null) throw new LoadException("Can't load the selected file!");
        socialNetwork.loadFromBackup(backupData);

        return socialNetwork;
    }

    /**
     * Method that will save a social network in a file .json
     *
     * @param socialNetwork the social network that will be loaded
     * @param file the file where social network will be saved
     * @throws SaveException if appears a error while trying to save the file
     */
    @Override
    public void save(SocialNetwork socialNetwork, File file) throws SaveException {
        if(socialNetwork == null) throw new SaveException("Can't save the digraph!");
        try {
            ArrayList<BackupData> dataBackup = new ArrayList<>();
            for (Vertex<User> vertex : socialNetwork.getRelationships().vertices()) {
                ArrayList<User> usersIncluded = new ArrayList<>();
                for (Edge<Relation, User> edgeOut : socialNetwork.getRelationships().outboundEdges(vertex)) {
                    usersIncluded.add(socialNetwork.getRelationships().opposite(vertex, edgeOut).element());
                }
                boolean interests = hasIndirects(vertex.element(), socialNetwork);
                dataBackup.add(new BackupData(vertex.element(), usersIncluded, interests));
            }

            Writer writer = new FileWriter(file.getPath());

            new Gson().toJson(dataBackup, writer);

            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that will check if a certain user has indirect relations with others
     *
     * @param u a user to check the indirect relations
     * @param sc the social network where will be checked the indirect relations of a user
     * @return true if has indirect relations otherwise false
     */
    public boolean hasIndirects(User u, SocialNetwork sc) {
        if (u.getType() == UserType.INCLUDED) {
            return false;
        }
        for (Vertex<User> v : sc.getRelationships().vertices()) {
            if (v.element().getId() == u.getId() && v.element().getName().equals(u.getName())) {
                for (Edge<Relation, User> edge : sc.getRelationships().incidentEdges(v)) {
                    if (edge.element().getType() == RelationType.INDIRECT) {
                        return true;
                    }
                }
                for (Edge<Relation, User> edge2 : sc.getRelationships().outboundEdges(v)) {
                    if (edge2.element().getType() == RelationType.INDIRECT) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
