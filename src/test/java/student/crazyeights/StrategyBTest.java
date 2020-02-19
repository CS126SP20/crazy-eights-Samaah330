package student.crazyeights;

import static student.crazyeights.Card.Rank;
import static student.crazyeights.Card.Suit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StrategyBTest {
    StrategyB strategyB;
    @Before
    public void setUp() {
        strategyB = new StrategyB();
        strategyB.playerCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
        strategyB.playerCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
        strategyB.playerCards.add(new Card(Suit.SPADES, Rank.KING));
        strategyB.playerCards.add(new Card(Suit.HEARTS, Rank.NINE));
        strategyB.playerCards.add(new Card(Suit.DIAMONDS, Rank.TWO));
    }

    @Test
    public void testShouldDrawCardForSameCardFalse() {
        assertEquals(false, strategyB.shouldDrawCard(new Card(Suit.HEARTS, Rank.NINE), null));
    }

    @Test
    public void testShouldDrawCardForSameRankFalse() {
        assertEquals(false, strategyB.shouldDrawCard(new Card(Suit.SPADES, Rank.NINE), Suit.HEARTS));
    }

    @Test
    public void testShouldDrawCardForSameSuitFalse() {
        assertEquals(false, strategyB.shouldDrawCard(new Card(Suit.CLUBS, Rank.FIVE), Suit.SPADES));
    }

    @Test
    public void testShouldDrawCardTrue() {
        assertEquals(true, strategyB.shouldDrawCard(new Card(Suit.DIAMONDS, Rank.FOUR), null));
    }

    @Test
    public void testInitialPlayerCardSize() {
        assertEquals(5, strategyB.playerCards.size());
    }

    @Test
    public void testPlayerReceivesNewCard() {
        Card card = new Card(Suit.SPADES, Rank.SIX);
        strategyB.receiveCard(card);
        assertEquals(card, strategyB.playerCards.get(5));
    }

    @Test
    public void testPlayCardSameRank() {
        strategyB.shouldDrawCard(new Card(Suit.SPADES, Rank.ACE), null);
        assertEquals(new Card(Suit.DIAMONDS, Rank.ACE), strategyB.playCard());
    }

    @Test
    public void testPlayCardSameSuit() {
        strategyB.shouldDrawCard(new Card(Suit.HEARTS, Rank.TWO), Suit.SPADES);
        assertEquals(new Card(Suit.HEARTS, Rank.NINE), strategyB.playCard);
    }

    @Test
    public void testPlaySameCard() {
        Card card = new Card(Suit.SPADES, Rank.KING);
        strategyB.shouldDrawCard(card, null);
        assertEquals(card, strategyB.playCard());
    }

    @Test
    public void testDeclareSuitClubs() {
        assertEquals(Suit.CLUBS, strategyB.declareSuit());
    }

    @Test
    public void testReset() {
        strategyB.reset();
        assertEquals(0, strategyB.playerCards.size());
    }

}
