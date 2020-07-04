
import java.text.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public class Event {
    private String name;
    private Date date;
    private String local;
    private Double price;
    private ArrayList<Registration> registrations; 
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy HH:mm");
    
    public Event(String name, String date, String local, Double price){
        validateName(name);
        this.date = null;
        try{ 
            this.date = ft.parse(date);
        } 
        catch(ParseException e){
            System.out.println("Date error");
        }
        validateDate(this.date);
        validateLocal(local);
        validatePrice(price);
        this.registrations = new ArrayList<>();  
    }
    public void validateName(String name){
        ErrorCode error = ErrorCode.EVENT_NAME_CANT_BE_NULL;
        if(name.equals("")){
            throw new EventManagerIllegalArgumentException(error);
        }
        else{
            this.name = name;
        }
    }
    public void validateLocal(String local){
        ErrorCode error = ErrorCode.EVENT_LOCAL_CANT_BE_NULL;
        if(local.equals("")){
            throw new EventManagerIllegalArgumentException(error);
        }
        else{
            this.local = local;
        }
    }
    public void validatePrice(Double price){
        ErrorCode error = ErrorCode.EVENT_LOCAL_CANT_BE_NULL;
        if(price == null){
            throw new EventManagerIllegalArgumentException(error);
        }
        else{
            this.price = price;
        }
    }
    public void validateDate(Date date){
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);       
        ErrorCode error = ErrorCode.EVENT_DATE_CANT_BE_NULL;
        ErrorCode error2 = ErrorCode.EVENT_DATE_CANT_BE_BEFORE;
        if(date == null){
            throw new EventManagerIllegalArgumentException(error);
        }
        else if(date.before(today)){
            throw new EventManagerIllegalArgumentException(error2);
        }
        else{
            this.date = date;
        }
    }
    public void addRegister(Registration registration){
        if(registration != null){
            this.registrations.add(registration);
        }
    }
    public String getName(){
        return this.name;
    }
    public Date getDate(){
        return this.date;
    }
    public String getLocal(){
        return this.local;
    }
    public Double getPrice(){
        return this.price;
    }
    public ArrayList<Registration> getRegistrations(){
        return this.registrations;
    }
    @Override
    public String toString(){
        String str = "";
        str += "|*************************************************|\n";
        str += "|                                                 |\n";
        str += "|                     Event                       |\n";
        str += "|                                                 |\n";
        str += "|*************************************************|\n";    
        str += " Name of the event: " + getName() + "\n";
        str += " Local of the event:" + getLocal() + "\n";
        str += " Date of the event: " + getDate() + "\n";
        str += " Number of participants in the event:" + getRegistrations().size() + "\n";
        
        
        return str;
        
    }
}
