import java.util.ArrayList;

public class Program {
    public static void run() {  
    Suit Spades = Suit.SPADES;
    Suit Diamonds = Suit.DIAMONDS;
    Suit Clubs = Suit.CLUBS;
    Suit Hearts = Suit.HEARTS;
    FaceName Jack = FaceName.JACK;
    FaceName King = FaceName.KING;
    FaceName Queen = FaceName.QUEEN;
    
    System.out.println("/** ------------------- Nível 1 ------------------- **/");
    
    Card carta = new FaceCard(Jack, Clubs);
    Card carta2 = new NumberedCard(3, Diamonds);
    System.out.println("Name: " + carta.getFaceName() +" | " + "Naipe: " + carta.getSuit());
    System.out.println("Number: " + carta2.getNumber() +" | " + "Naipe: " + carta2.getSuit());
    
    System.out.println("/** ----------------------------------------------- **/");  
    
    System.out.println("\n"); 
    
    System.out.println("/** ------------------- Nível 2 ------------------- **/"); 
    
    ArrayList<Card> cards = new ArrayList<>();
    cards.add(carta);
    cards.add(carta2);
    cards.add(new NumberedCard(1,Clubs));
    
    for(Card card: cards){
        System.out.println("Number: " + card.getName() +" | " + "Naipe: " + card.getSuit());
    }
    
    System.out.println("/** ----------------------------------------------- **/"); 
    
    System.out.println("\n"); 
    
    System.out.println("/** ------------------- Nível 3 ------------------- **/"); 
    
    cards.add(new FaceCard(King, Hearts));
    cards.add(new FaceCard(Queen, Spades));
    for(Card card: cards){
        System.out.println(card.toString());
    }
    
    System.out.println("/** ----------------------------------------------- **/");
    
    System.out.println("\n"); 
    
    System.out.println("/** ------------------- Nível 4 ------------------- **/"); 
    
    Deck deck = new Deck(cards);
    System.out.println(deck.toString());
    
    System.out.println("/** ----------------------------------------------- **/");
        
    System.out.println("\n"); 
    
    System.out.println("/** ------------------- Nível 5 ------------------- **/"); 
    
    SuecaDeck suecaDeck = new SuecaDeck();
   
    System.out.println(suecaDeck.toString());
    
    for (int i = 0; i < 33; i++) {
        suecaDeck.getRandomCard();
    }
    
    System.out.println("\n"); 
    System.out.println("After dealing the cards:"); 
    System.out.println(suecaDeck.toString());
    
    System.out.println("\n"); 
    
    System.out.println("/** ----------------------------------------------- **/");
    }
}
