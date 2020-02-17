package student.crazyeights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategyA implements PlayerStrategy {
    ArrayList<Card> playerCards = new ArrayList<>();
    int numDrawCards = 5;
    Card playCard;

    @Override
    public void init(int playerId, List<Integer> opponentIds) {
        // put it in some for loop or something
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
        for (int cardIndex = 0; cardIndex <playerCards.size(); cardIndex++) {
            if (playerCards.get(cardIndex).getRank().equals(Card.Rank.EIGHT)) {
                playCard = playerCards.get(cardIndex);
                return false; // because you would put down your crazy eight and change suit
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
        return playCard;
    }

    @Override
    public Card.Suit declareSuit() {
        // always declares clubs
        //return Card.Suit.CLUBS;
    }

    @Override
    public void processOpponentActions(List<PlayerTurn> opponentActions) {

    }

    @Override
    public void reset() {

    }
}
