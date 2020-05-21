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
        assertEquals(gamefieldJSON1, fields.get(0));
        assertEquals(gamefieldJSON11, fields.get(10));
        assertEquals(gamefieldJSON29, fields.get(28));
        assertEquals(gamefieldJSON57, fields.get(56));
        assertEquals(gamefieldJSON61, fields.get(60));
        assertEquals(gamefieldJSON65, fields.get(64));
        assertEquals(gamefieldJSON84, fields.get(83));
        assertEquals(gamefieldJSON85, fields.get(84));
        assertEquals(gamefieldJSON86, fields.get(85));
        assertEquals(gamefieldJSON96, fields.get(95));
    }

    @Test
    void testGetFieldsFromJSONSize() {
        List<GameField> fields = GameFieldDAO.getFieldsFromJSON();
        assertEquals(96, fields.size());
    }

}