package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {

    private Move move;
    private GameField src;
    private GameField dst;

    @BeforeEach
    void setup() {
        src = new GameField("empty.png", "field1", "startfield", "red",5);
        dst = new GameField("piece1red.png", "field11", "standard", "white",15);
        move = new Move(src, dst);
    }

    @Test
    void testGetSourceField() {
        assertTrue(src.equals(move.getSourceField()));
    }

    @Test
    void testGetDestinationField() {
        assertTrue(dst.equals(move.getDestinationField()));
    }
}
