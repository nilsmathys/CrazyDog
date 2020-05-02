package ch.zhaw.psit3.crazydog.Model.Piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    Piece piece;

    @BeforeEach
    void setup() {
        piece = new Piece(1, 2, "red", "testpic.png",1);
    }

    @Test
    void getPictureName() {
        assertEquals("testpic.png", piece.getPictureName());
    }

    @Test
    void setPictureName() {
        piece.setPictureName("testpic2.png");
        assertEquals("testpic2.png", piece.getPictureName());
    }

    @Test
    void getId() {
        assertEquals(1, piece.getId());
    }

    @Test
    void setId() {
        piece.setId(2);
        assertEquals(2, piece.getId());
    }

    @Test
    void getNumber() {
        assertEquals(2, piece.getNumber());
    }

    @Test
    void setNumber() {
        piece.setNumber(5);
        assertEquals(5, piece.getNumber());
    }

    @Test
    void getColour() {
        assertEquals("red", piece.getColor());
    }

    @Test
    void setColourId() {
        piece.setColor("blue");
        assertEquals("blue", piece.getColor());
    }

    @Test
    void getHomeFieldId() {
        assertEquals(1, piece.getHomeFieldId());
    }

    @Test
    void setHomeFieldId() {
        piece.setHomeFieldId(2);
        assertEquals(2, piece.getHomeFieldId());
    }

}