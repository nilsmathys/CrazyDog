package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Card.CardStock;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.List;

public class CrazyDog {
    static final int COLOURIDRED = 3;
    static final int COLOURIDGREEN = 4;
    static final int COLOURIDYELLOW = 5;
    static final int COLOURIDBLUE = 6;

    private int gameId = 0;
    private static Team team1 = null;
    private static Team team2 = null;
    private int nextPlayer; //Id des Spielrs der als nächster dran ist.
    private List<Piece> pieceList;
    private CardStock stock;
    private GameBoard gameBoard;
    private int roundNumber = 1;

    /**
     * Konstruktor falls neues Spiel
     * @param player1 erster Spieler, Initialisator und Partner des zweiten Spielers.
     * @param player2 zweiter Spieler und Partner des ersten Spielers.
     * @param player3 dritter Spieler und Partner der vierten Spielers.
     * @param player4 vierter Spieler und Partner des dritten Spielers.
     */
    public CrazyDog(Player player1, Player player2, Player player3, Player player4) {
        this.team1 = new Team(player1, player2, COLOURIDRED, COLOURIDGREEN);
        this.team2 = new Team(player3, player4, COLOURIDYELLOW, COLOURIDBLUE);
        this.nextPlayer = player1.getId();
        this.gameBoard = new GameBoard();
        this.pieceList = PieceDAO.getAllPieces();
        this.stock = new CardStock();
        stock.createStock();
    }

    /**
     * Konstrutor falls bereits ein Spiel besteht.
     */
    public CrazyDog(int gameId, Player player1, Player player2, Player player3, Player player4, int nextPlayer, GameBoard gameBoard) {
        this.gameId = gameId;
        this.team1 = new Team(player1, player2, COLOURIDRED, COLOURIDGREEN);
        this.team2 = new Team(player3, player4, COLOURIDYELLOW, COLOURIDBLUE);
        this.nextPlayer = nextPlayer;
        this.gameBoard = gameBoard;
        this.pieceList = PieceDAO.getAllPieces();
        //this.cardList = CardDAO.getAllCards();

    }

    public void play() {
        boolean playEnded = false;
        while (!playEnded) {
            Round round = new Round(roundNumber, stock, team1, team2, nextPlayer);
            playEnded = round.startRound();
            roundNumber++;
        }
    }


}
