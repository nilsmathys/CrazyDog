package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.*;


public class Round {

    Team team1;
    Team team2;
    CardDeck deck;
    private int nextPlayer;
    private static Map<Integer, CardsOnHand> playerAndHand = new HashMap<>();

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
        // TODO: show countdown for players to select a card
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

    }

    /**
     * Exchanges selected cards from teammembers
     * @param idPlayer1 id of teammember 1
     * @param cardPlayer1 selected card from teammember 1
     * @param idPlayer2 id of teammember 2
     * @param cardPlayer2 selected card from teammember 2
     */
    private void exchangeCards(int idPlayer1, Card cardPlayer1, int idPlayer2, Card cardPlayer2) {
        Card cardFromPlayer1 = playerAndHand.get(idPlayer1).discardCard(cardPlayer1.getId());
        Card cardFromPlayer2 = playerAndHand.get(idPlayer2).discardCard(cardPlayer2.getId());
        playerAndHand.get(idPlayer1).takeCard(cardFromPlayer2);
        playerAndHand.get(idPlayer2).takeCard(cardFromPlayer1);
    }

    /**
     * Handles a single round of the game. In case a team wins during a round, the round ends. If no team finishes,
     * the round ends when no player has a card left to play.
     * @return boolean value if a team has won or not.
     */
    public boolean startRound () {
        boolean hasWinner = false;
        while (!hasWinner || !playerOutOfCards()) {
            // TODO output should later be displayed in browser

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
        if (playerAndHand.get(0).isHandEmpty() && playerAndHand.get(1).isHandEmpty()
                && playerAndHand.get(2).isHandEmpty() && playerAndHand.get(3).isHandEmpty()) {
            allPlayersOutOfCards = true;
        }
        return allPlayersOutOfCards;
    }

    public static Map<Integer, CardsOnHand> getPlayerAndHand() {
        return playerAndHand;
    }
}
