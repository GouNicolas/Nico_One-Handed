package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Card;
import model.Hand;
import model.Rank;
import model.Suit;
import org.junit.jupiter.api.Test; // Add this import statement

public class HandTest {
    @Test
    public void testHand() {
        Hand hand = new Hand();
        Card card1 = new Card(Rank.ace, Suit.spades);
        Card card2 = new Card(Rank.two, Suit.hearts);

        // Test addCard
        hand.addCard(card1);
        hand.addCard(card2);
        assertEquals(2, hand.getHandSize());

        // Test getCard
        assertEquals(card1, hand.getCard(0));
        assertEquals(card2, hand.getCard(1));

        // Test removeCard
        hand.removeCard(card1);
        assertEquals(1, hand.getHandSize());
        assertEquals(card2, hand.getCard(0));

        // Test clearHand
        hand.clearHand();
        assertEquals(0, hand.getHandSize());
    }
}