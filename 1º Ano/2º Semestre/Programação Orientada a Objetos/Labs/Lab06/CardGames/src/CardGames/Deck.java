
package CardGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Esta classe representa um baralho de cartas
 * 
 * @author POO 2019/2020
 * @version maio/2020
 */

public class Deck {

    //private List<Card> cards;
    Stack<Card> cards;
    private Random random = new Random();

    public Deck() {
        cards = new Stack<>();
    }

    public Deck(List<Card> cardsList) {
        //this.cards = new ArrayList<>(cards);
        this.cards = new Stack<Card>();
        this.cards.addAll(cardsList);
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public void clear() {
        cards.clear();
    }

    @Override
    public String toString() {
        String cardsList = "";

        for (Card card : cards) {
            cardsList += card + "\n";
        }

        return cardsList;
    }

    public Card getRandomCard() {
        if (cards.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(cards.size() - 1);
        return cards.remove(randomIndex);
    }
    
    public void sortByValue(){
        Collections.sort(cards, Collections.reverseOrder());
    }
    
    public Iterator<Card> iterator() {
        return cards.iterator();     
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public List<Card> getCards() {
        ArrayList<Card> newCards = new ArrayList<Card>(this.cards);        
        return newCards;
    }
    
    public Card topCard() {
        return this.cards.firstElement();
    }
    
    public Card drawCard() {
        return this.cards.pop();
    }
    
    public void putCard(Card card) {
        this.cards.push(card);
    }
    
    public void sort(){
        Collections.sort(this.cards, new CardSuitValueComparator()); 
    }
}
