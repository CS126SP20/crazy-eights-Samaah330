package student.crazyeights;

import static student.crazyeights.Card.Rank;
import static student.crazyeights.Card.Suit;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StrategyATest {
    StrategyA strategyA;
    ArrayList<Card> playerCardsTest;
    //private final PrintStream originalOut = System.out;
    @Before
    public void setUp() {
        strategyA = new StrategyA();
        //playerCardsTest = new ArrayList<>();
        strategyA.playerCards.add(new Card(Suit.HEARTS, Rank.EIGHT));
        strategyA.playerCards.add(new Card(Suit.DIAMONDS, Rank.JACK));
        strategyA.playerCards.add(new Card(Suit.SPADES, Rank.SIX));
        strategyA.playerCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
        strategyA.playerCards.add(new Card(Suit.CLUBS, Rank.TWO));
    }

    @Test
    public void testShouldDrawCardFalse() {
        assertEquals(false, strategyA.shouldDrawCard(new Card(Suit.HEARTS, Rank.QUEEN), null));
    }

    @Test
    public void testShouldDrawCardFalseBecauseEight() {
        assertEquals(false, strategyA.shouldDrawCard(new Card(Suit.CLUBS, Rank.FIVE), Suit.HEARTS));
    }

    @Test
    public void testShouldDrawCardTrue() {
        strategyA.playerCards.remove(0); // removes card with rank of Eight
        assertEquals(true, strategyA.shouldDrawCard(new Card(Suit.CLUBS, Rank.FIVE), Suit.SPADES));
    }

    @Test
    public void testInitialPlayerCardSize() {
        assertEquals(5, strategyA.playerCards.size());
    }

    @Test
    public void testPlayerRecivesNewCard() {
        Card card = new Card(Suit.SPADES, Rank.SIX);
        strategyA.receiveCard(card);
        assertEquals(card, strategyA.playerCards.get(5));
    }

    @Test
    public void testPlayCardEight() {
        strategyA.shouldDrawCard(new Card(Suit.SPADES, Rank.ACE), null);
        assertEquals(new Card(Suit.HEARTS, Rank.EIGHT), strategyA.playCard());
    }

    @Test
    public void testPlaySameCard() {
        strategyA.playerCards.remove(0);
        Card card = new Card(Suit.SPADES, Rank.SIX);
        strategyA.shouldDrawCard(card, null);
        assertEquals(card, strategyA.playCard());
    }



}
