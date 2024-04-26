package model;
import java.util.*;

public class Deck {
    private ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList<Card>();
    }

    public void shuffleDeck(){
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()){
                Card tempCard = new Card(rank,suit);
                deck.add(tempCard);
            }
        }
        Collections.shuffle(deck);
    }
}