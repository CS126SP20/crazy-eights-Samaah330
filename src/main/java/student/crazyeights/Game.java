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

    int playerOneID = 1;
    int playerTwoID = 2;
    int playerThreeID = 3;
    int playerFourID = 4;

    int playerOnePoints = 0;
    int playerTwoPoints = 0;
    int playerThreePoints = 0;
    int playerFourPoints = 0;

    ArrayList<Integer> playerPoints = new ArrayList<>();

    int eightCardPoints = 50;
    int faceCardPoints = 10;

    boolean isNewGame = true;
    boolean isTournamentOver = false;

    List<Card> cardDeck = Card.getDeck();
    ArrayList<Card> playerOneCards = new ArrayList<>();
    ArrayList<Card> playerTwoCards = new ArrayList<>();
    ArrayList<Card> playerThreeCards = new ArrayList<>();
    ArrayList<Card> playerFourCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> allPlayerCards = new ArrayList<>();
    int cardDeckPosition = cardDeck.size() - 1;



    public void runGame() {
        initializeTournament();
        addPlayersToPlayerList();
        addPlayerCardsToAllPlayerCardsList();
        addPlayerPointsToList();

        while(!isTournamentOver) {
            if (isNewGame) {
                initializeGame();
                isNewGame = false;
            }
            Card topCard = cardDeck.get(cardDeckPosition);
            cardDeck.remove(cardDeckPosition);
            //cardDeckPosition--;

            for (int playerId = playerOneID; playerId <= playerFourID; playerId++) {
                if (players.get(playerId).shouldDrawCard(topCard, players.get(playerId).declareSuit())) {
                    players.get(playerId).receiveCard(cardDeck.get(cardDeckPosition));
                    cardDeck.remove(cardDeckPosition);
                }
                else {
                    players.get(playerId).playCard();
                    // change top card
                }
                if (cardDeck.size() == 0) { // also or if the player cards are empty
                    initializeGame();
                }
            }
        }
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
