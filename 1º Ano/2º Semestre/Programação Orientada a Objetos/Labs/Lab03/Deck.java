import java.util.ArrayList;
import java.util.Random;

public class Deck
{
    ArrayList<Card> cards;
    
    public Deck() {
         this.cards = new ArrayList<>();
    }
    
    public Deck(ArrayList<Card> newCards) {        
         this.cards = new ArrayList<>();
         this.cards = (ArrayList<Card>) newCards.clone();
    }
    
    public void addCard(Card card){
        this.cards.add(card);
    }
    
    public boolean removeCard(Card card){
        for(int i=0; i < this.cards.size(); i++){
            if(card == this.cards.get(i)){
                this.cards.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void clear() {
        for(int i=0; i < this.cards.size(); i++){
            this.cards.remove(i);
        }
    }
    
    Card getRandomCard() {
        Random generator = new Random();
        int index = generator.nextInt(this.cards.size());
        Card card = this.cards.get(index);
        boolean status = removeCard(card);   
        return status == true ? card : null;
    }
    
    public String toString() {
        String string = "";
            for(Card cards: this.cards){
                 string += "\n" + cards.toString();
            }
        return string;
    }
}
