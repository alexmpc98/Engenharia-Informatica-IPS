/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public class Program {
    public static void main(String args[]){
        //Nivel 1 ***************************************************
        ParticipantType Speaker = ParticipantType.SPEAKER;
        ParticipantType Student = ParticipantType.STUDENT;
        ParticipantType Teacher = ParticipantType.TEACHER;
        ParticipantType Other = ParticipantType.OTHER;
        
        Participant Alex = new Participant("Alex",Speaker);
        Participant Sergio = new Participant("Sérgio",Speaker);
        Registration newRegister1 = new Registration("26/05/2020 20:50",Alex);
        
        
        //Nivel 2 ***************************************************
        Event NOSAlive = new Event("Nos Alive","26/08/2020 20:50","Algés",200.00);
        NOSAlive.addRegister(newRegister1);
      
        // Exception 1
        //Participant ErrorUsername = new Participant("",Speaker);
        // Exception 2
        //Registration errorRegister = new Registration("323232",Alex);
        // Exception 3
        //Event errorEvent = new Event("","26/08/2020 20:50","Algés",200.00);
    }
}
