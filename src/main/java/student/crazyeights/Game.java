package student.crazyeights;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main game engine
 */
public class Game {

    PlayerStrategy playerOne = new StrategyA();
    PlayerStrategy playerTwo = new StrategyA();
    PlayerStrategy playerThree = new StrategyB();
    PlayerStrategy playerFour = new StrategyB();

    List<PlayerStrategy> players = new ArrayList<>();

    int playerOneID = 0;
    int playerTwoID = 1;
    int playerThreeID = 2;
    int playerFourID = 3;

    int playerOnePoints = 0;
    int playerTwoPoints = 0;
    int playerThreePoints = 0;
    int playerFourPoints = 0;

    ArrayList<Integer> playerPoints = new ArrayList<>();

    int eightCardPoints = 50;
    int faceCardPoints = 10;

    List<Card> cardDeck = Card.getDeck();
    ArrayList<Card> playerOneCards = new ArrayList<>();
    ArrayList<Card> playerTwoCards = new ArrayList<>();
    ArrayList<Card> playerThreeCards = new ArrayList<>();
    ArrayList<Card> playerFourCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> allPlayerCards = new ArrayList<>();

    int cardDeckPosition = cardDeck.size() - 1;

    public void runTournament() {
        initializeTournament();
        addPlayersToPlayerList();
        addPlayerCardsToAllPlayerCardsList();
        addPlayerPointsToList();

       // run game again and again until a player gets 200 points
        int index = 0;
        while (playerPoints.get(index) < 200) {
            System.out.println("Starting new game");
            playGame();
            index++;
            if (index == 4) {
                index = 0;
            }
        }

    }

    public void playGame() {
        initializeGame();

        Card topCard = cardDeck.get(cardDeckPosition);
        cardDeck.remove(cardDeckPosition);
        cardDeckPosition = cardDeck.size() - 1;

        for (int playerId = playerOneID; playerId <= playerFourID; playerId++) {
            if (players.get(playerId).shouldDrawCard(topCard, players.get(playerId).declareSuit())) {
                if (cardDeck.size() == 0) {
                    System.out.println("Exiting game because card deck is empty");
                    return;
                }
                players.get(playerId).receiveCard(cardDeck.get(cardDeckPosition));
                allPlayerCards.get(playerId).add(cardDeck.get(cardDeckPosition));
                cardDeck.remove(cardDeckPosition);
                cardDeckPosition = cardDeck.size() - 1;
            }
            else {
                Card cardPlayed = players.get(playerId).playCard();
                allPlayerCards.get(playerId).remove(cardPlayed);
                topCard = cardPlayed;
            }
            if (isPlayerCardsEmpty()) {
                return;
            }
            if (cardDeck.size() == 0) { // also or if the player cards are empty
                return;
            }
        }
    }

    public boolean isPlayerCardsEmpty() {
        for (int i = 0; i < allPlayerCards.size(); i++) {
           if (allPlayerCards.get(i).isEmpty()) {
               return true;
            }
        }
        return false;
    }
    public void initializeTournament() {
        ArrayList<Integer> opponentIDPlayerOne = new ArrayList<>();
        opponentIDPlayerOne.add(playerTwoID);
        opponentIDPlayerOne.add(playerThreeID);
        opponentIDPlayerOne.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerTwo = new ArrayList<>();
        opponentIDPlayerTwo.add(playerOneID);
        opponentIDPlayerTwo.add(playerThreeID);
        opponentIDPlayerTwo.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerThree = new ArrayList<>();
        opponentIDPlayerThree.add(playerOneID);
        opponentIDPlayerThree.add(playerTwoID);
        opponentIDPlayerThree.add(playerFourID);

        ArrayList<Integer> opponentIDPlayerFour = new ArrayList<>();
        opponentIDPlayerFour.add(playerOneID);
        opponentIDPlayerFour.add(playerTwoID);
        opponentIDPlayerFour.add(playerThreeID);

        playerOne.init(playerOneID, opponentIDPlayerOne);
        playerTwo.init(playerTwoID, opponentIDPlayerTwo);
        playerThree.init(playerThreeID, opponentIDPlayerThree);
        playerFour.init(playerFourID, opponentIDPlayerFour);
    }
    public void initializeGame() {
        sumPoints();
        // check to see if the tournament is over
        cardDeck = Card.getDeck();
        Collections.shuffle(cardDeck);
        int numPlayerCards = 5;

        for (int index = 0; index < players.size(); index++) {
            players.get(index).reset();
            players.get(index).receiveInitialCards(cardDeck);
        }

        playerOneCards.clear();
        playerTwoCards.clear();
        playerThreeCards.clear();
        playerFourCards.clear();

        for (int cardIndex = 0; cardIndex < numPlayerCards; cardIndex++) {
            playerOneCards.add(cardDeck.get(cardIndex));
            playerTwoCards.add(cardDeck.get(cardIndex));
            playerThreeCards.add(cardDeck.get(cardIndex));
            playerFourCards.add(cardDeck.get(cardIndex));
        }
    }

    public void addPlayersToPlayerList() {
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);
    }

    public void addPlayerCardsToAllPlayerCardsList() {
        allPlayerCards.add(playerOneCards);
        allPlayerCards.add(playerTwoCards);
        allPlayerCards.add(playerThreeCards);
        allPlayerCards.add(playerFourCards);
    }

    public void addPlayerPointsToList() {
        playerPoints.add(playerOnePoints);
        playerPoints.add(playerTwoPoints);
        playerPoints.add(playerThreePoints);
        playerPoints.add(playerFourPoints);
    }

    public void sumPoints() {
        for (int j = 0; j < allPlayerCards.size(); j++) {
            for (int i = 0; i < allPlayerCards.get(j).size(); i ++) {
                if (allPlayerCards.get(j).get(i).getRank().equals(Card.Rank.EIGHT)) {
                    playerPoints.get(j);
                }
            }
        }

        for (int index = 0; index < playerOneCards.size(); index++) {
            if (playerOneCards.get(index).getRank().equals(Card.Rank.EIGHT)) {
                playerOnePoints += eightCardPoints;
            } else if (playerOneCards.get(index).getRank().equals(Card.Rank.KING)) {
                playerOnePoints+= faceCardPoints;
            }
           // } else if (playerOneCards.get(index).getSuit().equals(Card.))
        }
    }

}
