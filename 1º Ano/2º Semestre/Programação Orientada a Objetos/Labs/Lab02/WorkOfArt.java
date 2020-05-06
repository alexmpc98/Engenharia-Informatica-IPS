
public class WorkOfArt {
    private int code;
    private boolean onDisplay;
    private static final Boolean OFF_DISPLAY = false;
    private static final Boolean ON_DISPLAY = true;
    public String artist;
    public String title;
    
    public WorkOfArt(String artist, String title)
    {
        this.code = this.code + 1;
        this.onDisplay = this.OFF_DISPLAY;
        this.artist = artist;
        this.title = title;
    }
    public WorkOfArt(){
        this("","");
    } 
    public int getCode() {
        return this.code;
    }
    public boolean getOnDisplay(){
        return this.onDisplay;
    }
    public String getArtist(){
        return this.artist;
    }
    public String getTitle(){
        return this.title;
    }
    public void setOnDisplay(boolean onDisplay){       
            this.onDisplay = (onDisplay == true) ? ON_DISPLAY : OFF_DISPLAY;
    }
    public void setArtist(String artist){
        if (artist != "") {
            this.artist = artist;
        }    
    }
    public void setTitle(String title){
        if (title != "") {
            this.title = title;
        }
    }
    public String toString(){
        return "Artist: " + this.artist + " / Title: " + this.title;
    }

}
