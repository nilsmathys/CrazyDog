package ch.zhaw.psit3.crazydog.Model.Piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    Piece piece;

    @BeforeEach
    public void setup() {
        piece = new Piece(1, 2, 3, "testpic.png");
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
    void getColourId() {
        assertEquals(3, piece.getColourId());
    }

    @Test
    void setColourId() {
        piece.setColourId(10);
        assertEquals(10, piece.getColourId());
    }
}