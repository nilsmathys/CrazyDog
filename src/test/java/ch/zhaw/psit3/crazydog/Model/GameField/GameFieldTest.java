package ch.zhaw.psit3.crazydog.Model.GameField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameFieldTest {
    GameField gameField;

    @BeforeEach
    void setup() {
        gameField = new GameField("imgName", "cssId", "standard", "white");
        ;
    }

    @Test
    void getGameFieldName() {
        assertEquals("standard", gameField.getGameFieldName());
    }

    @Test
    void getImageName() {
        assertEquals("imgName", gameField.getImageName());
    }

    @Test
    void getColor() {
        assertEquals("white", gameField.getColor());
    }

    @Test
    void getCssId() {
        assertEquals("cssId", gameField.getCssId());
    }

    @Test
    void setGameFieldName() {
        gameField.setGameFieldName("homefield");
        assertEquals("homefield", gameField.getGameFieldName());
    }

    @Test
    void ThrowExceptionSettingGamefieldName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameField.setGameFieldName("FalseName");
        });
    }

    @Test
    void setImageName() {
        gameField.setImageName("imgNameNew");
        assertEquals("imgNameNew", gameField.getImageName());
    }

    @Test
    void setColor() {
        gameField.setColor("black");
        assertEquals("black", gameField.getColor());
    }

    @Test
    void ThrowExceptionSettinColor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameField.setColor("FalseColor");
        });
    }

    @Test
    void setCssId() {
        gameField.setCssId("cssIdNew");
        assertEquals("cssIdNew", gameField.getCssId());
    }
}