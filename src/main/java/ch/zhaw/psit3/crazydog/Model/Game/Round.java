package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Round {

    private static Team team1;
    private static Team team2;
    CardDeck deck;
    private int nextPlayer;
    private static Map<Integer, CardsOnHand> playerAndHand = new HashMap<>();
    private static Card exchangeCardP1 = null;
    private static Card exchangeCardP2 = null;
    private static Card exchangeCardP3 = null;
    private static Card exchangeCardP4 = null;
    private static boolean roundStarted = false;

    public Round(int roundNumber, CardDeck deck, Team team1, Team team2, int nextPlayer) {
        this.team1 = team1;
        this.team2 = team2;
        this.nextPlayer = nextPlayer;
        this.deck = deck;

        playerAndHand.put(team1.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team1.getPlayer2().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer1().getId(), new CardsOnHand());
        playerAndHand.put(team2.getPlayer2().getId(), new CardsOnHand());

        distributeCards(roundNumber);
        // wait for players to select a card
        CompletableFuture.delayedExecutor(45, TimeUnit.SECONDS).execute(() -> {
            exchangeCards();
            roundStarted = true;
            UserInstructions.addNewInstruction("Round "+roundNumber +" started");
        });


    }

    /**
     * Evaluates the number of cards each player gets in a round.
     * For the first round 6 cards get distributed. With every following round the amount is reduced by 1.
     * If in the last round the hand only had 2 cards for each player it continues back at 6.
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
            case 1: cardsToDistribute = 6;
                break;
            case 2: cardsToDistribute = 5;
                break;
            case 3: cardsToDistribute = 4;
                break;
            case 4: cardsToDistribute = 3;
                break;
            case 5: cardsToDistribute = 2;
        }
        return cardsToDistribute;
    }

    /**
     * Distributes in turn a card for every player from the stock depending on the current round
     * @param round current round; stock: left cards to play from the previous round
     */
    private void distributeCards(int round) {
        int totalCardsToDistribute = 4 * getNumberOfCardsToDistribute(round);
        if(deck.getDeckSize() < totalCardsToDistribute) {
            deck.createDeck();
        }

        for(int i=0; i<totalCardsToDistribute; i=i+4) {
            playerAndHand.get(team1.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team1.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer1().getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(team2.getPlayer2().getId()).takeCard(deck.getCardFromDeck());
        }
        UserInstructions.addNewInstruction("Cards distributed to players");
    }
    /**
     * Exchanges selected cards from teammembers.
     * If a player has not selected a card within the time limit, the system picks one randomly.
     */
    private void exchangeCards() {
        if (exchangeCardP1 == null) {
            exchangeCardP1 = pickRandomCard(team1.getPlayer1().getId());
        }
        if (exchangeCardP2 == null) {
            exchangeCardP2 = pickRandomCard(team1.getPlayer2().getId());
        }
        if (exchangeCardP3 == null) {
            exchangeCardP3 = pickRandomCard(team2.getPlayer1().getId());
        }
        if (exchangeCardP4 == null) {
            exchangeCardP4 = pickRandomCard(team2.getPlayer2().getId());
        }
        playerAndHand.get(team1.getPlayer1().getId()).takeCard(exchangeCardP2);
        playerAndHand.get(team1.getPlayer2().getId()).takeCard(exchangeCardP1);
        playerAndHand.get(team2.getPlayer1().getId()).takeCard(exchangeCardP4);
        playerAndHand.get(team2.getPlayer2().getId()).takeCard(exchangeCardP3);
    }

    /**
     * The system automatically chooses a random card from the players hand
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
     * Handles a single round of the game. In case a team wins during a round, the round ends. If no team finishes,
     * the round ends when no player has a card left to play.
     * @return boolean value if a team has won or not.
     */
    public boolean startRound () {
        boolean hasWinner = false;
        while (!hasWinner) {
            // TODO output should later be displayed in browser
            if (playerOutOfCards()) {
                break;
            }
            hasWinner = false;
        }
        return hasWinner;
    }

    /**
     * Evaluates if one of the players still has a card to play.
     * @return
     */
    private boolean playerOutOfCards() {
        boolean allPlayersOutOfCards = false;
        if (    playerAndHand.get(team1.getPlayer1().getId()).isHandEmpty() &&
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

    public static boolean isRoundStarted() {
        return roundStarted;
    }
}
