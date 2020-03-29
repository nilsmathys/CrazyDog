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
    Team team1 = null;
    Team team2 = null;
    List<Piece> pieceList;
//    List<Card> cardList;
    GameBoard gameBoard;


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
        this.gameBoard = new GameBoard();
        PieceDAO pieceDAO = new PieceDAO();
        this.pieceList = pieceDAO.getAllPieces();
        //CardDAO cardDAO = new CardDAO();
        //this.cardList = cardDAO.getAllCards();
    }

    /**
     * Konstruktor, falls ein Spiel fortgesetzt werden soll.
     * @param gameId
     */
    public CrazyDog(int gameId) {
        loadGame(gameId);
    }

    /**
     * Lädt Spiel anhand der gameId
     * @param gameId int
     */
    public void loadGame(int gameId) {

    }

    /**
     * Speichert das Spiel anhand der GameId.
     * Falls kein Spiel existiert ist die GameId = 0 und es wird ein neues Spiel in die Datenbank gespeichert.
     * @param gameId int
     */
    public void saveGame(int gameId) {
        if(gameId == 0) {
            //saveNewGame
        } else {
            //saveExistGame
        }
    }
}
