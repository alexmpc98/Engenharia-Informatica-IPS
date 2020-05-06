import java.util.*;
public abstract class Post implements Searchable
{
    User author;
    Date timestamp;
    boolean visible;
    
    public Post(){
        this.author = null;
        this.visible = true;
        long millis = System.currentTimeMillis();
        this.timestamp = new Date(millis);
    }
    
    public abstract void show();
    
    public User getAuthor(){
        return this.author;
    }
    
    public void setAuthor(User newAuthor){
        this.author = newAuthor;            
    }
    
    public boolean isVisible(){
        return this.visible;
    }
}
