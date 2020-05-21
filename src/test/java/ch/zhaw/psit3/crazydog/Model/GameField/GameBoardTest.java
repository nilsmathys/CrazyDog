package ch.zhaw.psit3.crazydog.Model.GameField;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.zhaw.psit3.crazydog.Model.Game.Direction.CLOCKWISE;
import static ch.zhaw.psit3.crazydog.Model.Game.Direction.COUNTERCLOCKWISE;
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
        gamefieldDbLocal1 = new GameField("empty.png", "field1", "startfield", "red",5);
        gamefieldDbLocal11 = new GameField("piece1red.png", "field11", "standard", "white",15);
        gamefieldDbLocal29 = new GameField("piece3yellow.png", "field29", "standard", "white",33);
        gamefieldDbLocal57 = new GameField("empty.png", "field90", "destinationfield", "black",61);
        gamefieldDbLocal61 = new GameField("empty.png", "field61", "standard", "white",1);
        gamefieldDbLocal65 = new GameField("empty.png", "field65", "homefield", "red",1);
        gamefieldDbLocal84 = new GameField("empty.png", "field84", "destinationfield", "green",1);
        gamefieldDbLocal85 = new GameField("empty.png", "field85", "destinationfield", "blue",20);
        gamefieldDbLocal86 = new GameField("empty.png", "field86", "destinationfield", "white",19);
        gamefieldDbLocal96 = new GameField("empty.png", "field96", "destinationfield", "yellow",49);
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
    void getGameFieldsWithPiecesOfPlayersColor_red() {
        Piece redpiece1 = new Piece(1, 1, "red", "piece1red.png",77);
        Piece yellowpiece1 = new Piece(2, 1, "yellow", "piece1yellow.png",78);
        GameField field18 = gameBoard.getFields().get(18);
        GameField field32 = gameBoard.getFields().get(32);
        field18.setPieceOnField(redpiece1);
        field32.setPieceOnField(yellowpiece1);
        List<GameField> fields = gameBoard.getGameFieldsWithPiecesOfPlayersColor("red");
        assertFalse(fields.contains(field32));
    }

    @Test
    void getGameFieldsWithPiecesOfPlayersColor_blue() {
        Piece bluepiece1 = new Piece(1, 1, "blue", "piece1blue.png",77);
        Piece bluepiece2 = new Piece(2, 2, "blue", "piece2blue.png",78);
        GameField field20 = gameBoard.getFields().get(20);
        GameField field35 = gameBoard.getFields().get(35);
        field20.setPieceOnField(bluepiece1);
        field35.setPieceOnField(bluepiece2);
        List<GameField> fields = gameBoard.getGameFieldsWithPiecesOfPlayersColor("blue");
        assertTrue(fields.contains(field35));
    }

    @Test
    void getGameFieldByCalculationId() {
        GameField field20 = gameBoard.getFields().get(39);
        assertEquals(field20, gameBoard.getGameFieldByCalculationId(24, "standard"));
    }

    @Test
    void getGameFieldByCSSId() {
        GameField field21 = gameBoard.getFields().get(40);
        assertEquals(field21, gameBoard.getGameFieldByCSSId("field21"));
    }

    // *** HOMEFIELD ***
    @Test
    void setPieceOnHomefield() {
        assertNull(gameBoard.getGameFieldByCalculationId(36, "homefield").getPieceOnField()); //home of piece1green
        Piece piece1green = new Piece(1, 1, "green", "piece1green.png",36);
        gameBoard.setPieceOnHomefield(36,piece1green);
        assertEquals(piece1green, gameBoard.getGameFieldByCalculationId(36, "homefield").getPieceOnField());
    }

    // *** DESTINATIONFIELD ***

    @Test
    void renumberDestinationFields_clockwise() {
        GameBoard board = new GameBoard();
        board.renumberDestinationFields(CLOCKWISE);
        assertEquals(57, board.getGameFieldByCSSId("field96").getIdForCalculation()); // yellow1 dest
    }

    @Test
    void renumberDestinationFields_counterclockwise() {
        GameBoard board = new GameBoard();
        board.renumberDestinationFields(COUNTERCLOCKWISE);
        assertEquals(49, board.getGameFieldByCSSId("field96").getIdForCalculation());
    }

    @Test
    void getListOfDestinationFieldsByColorColorRed() {
        List<GameField> fields = gameBoard.getListOfDestinationFieldsByColor("red");
        assertEquals("red", fields.get(0).getColor());
        assertEquals("red", fields.get(1).getColor());
        assertEquals("red", fields.get(2).getColor());
        assertEquals("red", fields.get(3).getColor());
        assertNotEquals("yellow", fields.get(1).getColor());
    }

    @Test
    void getMapOfDestinationFieldsByColor_red() {
        Map<Integer, String> destFieldMap = new HashMap<>();
        destFieldMap.put(1, "field92");
        destFieldMap.put(2, "field91");
        destFieldMap.put(3, "field90");
        destFieldMap.put(4, "field89");
        assertEquals(destFieldMap, gameBoard.getMapOfDestinationFieldsByColor("red"));
    }

    @Test
    void getMapOfDestinationFieldsByColor_blue() {
        Map<Integer, String> destFieldMap = new HashMap<>();
        destFieldMap.put(1, "field88");
        destFieldMap.put(2, "field87");
        destFieldMap.put(3, "field86");
        destFieldMap.put(4, "field85");
        assertEquals(destFieldMap, gameBoard.getMapOfDestinationFieldsByColor("blue"));
    }

    @Test
    void getMapOfDestinationFieldsByColor_yellow() {
        Map<Integer, String> destFieldMap = new HashMap<>();
        destFieldMap.put(1, "field96");
        destFieldMap.put(2, "field95");
        destFieldMap.put(3, "field94");
        destFieldMap.put(4, "field93");
        assertEquals(destFieldMap, gameBoard.getMapOfDestinationFieldsByColor("yellow"));
    }

    @Test
    void getMapOfDestinationFieldsByColor_green() {
        Map<Integer, String> destFieldMap = new HashMap<>();
        destFieldMap.put(1, "field84");
        destFieldMap.put(2, "field83");
        destFieldMap.put(3, "field82");
        destFieldMap.put(4, "field81");
        assertEquals(destFieldMap, gameBoard.getMapOfDestinationFieldsByColor("green"));
    }

    @Test
    void getStandardStartfieldGameFieldOrWormholeByIdForCalculation_standard() {
        assertEquals("standard", gameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(6).getGameFieldName());
    }

    @Test
    void getStandardStartfieldGameFieldOrWormholeByIdForCalculation_startfield() {
        assertEquals("startfield", gameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(21).getGameFieldName());
    }

    @Test
    void getStandardStartfieldGameFieldOrWormholeByIdForCalculation_wormhole() {
        assertEquals("wormhole", gameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(29).getGameFieldName());
    }

    @Test
    void getStandardStartfieldGameFieldOrWormholeByIdForCalculation_destinationfield() {
        assertNotEquals("destinationfield", gameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(4));
    }

    @Test
    void getStandardStartfieldGameFieldOrWormholeByIdForCalculation_homefield() {
        assertNotEquals("homefield", gameBoard.getStandardStartfieldGameFieldOrWormholeByIdForCalculation(4));
    }

    // *** PIECES ***
    @Test
    void getPlacesOfPiecesByColor_blue() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "field80");
        map.put(2, "field79");
        map.put(3, "field78");
        map.put(4, "field77");
        Map<Integer, String> fields = gameBoard.getPlacesOfPiecesByColor("blue");
        assertEquals(map, fields);
    }

    @Test
    void getFieldsWithPieces() {
        Piece redpiece1 = new Piece(1, 1, "red", "piece1red.png",68);
        Piece yellowpiece1 = new Piece(2, 1, "yellow", "piece1yellow.png",72);
        Piece greenpiece1 = new Piece(3, 1, "green", "piece1green.png",76);
        Piece bluepiece1 = new Piece(4, 1, "blue", "piece1blue.png",80);
        GameField field7 = gameBoard.getFields().get(18);
        GameField field23 = gameBoard.getFields().get(42);
        GameField field24 = gameBoard.getFields().get(43);
        GameField field26 = gameBoard.getFields().get(45);
        field7.setPieceOnField(redpiece1);
        field23.setPieceOnField(yellowpiece1);
        field24.setPieceOnField(greenpiece1);
        field26.setPieceOnField(bluepiece1);
        List<GameField> fields = gameBoard.getFieldsWithPieces();
        assertTrue(fields.contains(field7));
        assertTrue(fields.contains(field23));
        assertTrue(fields.contains(field24));
        assertTrue(fields.contains(field26));
    }

}