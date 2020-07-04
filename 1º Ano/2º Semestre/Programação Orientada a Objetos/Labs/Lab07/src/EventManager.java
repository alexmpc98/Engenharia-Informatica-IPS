
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

public class EventManager {
    private ArrayList<Event> events; 
    
    public EventManager(){
        this.events = new ArrayList<>();
    }
    public void registerEvent(Event event){
        if(event != null)
            this.events.add(event);
    }
    private ArrayList<Registration> registrations; 
    public void ticketInfo(Event event, Registration registration){
        String str = "";
        if(event!=null && registration != null){
            str += "Nome do evento:" + event.getName() + "\n";
            str += "Local do evento:" + event.getLocal() + "\n";
            str += "Data do evento:" + event.getDate() + "\n";
            for(Registration reg : this.registrations){
                if(reg == registration){
                    str += "Nome do participant:" + reg.getParticipant().getName() +"\n";
                    str += "Preço do bilhete:" + reg.calculateCost(reg.getParticipant());
                }
            }
                    
        }
    }
}
