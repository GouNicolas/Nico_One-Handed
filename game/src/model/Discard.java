package model;

import java.util.ArrayList;

/**
 * The Discard class represents a discard pile of cards in a card game.
 * It extends the ListOfCards class and provides methods to add, remove, and access cards in the discard pile.
 */
public class Discard implements ListOfCards {
    private ArrayList<Card> discard;
    
    /**
     * Constructs an empty discard pile.
     */
    public Discard() {
        discard = new ArrayList<Card>();
    }
    public Discard(String discardString) {
        discard = new ArrayList<Card>();
        for (String cardString : discardString.split(";")) {
            Card card = new Card(cardString);
            discard.add(card);
        }
    }

    /**
     * Adds a card to the discard pile.
     * @param card the card to be added
     */
    public void addCard(Card card) {
        discard.add(card);
    }

    /**
     * Removes a card from the discard pile.
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        discard.remove(card);
    }

    /**
     * Returns the number of cards in the discard pile.
     * @return the number of cards in the discard pile
     */
    public int length() {
        return discard.size();
    }


    /**
     * Clears the discard pile, removing all cards.
     */
    public void clearDiscard() {
        discard.clear();
    }

    /**
     * Returns the card at the specified position in the discard pile.
     * @param position the position of the card to be retrieved
     * @return the card at the specified position
     */
    public Card getCard(int position) {
        return discard.get(position);
    }

    /**
     * Returns a string representation of the discard.
     *
     * @return a string representation of the discard
     */
    public String toString(){
        String str = "";
        for (Card card : discard){
            str += card.toString() + ";";
        }
        return str;
    }
    @Override
    public ArrayList<Card> getCards() {
        return discard;
    }
    @Override
    public void shuffleCards() {
        // do nothing
        throw new UnsupportedOperationException("Cannot shuffle the discard pile.");
    }

}