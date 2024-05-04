package model;

/**
 * Represents a playing card.
 */
public class Card {

    private Rank rank;
    private Suit suit;
    
    /**
     * Constructs a card with the specified rank and suit.
     * 
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns the rank of the card.
     * 
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the suit of the card.
     * 
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns a string representation of the card.
     * 
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Checks if this card is equal to the specified card.
     * 
     * @param card the card to compare
     * @return true if the cards are equal, false otherwise
     */
    public boolean equals(Card card) {
        if (card == null) {
            return false;
        }
        else{
            return this.rank == card.rank && this.suit == card.suit;
        }
    }

    /**
     * Returns the hash code value for the card.
     * 
     * @return the hash code value for the card
     */
    public int hashCode() {
        return rank.hashCode() + suit.hashCode();
    }

    /**
     * Checks if this card has the same suit as the specified card.
     * 
     * @param card the card to compare
     * @return true if the cards have the same suit, false otherwise
     */
    public boolean suitEquals(Card card) {
        if (card == null) {
            return false;
        }
        else{
            return this.suit == card.suit;
        }
    }

    /**
     * Checks if this card has the same rank as the specified card.
     * 
     * @param card the card to compare
     * @return true if the cards have the same rank, false otherwise
     */
    public boolean rankEquals(Card card) {
        if (card == null) {
            return false;
        }
        else{
            return this.rank == card.rank;
        }
    }

}