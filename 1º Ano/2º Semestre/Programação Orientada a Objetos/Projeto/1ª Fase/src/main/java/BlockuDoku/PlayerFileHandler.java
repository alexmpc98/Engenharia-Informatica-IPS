package BlockuDoku;

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
     * @throws ExceptionManager CANT_SAVE_PLAYER
     */
    public static void savePlayerManager(String fileName, PlayerManager playerManager) throws ExceptionManager {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(playerManager);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ExceptionManager(ErrorCode.CANT_SAVE_PLAYER);
        }
    }

    /**
     * Este método recebe o nome do ficheiro e lê o gestor de jogadores do
     * respetivo ficheiro.
     *
     * @param fileName String
     * @return Gestor de jogadores (PlayerManager)
     */
    public static PlayerManager loadPlayerManager(String fileName) {
        PlayerManager readPlayerManager;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            readPlayerManager = (PlayerManager) ois.readObject();
            System.out.println(readPlayerManager);
            ois.close();
            return readPlayerManager;
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            //System.out.println(e.getMessage());
        }
        return null;
    }
}
