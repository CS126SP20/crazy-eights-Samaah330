package student.crazyeights;

import java.util.ArrayList;
import java.util.List;

public class StrategyB implements PlayerStrategy {
    ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    @Override
    public void init(int playerId, List<Integer> opponentIds) {
        // would the id's have to be diff than strategy A ..?
        System.out.println("You are player " + playerId +
                " and you are playing against player " + opponentIds.get(0) +
                " , " + opponentIds.get(1) + " ,and " + opponentIds.get(2));
    }

    @Override
    public void receiveInitialCards(List<Card> cards) {
        playerCards.clear();
        for (int cardIndex = 0; cardIndex < numDrawCards; cardIndex++) {
            playerCards.add(cards.get(cardIndex));
        }
    }

    @Override
    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
        for (int cardIndex = 0; cardIndex < playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).equals(topPileCard)) {
                return false;
            }
        }
        // strategy B is not very smart and wont check if they have eights
        return true;
    }

    @Override
    public void receiveCard(Card drawnCard) {

    }

    @Override
    public Card playCard() {
        return null;
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

    }
}
