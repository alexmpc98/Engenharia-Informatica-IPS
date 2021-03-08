/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.model;

/**
 *
 * @author brunomnsilva
 */
public class BookmarkEntry {
    private String key;
    private String url;    

    public BookmarkEntry(String key, String url) throws BookmarkInvalidOperation {
        if(key == null || key.isEmpty()) {
            throw new BookmarkInvalidOperation("Key must not be empty.");
        }
        this.key = key;
        this.url = url;        
    }

    public BookmarkEntry(String key) throws BookmarkInvalidOperation {
        this(key, "");
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        if( !url.isEmpty() ) {            
            return key + " ( " + url + " )";
        } else {
            return key;
        }
    }

    public boolean isFolder()
    {
        return url.isEmpty();

    }
     
}
