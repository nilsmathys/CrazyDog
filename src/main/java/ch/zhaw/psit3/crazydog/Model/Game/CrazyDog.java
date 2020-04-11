package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazydogApplication;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
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
    private CardDeck deck;
    private static GameBoard gameBoard;

    /**
     * leerer Konstruktur um Webseite starten zu können von dieser Klasse aus.
     */
    public CrazyDog()
    {
        gameBoard = new GameBoard();
        pieceList = PieceDAO.getAllPieces();
        deck = new CardDeck();
        deck.createDeck();
    }

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
        this.deck = new CardDeck();
        deck.createDeck();
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

    public void playGame() {
        boolean playEnded = false;
        int roundNumber = 1;
        while (!playEnded) {
            Round round = new Round(roundNumber, deck, team1, team2, nextPlayer);
            playEnded = round.startRound();
            roundNumber++;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CrazyDog.class, args);

        // Initialize Players
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Heidi", "heidi@test.com", "red"));
        players.add(new Player(2, "Johannes", "johannes@test.com", "yellow"));
        players.add(new Player(3, "Isabella", "isabella@test.com", "green"));
        players.add(new Player(4, "Peter", "peter@test.com", "blue"));
        GameState.putPlayers(players);

        Map<String, String> fieldsAndPieces = new HashMap<>();
        for(GameField field : gameBoard.getFields())
        {
            fieldsAndPieces.put(field.getCssId(),field.getImageName());
        }

        GameState.putAllFieldsAndPieces(fieldsAndPieces);
    }

}
