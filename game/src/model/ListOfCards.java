package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * It is used as a base class for Deck, Hand, and Discard classes.
 */
public abstract interface ListOfCards {

    /**
     * Returns the list of cards.
     * @return An ArrayList of Card objects.
     */
    public ArrayList<Card> getCards();

    /**
     * Adds a card to the list.
     * @param card The Card object to be added.
     */
    public void addCard(Card card);

    /**
     * Removes a card from the list.
     * @param card The Card object to be removed.
     */
    public void removeCard(Card card);

    /**
     * Returns a card from the list at the specified index.
     * @param index The index of the Card object to be returned.
     * @return The Card object at the specified index.
     */
    public Card getCard(int index);

    /**
     * Shuffles the list of cards.
     */
    public void shuffleCards();
    /**
     * Returns the number of cards in the list.
     * @return The number of cards in the list.
     */
    public int length();

}