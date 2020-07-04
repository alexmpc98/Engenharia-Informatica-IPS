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
public class PriorityCombobox extends ComboBox<String> {

    private final ObservableList<String> priorities;

    public PriorityCombobox() {
        List<String> enumPriority = Stream.of(PriorityLevel.values())
                .map((aEnum) -> aEnum.toString())
                .collect(Collectors.toList());
        priorities = FXCollections.observableArrayList(enumPriority);        
        this.setItems(priorities);
        this.getSelectionModel().selectFirst();
    }

}
