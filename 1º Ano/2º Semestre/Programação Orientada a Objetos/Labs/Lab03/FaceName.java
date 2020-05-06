public enum FaceName
{
    KING, QUEEN, JACK;
    
    @Override
    public String toString(){
        switch(this){
            case KING:  return "Rei";
            case QUEEN: return "Rainha";
            case JACK: return "Valete";
            default: return "";
        }
    }
}
