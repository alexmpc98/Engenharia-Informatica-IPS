import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.text.DecimalFormat;
public class FishingFleet {
    private final double COST_PER_BOAT = 1200.0;
    private final double COST_PER_WORKER = 60.5;
    private final double GAIN_PER_KG = 1.2;
    HashMap<String, Boat> boats = new HashMap<String, Boat>();
    private String companyName;
    
    /**
     * COnstrutor para objetos da classe FishingFleet
     */
    public FishingFleet(String companyName) {
       if (companyName == "") {
           this.companyName = "Desconhecido";
       } else {
           this.companyName = companyName;
       }
    }
    
    public void registerBoat(Boat boat) {
       int index = this.boats.size();
       this.boats.put("B{"+index+"}", boat); 
    }
    
    public void listBoats() {
       System.out.println("Barcos: ");     
           this.boats.entrySet().forEach(entry->{
           System.out.println(entry.getKey());  
       });
    }

    public void returnBoats() {
       this.boats.entrySet().forEach(entry->{
            entry.getValue().returnToPort();
       }); 
    }
    
    public void returnBoat(String boatID) {
       this.boats.get(boatID).returnToPort();
    }
    
    public void sendBoat(String boatID) {
       this.boats.get(boatID).startFishing();
    }
    
    public void collectBoat(String boatID) {
       this.boats.get(boatID).collect();
    }
    
    public int getTotalWorkers() {
       int totalWorkers = 0; 
       for(HashMap.Entry<String, Boat> boat : this.boats.entrySet()){
          totalWorkers += boat.getValue().getNumberOfFishermen();
       }
       return totalWorkers;
    }
    
    public double getCurrentCost() {
      double cost = 0;
      cost = (this.boats.size() * this.COST_PER_BOAT) + (getTotalWorkers() * this.COST_PER_WORKER);
      return cost;
    }
    
    public double getCurrentGain() {
      double gain = 0;
      int totalFish = 0;
      /*for (Tank tank : this.tanks) {
          totalFish += tank.getCurrentQuantity();
      }*/
      for(HashMap.Entry<String, Boat> boat : this.boats.entrySet()){
        if (boat.getValue().getStatus() != BoatStatus.MOORED) {  
          totalFish += boat.getValue().getTotalFish();
        }
       }
      gain = totalFish * this.GAIN_PER_KG;
      return gain;
    }
    
    public void showInformation () {
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() { 
        String returnString = "Empresa: " + this.companyName + "\n" + 
                              "Nº de trabalhadores: " + getTotalWorkers() + "\n" +
                              "Nº de embarcações: " + boats.size() + "\n" +
                              "\nEmbarcações: \n";
        DecimalFormat df = new DecimalFormat("0.00");
        for(HashMap.Entry<String, Boat> boat : this.boats.entrySet()){
          returnString += boat.getValue().toString() + "\n";
        }  
        returnString += "Contabilidade: \n" + 
                        "            - Despesa atual estimada: " + df.format(getCurrentCost()) + "\n" +
                        "            - Lucro atual estimado: " + df.format(getCurrentGain()) + "\n" +
                        "            - Balanço atual: " + df.format((getCurrentGain() - getCurrentCost())); 
        return returnString;  
    }
    
}
