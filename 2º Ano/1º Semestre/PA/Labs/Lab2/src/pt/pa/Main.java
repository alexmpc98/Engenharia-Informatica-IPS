/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa;

import javafx.geometry.Pos;
import pt.pa.adts.Position;
import pt.pa.adts.Tree;
import pt.pa.adts.TreeLinked;
import pt.pa.model.BookmarkEntry;
import pt.pa.model.BookmarkInvalidOperation;
import pt.pa.model.BookmarkManager;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // take comments when BookmarkManager is implemented
        try {
            BookmarkManager manager = new BookmarkManager();

            manager.addBookmarkFolder("bookmarks", "Jornais");
            manager.addBookmarkFolder("jornais", "Finanças");
            manager.addBookmarkFolder("bookmarks", "Redes Sociais");
            manager.addBookmarkFolder("bookmarks", "Diversos");

            manager.addBookmarkEntry("jornais", "Publico", "http://www.publico.pt");
            manager.addBookmarkEntry("jornais", "Expresso", "http://www.expresso.pt");
            manager.addBookmarkEntry("finanças", "Diário Económico", "http://economico.sapo.pt/");

            manager.addBookmarkEntry("redes sociais", "Facebook", "http://www.facebook.com");
            manager.addBookmarkEntry("redes sociais", "Instagram", "http://www.instagram.com");

            manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
            manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");

            manager.addBookmarkEntry("bookmarks", "IPS", "http://www.ips.pt");

            // 5 - Acrescente o folder e os bookmarks marcados a negrito
            manager.addBookmarkFolder("jornais", "Desportivos");
            manager.addBookmarkEntry("desportivos", "Record", "http://www.record.xl.pt");
            manager.addBookmarkEntry("desportivos", "aBola", "http://www.abola.pt");

            manager.moveEntryToFolder("aBola","Diversos");
            System.out.println(manager);


            //Number of entries
            System.out.println(manager.getTotalEntries());



            System.exit(0);
        } catch (BookmarkInvalidOperation exception) {
            System.err.println(exception.getMessage());
        }


    }

}
