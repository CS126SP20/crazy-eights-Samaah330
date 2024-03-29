package student.crazyeights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * First implementation of PlayerStrategy
 * In this strategy the player only checks for the same
 * exact card and for eights
 * does not declare a suit
 */
public class StrategyA implements PlayerStrategy {
    public ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    Card playCard;

    @Override
    public void init(int playerId, List<Integer> opponentIds) {
        System.out.println("You are player " + playerId);
    }

    @Override
    public void receiveInitialCards(List<Card> cards) {
        for (int cardIndex = 0; cardIndex < numDrawCards; cardIndex++) {
            playerCards.add(cards.get(cardIndex));
        }
    }

    @Override
    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
        for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).getRank().equals(Card.Rank.EIGHT)) {
                System.out.println(playCard);
                playCard = playerCards.get(cardIndex);
                return false;
            }
        }

       for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).equals(topPileCard)) {
                playCard = playerCards.get(cardIndex);
                return false;
            }
        }

        return true;
    }

    @Override
    public void receiveCard(Card drawnCard) {
        playerCards.add(drawnCard);
    }

    @Override
    public Card playCard() {
       System.out.println(playCard);
        return playCard;
    }

    @Override
    public Card.Suit declareSuit() {
        return null;
    }

    @Override
    public void processOpponentActions(List<PlayerTurn> opponentActions) {
    }

    @Override
    public void reset() {
        playerCards.clear();
    }
}
