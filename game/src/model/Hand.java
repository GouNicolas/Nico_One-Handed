package model;

import java.util.ArrayList;

/**
 * Represents your hand in this game 
 * containing a list of cards.
 */
public class Hand implements ListOfCards {
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }
    public Hand(String handString) {
        hand = new ArrayList<Card>();
        for (String cardString : handString.split(";")) {
            Card card = new Card(cardString);
            hand.add(card);
        }
    }
    /**
     * Adds a card to the hand.
     * @param card the card to be added
     */
    public void addCard(Card card) {
        hand.add(0, card);
    }

    /**
     * Removes a card from the hand.
     * @param card the card to be removed
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Returns the number of cards in the hand.
     * @return the number of cards in the hand
     */
    public int length() {
        return hand.size();
    }

    /**
     * Clears the hand, removing all cards.
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Returns the card at the specified position in the hand.
     * @param position the position of the card to be retrieved
     * @return the card at the specified position, or null if the position is invalid
     */
    public Card getCard(int position) {
        if (position < 0 || position >= hand.size()) {
            return null;
        }
        return hand.get(position);
    }

    /**
     * Returns a string representation of the hand.
     *
     * @return a string representation of the hand
     */
    public String toString(){
        String str = "";
        for (Card card : hand){
            str += card.toString() + ";";
        }
        return str;
    }
    @Override
    public ArrayList<Card> getCards() {
        return hand;
    }
    @Override
    public void shuffleCards() {
        // do nothing
        throw new UnsupportedOperationException("Cannot shuffle the hand.");
    }

}