package model;

import java.util.ArrayList;

/**
 * The Discard class represents a discard pile of cards in a card game.
 * It extends the ListOfCards class and provides methods to add, remove, and access cards in the discard pile.
 */
public class Discard extends ListOfCards {
    private ArrayList<Card> discard;
    
    /**
     * Constructs an empty discard pile.
     */
    public Discard() {
        discard = new ArrayList<Card>();
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
     * Returns the size of the discard pile.
     * @return the size of the discard pile
     */
    public int getDiscardSize() {
        return discard.size();
    }

    /**
     * Returns the list of cards in the discard pile.
     * @return the list of cards in the discard pile
     */
    public ArrayList<Card> getDiscard() {
        return discard;
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
     * Displays the top card in the discard pile.
     */
    public void displayTopCard() {
        System.out.println(discard.get(discard.size() - 1));
    }
}