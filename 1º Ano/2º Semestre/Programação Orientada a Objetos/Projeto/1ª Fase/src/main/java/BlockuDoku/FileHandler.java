/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre e Sérgio
 */
public abstract class FileHandler {
    /**
     * Este método recebe um filename e a extensão e verifica se o ficheiro
     * existe.
     *
     * @param fileName String
     * @param extension String
     * @return Verdadeiro ou falso (boolean)
     */
    public static boolean fileExists(String fileName, String extension) {
        try {
            final Path path = Files.createTempFile(fileName, extension);
            Files.exists(path);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(PlayerFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
