import java.util.ArrayList;
import java.util.Random;
public class Boat {
    private final int MAX_TANKS = 6;
    private int numberOfFishermen;
    private String name;
    private BoatStatus status;
    private ArrayList<Tank> tanks;

    /**
     * COnstrutor para objetos da classe Boat
     */
    public Boat(int numberOfFishermen, String name, int tankQuantity) {
        if (numberOfFishermen >= 1 && numberOfFishermen <= 10) {
             this.numberOfFishermen = numberOfFishermen;
        } else {
             this.numberOfFishermen = 3;
        }
        if (name == "") {
            this.name = "Desconhecido";
        } else {
            this.name = name;
        }
        this.status = BoatStatus.MOORED;
        this.tanks = new ArrayList<Tank>();
        if (tankQuantity <= this.MAX_TANKS) {
            for (int i = 0; i < tankQuantity; i++) {
                this.tanks.add(new Tank(i));
            }
        } else {
            for (int i = 0; i < this.MAX_TANKS; i++) {
                this.tanks.add(new Tank(i));
            }
        }
    }

    public int getNumberOfFishermen() {
        return this.numberOfFishermen;
    }
    
    public void setNumberOfFishermen(int numberOfFishermen) {
        this.numberOfFishermen = numberOfFishermen;
    }
    
    public BoatStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(BoatStatus status) {
        this.status = status;
    }
    
    public String installTank () {
        if (this.tanks.size() < this.MAX_TANKS) {
            this.tanks.add(new Tank(this.tanks.size()));
            return "Tanque adicionado com Successo!";
        }
        return "Tanque não adicionado!";
    }
    
    public String startFishing() {
        if (this.status == BoatStatus.FISHING) {
            return "O barco já se encontra a pescar!";
        } else {
            this.status = BoatStatus.FISHING;
        }
        return "O barco começou a pescar!";
    }
    
    public String returnToPort() {
        if (this.status == BoatStatus.SAILING) {
            this.status = BoatStatus.MOORED;
            return "O barco voltou ao porto!";
        }
        return "O barco não pode voltar ao porto, porque não recolheu as redes!";
    }
    
    public String collect() {
        if (this.status == BoatStatus.FISHING) {
            Random rand = new Random(); 
            int random_fish = rand.nextInt(20001);
            int fishNumberForEachTank = random_fish / this.tanks.size();
            int extraFish = 0;
            for (Tank tank : this.tanks) {
                extraFish += tank.insert(fishNumberForEachTank);
            }
            if (extraFish > 0) {
               return "A capacidade máxima do barco foi ultrapassada!";
            }
            this.status = BoatStatus.SAILING;
            return "Foram adicionados " + random_fish + " Kgs de peixe!";
        } else {
            return "O barco não se encontra a pescar!";
        }
    }
    
    public int getTotalFish() {
        int totalFish = 0;
        for (Tank tank : this.tanks) {
                totalFish += tank.getCurrentQuantity();
        }
        return /*"Total de peixe: " + */totalFish /*+ " Kgs"*/;  
    }
   
    @Override
    public String toString() {
        String returnString = "Barco: " + this.name + "\n" + 
                              "            -" + numberOfFishermen + " tripulantes \n" +
                              "Tanques: \n";
        int totalFish = 0;
        int totalSpace = 0;
        for (Tank tank : this.tanks) {
                totalFish += tank.getCurrentQuantity();
                totalSpace += 5000; 
                returnString += tank.toString();
        }
        returnString += "Capacidade: " + totalFish + "/" + totalSpace + "\n"; 
        return returnString;
               
    }
}
