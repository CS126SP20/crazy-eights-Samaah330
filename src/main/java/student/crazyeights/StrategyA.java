package student.crazyeights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Questions:
// confused about processOpponentActionsMethod
// ID thing

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

       /* for (Card c : playerCards) {
            if (c.equals(topPileCard)) {
                return false;
            }
        }*/
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
        return null; // player never declares suit
    }

    // really confused about this method
    @Override
    public void processOpponentActions(List<PlayerTurn> opponentActions) {
    }

    @Override
    public void reset() {
        playerCards.clear();
    }
}
