package model;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards.
 */
public class Deck extends ListOfCards {
    private ArrayList<Card> deck;
    
    /**
     * Constructs an empty deck.
     */
    public Deck(){
        deck = new ArrayList<Card>();
    }

    /**
     * Returns the deck of cards.
     * @return the deck of cards
     */
    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    /**
     * Initializes the deck with a standard set of 52 cards and shuffles them.
     */
    public void initDeck(){
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()){
                Card tempCard = new Card(rank,suit);
                deck.add(tempCard);
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Returns the card at the top of the deck.
     * @return the card at the top of the deck
     */
    public Card getHead(){
        return this.deck.get(0);
    }

    /**
     * Removes the card at the top of the deck.
     */
    public void removeHead(){
        this.deck.remove(0);
    }

    /**
     * Returns the number of cards in the deck.
     * @return the number of cards in the deck
     */
    public int length(){
        return deck.size();
    }

    /**
     * Displays all the cards in the deck.
     */
    public void display(){
        for (Card card : deck){
            System.out.println(card);
        }
    }
}