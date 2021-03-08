package pt.pa.model;

import pt.pa.adts.Position;
import pt.pa.adts.Tree;
import pt.pa.adts.TreeLinked;
import java.util.*;

public class BookmarkManager{
    private Tree<BookmarkEntry> bookmarks;

    public BookmarkManager(BookmarkEntry bookmarkEntry){
        this.bookmarks = new TreeLinked<>(bookmarkEntry);
    }

    public BookmarkManager(){
        this.bookmarks = new TreeLinked<>(new BookmarkEntry("bookmarks"));
    }

    private Position<BookmarkEntry> find(String key){
        Iterable<Position<BookmarkEntry>> positions = bookmarks.positions();
        for (Position<BookmarkEntry> p : positions) {
            if(p.element().getKey().compareToIgnoreCase(key) == 0) {
                return p;
            }
        }
        return null;
    }

    private boolean exists(String key){
        return find(key) != null;
    }

    public void addBookmarkFolder(String keyParent, String keyFolder) throws BookmarkInvalidOperation{
        addBookmarkEntry(keyParent,keyFolder,"");
    }

    public void addBookmarkEntry(String keyParent, String keyEntry, String url) throws BookmarkInvalidOperation{
        Position<BookmarkEntry> parentPosition = find(keyParent);

        if(parentPosition == null) throw new BookmarkInvalidOperation("Invalid parent folder.");
        if(!parentPosition.element().isFolder()) throw new BookmarkInvalidOperation("Specified parent is not a folder: " + keyParent);

        if(exists(keyEntry)) throw new BookmarkInvalidOperation("A folder/bookmark already contains this key: " + keyEntry);

        BookmarkEntry entry = new BookmarkEntry(keyEntry, url);

        bookmarks.insert(parentPosition, entry);
    }

    public int getTotalEntries(){
        return this.bookmarks.size();
    }

    public String getParentFolder(String keyEntry) throws BookmarkInvalidOperation{
        Position<BookmarkEntry> entryPosition = find(keyEntry);

        if(entryPosition == null) throw new BookmarkInvalidOperation("This bookmark does not exist: " + keyEntry);

        if(entryPosition.element().isFolder()) throw new BookmarkInvalidOperation("This is a folder: " + keyEntry);

        Position<BookmarkEntry> parentPosition = bookmarks.parent(entryPosition);
        return parentPosition.element().getKey();
    }

    public void testMove(String key, String parentKey){
        Position<BookmarkEntry> keyPosition = find(key);
        Position<BookmarkEntry> parentPosition = find(parentKey);

        if(keyPosition == null) throw new BookmarkInvalidOperation("Invalid key: " + key);
        if(parentPosition == null) throw new BookmarkInvalidOperation("Invalid folder: " + parentKey);

        if(!parentPosition.element().isFolder())
            throw new BookmarkInvalidOperation("Cannot move to entry.");

        bookmarks.move(keyPosition, parentPosition);
    }

    public int getTotalLinks(){
        int counter = 0;
        for(BookmarkEntry bk : this.bookmarks.elements()){
            if(!bk.isFolder()){
                counter++;
            }
        }
        return counter;
    }

    public void moveEntryToFolder(String keyEntry, String folder) throws BookmarkInvalidOperation{
        if(!exists(folder)){
            throw new BookmarkInvalidOperation("This folder doesn't exist!");
        }
        if(!exists(keyEntry)){
            throw new BookmarkInvalidOperation("This entry doesn't exist!");
        }
        if(!find(folder).element().isFolder()){
            throw new BookmarkInvalidOperation("This provided folder is not a folder!");
        }
        // Poderiamos verificar se seriam nós externos ou root em vez disto
        if(find(keyEntry).element().isFolder()){
            throw new BookmarkInvalidOperation("This provided entry is actually a folder!");
        }
        this.bookmarks.insert(find(folder),find(keyEntry).element(),0);
        this.bookmarks.remove(find(keyEntry));
        //Ordem 0, devido a ser um nó externo
    }

    @Override
    public String toString() {
        return " -- Bookmark manager -- :\n" + this.bookmarks + "\n --- End of bookmarks -- !";
    }
}
