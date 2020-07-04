/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */

public class Participant {
    private String name;
    private ParticipantType type;
    
    
    public Participant(String name,ParticipantType type){   
        validateName(name);
        validateType(type);
   }
   public String getName(){
       return this.name;
   } 
   public ParticipantType getType(){
       return this.type;
   }
   public void validateName(String name){
       ErrorCode error = ErrorCode.PARTICIPANT_NAME_CANT_BE_NULL;
       if(name.equals("")){
            throw new EventManagerIllegalArgumentException(error);
       }else{
           this.name = name;
       }
   }
   public void validateType(ParticipantType type){
       ErrorCode error = ErrorCode.PARTICIPANT_TYPE_CANT_BE_NULL;
       if(type == null) 
            throw new EventManagerIllegalArgumentException(error);
        else 
            switch(type) {
            case TEACHER:
                this.type = ParticipantType.TEACHER;
                break;
            case STUDENT:
                this.type = ParticipantType.STUDENT;
                break;
            case SPEAKER:
                this.type = ParticipantType.SPEAKER; 
                break;
            default:
                this.type = ParticipantType.OTHER;
                break;
        }            
   }           
}
