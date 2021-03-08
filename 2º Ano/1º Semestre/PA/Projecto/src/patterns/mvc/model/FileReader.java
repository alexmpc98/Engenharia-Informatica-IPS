package patterns.mvc.model;

import exceptions.FileReaderException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Class to read data from a file in order to populate the SocialNetwork
 */
public class FileReader implements Serializable {
    private Map<User, ArrayList<User>> socialMap;
    private Map<User, ArrayList<Integer>> socialMapAux;
    private Map<User,ArrayList<User>> socialMapIterative;
    private Map<Interest, ArrayList<Integer>> mapInterest;

    /**
     * Method to initialize the File Reader
     */
    public FileReader() {
        socialMap = new HashMap<>();
        socialMapAux = new HashMap<>();
        mapInterest = new HashMap<>();
        importRelationship("relationships");
        importUserNames("user_names");
        importInterests("interests");
        importInterestsNames("interest_names");
    }

    /**
     * Method to import relationships from a file
     * @param fileName Filename of the file whose relationships are going to be imported
     */
    public void importRelationship(String fileName){
        String file = "inputFiles/" + fileName + ".csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                ArrayList<Integer> values = new ArrayList<>();
                for (int i = 1; i < results.length; i++) {
                    values.add(parseInt(results[i]));
                }
                socialMapAux.put(new User(parseInt(results[0]), UserType.ADDED), values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(User u: socialMapAux.keySet()){
            ArrayList<User> newArray = new ArrayList<>();
            for(Integer i : socialMapAux.get(u)){
                newArray.add(getUser(i));
                getSocialMap().put(u,newArray);
            }
        }
    }

    /**
     * Method to import a single relationship from the file relationships.csv
     * @param id id of the relationship being imported
     */
    public void importRelationshipIterative(int id){
        socialMapIterative = new HashMap<>();
        String file = "inputFiles/relationships.csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                if(parseInt(results[0]) == id){
                    ArrayList<User> values = new ArrayList<>();
                    for(int i = 1; i < results.length; i++) {
                        values.add(new User(parseInt(results[i]),getUserName(parseInt(results[i])),UserType.INCLUDED));
                    }
                    socialMapIterative.put(new User(parseInt(results[0]),getUserName(parseInt(results[0])),UserType.ADDED),values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method to import the names of the users in one file
     * @param fileName filename of the file with the users
     */
    public void importUserNames(String fileName) {
        String file = "inputFiles/" + fileName + ".csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                for (int i = 1; i < results.length; i++) {
                    User u = getUser(parseInt(results[0]));
                    u.setName(results[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method to import the interests of the users in one file
     * @param fileName filename of the file with the interests
     */
    public void importInterests(String fileName) {
        String file = "inputFiles/" + fileName + ".csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                ArrayList<Integer> userIdArray = new ArrayList<>();
                for (int i = 1; i < results.length; i++) {
                    userIdArray.add(parseInt(results[i]));
                }
                mapInterest.put(new Interest(parseInt(results[0])), userIdArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method to import the names of the interests in one file
     * @param fileName filename of the file with the interests
     */
    public void importInterestsNames(String fileName) {
        String file = "inputFiles/" + fileName + ".csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                for (int i = 1; i < results.length; i++) {
                    Interest interest = getInterest(parseInt(results[0]));
                    interest.setName(results[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Selector method to get an interest from an id
     * @param id id of the interest
     * @return interest if the interest was loaded, null otherwise
     */
    public Interest getInterest(int id){
        for(Interest interest : this.mapInterest.keySet()){
            if(interest.getId() == id){
                return interest;
            }
        }
        return null;
    }

    /**
     * Selector method to get an user from an id
     * @param id id of the user
     * @return user if it's loaded, null otherwise
     */
    public User getUser(int id){
        for(User u: socialMap.keySet()){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    /**
     * Selector method to get the username of a user from an id
     * @param userId id of the user
     * @return name of the user if it was founded in user_names.csv , "" otherwise
     */
    public String getUserName(int userId) {
        String file = "inputFiles/user_names.csv";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] results = line.split(";");
                if(parseInt(results[0]) == userId){
                    return results[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileReaderException fe) {
            throw new FileReaderException("File doesn't exists or it may be broken!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * Selector method to get the social map
     * @return socialMap temporary map used for the global import of the program data
     */
    public Map<User, ArrayList<User>> getSocialMap(){
        return this.socialMap;
    }

    /**
     * Selector method to get the iterative map
     * @return socialMapIterative used for the iterative import of the program data
     */
    public Map<User, ArrayList<User>> getIterativeMap(){
        return this.socialMapIterative;
    }

    /**
     * Selector method to get the map of interests
     * @return mapInterest Map with the interests loaded
     */
    public Map<Interest, ArrayList<Integer>> getInterestMap(){
        return this.mapInterest;
    }

    /**
     * Selector method to get the the number of relations of an user
     * @param u User whose relations are wanted
     * @return int number of relations of an user u
     */
    public int sizeOfValueFromUserMap(User u){
        return getIterativeMap().get(u).size();
    }

    /**
     * Selector method to get the number of users loaded by iterative means
     * @return int number of users loaded by iterative means
     */
    public int sizeOfUserMapKeySet(){
        return getIterativeMap().keySet().size();
    }

    /**
     * Selector method to get the user id from an interest in the interest map loaded
     * @param interest interest whose id is wanted
     * @param n number of the id from the array of interests
     * @return int id of the user in n index from the interest
     */
    public int returnValueFromInterestMap(Interest interest, int n){
        return getInterestMap().get(interest).get(n);
    }


    /**
     * Verifies the user existence in the interest map
     * @param interest Interest that should be searched as key in map
     * @param userId User id is meant to be searched in interest map values
     * @return true if user id is in values of interest map, false otherwise
     */
    public boolean interestMapValuesContainsUser(Interest interest,int userId){
        if(getInterestMap().get(interest).contains(userId)){
            return true;
        }
        return false;
    }

    /**
     * Selector method to get the size of an interest in the interests loaded
     * @param interest interest whose size is wanted
     * @return int size of an interest
     */
    public int sizeOfValueFromInterestMap(Interest interest){
        return getInterestMap().get(interest).size();
    }
}
