package patterns.mvc.model;

import java.io.Serializable;

/**
 * Class to define an interest of an user
 */
public class Interest implements Serializable {
    private int id;
    private String name;

    /**
     * Method to initialize an interest
     * @param id id of the interest
     */
    public Interest(int id) {
        this.id = id;
        this.name = "";
    }

    /**
     * Selector method to get the id of the interest
     * @return id ID of the interest
     */
    public int getId() {
        return id;
    }

    /**
     * Selector method to get the name of the interest
     * @return name Name of the interest
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name of the interest
     * @param name Name of the interest
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to show the interest
     * @return String with the information of the interest
     */
    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
