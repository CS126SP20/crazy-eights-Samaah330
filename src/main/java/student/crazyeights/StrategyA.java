package student.crazyeights;

import java.util.List;

public class StrategyA implements PlayerStrategy {
    PlayerTurn player = new PlayerTurn();
    @Override
    public void init(int playerId, List<Integer> opponentIds) {
        player.playerId = playerId;
    }

    @Override
    public void receiveInitialCards(List<Card> cards) {

    }

    @Override
    public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
        return false;
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
