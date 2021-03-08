package pt.pa.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkManagerTest {
    private BookmarkManager manager;
    @BeforeEach
    void setUp() {
       manager= new BookmarkManager();

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
    }

    @Test
    void getTotalEntries(){
        // Neste caso concreto existem 10, dai o equals 10
        assertEquals(this.manager.getTotalLinks(),10);
        this.manager.addBookmarkEntry("desportivos","zerozero","zerozero.pt");
        assertEquals(this.manager.getTotalLinks(),11);
        this.manager.addBookmarkFolder("jornais","politicos");
        assertEquals(this.manager.getTotalLinks(),11);
    }

    @Test
    void testMoveEntryToFolder(){
        assertEquals(this.manager.getParentFolder("abola"),"Desportivos");
        this.manager.moveEntryToFolder("abola","diversos");
        assertEquals(this.manager.getParentFolder("abola"),"Diversos");
    }

    @Test
    void testMoveEntryToFolderWithFolderNotExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.moveEntryToFolder("record","China");
        });
    }
    @Test
    void testMoveEntryToFolderWithEntryNotExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.moveEntryToFolder("China","jornais");
        });
    }
    @Test
    void testMoveEntryToFolderWithFolderNotBeingFolder(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.moveEntryToFolder("abola","record");
        });
    }
    @Test
    void testMoveEntryToFolderWithEntryBeingFolder(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.moveEntryToFolder("desportivos","jornais");
        });
    }

    @Test
    void addBookmarkFolderWithParentNotExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.addBookmarkFolder("China","myFolderName");
        });
    }
    @Test
    void addBookmarkFolderWithFolderAlreadyExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.addBookmarkFolder("bookmarks","bookmarks");
        });
    }
    @Test
    void addBookmarkEntryWithEntryAlreadyExisting(){
        this.manager.addBookmarkEntry("bookmarks","myFolderName","randomurl.pt");
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.addBookmarkEntry("bookmarks","myFolderName","randomurl.pt");
        });
    }
    @Test
    void addBookmarkEntryWithParentNotExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.addBookmarkEntry("China","myFolderName","randomurl.pt");
        });

    }

    @Test
    void getParentFolderWithEntryNotExisting(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.getParentFolder("China");
        });
    }

    @Test
    void getParentFolderWithEntryBeingRoot(){
        assertThrows(BookmarkInvalidOperation.class,() ->{
            this.manager.getParentFolder("bookmarks");
        });
    }
}