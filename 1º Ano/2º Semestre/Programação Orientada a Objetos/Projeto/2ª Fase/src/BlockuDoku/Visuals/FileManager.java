/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre e Sérgio
 */
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
        FileChooser.ExtensionFilter binFilter = new FileChooser.ExtensionFilter("Bin Files (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().addAll(binFilter);
        fileChooser.setInitialDirectory(new File("C:\\"));
        File file;
        if (action) {
            fileChooser.setTitle("Carregar um jogo");
            file = fileChooser.showOpenDialog(stage);

        } else {
            fileChooser.setTitle("Guardar o jogo");
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
