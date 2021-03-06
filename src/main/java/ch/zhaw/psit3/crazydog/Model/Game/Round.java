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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Round</h1>
 * The Round handles a single round in the game. <br>
 * <ol>
 *     <li>It calculates the amount of cards needed in a certain round and distributes them from the deck.</li>
 *     <li>It waits for the players to select a card to exchange and hands it (or a random card if none is selected)
 *         over to its teammember.</li>
 *     <li>It controls the flow of the game and checks after every turn if a team has won in order to end the game.</li>
 * </ol>
 *
 * @author R. Somma, S. Werlin
 * @version 1.0
 * @since April 2020
 */
public class Round {
    private static final Logger LOGGER = Logger.getLogger(Round.class.getName());

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

    /**
     * Constructor to create new round
     * Resets static values back to its default value.
     * Distributes cards and exchanges selected cards among team members.
     *
     * @param roundNumber current round number
     * @param deck        deck of cards left from the previous round
     * @param team1       a team consisting of player 1 and player 2
     * @param team2       a team consisting of player 3 and player 4
     */
    public Round(int roundNumber, CardDeck deck, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.deck = deck;
        this.playerAndHand = new HashMap<>();
        this.exchangeCardP1 = null;
        this.exchangeCardP2 = null;
        this.exchangeCardP3 = null;
        this.exchangeCardP4 = null;
        this.roundStarted = false;
        playerAndHand.put(team1.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team1.getPlayer2().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer2().getId(), new CardsOnHand());

        distributeCards(roundNumber);
        // wait for players to select a card
        UserInstructions.addNewInstruction("Bitte Karte auswählen, welche mit dem Team-Spieler getauscht wird.");
        CompletableFuture.delayedExecutor(45, TimeUnit.SECONDS).execute(() -> { //45 Seconds delay
            exchangeCards();
            roundStarted = true;
            UserInstructions.addNewInstruction("Runde " + roundNumber + " gestartet");
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
    static int getNumberOfCardsToDistribute(int round) {
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
        int totalCardsToDistribute = 4 * getNumberOfCardsToDistribute(round); // 4 players
        if (deck.getDeckSize() < totalCardsToDistribute) {
            deck.createDeck();
        }

        for (int i = 0; i < totalCardsToDistribute; i = i + 4) {
            playerAndHand.get(team1.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team1.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
        }
        UserInstructions.addNewInstruction(totalCardsToDistribute / 4 + " Karten an die Spieler ausgeteilt");
    }

    /**
     * Sets the exchangeCard variable with the card the user selected to exchange with its team member
     *
     * @param playerId id of a certain player
     * @param cardId   id of selected card to exchange with team member
     */
    public static void setExchangeCard(int playerId, int cardId) {
        if (playerId == team1.getPlayer1().getId()) {
            exchangeCardP1 = playerAndHand.get(team1.getPlayer1().getId()).discardCard(cardId);
        } else if (playerId == team1.getPlayer2().getId()) {
            exchangeCardP2 = playerAndHand.get(team1.getPlayer2().getId()).discardCard(cardId);
        } else if (playerId == team2.getPlayer1().getId()) {
            exchangeCardP3 = playerAndHand.get(team2.getPlayer1().getId()).discardCard(cardId);
        } else {
            exchangeCardP4 = playerAndHand.get(team2.getPlayer2().getId()).discardCard(cardId);
        }
    }

    /**
     * Exchanges selected cards from team members.
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
        UserInstructions.addNewInstruction("Karten wurden getauscht");
    }

    /**
     * The system automatically chooses a random card from the players hand
     *
     * @param playerId id of certain player
     * @return a Card from this player's hand
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
        int playerId = CrazyDog.getNextPlayer();
        CardsOnHand cards = playerAndHand.get(playerId);

        //if no valid turn is available with the cards on hand, then discard all cards on hand.
        if (!isThereAnyMovePossibleWithCardsOnHand(cards, playerId)) {
            cards.discardAllCards();
        }

        //wait for the flag to be set in the Game Logic that a legal move was made.
        //CardsOnHand must not be empty otherwise there will be nothing to do
        if (!cards.isHandEmpty()) {
            while (!Turn.isLegalMoveMade()) {
                currentTime = System.currentTimeMillis();

                //exit loop after maximum time of the Round has elapsed.
                if (currentTime >= (startTime + MAXIMUMTIMEROUND)) {
                    UserInstructions.addNewInstruction("Spieler " + CrazyDog.getNextPlayer() + " hat die Zeit für einen Zug überschritten");
                    //discard a random card from the player's hand
                    pickRandomCard(CrazyDog.getNextPlayer());
                    break;
                }
            }
        } else {
            UserInstructions.addNewInstruction("Spieler " + CrazyDog.getNextPlayer() + " hat keine Karte mehr in der Hand und wird ausgelassen");
        }
        //remove selected card
        if (Turn.getChosenCardId() != 0) {
            cards.discardCard(Turn.getChosenCardId());
            Turn.resetChosenCardId();
        }

        //reset Flag
        Turn.resetLegalMoveStatus();
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
        while (!isRoundStarted()) {
            try {
                //go to sleep for a second
                Thread.sleep(1000);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Round could not start.");
            }
        }

        boolean hasWinner = false;
        while (!hasWinner) {

            // SPIELZUG nextPlayer
            UserInstructions.addNewInstruction("Spieler " + CrazyDog.getNextPlayer() + " ist dran. Spiele eine Karte");
            makeTurn();

            LOGGER.info("Turn ok.");

            if (allPiecesOfTeamAtDestination()) {
                if (team1.getPlayer1().getId() == CrazyDog.getNextPlayer() || team1.getPlayer2().getId() == CrazyDog.getNextPlayer()) {
                    CrazyDog.setWinnerTeam(team1);
                } else {
                    CrazyDog.setWinnerTeam(team2);
                }
                hasWinner = true;
                break;
            }

            //set next player
            CrazyDog.increaseNextPlayer();

            //if all players are out of cards, then the loop will break
            if (allPlayerOutOfCards()) {
                break;
            }
        }
        return hasWinner;
    }

    /**
     * Retrieves the information whether or not a round has started
     *
     * @return true if round has started
     */
    public static boolean isRoundStarted() {
        return roundStarted;
    }

    private Player getTeamPlayer(int playerId) {
        Player teamPlayer;
        if (playerId == CrazyDog.getTeam1().getPlayer1().getId()) {
            teamPlayer = CrazyDog.getTeam1().getPlayer2();
        } else if (playerId == CrazyDog.getTeam1().getPlayer2().getId()) {
            teamPlayer = CrazyDog.getTeam1().getPlayer1();
        } else if (playerId == CrazyDog.getTeam2().getPlayer1().getId()) {
            teamPlayer = CrazyDog.getTeam2().getPlayer2();
        } else if (playerId == CrazyDog.getTeam2().getPlayer2().getId()) {
            teamPlayer = CrazyDog.getTeam2().getPlayer1();
        } else {
            throw new IllegalArgumentException("No team member for this player id available.");
        }
        return teamPlayer;
    }

    /**
     * Checks if all pieces of current player and its team member are on the destined destination field.
     *
     * @return true if all pieces are at their destination fields
     */
    private boolean allPiecesOfTeamAtDestination() {
        boolean allTeamPiecesAtDestination = false;
        String currentPlayerColor = CrazyDog.getPlayerColorById(CrazyDog.getNextPlayer());
        String teamPlayerColor = getTeamPlayer(CrazyDog.getNextPlayer()).getColor();

        Map<Integer, String> currentPiecesPlayer = CrazyDog.getGameBoard().getPlacesOfPiecesByColor(currentPlayerColor);
        Map<Integer, String> destFieldsPlayer = CrazyDog.getGameBoard().getMapOfDestinationFieldsByColor(currentPlayerColor);
        Map<Integer, String> currentPiecesTeamPlayer = CrazyDog.getGameBoard().getPlacesOfPiecesByColor(teamPlayerColor);
        Map<Integer, String> destFieldsTeamPlayer = CrazyDog.getGameBoard().getMapOfDestinationFieldsByColor(teamPlayerColor);

        if (arePiecesOfPlayerAtDestination(currentPiecesPlayer, destFieldsPlayer) &&
                arePiecesOfPlayerAtDestination(currentPiecesTeamPlayer, destFieldsTeamPlayer)) {
            allTeamPiecesAtDestination = true;
        }
        return allTeamPiecesAtDestination;
    }

    /**
     * Checks if all pieces of a certain player are at the destined destination field.
     *
     * @param currentPieces cssId of current piece positions
     * @param destFields    cssId of destination field
     * @return true if all pieces are on their specific destination field
     */
    static boolean arePiecesOfPlayerAtDestination(Map<Integer, String> currentPieces, Map<Integer, String> destFields) {
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
     *
     * @return true if no player has a card left to play
     */
    boolean allPlayerOutOfCards() {
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
     * Check if the cards on the Hand, if any move is possible or not
     *
     * @param cards    CardsOnHand object, for the associated player
     * @param playerID PlayerId of the current Player which cards we have
     * @return return true if there is any move possible, otherwise return false
     */
    private boolean isThereAnyMovePossibleWithCardsOnHand(CardsOnHand cards, int playerID) {
        boolean anyMovePossible = false;
        for (Card card : cards.getHand()) {
            Turn.calculateMoves(card.getValue(), playerID);       // Calculate all the possible moves
            List<Move> moves = Turn.getMoves();
            //if card is 3 or questionmark, their is always a move that is possible.
            if (!moves.isEmpty() || card.getValue() == 3 || card.getValue() == 14) {
                anyMovePossible = true;
                break;
            }
        }
        return anyMovePossible;
    }

}
