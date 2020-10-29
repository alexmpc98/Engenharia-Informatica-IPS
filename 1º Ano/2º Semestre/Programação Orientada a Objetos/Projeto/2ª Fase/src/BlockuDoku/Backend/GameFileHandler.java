package BlockuDoku.Backend;

import BlockuDoku.Backend.Exceptions.ErrorCode;
import BlockuDoku.Backend.Exceptions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alexandre e Sérgio
 */
public abstract class GameFileHandler extends FileHandler {

    /**
     * Este método recebe um ficheiro e um jogo e guarda-o no
     * respetivo ficheiro.
     *
     * @param file File
     * @param game Game
     * @throws BlockuDoku.Backend.Exceptions.SaveException (SaveException)
    
     */
    public static void saveGame(File file, Game game) throws SaveException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(game);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            throw new SaveException(ErrorCode.CANT_SAVE_GAME);
        }
    }
    
    /**
     * Este método recebe um ficheiro e lê um jogo do respetivo ficheiro.
     *
     * @param file File
     * @return Jogo (Game)
     * @throws BlockuDoku.Backend.Exceptions.LoadException (LoadException)
     */
    public static Game loadGame(File file) throws LoadException {
        Game readGame;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            readGame = (Game) ois.readObject();
            ois.close();
            return readGame;
        } catch (IOException ex) {
            throw new LoadException(ErrorCode.CANT_LOAD_GAME);
        } catch (ClassNotFoundException ex) {
            throw new LoadException(ErrorCode.CANT_LOAD_GAME);
        }
    }
}
