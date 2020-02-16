package student.crazyeights;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public void runGame() {
        PlayerStrategy playerOne = new StrategyA();
        PlayerStrategy playerTwo = new StrategyA();
        PlayerStrategy playerThree = new StrategyB();
        PlayerStrategy playerFour = new StrategyB();
        int playerOneID = 1;
        int playerTwoID = 2;
        int playerThreeID = 3;
        int playerFourID = 4;

        ArrayList<Integer> opponentID = new ArrayList<Integer>();
        opponentID.add(playerTwoID);
        opponentID.add(playerThreeID);
        opponentID.add(playerFourID);

        playerOne.init(playerOneID, opponentID);


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
