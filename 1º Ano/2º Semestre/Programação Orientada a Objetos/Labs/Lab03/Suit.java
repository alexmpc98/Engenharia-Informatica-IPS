public enum Suit 
{
    NONE, SPADES, HEARTS, DIAMONDS, CLUBS;
    
    @Override
    public String toString(){
        switch(this){
            case SPADES:    return "espadas";
            case HEARTS:    return "copas";
            case DIAMONDS:  return "ouros";
            case CLUBS:     return "paus";
            default:        return "";
        }
    }
}
