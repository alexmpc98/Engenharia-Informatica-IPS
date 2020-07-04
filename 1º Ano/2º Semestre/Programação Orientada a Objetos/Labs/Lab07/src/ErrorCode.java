/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public enum ErrorCode {
    PARTICIPANT_NAME_CANT_BE_NULL,PARTICIPANT_TYPE_CANT_BE_NULL,EVENT_NAME_CANT_BE_NULL,EVENT_LOCAL_CANT_BE_NULL,INVALIDE_PRICE, 					
    EVENT_DATE_CANT_BE_NULL,EVENT_DATE_CANT_BE_BEFORE,REGISTRATION_DATE_CANT_BE_AFTER,REGISTRATION_DATE_CANT_BE_NULL,FILE_CANT_BE_NULL_OR_EMPTY 		
;
    
    @Override
    public String toString(){
        switch(this){
            case PARTICIPANT_NAME_CANT_BE_NULL:  return "O nome do participante tem de ser fornecido";
            case PARTICIPANT_TYPE_CANT_BE_NULL: return "O tipo do participante tem de ser fornecido";
            case EVENT_NAME_CANT_BE_NULL: return "O nome do evento tem de ser fornecido";
            case EVENT_LOCAL_CANT_BE_NULL: return "O local do evento tem de ser fornecido";
            case INVALIDE_PRICE: return "O preço do ingresso tem de ser positivo";
            case EVENT_DATE_CANT_BE_NULL: return "A data do evento tem de ser fornecida";
            case EVENT_DATE_CANT_BE_BEFORE: return "A data do evento não pode ser anterior à atual";
            case REGISTRATION_DATE_CANT_BE_AFTER: return "A data do registo não pode ser posterior à atual";
            case REGISTRATION_DATE_CANT_BE_NULL: return "A data do registo tem de ser fornecida";  
            case FILE_CANT_BE_NULL_OR_EMPTY: return "O ficheiro para imprimir não ser vazio ou nulo";
            default: return "";
        }
    } 
}
