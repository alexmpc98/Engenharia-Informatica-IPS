/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jose-MSI
 */
public class CardGames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("=======================");
        
        List<Card> cards = new ArrayList<>();
        cards.add(new FaceCard(FaceName.JACK, Suit.CLUBS));
        cards.add(new NumberedCard(3, Suit.DIAMONDS));
        cards.add(new NumberedCard(1, Suit.CLUBS));
        cards.add(new FaceCard(FaceName.KING, Suit.HEARTS));
        cards.add(new FaceCard(FaceName.QUEEN, Suit.SPADES));
        
        System.out.println("Lista de Cartas soltas:\n");
        for (Card card : cards) {
            System.out.println(card);
        }
       
        System.out.println("\n\n==================");
        
        Deck deck1 = new Deck(cards);

        System.out.println("Baralho de cartas:\n");
        System.out.println(deck1);

        System.out.println("\n===========================");
        
        SuecaDeck deck2 = new SuecaDeck();   
        System.out.println("Baralho de cartas da Sueca:\n");
        System.out.println(deck2);  
            
        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");    
        
        deck2.sortByValue();   
        System.out.println("Baralho de cartas da Sueca ordenado:\n");
        Iterator <Card> i  = deck2.iterator(); 
        while(i.hasNext()) {
            Card card = (Card)i.next(); 
            System.out.print("> "+card.toString() + "(" + card.getValue() + ")\n");        
        }
        
        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        deck2.shuffle();
        System.out.println("Baralho de cartas da Sueca baralhado:\n");
        System.out.println(deck2);  
        
        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        System.out.println("Novo baralho de cartas da Sueca:\n");
        System.out.println(deck2.getCards());  
        
        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        System.out.println("Baralho de cartas da Sueca s/ ouros:\n");   
        for (Card card : deck2.getCards()) {
            if (card.getSuit() == Suit.DIAMONDS) 
                deck2.removeCard(card);
            else
                System.out.print(card.toString() + "\n");  
        }

        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        System.out.println("Baralho de cartas da Sueca s/ ouros:\n");   
        for (Card card : deck2.getCards()) {
            if (card.getSuit() == Suit.DIAMONDS) 
                deck2.removeCard(card);
            else
                System.out.print(card.toString() + "\n");  
        }

        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        GameScores gameScore = new GameScores();
        gameScore.insertScore(new Player("Manuel"), 18);        
        
        gameScore.insertScore(new Player("Ana"), 12);  
        gameScore.insertScore(new Player("Joao"), 15);
        System.out.println(gameScore.toString());

        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        List <Card> temp_deck1_cards = new ArrayList<Card>(deck1.getCards());
        deck1.clear();
        System.out.println("Cartas de cima:\n");   
        for (Card card : temp_deck1_cards) {
            deck1.putCard(card);
            System.out.println(deck1.topCard());
        }    
        System.out.println("Cartas retiradas do baralho:\n");   
        for (Card card : temp_deck1_cards) {
            System.out.println(deck1.drawCard());
        }

        System.out.println("\n==================");
        
        
        System.out.println("\n===========================");
        
        SuecaDeck deck3 = new SuecaDeck();          
        System.out.println("Cartas baralhadas:\n");  
        deck3.shuffle();
        System.out.println(deck3.toString());
        System.out.println("\nBaralho ordenado:\n");   
        deck3.sort();
        System.out.println(deck3.toString());

        System.out.println("\n==================");
        
    }

}
