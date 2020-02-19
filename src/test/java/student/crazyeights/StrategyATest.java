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
    Game game;
    ArrayList<Card> playerCardsTest;
    //private final PrintStream originalOut = System.out;
    @Before
    public void setUp() {
        strategyA = new StrategyA();
        game = new Game();
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
        assertEquals(false, strategyA.shouldDrawCard(new Card(Suit.CLUBS, Rank.FIVE), null));
    }

    @Test
    public void testShouldDrawCardTrue() {
        strategyA.playerCards.remove(0);
        assertEquals(true, strategyA.shouldDrawCard(new Card(Suit.CLUBS, Rank.FIVE), null));
    }



}
