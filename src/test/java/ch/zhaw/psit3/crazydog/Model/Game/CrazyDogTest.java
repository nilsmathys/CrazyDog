package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrazyDogTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private CrazyDog crazyDog;

    @BeforeEach
    void setup() {
        player1 = PlayerDAO.getPlayerById(1);
        player2 = PlayerDAO.getPlayerById(2);
        player3 = PlayerDAO.getPlayerById(3);
        player4 = PlayerDAO.getPlayerById(4);
        crazyDog = new CrazyDog(player1, player2, player3, player4);
    }

    @Test
    void getTeam1() {
        assertEquals(player1, crazyDog.getTeam1().getPlayer1());
        assertEquals(player2, crazyDog.getTeam1().getPlayer2());
    }

    @Test
    void getTeam2() {
        assertEquals(player3, crazyDog.getTeam2().getPlayer1());
        assertEquals(player4, crazyDog.getTeam2().getPlayer2());
    }

    @Test
    void getNextPlayer() {
        assertEquals(1, crazyDog.getNextPlayer());
    }

    @Test
    void increaseNextPlayer_initial() {
        crazyDog.increaseNextPlayer();
        assertEquals(2,crazyDog.getNextPlayer());
    }

    @Test
    void increaseNextPlayer_player4plusOne() {
        crazyDog.increaseNextPlayer();
        crazyDog.increaseNextPlayer();
        crazyDog.increaseNextPlayer();
        crazyDog.increaseNextPlayer();
        assertEquals(1,crazyDog.getNextPlayer());
    }

    @Test
    void getPlayerColorById() {

        assertEquals("red", crazyDog.getPlayerColorById(1));
        assertEquals("green", crazyDog.getPlayerColorById(2));
        assertEquals("yellow", crazyDog.getPlayerColorById(3));
        assertEquals("blue", crazyDog.getPlayerColorById(4));
    }

    @Test
    void getDirection() {
        assertEquals(Direction.CLOCKWISE, crazyDog.getDirection());
    }

    @Test
    void changeDirection() {
        crazyDog.changeDirection();
        assertEquals(Direction.COUNTERCLOCKWISE, crazyDog.getDirection());
    }

    @Test
    void changeDirection_4Times() {
        crazyDog.changeDirection();
        crazyDog.changeDirection();
        crazyDog.changeDirection();
        crazyDog.changeDirection();
        assertEquals(Direction.CLOCKWISE, crazyDog.getDirection());
    }

    @Test
    void getRoundNumber() {
        assertEquals(1, crazyDog.getRoundNumber());
    }

    @Test
    void setRoundNumber() {
        crazyDog.setRoundNumber(4);
        assertEquals(4, crazyDog.getRoundNumber());
    }

    @Test
    void isInitialized() {
        assertFalse(crazyDog.isInitialized());
    }

    @Test
    void positionFieldsInitial() {
        GameBoard gameBoard = crazyDog.getGameBoard();
        List<GameField> fields = gameBoard.getFieldsWithPieces();
        for(GameField field: fields) {
            assertNotNull(field.getPieceOnField());
            assertEquals("homefield",field.getGameFieldName());
        }

    }

}