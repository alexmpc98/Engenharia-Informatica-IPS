import java.util.*;
import java.text.*;

public class EventPost extends Post implements Notifiable
{
    String description;
    Date date;
    String location;
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy HH:mm");
    
    public EventPost(String description,String location,String date){
        this.description = description;
        this.location = location;
        this.date = null;
        try{ 
            this.date = ft.parse(date);
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public boolean search(String content){
        if(description == content || location == content || getAuthor().username == content){
                return true;
        }
        return false;
    }
    
    @Override
    public void show(){
        String str = "";
        str += "|************************************************ \n";
        str += "| Author: " + (this.author == null ? "Não tem autor" : this.author.username) + "\n";
        str += "| Date: " + ft.format(timestamp) + "\n";
        str += "| \n";
        str += "| Event: \n";
        str += "| "  + this.description + "\n";
        str += "| \n";
        str += "| "  + ft.format(this.date) + " | " + this.location + " \n";
        str += "|************************************************ \n";
        System.out.println(str);
    }
    
    public void showNotification(){
        String str = "";
        str += "|************************************************ \n";
        str += "| Evento: " + this.description + "\n";
        str += "| Em: " + ft.format(this.date) + ", " + this.location + " \n";
        str += "|************************************************ \n";
        System.out.println(str);
    }
}
