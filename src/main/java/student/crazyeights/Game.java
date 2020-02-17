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

    ArrayList<PlayerStrategy> players = new ArrayList<PlayerStrategy>();

    int playerOneID = 1;
    int playerTwoID = 2;
    int playerThreeID = 3;
    int playerFourID = 4;

    int playerOnePoints = 0;
    int playerTwoPoints = 0;
    int playerThreePoints = 0;
    int playerFourPoints = 0;

    boolean isNewGame = true;
    boolean isTournamentOver = false;

    List<Card> cardDeck = Card.getDeck();
    ArrayList<Card> playerOneCards = new ArrayList<>();
    ArrayList<Card> playerTwoCards = new ArrayList<>();
    ArrayList<Card> playerThreeCards = new ArrayList<>();
    ArrayList<Card> playerFourCards = new ArrayList<>();
    int cardDeckPosition = 0;



    public void runGame() {
        initializeTournament();
        Card topCard = cardDeck.get(cardDeckPosition);
        cardDeckPosition++;
        Card.Suit changedSuit = null;
        addPlayersToPlayerList();

        while(!isTournamentOver) {
            if (isNewGame) {
                initializeGame();
                isNewGame = false;
            }

            for (int playerId = playerOneID; playerId < playerFourID; playerId++) {
                if (players.get(playerId).shouldDrawCard(topCard, changedSuit)) {
                    players.get(playerId).receiveCard(cardDeck.get(cardDeckPosition));
                    cardDeck.remove(cardDeckPosition);
                    cardDeckPosition++;
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
        ArrayList<Integer> opponentID = new ArrayList<>();
        opponentID.add(playerTwoID);
        opponentID.add(playerThreeID);
        opponentID.add(playerFourID);

        playerOne.init(playerOneID, opponentID);
        // should i do this for each of the players
    }
    public void initializeGame() {
        cardDeck = Card.getDeck();
        Collections.shuffle(cardDeck);
        int numPlayerCards = 5;

        playerOne.receiveInitialCards(cardDeck);
        playerTwo.receiveInitialCards(cardDeck);
        playerThree.receiveInitialCards(cardDeck);
        playerFour.receiveInitialCards(cardDeck);

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


}
