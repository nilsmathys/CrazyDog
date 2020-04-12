package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Player.Player;

import java.util.*;


public class Round {

    List<Player> players;
    Map<Integer, CardsOnHand> playerAndHand;
    CardDeck deck;
    private int nextPlayer;

    public Round(int roundNumber, CardDeck deck, List<Player> players, int nextPlayer) {
        this.players = players;
        this.nextPlayer = nextPlayer;
        this.deck = deck;

        playerAndHand = new HashMap<>();
        playerAndHand.put(players.get(0).getId(), new CardsOnHand());
        playerAndHand.put(players.get(1).getId(), new CardsOnHand());
        playerAndHand.put(players.get(2).getId(), new CardsOnHand());
        playerAndHand.put(players.get(3).getId(), new CardsOnHand());

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
            //playerAndHand.get(players.get(0).getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(players.get(1).getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(players.get(2).getId()).takeCard(deck.getCardFromDeck());
            playerAndHand.get(players.get(3).getId()).takeCard(deck.getCardFromDeck());
        }

        // only for prototype demo
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(21, "standard", 2));
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(101, "standard", 10));
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(22, "standard", 2));
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(91, "standard", 9));
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(61, "standard", 6));
        playerAndHand.get(players.get(0).getId()).takeCard(new Card(111, "eleven", 11));

        GameState.setAllPlayersAndHand(playerAndHand);
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

}
