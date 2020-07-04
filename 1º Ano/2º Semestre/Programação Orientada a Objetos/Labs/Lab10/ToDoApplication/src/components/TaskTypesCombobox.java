package components;

import javafx.collections.ObservableList;
import Model.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Capinha
 */
//Nivel X ----------------------------------------------------------------
public class TaskTypesCombobox extends ComboBox<String> {

    private final ObservableList<String> tasks;

    public TaskTypesCombobox() {
        List<String> enumNames = Stream.of(TaskTypes.values())
                .map((aEnum) -> aEnum.toString())
                .collect(Collectors.toList());
        tasks = FXCollections.observableArrayList(enumNames);
        
        this.setItems(tasks);
        this.getSelectionModel().selectFirst();
    }

}
