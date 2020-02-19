package student.crazyeights;

import static student.crazyeights.Card.Rank;
import static student.crazyeights.Card.Suit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
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

}
