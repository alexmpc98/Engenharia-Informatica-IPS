import java.util.ArrayList;
/**
 * Escreva a descrição da classe Boat aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Boat
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private final int MAX_TANKS = 6;
    private int numberOfFishermen;
    private String name;
    private BoatStatus status;
    private ArrayList<Tank> tanks;

    public Boat(int numberOfFishermen, String description, BoatStatus status)
    {
        if(numberOfFishermen > 0 && numberOfFishermen < 11)
            this.numberOfFishermen = numberOfFishermen;
        else
            this.numberOfFishermen = 3;
           
        if(description.isEmpty())
            this.name = "Unknown";
        else
            this.name = description;
    }
}
