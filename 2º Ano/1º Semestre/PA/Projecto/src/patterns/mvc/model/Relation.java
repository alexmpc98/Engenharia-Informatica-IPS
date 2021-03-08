package patterns.mvc.model;

import java.io.Serializable;

/**
 * A class that represents a Relation of the Social Network
 *
 */
public class Relation implements Serializable {
    private String name;
    private RelationType type;

    /**
     * Method to initialize a relation
     * @param name name of the relation
     * @param type type of the relation
     */
    public Relation(String name, RelationType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Selector method to return the name of the relation
     *
     * @return name of the relation
     */
    public String getName() {
        return name;
    }

    /**
     * Selector method to return the type of the relation
     *
     * @return type Type of the relation
     */
    public RelationType getType() {
        return type;
    }

    /**
     * @param type the type of the relation
     */
    public void setType(RelationType type) {
        this.type = type;
    }

    /**
     * Method to verify if a relation is of the type indirect
     *
     * @return Return true if this relation is indirect, false otherwise
     */
    public boolean isIndirect(){
        if(this.getType() == RelationType.INDIRECT){
            return true;
        }
        return false;
    }

    /**
     * Method to verify if a relation is of the type normal
     *
     * @return Return true if this relation is normal, false otherwise
     */
    public boolean isDirectNormal(){
        if(this.getType() == RelationType.DIRECT_NORMAL){
            return true;
        }
        return false;
    }

    /**
     * Method to verify if a relation is of the type direct interest
     *
     * @return Return true if this relation is Direct interests, false otherwise
     */
    public boolean isDirectWithInterests(){
        if(this.getType() == RelationType.DIRECT_INTERESTS){
            return true;
        }
        return false;
    }


    /**
     * Method to show the relation
     * @return String with the information of the relation
     */
    @Override
    public String toString() {
        return name;
    }
}

