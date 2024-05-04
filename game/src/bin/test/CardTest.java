package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;
import model.Rank;
import model.Suit;

public class CardTest {
    @Test
    public void testCard() {
        Card card = new Card(Rank.ace, Suit.spades);
        
        // Test constructor and getters
        assertEquals(Rank.ace, card.getRank());
        assertEquals(Suit.spades, card.getSuit());
    }
}