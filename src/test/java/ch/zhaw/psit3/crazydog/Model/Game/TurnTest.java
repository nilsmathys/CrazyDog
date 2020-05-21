package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {

    @BeforeAll
    static void beforeAll() {
        Player player1 = new Player(1, "remo", "bertsrem@students.zhaw.ch");
        player1.setColor("red");
        CrazyDog.addToPLayerList(player1);
        Player player2 = new Player(2, "riccardo", "sommaric@students.zhaw.ch");
        player1.setColor("green");
        CrazyDog.addToPLayerList(player2);
        Player player3 = new Player(3, "werliste", "werliste@students.zhaw.ch");
        player1.setColor("yellow");
        CrazyDog.addToPLayerList(player3);
        Player player4 = new Player(4, "mathynil", "mathynil@students.zhaw.ch");
        player1.setColor("blue");
        CrazyDog.addToPLayerList(player4);
        CrazyDog crazyDog = new CrazyDog(player1, player2, player3, player4);
    }

    @Test
    void calculateNormalFields() {
        Turn.calculateMoves(5, 1);
        assertEquals(true, Turn.getMoves().isEmpty());

        Turn.calculateMoves(13, 1);
        assertEquals(1, Turn.getMoves().size());

        Turn.calculateMoves(11, 1);
        assertEquals(1, Turn.getMoves().size());
    }

    @Test
    void checkIfOpponentPieceOnField() {
        // Test without an enemy piece on the field. Should return false
        GameField gamefield = new GameField("empty.png", "field4", "standard", "white", 6);
        assertEquals(false, Turn.checkIfOpponentPieceOnField(gamefield, "red"));

        // Test with an enemy piece on the destination field. Should return true.
        gamefield.setPieceOnField(new Piece(1, 1, "green", "piece1green.png", 3));
        assertEquals(true, Turn.checkIfOpponentPieceOnField(gamefield, "red"));
    }

    @Test
    void addToSourcesAndDestinations() {
        // Test without an enemy piece on the field. Should return false
        GameField sourceField = new GameField("empty.png", "field12", "standard", "white", 12);
        GameField destinationField = new GameField("empty.png", "field23", "standard", "white", 23);

        // Test with an enemy piece on the destination field. Should return true.
        //gamefield.setPieceOnField(new Piece(1, 1, "green", "piece1green.png", 3));
        //assertEquals(true, Turn.checkIfOpponentPieceOnField(gamefield, "red"));
    }
}
