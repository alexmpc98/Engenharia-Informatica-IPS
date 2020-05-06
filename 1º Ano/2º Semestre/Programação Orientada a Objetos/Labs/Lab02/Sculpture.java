public class Sculpture extends WorkOfArt
{
    private String material;    
    
    public Sculpture(String artist, String title, String material){
        super(artist,title);
        this.material = material;
    }
    
    public Sculpture(){
        super("","");
        this.material = "";
    }
    
    public String getMaterial(){
        return this.material;
    }
    
    public void setMaterial(String material){
        this.material = material;
    }
    
    @Override
    public String toString(){
        return "|Sculpture| " + super.toString() + " / Material: " + this.material ;
    }

}

