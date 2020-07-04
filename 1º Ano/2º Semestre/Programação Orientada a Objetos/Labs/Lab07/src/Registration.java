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
public class Registration {
    private Date registrationDate;
    private Participant participant;
    private double cost;
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yy HH:mm");
    
    public Registration(String registrationDate, Participant participant){
        this.registrationDate = null;
        try{ 
            this.registrationDate = ft.parse(registrationDate);
        } 
        catch (ParseException e){
            System.out.println("Date error");
        }
        validateDate(this.registrationDate);
        this.participant = participant;
        this.cost = 10.99;
    }
    public void validateDate(Date registrationDate){
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);    
        ErrorCode error = ErrorCode.REGISTRATION_DATE_CANT_BE_AFTER;
        ErrorCode error2 = ErrorCode.REGISTRATION_DATE_CANT_BE_NULL;
        if(registrationDate == null){
            throw new EventManagerIllegalArgumentException(error2);
        }
        else if(registrationDate.before(today)){
            throw new EventManagerIllegalArgumentException(error);
        }
        else{
            this.registrationDate = registrationDate;
        }
    }
    public double calculateCost(Participant participant){
        if(participant.getType().toString().equals("Other"))
            return this.cost;
        return (this.cost * participant.getType().getDiscount())/100;
    }
    public Participant getParticipant(){
        return this.participant;
    }
    public Date getDate(){
        return this.registrationDate;
    }
}
