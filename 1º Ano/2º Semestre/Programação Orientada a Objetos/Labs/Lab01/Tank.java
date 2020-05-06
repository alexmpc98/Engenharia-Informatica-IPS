

public class Tank {
    private final int MAX_QUANTITY = 5000; //kgs
    private int id;
    private int currentQuantity;

    /**
     * COnstrutor para objetos da classe Tank
     */
    public Tank(int id) {
        // inicializa variáveis de instância
        this.id = id;
        this.currentQuantity = 0;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    
    public int getCurrentQuantity() {
        return this.currentQuantity;
    }
    
    public int isFull() {
        if (getCurrentQuantity() == this.MAX_QUANTITY) {
            return 1;
        }
        return 0;
    }
    
    public void empty() {
        setCurrentQuantity(0);
    }
    
    public int insert(int quantity) {
        int currentQuantity  = getCurrentQuantity();
        int returnStatement = 0;
        if ((this.MAX_QUANTITY - currentQuantity) >= quantity) {
           setCurrentQuantity(currentQuantity + quantity); 
        } else {
           setCurrentQuantity(currentQuantity + (this.MAX_QUANTITY - currentQuantity)); 
        }
        if (quantity - (this.MAX_QUANTITY - currentQuantity) >= 0) { 
            returnStatement = quantity - (this.MAX_QUANTITY - currentQuantity); 
        } 
        return returnStatement;
    }

    @Override
    public String toString() { 
        return "            - Tanque" + this.id + ": "+ this.currentQuantity + "\n";
    }
}
