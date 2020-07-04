/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */

public enum ParticipantType {
    TEACHER,STUDENT,SPEAKER,OTHER;
    
    @Override
    public String toString(){
        switch(this){
            case TEACHER:  return "Teacher";
            case STUDENT: return "Student";
            case SPEAKER: return "Speaker";
            default: return "Other";
        }
    } 
     public int getDiscount(){
         switch(this){
            case TEACHER:  return 25;
            case STUDENT: return 50;
            case SPEAKER: return 100;
            default: return 0;
         }
     }
}

