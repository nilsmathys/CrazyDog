package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;

import java.util.List;

public class CrazyDog {
    static final int colourIdRed = 3;
    static final int colourIdGreen = 4;
    static final int colourIdYellow = 5;
    static final int colourIdBlue = 6;

    private int gameId = 0;
    private Team team1 = null;
    private Team team2 = null;
    private int nextPlayer; //Id des Spielrs der als nächster dran ist.
    private List<Piece> pieceList;
//    List<Card> cardList;
    private GameBoard gameBoard;


    /**
     * Konstruktor falls neues Spiel
     * @param player1 erster Spieler, Initialisator und Partner des zweiten Spielers.
     * @param player2 zweiter Spieler und Partner des ersten Spielers.
     * @param player3 dritter Spieler und Partner der vierten Spielers.
     * @param player4 vierter Spieler und Partner des dritten Spielers.
     */
    public CrazyDog(Player player1, Player player2, Player player3, Player player4) {
        this.team1 = new Team(player1, player2, colourIdRed, colourIdGreen);
        this.team2 = new Team(player3, player4, colourIdYellow, colourIdBlue);
        this.nextPlayer = player1.getId();
        this.gameBoard = new GameBoard();
        this.pieceList = PieceDAO.getAllPieces();
        //this.cardList = CardDAO.getAllCards();
    }

    /**
     * Konstrutor falls bereits ein Spiel besteht.
     */
    public CrazyDog(int gameId, Player player1, Player player2, Player player3, Player player4, int nextPlayer, GameBoard gameBoard) {
        this.gameId = gameId;
        this.team1 = new Team(player1, player2, colourIdRed, colourIdGreen);
        this.team2 = new Team(player3, player4, colourIdYellow, colourIdBlue);
        this.nextPlayer = nextPlayer;
        this.gameBoard = gameBoard;
        this.pieceList = PieceDAO.getAllPieces();
        //this.cardList = CardDAO.getAllCards();

    }

}
