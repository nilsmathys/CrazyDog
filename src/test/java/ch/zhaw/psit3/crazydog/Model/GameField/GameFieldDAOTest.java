package ch.zhaw.psit3.crazydog.Model.GameField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldDAOTest {

    static GameField gamefieldJSON1;
    static GameField gamefieldJSON11;
    static GameField gamefieldJSON29;
    static GameField gamefieldJSON57;
    static GameField gamefieldJSON61;
    static GameField gamefieldJSON65;
    static GameField gamefieldJSON84;
    static GameField gamefieldJSON85;
    static GameField gamefieldJSON86;
    static GameField gamefieldJSON96;


    @BeforeAll
    static void setup() {
        gamefieldJSON1 = new GameField("empty.png", "field1", "startfield", "red",5);
        gamefieldJSON11 = new GameField("empty.png", "field11", "standard", "white",15);
        gamefieldJSON29 = new GameField("empty.png", "field29", "standard", "white",33);
        gamefieldJSON57 = new GameField("empty.png", "field57", "wormhole", "black",61);
        gamefieldJSON61 = new GameField("empty.png", "field61", "standard", "white",1);
        gamefieldJSON65 = new GameField("piece4red.png", "field65", "homefield", "red",1);
        gamefieldJSON84 = new GameField("empty.png", "field84", "destinationfield", "green",1);
        gamefieldJSON85 = new GameField("empty.png", "field85", "destinationfield", "blue",20);
        gamefieldJSON86 = new GameField("empty.png", "field86", "destinationfield", "blue",19);
        gamefieldJSON96 = new GameField("empty.png", "field96", "destinationfield", "yellow",49);
    }

    @Test
    void testGetFieldsFromJSON() {
        List<GameField> fields = GameFieldDAO.getFieldsFromJSON();
        assertTrue(gamefieldJSON1.equals(fields.get(0)));
        assertTrue(gamefieldJSON11.equals(fields.get(10)));
        assertTrue(gamefieldJSON29.equals(fields.get(28)));
        assertTrue(gamefieldJSON57.equals(fields.get(56)));
        assertTrue(gamefieldJSON61.equals(fields.get(60)));
        assertTrue(gamefieldJSON65.equals(fields.get(64)));
        assertTrue(gamefieldJSON84.equals(fields.get(83)));
        assertTrue(gamefieldJSON85.equals(fields.get(84)));
        assertTrue(gamefieldJSON86.equals(fields.get(85)));
        assertTrue(gamefieldJSON96.equals(fields.get(95)));
    }

    @Test
    void testGetFieldsFromJSONSize() {
        List<GameField> fields = GameFieldDAO.getFieldsFromJSON();
        assertEquals(96, fields.size());
    }

}