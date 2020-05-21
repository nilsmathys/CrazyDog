package ch.zhaw.psit3.crazydog;

import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <h1>CrazyDog</h1>
 * It initiates a new Crazy Dog game and starts a round. As long as no winner is set a new round will be started as soon as the previous one is over.
 *
 * @author R. Bertschinger, N. Mathys, R. Somma, S. Werlin
 * @version 1.0
 * @since March 2020
 */
@SpringBootApplication
public class CrazyDog {
    private static final Logger LOGGER = Logger.getLogger(CrazyDog.class.getName());
    private int gameId;
    private static List<Player> playerList = new ArrayList<Player>();
    private static Team team1;
    private static Team team2;
    private static Team winnerTeam = null;
    private static int nextPlayer; //Id des Spielrs der als nächster dran ist.
    private List<Piece> pieceList;
    private CardDeck deck;
    private static GameBoard gameBoard;
    private static Direction direction = Direction.COUNTERCLOCKWISE;
    private static int roundNumber = 1;

    private static ConfigurableApplicationContext ctx;
    private static boolean isInitialized = false;

    /**
     * Empty constructor to start the website.
     */
    public CrazyDog() {
        gameBoard = new GameBoard();
        pieceList = PieceDAO.getAllPieces();
        positionFieldsInitial();
        deck = new CardDeck();
        deck.createDeck();
    }

    /**
     * Constructor for a new game.
     *
     * @param player1 first player, initiator of the game and team member of second player
     * @param player2 second player and team member of first player
     * @param player3 third player and team member of fourth player
     * @param player4 fourth player and team member of third player
     */
    public CrazyDog(Player player1, Player player2, Player player3, Player player4) {
        this.team1 = new Team(player1, player2, "red", "green");
        this.team2 = new Team(player3, player4, "yellow", "blue");
        player1.setColor("red");
        player2.setColor("green");
        player3.setColor("yellow");
        player4.setColor("blue");
        this.nextPlayer = player1.getId();
        this.winnerTeam = null;
        this.gameBoard = new GameBoard();
        this.pieceList = PieceDAO.getAllPieces();
        positionFieldsInitial();
        this.deck = new CardDeck();
        deck.createDeck();
        //im JSON ist COUNTERCLOCKWISE abgespeichert, deshalb muss die Direction geändert werden
        changeDirection();
    }

    /**
     * Constructor if a game already exits and needs to be continued.
     *
     * @param gameId     the id this game was stored under
     * @param player1    first player and team member of second player
     * @param player2    second player and team member of first player
     * @param player3    third player and team member of fourth player
     * @param player4    fourth player and team member of third player
     * @param nextPlayer player id of the one who's turn it is
     * @param gameBoard  the whole game board at the time the game was paused (including the last place of each piece)
     */
    public CrazyDog(int gameId, Player player1, Player player2, Player player3, Player player4, int nextPlayer, GameBoard gameBoard) {
        this.gameId = gameId;
        this.team1 = new Team(player1, player2, "red", "green");
        this.team2 = new Team(player3, player4, "yellow", "blue");
        player1.setColor("red");
        player2.setColor("green");
        player3.setColor("yellow");
        player4.setColor("blue");
        this.nextPlayer = nextPlayer;
        this.winnerTeam = null;
        this.gameBoard = gameBoard;
        //im JSON ist COUNTERCLOCKWISE abgespeichert, deshalb muss die Direction geändert werden
        changeDirection();
        this.pieceList = PieceDAO.getAllPieces();
        positionFieldsInitial();
    }

    /**
     * Start the round with two teams in a while loop. As long as there is no winner and the round has ended due to
     * the circumstance that no player has a card left to play a new round will be started.
     *
     * @param team1 a team consisting of player 1 and player 2
     * @param team2 a team consisting of player 3 and player 4
     */
    private void playGame(Team team1, Team team2) {
        LOGGER.info("New game started!");
        CardDeck deck = new CardDeck();
        deck.createDeck();
        deck.getCardDeck();

        boolean playEnded = false;

        while (!playEnded) {
            Round round = new Round(roundNumber, deck, team1, team2);
            playEnded = round.startRound();
            roundNumber++;
            nextPlayer = 1; //reset player to player 1
        }
    }

    /**
     * Start springboot's server.
     * @param args unused
     */
    public static void main(String[] args) {
        ctx = SpringApplication.run(CrazyDog.class, args);
    }

    /**
     * Initialize a new Game
     */
    public static void initializeGame() {
        LOGGER.info("New Game initialized");
        Player player1 = PlayerDAO.getPlayerById(1);
        playerList.add(player1);
        Player player2 = PlayerDAO.getPlayerById(2);
        playerList.add(player2);
        Player player3 = PlayerDAO.getPlayerById(3);
        playerList.add(player3);
        Player player4 = PlayerDAO.getPlayerById(4);
        playerList.add(player4);

        CrazyDog crazyDog = new CrazyDog(player1, player2, player3, player4);
        isInitialized = true;
        UserInstructions.addNewInstruction("Spiel startet jetzt");
        crazyDog.playGame(crazyDog.team1, crazyDog.team2);
    }

    /**
     * Position the piece on their home fields at the beginning of the game
     */
    private void positionFieldsInitial() {
        for (Piece piece : pieceList) {
            gameBoard.setPieceOnHomefield(piece.getHomeFieldId(), piece);
        }
    }

    public static Team getTeam1() {
        return team1;
    }

    public static Team getTeam2() {
        return team2;
    }

    public static int getNextPlayer() {
        return nextPlayer;
    }

    /**
     * increase player by one, or start by 1 again
     */
    public static void increaseNextPlayer() {
        if (nextPlayer == 4) {
            nextPlayer = 1;
        } else {
            nextPlayer++;
        }
    }

    public static GameBoard getGameBoard() {
        return gameBoard;
    }
    public static void setWinnerTeam(Team team) { winnerTeam = team; }
    public static Team getWinnerTeam() { return winnerTeam; }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Get a player's color by its id
     *
     * @param id player id
     * @return player's color
     */
    public static String getPlayerColorById(int id) {
        String color = "";
        for(Player player : playerList) {
            if(player.getId() == id) {
                color = player.getColor();
            }
        }
        return color;
    }

    /**
     * Change game direction and renumber all Destination Fields.
     */
    public static void changeDirection() {
        if (direction == Direction.COUNTERCLOCKWISE) {
            direction = Direction.CLOCKWISE;
        } else {
            direction = Direction.COUNTERCLOCKWISE;
        }
        gameBoard.renumberDestinationFields(direction);
    }

    /**
     * get current direction
     */
    public static Direction getDirection() {
        return direction;
    }

    /**
     * set current direction
     */
    public static void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public static int getRoundNumber() {
        return roundNumber;
    }

    public static void setRoundNumber(int roundNumber) {
        CrazyDog.roundNumber = roundNumber;
    }

    public static void kill() {
        ctx.close();
    }

    public static boolean isInitialized() {
        return isInitialized;
    }
}

