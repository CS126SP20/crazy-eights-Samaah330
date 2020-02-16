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
    int cardDeckPosition = 0;



    public void runGame() {

        initializeTournament();

        if (isNewGame) {
            initializeGame();
        }

        Card topCard = cardDeck.get(cardDeckPosition);
        cardDeckPosition++;
        Card.Suit changedSuit = null;

        while(!isTournamentOver) {
            // call reset after every turn and in reset check to see if the game is actually over
            // one player turn
            if (playerOne.shouldDrawCard(topCard,changedSuit)) {
                playerOne.receiveCard(cardDeck.get(cardDeckPosition));
                cardDeckPosition++;
            } else {
                playerOne.playCard();
                // change top card to the card that the player just put down
            }
            playerOne.reset();

            if (playerTwo.shouldDrawCard(topCard,changedSuit)) {
                playerTwo.receiveCard(cardDeck.get(cardDeckPosition));
            } else {
                playerTwo.playCard();
                // change top card
            }
            playerTwo.reset();

            // do this same thing for player three and player four



    }


    }
    public void initializeTournament() {
        ArrayList<Integer> opponentID = new ArrayList<>();
        opponentID.add(playerTwoID);
        opponentID.add(playerThreeID);
        opponentID.add(playerFourID);

        playerOne.init(playerOneID, opponentID);
    }
    public void initializeGame() {
        Collections.shuffle(cardDeck);
        playerOne.receiveInitialCards(cardDeck);
        playerTwo.receiveInitialCards(cardDeck);
        playerThree.receiveInitialCards(cardDeck);
        playerFour.receiveInitialCards(cardDeck);
    }



    /*ArrayList<Card> discardPile = new ArrayList<Card>();
    ArrayList<Card> drawPile = new ArrayList<Card>();

    //holds all players and each player with its own cards
    ArrayList<List<Card>> playerPile = new ArrayList<List<Card>>();

    int[] playerId = {1,2,3,4};

    public Game()
    {

    }
    public Game(ArrayList<Card> discardPile,
                ArrayList<Card> drawPile,
                ArrayList<Card> playerOnePile,
                ArrayList<Card> playerTwoPile,
                ArrayList<Card> playerThreePile,
                ArrayList<Card> playerFourPile) {

        this.discardPile = discardPile;
        this.drawPile = drawPile;

        playerPile.add(playerOnePile);
        playerPile.add(playerTwoPile);
        playerPile.add(playerThreePile);
        playerPile.add(playerFourPile);
    }

    public void StartGame() {

        ArrayList<PlayerStrategy> players = new ArrayList<PlayerStrategy>();

        PlayerStrategy playerOne = new StrategyA();
        PlayerStrategy playerTwo = new StrategyA();
        PlayerStrategy playerThree = new StrategyB();
        PlayerStrategy playerFour = new StrategyB();

        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);

        for (int turn = 0; turn < players.size(); turn++) {
            PlayerStrategy player = players.get(turn);
            player.reset();

            player.init(playerId[turn], null);
            ArrayList<Card> currentPlayerPile = (ArrayList<Card>) playerPile.get(turn);
            player.receiveInitialCards(currentPlayerPile);
        }

        while (!GameOver())
        {

            for (int turn = 0; turn < players.size(); turn++)
            {
                PlayerStrategy player = players.get(turn);

                int topCardIndex = this.drawPile.size()-1;
                Card topCard = this.drawPile.get(topCardIndex);

                PlayerTurn objPlayerTurn = new PlayerTurn();
                objPlayerTurn.playerId = playerId[turn];

                ArrayList<Card> currentPlayerPile = (ArrayList<Card>) playerPile.get(turn);

                if (player.shouldDrawCard(topCard, player.declareSuit()))
                {
                    player.receiveCard(topCard);
                    currentPlayerPile.add(topCard);
                    objPlayerTurn.drewACard = true;
                }
                else {
                    Card card = player.playCard();
                    currentPlayerPile.remove(card);
                    discardPile.add(card);
                }
            }
        }
    }

    public boolean GameOver()
    {
        return false;
    }


     */

}
