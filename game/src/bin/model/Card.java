package model;

public class Card {

    private Rank rank;
    private Suit suit;
    
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public boolean equals(Card card) {
        if (card == null) {
            return false;
        }
        else{
            return this.rank == card.rank && this.suit == card.suit;
        }
    }
}