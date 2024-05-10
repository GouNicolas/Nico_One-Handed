package model;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards.
 */
public class Deck implements ListOfCards {
    private ArrayList<Card> deck;
    
    /**
     * Constructs an empty deck.
     */
    public Deck(){
        deck = new ArrayList<Card>();
    }
    /**
     * Constructs a deck from a string representation.
     * @param deckString
     */
    public Deck(String deckString) {
        deck = new ArrayList<Card>();
        for (String cardString : deckString.split(";")) {
            Card card = new Card(cardString);
            deck.add(card);
        }
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
        shuffleCards();
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

    /**
     * Returns a string representation of the deck.
     *
     * @return a string representation of the deck
     */
    public String toString(){
        String str = "";
        for (Card card : deck){
            str += card.toString() + ";";
        }
        return str;
    }
    @Override
    public ArrayList<Card> getCards() {
        return deck;
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
    }
    @Override
    public void removeCard(Card card) {
        deck.remove(card);
    }
    @Override
    public Card getCard(int index) {
        return deck.get(index);
    }
    @Override
    public void shuffleCards() {
        Collections.shuffle(deck);
    }

    
}