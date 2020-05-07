package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Round {

    private static Team team1;
    private static Team team2;
    private CardDeck deck;
    private static Map<Integer, CardsOnHand> playerAndHand = new HashMap<>();
    private static Card exchangeCardP1 = null;
    private static Card exchangeCardP2 = null;
    private static Card exchangeCardP3 = null;
    private static Card exchangeCardP4 = null;
    private static boolean roundStarted = false;
    private final int MAXIMUMTIMEROUND = 30000; //in milli seconds

    public Round(int roundNumber, CardDeck deck, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.deck = deck;
        playerAndHand.put(team1.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team1.getPlayer2().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer2().getId(), new CardsOnHand());

        distributeCards(roundNumber);
        // wait for players to select a card
        UserInstructions.addNewInstruction("Please choose a card to exchange with your Team mate and klick on the button");
        CompletableFuture.delayedExecutor(45, TimeUnit.SECONDS).execute(() -> {
            exchangeCards();
            roundStarted = true;
            UserInstructions.addNewInstruction("Round " + roundNumber + " started");
        });


    }

    /**
     * Evaluates the number of cards each player gets in a round.
     * For the first round 6 cards get distributed. With every following round the amount is reduced by 1.
     * If in the last round the hand only had 2 cards for each player it continues back at 6.
     *
     * @param round: the nth round played in this game
     * @return number of cards per player
     */
    private int getNumberOfCardsToDistribute(int round) {
        int cardsToDistribute = 0;
        int number = round;
        while (number > 5) {
            number -= 5;
        }
        switch (number) {
            case 1:
                cardsToDistribute = 6;
                break;
            case 2:
                cardsToDistribute = 5;
                break;
            case 3:
                cardsToDistribute = 4;
                break;
            case 4:
                cardsToDistribute = 3;
                break;
            case 5:
                cardsToDistribute = 2;
        }
        return cardsToDistribute;
    }

    /**
     * Distributes in turn a card for every player from the stock depending on the current round
     *
     * @param round current round; stock: left cards to play from the previous round
     */
    private void distributeCards(int round) {
        int totalCardsToDistribute = 4 * getNumberOfCardsToDistribute(round);
        if (deck.getDeckSize() < totalCardsToDistribute) {
            deck.createDeck();
        }

        for (int i = 0; i < totalCardsToDistribute; i = i + 4) {
            playerAndHand.get(team1.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team1.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
        }
        UserInstructions.addNewInstruction(totalCardsToDistribute/4 + " Cards distributed to players");
    }

    /**
     * Exchanges selected cards from teammembers.
     * If a player has not selected a card within the time limit, the system picks one randomly.
     */
    private void exchangeCards() {
        int playerOneId = team1.getPlayer1().getId();
        int playerTwoId = team1.getPlayer2().getId();
        int playerThreeId = team2.getPlayer1().getId();
        int playerFourId = team2.getPlayer2().getId();

        if (exchangeCardP1 == null) {
            exchangeCardP1 = pickRandomCard(playerOneId);
        }
        if (exchangeCardP2 == null) {
            exchangeCardP2 = pickRandomCard(playerTwoId);
        }
        if (exchangeCardP3 == null) {
            exchangeCardP3 = pickRandomCard(playerThreeId);
        }
        if (exchangeCardP4 == null) {
            exchangeCardP4 = pickRandomCard(playerFourId);
        }
        playerAndHand.get(playerOneId).takeCard(exchangeCardP2);
        playerAndHand.get(playerTwoId).takeCard(exchangeCardP1);
        playerAndHand.get(playerThreeId).takeCard(exchangeCardP4);
        playerAndHand.get(playerFourId).takeCard(exchangeCardP3);
        UserInstructions.addNewInstruction("Cards exchanged");
    }

    /**
     * The system automatically chooses a random card from the players hand
     *
     * @param playerId
     * @return Card
     */
    private Card pickRandomCard(int playerId) {
        Random rand = new Random();
        Card randomCard = playerAndHand.get(playerId).getHand()
                .get(rand.nextInt(playerAndHand.get(playerId).getHand().size()));
        return playerAndHand.get(playerId).discardCard(randomCard.getId());
    }

    /**
     * make a new turn for one player
     */
    private void makeTurn() {
        long startTime = System.currentTimeMillis();
        long currentTime;

        CardsOnHand cards = playerAndHand.get(CrazyDog.getNextPlayer());

        //wait for the flag to be set in the Game Logic that a legal move was made.
        //CardsOnHand must not be empty otherwise there will be nothing to do
        if(!cards.isHandEmpty())
        {
            while(!GameLogic.isLegalMoveMade()) {
                currentTime = System.currentTimeMillis();

                //exit loop after maximum time of the Round has elapsed.
                if(currentTime>=(startTime+MAXIMUMTIMEROUND)) {
                    UserInstructions.addNewInstruction("Player " + CrazyDog.getNextPlayer() + " elapsed maximum time of a turn.");
                    //discard a random card from the player's hand
                    pickRandomCard(CrazyDog.getNextPlayer());
                    break;
                }
            }
        }
        else {
            UserInstructions.addNewInstruction("Player " + CrazyDog.getNextPlayer() + " has no cards left and will be skipped");
        }
        //remove selected card
        if(GameLogic.getChosenCardId() != 0) {
            cards.discardCard(GameLogic.getChosenCardId());
            GameLogic.resetChosenCardId();
        }

        //reset Flag
        GameLogic.resetLegalMoveStatus();
        //set next player
        CrazyDog.increaseNextPlayer();
    }

    /**
     * Handles a single round of the game. In case a team wins during a round, the round ends. If no team finishes,
     * the round ends when no player has a card left to play.
     *
     * @return boolean value if a team has won or not.
     */
    public boolean startRound() {

        //wait for the round to start
        //this means, that the user need to exchange their cards first.
        while(!isRoundStarted()) {
            try {
                //go to sleep for a second
                Thread.sleep(1000);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        boolean hasWinner = false;
        while (!hasWinner) {
            // TODO output should later be displayed in browser
            //if all players are out of cards, then the loop will break
            if (allPlayerOutOfCards()) {
                break;
            }

            // SPIELZUG nextPlayer

            if (allPiecesOfTeamAtDestination()) {
                if (team1.getPlayer1().getId() == CrazyDog.getNextPlayer() || team1.getPlayer2().getId() == CrazyDog.getNextPlayer()) {
                    CrazyDog.setWinnerTeam(team1);
                } else {
                    CrazyDog.setWinnerTeam(team2);
                }
                hasWinner = true;
                break;
            }

            UserInstructions.addNewInstruction("It's Player " + CrazyDog.getNextPlayer() + "'s turn. Please play a card");
            makeTurn();
            System.out.println("Turn OK");

            // nextPlayer +1

            if (allPlayerOutOfCards()) {
                break;
            }
        }
        return hasWinner;
    }

    /**
     * Checks if all pieces of current player and its teamplayer are on the destined destinationfield.
     * @return true if all pieces are at their destinationfields
     */
    private boolean allPiecesOfTeamAtDestination() {
        boolean allTeamPiecesAtDestination = false;
        Player currentPlayer;
        Player teamPlayer;
        if (CrazyDog.getNextPlayer() == CrazyDog.getTeam1().getPlayer1().getId()) {
            currentPlayer = CrazyDog.getTeam1().getPlayer1();
            teamPlayer = CrazyDog.getTeam1().getPlayer2();
        } else if (CrazyDog.getNextPlayer() == CrazyDog.getTeam1().getPlayer2().getId()) {
            currentPlayer = CrazyDog.getTeam1().getPlayer2();
            teamPlayer = CrazyDog.getTeam1().getPlayer1();
        } else if (CrazyDog.getNextPlayer() == CrazyDog.getTeam2().getPlayer1().getId()) {
            currentPlayer = CrazyDog.getTeam2().getPlayer1();
            teamPlayer = CrazyDog.getTeam2().getPlayer2();
        } else {
            currentPlayer = CrazyDog.getTeam2().getPlayer2();
            teamPlayer = CrazyDog.getTeam2().getPlayer1();
        }

        Map<Integer, String> currentPiecesPlayer = GameBoard.getPlacesOfPiecesByColor(currentPlayer.getColor());
        Map<Integer, String> destFieldsPlayer = getDestinationFieldsByPlayerColor(currentPlayer.getColor());
        Map<Integer, String> currentPiecesTeamPlayer = GameBoard.getPlacesOfPiecesByColor(teamPlayer.getColor());
        Map<Integer, String> destFielsTeamPlayer = getDestinationFieldsByPlayerColor(teamPlayer.getColor());

        if (arePiecesOfPlayerAtDestination(currentPiecesPlayer, destFieldsPlayer) &&
            arePiecesOfPlayerAtDestination(currentPiecesTeamPlayer, destFielsTeamPlayer)) {
            allTeamPiecesAtDestination = true;
        }
        return allTeamPiecesAtDestination;
    }

    /**
     * Gets the cssIds of the four destinationfields of a certain color
     * @param playerColor
     * @return List with 4 cssIds
     */
    private Map<Integer, String> getDestinationFieldsByPlayerColor(String playerColor) {
        Map<Integer, String> destFieldMap = new HashMap<>();
        if (playerColor.equals("red")) {
            destFieldMap.put(1, "field92");
            destFieldMap.put(2,"field91");
            destFieldMap.put(3, "field90");
            destFieldMap.put(4, "field89");
        } else if (playerColor.equals("yellow")) {
            destFieldMap.put(1, "field96");
            destFieldMap.put(2,"field95");
            destFieldMap.put(3, "field94");
            destFieldMap.put(4, "field93");
        } else if (playerColor.equals("green")) {
            destFieldMap.put(1, "field84");
            destFieldMap.put(2,"field83");
            destFieldMap.put(3, "field82");
            destFieldMap.put(4, "field81");
        } else {
            destFieldMap.put(1, "field88");
            destFieldMap.put(2,"field87");
            destFieldMap.put(3, "field86");
            destFieldMap.put(4, "field85");
        }
        return destFieldMap;
    }

    /**
     * checks if all pieces of a certain player are at the destined destinationfield
     * @param currentPieces cssId of current piece positions
     * @param destFields cssId of destinationfield
     * @return true if all pieces are on their specific destinationfield
     */
    private boolean arePiecesOfPlayerAtDestination(Map<Integer,String> currentPieces, Map<Integer,String> destFields) {
        boolean allPiecesAtDestination = false;
        if (currentPieces.get(1).equals(destFields.get(1)) &&
            currentPieces.get(2).equals(destFields.get(2)) &&
            currentPieces.get(3).equals(destFields.get(3)) &&
            currentPieces.get(4).equals(destFields.get(4))) {
            allPiecesAtDestination = true;
        }
        return allPiecesAtDestination;
    }

    /**
     * Evaluates if one of the players still has a card to play.
     * @return
     */
    private boolean allPlayerOutOfCards() {
        boolean allPlayersOutOfCards = false;
        if (playerAndHand.get(team1.getPlayer1().getId()).isHandEmpty() &&
                playerAndHand.get(team1.getPlayer2().getId()).isHandEmpty() &&
                playerAndHand.get(team2.getPlayer1().getId()).isHandEmpty() &&
                playerAndHand.get(team2.getPlayer2().getId()).isHandEmpty()
        ) {
            allPlayersOutOfCards = true;
        }
        return allPlayersOutOfCards;
    }

    public static Map<Integer, CardsOnHand> getPlayerAndHand() {
        return playerAndHand;
    }

    /**
     * Sets the exchangeCard variable with the card the user selected to exchange with its teamplayer
     *
     * @param playerId
     * @param cardId
     */
    public static void setExchangeCard(int playerId, int cardId) {
        if (playerId == team1.getPlayer1().getId()) {
            exchangeCardP1 = playerAndHand.get(team1.getPlayer1().getId()).discardCard(cardId);
        } else if (playerId == team1.getPlayer2().getId()) {
            exchangeCardP2 = playerAndHand.get(team1.getPlayer2().getId()).discardCard(cardId);
        } else if (playerId == team2.getPlayer1().getId()) {
            exchangeCardP3 = playerAndHand.get(team2.getPlayer1().getId()).discardCard(cardId);
        } else if (playerId == team2.getPlayer2().getId()) {
            exchangeCardP4 = playerAndHand.get(team2.getPlayer2().getId()).discardCard(cardId);
        } else {

        }
    }

    /**
     * Calculates the new Destination if the piece is on a wormhole
     *
     * @param acctualDest
     * @return new Destination
     */
    public static int calcDestWhenPieceOnWomrhole(int acctualDest) {
        Random r = new Random();
        int destInt = acctualDest;
        while (acctualDest == Values.Wormhole1 || acctualDest == Values.Wormhole2 || acctualDest == Values.Wormhole3 || acctualDest == Values.Wormhole4) {
            destInt = r.nextInt(64) + 1;
        }
        return destInt;
    }


    public static boolean isRoundStarted() {
        return roundStarted;
    }

}
