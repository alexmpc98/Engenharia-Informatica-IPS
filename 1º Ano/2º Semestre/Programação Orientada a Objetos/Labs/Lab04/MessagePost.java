import java.util.*;
import java.text.SimpleDateFormat;

public class MessagePost extends Post
{
    String message;
    
    public MessagePost(String message){
        this.message = message;
    }
    
    public boolean search(String content){
        if((getAuthor().username).contains(content) || message.contains(content)){
                return true;
        }
        return false;
    }
    
    @Override
    public void show(){
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy HH:mm");
        String str = "";
        str += "|************************************************ \n";
        str += "| Author: " + (this.author == null ? "Não tem autor" : this.author.username) + "\n";
        str += "| Date: " + ft.format(timestamp) + "\n";
        str += "| \n";
        str += "| Message: \n";
        str += "| " + this.message + "\n";
        str += "|************************************************ \n";
        System.out.println(str); 
    }
 
}
