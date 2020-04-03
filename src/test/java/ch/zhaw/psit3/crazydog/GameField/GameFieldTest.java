package ch.zhaw.psit3.crazydog.GameField;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class GameFieldTest {
    GameField field;

    @BeforeEach
    public void setUp() {
        field = new GameField("empty.png","field2","standard","white");
    }

    @Test
    public void testGetImageName() {
        assertEquals(field.getImageName(),"empty.png");
    }

    @Test
    public void testGetColor() {
        assertEquals(field.getColor(),"white");
    }

    @Test
    public void testGetCssId() {
        assertEquals(field.getCssId(),"field2");
    }

    @Test
    public void testGetGameFieldName() {
        assertEquals(field.getGameFieldName(),"standard");
    }

    @Test
    public void testSetGameFieldName() {
        field.setGameFieldName("wormhole");
        assertEquals(field.getGameFieldName(),"wormhole");
    }

    @Test
    public void testSetGameFieldNameWrongName() {
        assertThrows(IllegalArgumentException.class, () -> field.setGameFieldName("notexisting"));
    }

    @Test
    public void testSetImageName() {
        field.setImageName("piece3yellow.png");
        assertEquals(field.getImageName(),"piece3yellow.png");
    }

    @Test
    public void testSetColor() {
        field.setColor("yellow");
        assertEquals(field.getColor(),"yellow");
    }

    @Test
    public void testSetColorWrongColor() {
        assertThrows(IllegalArgumentException.class, () -> field.setGameFieldName("color"));
    }

    @Test
    public void testSetCssId() {
        field.setCssId("field70");
        assertEquals(field.getCssId(),"field70");
    }


}
