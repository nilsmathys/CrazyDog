package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.*;


public class Round {

    LinkedHashMap<Integer, CardsOnHand> cardsOnHandMap = new LinkedHashMap<>();
    ArrayList<Player> playerList = new ArrayList<>();

    private Player nextPlayer;

    public Round(int roundNumber, CardDeck deck, Team t1, Team t2, int nextPlayer) {
        cardsOnHandMap.put(t1.getPlayer1().getId(), new CardsOnHand());
        cardsOnHandMap.put(t1.getPlayer2().getId(), new CardsOnHand());
        cardsOnHandMap.put(t2.getPlayer1().getId(), new CardsOnHand());
        cardsOnHandMap.put(t2.getPlayer2().getId(), new CardsOnHand());
        playerList.add(t1.getPlayer1());
        playerList.add(t1.getPlayer2());
        playerList.add(t2.getPlayer1());
        playerList.add(t2.getPlayer2());
        this.nextPlayer = playerList.get(nextPlayer);

        distributeCards(roundNumber, deck);
        // TODO: show countdown for players to select a card
        changeCardWithTeamplayer();
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
    private void distributeCards(int round, CardDeck deck) {
        int totalCardsToDistribute = 4 * getNumberOfCardsToDistribute(round);
        if(deck.getDeckSize() < totalCardsToDistribute) {
            deck.createDeck();
        }
        for(int i=0; i<totalCardsToDistribute; i+=4) {
            cardsOnHandMap.get(playerList.get(0).getId()).takeCard(deck.getCardFromDeck());
            cardsOnHandMap.get(playerList.get(1).getId()).takeCard(deck.getCardFromDeck());
            cardsOnHandMap.get(playerList.get(2).getId()).takeCard(deck.getCardFromDeck());
            cardsOnHandMap.get(playerList.get(3).getId()).takeCard(deck.getCardFromDeck());
        }
    }

    /**
     * Exchanges the selected card between the teammembers.
     */
    private void changeCardWithTeamplayer() {
        Card selectedCardPlayer1 = cardsOnHandMap.get(playerList.get(0).getId()).discardCard();
        Card selectedCardPlayer2 = cardsOnHandMap.get(playerList.get(1).getId()).discardCard();
        Card selectedCardPlayer3 = cardsOnHandMap.get(playerList.get(2).getId()).discardCard();
        Card selectedCardPlayer4 = cardsOnHandMap.get(playerList.get(3).getId()).discardCard();

        cardsOnHandMap.get(playerList.get(0).getId()).takeCard(selectedCardPlayer2);
        cardsOnHandMap.get(playerList.get(1).getId()).takeCard(selectedCardPlayer1);
        cardsOnHandMap.get(playerList.get(2).getId()).takeCard(selectedCardPlayer4);
        cardsOnHandMap.get(playerList.get(3).getId()).takeCard(selectedCardPlayer3);
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
            System.out.println(nextPlayer.getUsername() + " ist am Zug.");
            cardsOnHandMap.get(nextPlayer.getId()).playCard(nextPlayer.getUsername());

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
        if (cardsOnHandMap.get(playerList.get(0).getId()).isHandEmpty()
                && cardsOnHandMap.get(playerList.get(1).getId()).isHandEmpty()
                && cardsOnHandMap.get(playerList.get(2).getId()).isHandEmpty()
                && cardsOnHandMap.get(playerList.get(3).getId()).isHandEmpty()) {
            allPlayersOutOfCards = true;
        }
        return allPlayersOutOfCards;
    }

}
