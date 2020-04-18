package ch.zhaw.psit3.crazydog;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.CrazyDogDAO;
import ch.zhaw.psit3.crazydog.Model.Game.GameState;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerAndHand;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
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

    public int gameId;
    public Team team1;
    public Team team2;
    public int nextPlayer; //Id des Spielrs der als nächster dran ist.
    public List<Piece> pieceList;
    public CardDeck deck;
    public static GameBoard gameBoard;
    public PlayerAndHand playerAndHand1;
    public PlayerAndHand playerAndHand2;
    public PlayerAndHand playerAndHand3;
    public PlayerAndHand playerAndHand4;


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
        CardsOnHand cardsPlayer1 = new CardsOnHand();
        CardsOnHand cardsPlayer2 = new CardsOnHand();
        CardsOnHand cardsPlayer3 = new CardsOnHand();
        CardsOnHand cardsPlayer4 = new CardsOnHand();
        playerAndHand1 = new PlayerAndHand(player1, cardsPlayer1);
        playerAndHand2 = new PlayerAndHand(player2, cardsPlayer2);
        playerAndHand3 = new PlayerAndHand(player3, cardsPlayer3);
        playerAndHand4 = new PlayerAndHand(player4, cardsPlayer4);
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
    }

    private void playGame(Team team1, Team team2, int nextPlayer) {
        CardDeck deck = new CardDeck();
        deck.createDeck();
        deck.getCardDeck();

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

        Player player1 = PlayerDAO.getPlayerById(1);
        Player player2 = PlayerDAO.getPlayerById(2);;
        Player player3 = PlayerDAO.getPlayerById(3);
        Player player4 = PlayerDAO.getPlayerById(4);

        CrazyDog crazyDog = new CrazyDog(player1, player2, player3, player4);
        crazyDog.playGame(crazyDog.team1, crazyDog.team2, crazyDog.nextPlayer);

//        // Initialize Players
//        List<Player> players = new ArrayList<>();
//        players.add(player1);
//        players.add(player2);
//        players.add(player3);
//        players.add(player4);
//        GameState.putPlayers(players);
//
//        Map<String, String> fieldsAndPieces = new HashMap<>();
//        for(GameField field : gameBoard.getFields())
//        {
//            fieldsAndPieces.put(field.getCssId(),field.getImageName());
//        }
//
//        GameState.putAllFieldsAndPieces(fieldsAndPieces);
//
//        playGame(players);
    }

}

