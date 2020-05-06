public class Room
{
    private int number;
    private String name;
    private double area;
   
    public Room(String name, double area){
         this.name = name;
         this.area = area;
         this.number = this.number + 1;
    }
    
    public Room(){
        this.name = "";
        this.area = 0.00;
        this.number = this.number + 1;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getArea(){
        return this.area;
    }
    
    public void setName(String name){
        if (name != "") {
             this.name = name;
        }
    }
    
    public void setArea(double area){
        if (area != 0.00) {
            this.area = area;
        }
        
    }
    
    public String toString(){
        return "Room Name: " + this.name;
    }
       
}
