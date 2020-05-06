public class FaceCard extends Card
{
    private FaceName faceName;
    private int value; 
    
    public FaceCard(FaceName face, Suit suit){
        super(suit);
        
        if(face == FaceName.KING) {
            this.faceName = FaceName.KING; 
        } else if(face == FaceName.QUEEN) {
            this.faceName = FaceName.QUEEN;
        } else if(face == FaceName.JACK) {
            this.faceName = FaceName.JACK;
        }   
    }
    
    public FaceCard(FaceName face, Suit suit, int value){
        super(suit, value > 0 ? value : 0);
        
        switch(face){
             case KING:
                this.faceName = FaceName.KING;
             case QUEEN:
                this.faceName = FaceName.QUEEN;
             case JACK:
                this.faceName = FaceName.JACK;
        }   
    }
    
    @Override
    public FaceName getFaceName(){
        return this.faceName;
    }
    
    public String getName(){
        return this.faceName.toString();  
    }
    
    public String toString(){
        return getName() + " de " + String.valueOf(getSuit());
    }
    
}
