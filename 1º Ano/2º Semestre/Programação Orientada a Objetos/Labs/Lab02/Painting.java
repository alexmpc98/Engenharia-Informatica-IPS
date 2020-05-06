public class Painting extends WorkOfArt {
    private String artMovement;
    private enum InkType {
        OIL,
        WATERCOLOR,
        PASTEL,
        ACRYLIC,
        OTHER  
    };
    private enum Support {
        ON_CANVAS,
        ON_PAPER,
        ON_WOOD,
        ON_GLASS,
        OTHER
    };
    private InkType inkType;
    private Support support;
    
    public Painting(String artist, String title,String artMovement,String inkTypeConstructor, String SupportConstructor) {
        super(artist,title);
        this.artMovement = artMovement;
        this.inkType = InkType.valueOf(inkTypeConstructor);
        this.support = Support.valueOf(SupportConstructor);
    }
    
    public Painting(){
        super("","");
        this.artMovement = "";
        this.inkType = InkType.OTHER;
        this.support = Support.OTHER;
    }
    
    public String getArtMovement(){
        return this.artMovement;
    }
    
    public InkType getInkType(){
        return this.inkType;
    }
    
    public Support getSupport(){
        return this.support;
    }
    
    public void setArtMovement(String artMovement){
        this.artMovement = artMovement;
    }
    
    public void setInkType(String inkTypeSet){
        this.inkType = InkType.valueOf(inkTypeSet);
    }
    
    public void setSupport(String SupportSet){
        this.support = Support.valueOf(SupportSet);;
    }
    
    @Override
    public String toString(){
        return "|Painting| " + super.toString() + " / Art Movement: " 
        + this.artMovement + " / Ink Type: " + this.inkType + " / Support: " + this.support;
    }
    
}
