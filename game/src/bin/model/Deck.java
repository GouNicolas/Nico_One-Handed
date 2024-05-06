package model;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ListOfCards{
    private ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList<Card>();
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    public void initDeck(){
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()){
                Card tempCard = new Card(rank,suit);
                deck.add(tempCard);
            }
        }
        Collections.shuffle(deck);
    }

    public Card getHead(){
        return this.deck.get(0);
    }

    public void removeHead(){
        this.deck.remove(0);
    }
}