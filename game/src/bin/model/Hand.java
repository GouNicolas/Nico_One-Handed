package model;

import java.util.ArrayList;

/**
 * Represents your hand in this game 
 * containing a list of cards.
 */
public class Hand extends ListOfCards {
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
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
     * Returns the size of the hand.
     * @return the size of the hand
     */
    public int getHandSize() {
        return hand.size();
    }

    /**
     * Returns the entire hand.
     * @return the entire hand
     */
    public ArrayList<Card> getHand() {
        return hand;
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
}
