package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardOnHand;
import ch.zhaw.psit3.crazydog.Model.Card.CardStock;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.*;


public class Round {

    LinkedHashMap<Integer, CardOnHand> players = new LinkedHashMap<>();
    ArrayList<Player> playerList = new ArrayList<>();

    private int nextPlayer;

    public Round(int roundNumber, CardStock stock, Team t1, Team t2, int nextPlayer) {
        players.put(t1.getPlayer1().getId(), new CardOnHand());
        players.put(t1.getPlayer2().getId(), new CardOnHand());
        players.put(t2.getPlayer1().getId(), new CardOnHand());
        players.put(t2.getPlayer2().getId(), new CardOnHand());
        playerList.add(t1.getPlayer1());
        playerList.add(t1.getPlayer2());
        playerList.add(t2.getPlayer1());
        playerList.add(t2.getPlayer2());
        this.nextPlayer = nextPlayer;

        CardOnHand coh = (CardOnHand) players.get(nextPlayer);

        distributeCards(roundNumber, stock);
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
    private void distributeCards(int round, CardStock stock) {
        int totalCardsToDistribute = 4 * getNumberOfCardsToDistribute(round);
        if(stock.getStockSize() < totalCardsToDistribute) {
            stock.createStock();
        }
        for(int i=0; i<totalCardsToDistribute; i+=4) {
            players.get(playerList.get(0).getId()).addCard(stock.getCardFromStock());
            players.get(playerList.get(1).getId()).addCard(stock.getCardFromStock());
            players.get(playerList.get(2).getId()).addCard(stock.getCardFromStock());
            players.get(playerList.get(3).getId()).addCard(stock.getCardFromStock());
        }
    }

    /**
     * Exchanges the selected card between the teammembers.
     */
    private void changeCardWithTeamplayer() {
        Card selectedCardPlayer1 = players.get(playerList.get(0).getId()).getSelectedCard();
        Card selectedCardPlayer2 = players.get(playerList.get(1).getId()).getSelectedCard();
        Card selectedCardPlayer3 = players.get(playerList.get(2).getId()).getSelectedCard();
        Card selectedCardPlayer4 = players.get(playerList.get(3).getId()).getSelectedCard();

        players.get(playerList.get(0).getId()).removeCard(selectedCardPlayer1);
        players.get(playerList.get(0).getId()).addCard(selectedCardPlayer2);
        players.get(playerList.get(1).getId()).removeCard(selectedCardPlayer2);
        players.get(playerList.get(1).getId()).addCard(selectedCardPlayer1);
        players.get(playerList.get(2).getId()).removeCard(selectedCardPlayer3);
        players.get(playerList.get(2).getId()).addCard(selectedCardPlayer4);
        players.get(playerList.get(3).getId()).removeCard(selectedCardPlayer4);
        players.get(playerList.get(3).getId()).addCard(selectedCardPlayer3);
    }

    /**
     * Handles a single round of the game. In case a team wins during a round, the round ends. If no team finishes,
     * the round ends when no player has a card left to play.
     * @return boolean value if a team has won or not.
     */
    public boolean startRound () {
        boolean hasWinner = false;
        while (!hasWinner || !playerOutOfCards()) {
            showCurrentMoveInformation("TURN", playerList.get(0), null);


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
        if (players.get(playerList.get(0).getId()).isHandEmpty()
                && players.get(playerList.get(1).getId()).isHandEmpty()
                && players.get(playerList.get(2).getId()).isHandEmpty()
                && players.get(playerList.get(3).getId()).isHandEmpty()) {
            allPlayersOutOfCards = true;
        }
        return allPlayersOutOfCards;
    }


    public enum Instruction {
        TURN, CHOOSE, MOVE
    }

    /**
     * Shows player information on current game situation
     * @param instr: instruction word; p: player whose turn it is; c: players chosen card
     */
    private void showCurrentMoveInformation(String instr, Player p, Card c) {
        // TODO browser output of instruction
        Instruction i = Instruction.valueOf(instr);
        switch (i) {
            case TURN: System.out.println(p.getUsername() + " ist am Zug.");
                break;
            case CHOOSE: System.out.println(p.getUsername() + " hat die Karte " + c.getName() + " ausgespielt.");
                break;
            case MOVE: System.out.println(p.getUsername() + " hat die Figur bewegt.");
                break;
        }
    }
}
