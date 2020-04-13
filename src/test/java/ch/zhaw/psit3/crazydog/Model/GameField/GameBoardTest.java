package ch.zhaw.psit3.crazydog.Model.GameField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    static GameBoard gameBoard;
    static GameField gamefieldDbLocal1;
    static GameField gamefieldDbLocal11;
    static GameField gamefieldDbLocal29;
    static GameField gamefieldDbLocal57;
    static GameField gamefieldDbLocal61;
    static GameField gamefieldDbLocal65;
    static GameField gamefieldDbLocal84;
    static GameField gamefieldDbLocal85;
    static GameField gamefieldDbLocal86;
    static GameField gamefieldDbLocal96;
    static GameField newGamefield;

    @BeforeEach
    void setup() {
        gameBoard = new GameBoard();
    }

    @BeforeAll
    static void beforeAll() {
        gamefieldDbLocal1 = new GameField("empty.png", "field1", "startfield", "red");
        gamefieldDbLocal11 = new GameField("empty.png", "field11", "standard", "white");
        gamefieldDbLocal29 = new GameField("empty.png", "field29", "standard", "white");
        gamefieldDbLocal57 = new GameField("empty.png", "field57", "wormhole", "black");
        gamefieldDbLocal61 = new GameField("empty.png", "field61", "standard", "white");
        gamefieldDbLocal65 = new GameField("piece4red.png", "field65", "homefield", "red");
        gamefieldDbLocal84 = new GameField("empty.png", "field84", "destinationfield", "green");
        gamefieldDbLocal85 = new GameField("empty.png", "field85", "destinationfield", "blue");
        gamefieldDbLocal86 = new GameField("empty.png", "field86", "destinationfield", "blue");
        gamefieldDbLocal96 = new GameField("empty.png", "field96", "destinationfield", "yellow");
        newGamefield = new GameField("piece4red.img", "field11", "standard", "white");

    }

    @Test
    void changePictureOnField() {
        gameBoard.changePictureOnField(10, "piece4red.img");
        assertEquals(newGamefield.getImageName(), gameBoard.getSpecificField(10).getImageName());
    }

    @Test
    void getFields() {
        List<GameField> fields = gameBoard.getFields();
        assertEquals(gamefieldDbLocal11.getImageName(), fields.get(10).getImageName());
        assertEquals(gamefieldDbLocal29.getImageName(), fields.get(28).getImageName());
        assertEquals(gamefieldDbLocal57.getImageName(), fields.get(56).getImageName());
        assertEquals(gamefieldDbLocal57.getCssId(), fields.get(56).getCssId());
        assertEquals(gamefieldDbLocal57.getGameFieldName(), fields.get(56).getGameFieldName());
        assertEquals(gamefieldDbLocal61.getImageName(), fields.get(60).getImageName());
        assertEquals(gamefieldDbLocal65.getImageName(), fields.get(64).getImageName());
        assertEquals(gamefieldDbLocal84.getImageName(), fields.get(83).getImageName());
        assertEquals(gamefieldDbLocal85.getImageName(), fields.get(84).getImageName());
        assertEquals(gamefieldDbLocal86.getImageName(), fields.get(85).getImageName());
        assertEquals(gamefieldDbLocal86.getColor(), fields.get(85).getColor());
        assertEquals(gamefieldDbLocal96.getImageName(), fields.get(95).getImageName());

    }

    @Test
    void getSpecificField() {
        assertEquals(gamefieldDbLocal11.getImageName(), gameBoard.getSpecificField(10).getImageName());
        assertEquals(gamefieldDbLocal29.getImageName(), gameBoard.getSpecificField(28).getImageName());
        assertEquals(gamefieldDbLocal57.getImageName(), gameBoard.getSpecificField(56).getImageName());
        assertEquals(gamefieldDbLocal57.getCssId(), gameBoard.getSpecificField(56).getCssId());
        assertEquals(gamefieldDbLocal57.getGameFieldName(), gameBoard.getSpecificField(56).getGameFieldName());
        assertEquals(gamefieldDbLocal61.getImageName(), gameBoard.getSpecificField(60).getImageName());
        assertEquals(gamefieldDbLocal65.getImageName(), gameBoard.getSpecificField(64).getImageName());
        assertEquals(gamefieldDbLocal84.getImageName(), gameBoard.getSpecificField(83).getImageName());
        assertEquals(gamefieldDbLocal85.getImageName(), gameBoard.getSpecificField(84).getImageName());
        assertEquals(gamefieldDbLocal86.getImageName(), gameBoard.getSpecificField(85).getImageName());
        assertEquals(gamefieldDbLocal86.getColor(), gameBoard.getSpecificField(85).getColor());
        assertEquals(gamefieldDbLocal96.getImageName(), gameBoard.getSpecificField(95).getImageName());
    }

    @Test
    void testExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.changePictureOnField(-1, "test1");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.changePictureOnField(gameBoard.getFields().size() + 1, "test1");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.getSpecificField(-1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.getSpecificField(gameBoard.getFields().size() + 1);
        });
    }

}