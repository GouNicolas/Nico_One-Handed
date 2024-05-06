package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This abstract class represents a list of cards.
 * It is used as a base class for Deck, Hand, and Discard classes.
 */
public abstract class ListOfCards {
    // The list of Card objects
    protected ArrayList<Card> cards;

    /**
     * Constructor that initializes an empty the list of cards.
     */
    public ListOfCards() {
        cards = new ArrayList<>();
    }

    /**
     * Returns the list of cards.
     * @return An ArrayList of Card objects.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Adds a card to the list.
     * @param card The Card object to be added.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes a card from the list.
     * @param card The Card object to be removed.
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Returns a card from the list at the specified index.
     * @param index The index of the Card object to be returned.
     * @return The Card object at the specified index.
     */
    public Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Shuffles the list of cards.
     */
    public void shuffleCards() {
        Collections.shuffle(cards);
    }
    /**
     * Returns the number of cards in the list.
     * @return The number of cards in the list.
     */
    public int length() {
        return cards.size();
    }
}