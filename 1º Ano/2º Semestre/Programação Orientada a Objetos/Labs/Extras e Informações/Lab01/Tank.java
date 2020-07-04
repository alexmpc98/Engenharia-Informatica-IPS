
/**
 * Escreva a descrição da classe Tank aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Tank
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private final int MAX_QUANTITY = 5000;
    private int id;
    private int currentQuantity = 0;

    /**
     * COnstrutor para objetos da classe Tank
     */
    public Tank(int id)
    {
        this.id = id;
    }
    public void setcurrentQuantity(int currentQuantity){
        this.currentQuantity = currentQuantity;
    }
    public boolean isFull(){
        if(this.currentQuantity == MAX_QUANTITY){
            return true;
                                                }
        else
        {
            return false;
        }
    }
    public void empty(){ 
        this.currentQuantity = 0;
    }
    public void insert(int quantityToAdd){
    int leftOver = 0;
    if(this.currentQuantity + quantityToAdd > this.MAX_QUANTITY){
    this.currentQuantity = this.MAX_QUANTITY;
    leftOver = this.currentQuantity + quantityToAdd - this.MAX_QUANTITY;
    System.out.println("Tank is full, " + leftOver + "kg left!");
        }
    
    else{
        
       this.currentQuantity = this.currentQuantity + quantityToAdd;
    }
    
    }
}
