package BlockuDoku.Backend;

import BlockuDoku.Backend.Exceptions.ErrorCode;
import BlockuDoku.Backend.Exceptions.*;
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
public abstract class PlayerFileHandler extends FileHandler{

    /**
     * Este método recebe o nome do ficheiro e o gestor de jogadores e guarda-o
     * no respetivo ficheiro.
     *
     * @param fileName String
     * @param playerManager PlayerManager
     * @throws BlockuDoku.Backend.Exceptions.SaveException (SaveException)

     */
    public static void savePlayerManager(String fileName, PlayerManager playerManager) throws SaveException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(playerManager);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            throw new SaveException(ErrorCode.CANT_SAVE_PLAYER);
        }
    }

    /**
     * Este método recebe o nome do ficheiro e lê o gestor de jogadores do
     * respetivo ficheiro.
     *
     * @param fileName String
     * @return Gestor de jogadores (PlayerManager)
     * @throws BlockuDoku.Backend.Exceptions.LoadException (LoadException)
     */
    public static PlayerManager loadPlayerManager(String fileName) throws LoadException {
        PlayerManager readPlayerManager;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            readPlayerManager = (PlayerManager) ois.readObject();
            ois.close();
            return readPlayerManager;
        } catch (IOException ex) {
            throw new LoadException(ErrorCode.CANT_LOAD_PLAYER);
        } catch (ClassNotFoundException ex) {
            throw new LoadException(ErrorCode.CANT_LOAD_PLAYER);
        }
    }
}
