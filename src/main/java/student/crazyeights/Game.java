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

    int winningPoints = 200;

    List<Card> cardDeck = Card.getDeck();
    ArrayList<Card> playerOneCards = new ArrayList<>();
    ArrayList<Card> playerTwoCards = new ArrayList<>();
    ArrayList<Card> playerThreeCards = new ArrayList<>();
    ArrayList<Card> playerFourCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> allPlayerCards = new ArrayList<>();

    int cardDeckPosition = cardDeck.size() - 1;

    public void runTournament() {
        System.out.println("starting new tournamenet");
        initializeTournament();
        addPlayersToPlayerList();
        addPlayerCardsToAllPlayerCardsList();
        addPlayerPointsToList();

        while (!isTournamentOver()) {
            playGame();
        }
        System.out.println("Tournament has ended");

    }

    public void playGame() {
        System.out.println("starting new game");
        initializeGame();

        Card topCard = cardDeck.get(cardDeckPosition);
        cardDeck.remove(cardDeckPosition);
        cardDeckPosition = cardDeck.size() - 1;

        for (int playerId = playerOneID; playerId <= playerFourID; playerId++) {
            if (players.get(playerId).shouldDrawCard(topCard, players.get(playerId).declareSuit())) {
                drawCard(playerId);
            }
            else {
               // playCard(playerId, topCard);
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

    public void drawCard(int playerId) {
        if (cardDeck.size() == 0) {
            System.out.println("Exiting game because card deck is empty");
            return;
        }
        players.get(playerId).receiveCard(cardDeck.get(cardDeckPosition));
        allPlayerCards.get(playerId).add(cardDeck.get(cardDeckPosition));
        cardDeck.remove(cardDeckPosition);
        cardDeckPosition = cardDeck.size() - 1;
    }

    /*public void playCard(int playerId, Card topCard) {
        Card cardPlayed = players.get(playerId).playCard();
        allPlayerCards.get(playerId).remove(cardPlayed);
        topCard = cardPlayed;
    } */
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

    /**
     * checks to see if the tournament is over by summing all player's points at the end of each game
     * @return true if a player has more than 200 points
     */
    public boolean isTournamentOver() {
        System.out.println("checking to see if tournament is over");
        for (int playerID = 0; playerID < allPlayerCards.size(); playerID++) {
            for (int i = 0; i < allPlayerCards.get(playerID).size(); i ++) {
                if (playerID == 0) {
                    playerOnePoints += allPlayerCards.get(playerID).get(i).getPointValue();
                } else if (playerID == 1) {
                    playerTwoPoints += allPlayerCards.get(playerID).get(i).getPointValue();
                } else if (playerID == 2) {
                    playerThreePoints += allPlayerCards.get(playerID).get(i).getPointValue();
                } else if (playerID == 3) {
                    playerFourPoints += allPlayerCards.get(playerID).get(i).getPointValue();
                }
                if (playerPoints.get(playerID) >= winningPoints) {
                    System.out.println("tournament is over");
                    return true;
                }
            }
        }
        return false;
    }

}
