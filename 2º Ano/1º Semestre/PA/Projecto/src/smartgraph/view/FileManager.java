package smartgraph.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileManager {

    private File file;

    /**
     * Constructor que recebe um stage e a ação (Ler/Escrever) e inicializa um
     * FileManager.
     *
     * @param stage Stage
     * @param action boolean
     */
    public FileManager(Stage stage, boolean action) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter binFilter = new FileChooser.ExtensionFilter("Bin Files (*.bin), Json Files(*.json)", "*.bin", "*.json");
        fileChooser.getExtensionFilters().addAll(binFilter);
        fileChooser.setInitialDirectory(new File("C:\\"));
        File file;
        if (action) {
            fileChooser.setTitle("Load a social network");
            file = fileChooser.showOpenDialog(stage);
        } else {
            fileChooser.setTitle("Save a social network");
            file = fileChooser.showSaveDialog(stage);
        }
        this.file = file;
    }

    /**
     * Este método devolve o ficheiro.
     *
     * @return Ficheiro (File)
     */
    public File getFile() {
        return this.file;
    }


}
