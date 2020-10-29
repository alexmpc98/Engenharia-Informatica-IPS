package BlockuDoku.Backend;

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
     * Este método recebe o nome do ficheiro e o gestor de jogos e guarda-os no
     * respetivo ficheiro.
     *
     * @param fileName String
     * @param gameManager GameManager
     * @throws ExceptionManager CANT_SAVE_GAME
     */
    public static void saveGameManager(String fileName, GameManager gameManager) throws ExceptionManager {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(gameManager);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new ExceptionManager(ErrorCode.CANT_SAVE_GAME);
        }
    }

    /**
     * Este método recebe o nome do ficheiro e lê o gestor de jogos do respetivo
     * ficheiro.
     *
     * @param fileName String
     * @return Gestor de jogos (GameManager)
     */
    public static GameManager loadGameManager(String fileName) {
        GameManager readGameManager;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            readGameManager = (GameManager) ois.readObject();
            ois.close();
            return readGameManager;
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            //System.out.println(e.getMessage());
        }
        return null;
    }
}
