package student.crazyeights;

import java.util.ArrayList;
import java.util.List;

/**
 * Second implementation of PlayerStrategy
 * In this strategy the player does not check for eights
 * and checks for either the same suit or rank
 * declares the suit as the first card in their deck
 */
public class StrategyB implements PlayerStrategy {

    ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    Card playCard;

    @Override
    public void init(int playerId, List<Integer> opponentIds) {
        System.out.println("You are player " + playerId);
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
            if (playerCards.get(cardIndex).getRank().equals(topPileCard.getRank()) ||
                playerCards.get(cardIndex).getSuit().equals(topPileCard.getSuit())) {
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
        return playCard;
    }

    @Override
    public Card.Suit declareSuit() {
        return playerCards.get(0).getSuit();
    }

    @Override
    public void processOpponentActions(List<PlayerTurn> opponentActions) {

    }

    @Override
    public void reset() {
        playerCards.clear();
    }
}
