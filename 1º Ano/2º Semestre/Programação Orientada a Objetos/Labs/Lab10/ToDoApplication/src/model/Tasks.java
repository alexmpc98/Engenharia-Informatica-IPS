package Model;

import Model.Task;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Capinha
 */
public class Tasks extends HashSet<Task> implements Serializable {

    public Set<Task> getCollectionFromType(TaskTypes type) {
        if (type.equals(TaskTypes.INDIFFERENT)) {
            return this;
        } else {
            return this.stream()
                    .filter(c -> c.getType().equals(type))
                    .collect(Collectors.toSet());
        }
    }

    public Set<Task> getNotConcludedCollection(boolean notConcluded) {
        if (notConcluded) {
            return this.stream()
                    .filter(c -> !c.isConclude())
                    .collect(Collectors.toSet());
        } else {
            return this;
        }
    }

}
