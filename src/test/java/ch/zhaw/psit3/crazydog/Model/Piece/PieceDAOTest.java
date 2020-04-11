package ch.zhaw.psit3.crazydog.Model.Piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PieceDAOTest {
    Piece piece1;
    Piece piece2;
    Piece piece3;
    Piece piece4;
    Piece piece5;
    Piece piece6;
    Piece piece7;
    Piece piece8;
    Piece piece9;
    Piece piece10;
    Piece piece11;
    Piece piece12;
    Piece piece13;
    Piece piece14;
    Piece piece15;
    Piece piece16;

    @BeforeEach
    void setup() {
        piece1 = new Piece(1, 1, 3, "piece1red.png");
        piece2 = new Piece(2, 2, 3, "piece2red.png");
        piece3 = new Piece(3, 3, 3, "piece3red.png");
        piece4 = new Piece(4, 4, 3, "piece4red.png");
        piece5 = new Piece(5, 1, 4, "piece1green.png");
        piece6 = new Piece(6, 2, 4, "piece2green.png");
        piece7 = new Piece(7, 3, 4, "piece3green.png");
        piece8 = new Piece(8, 4, 4, "piece4green.png");
        piece9 = new Piece(9, 1, 5, "piece1yellow.png");
        piece10 = new Piece(10, 2, 5, "piece2yellow.png");
        piece11 = new Piece(11, 3, 5, "piece3yellow.png");
        piece12 = new Piece(12, 4, 5, "piece4yellow.png");
        piece13 = new Piece(13, 1, 6, "piece1blue.png");
        piece14 = new Piece(14, 2, 6, "piece2blue.png");
        piece15 = new Piece(15, 3, 6, "piece3blue.png");
        piece16 = new Piece(16, 4, 6, "piece4blue.png");
    }

    @Test
    void getPieceById() {
        Piece dbPiece = PieceDAO.getPieceById(1);
        assertEquals(piece1.getId(), dbPiece.getId());
        assertEquals(piece1.getNumber(), dbPiece.getNumber());
        assertEquals(piece1.getColourId(), dbPiece.getColourId());
        assertEquals(piece1.getPictureName(), dbPiece.getPictureName());
    }

    @Test
    void getAllPieces() {
        List<Piece> dbPieces = PieceDAO.getAllPieces();
        assertEquals(piece1.getId(), dbPieces.get(0).getId());
        assertEquals(piece2.getId(), dbPieces.get(1).getId());
        assertEquals(piece3.getId(), dbPieces.get(2).getId());
        assertEquals(piece4.getId(), dbPieces.get(3).getId());
        assertEquals(piece5.getId(), dbPieces.get(4).getId());
        assertEquals(piece6.getId(), dbPieces.get(5).getId());
        assertEquals(piece7.getId(), dbPieces.get(6).getId());
        assertEquals(piece8.getId(), dbPieces.get(7).getId());
        assertEquals(piece9.getId(), dbPieces.get(8).getId());
        assertEquals(piece10.getId(), dbPieces.get(9).getId());
        assertEquals(piece11.getId(), dbPieces.get(10).getId());
        assertEquals(piece12.getId(), dbPieces.get(11).getId());
        assertEquals(piece13.getId(), dbPieces.get(12).getId());
        assertEquals(piece14.getId(), dbPieces.get(13).getId());
        assertEquals(piece15.getId(), dbPieces.get(14).getId());
        assertEquals(piece16.getId(), dbPieces.get(15).getId());
    }

    @Test
    void getColourIdFromPeace() {
        assertEquals(3, PieceDAO.getColourId(1));
    }

    @Test
    void getColourFromPiece() {
        assertEquals("red", PieceDAO.getColourFromPiece(1));
    }

    @Test
    void getNumberOfPiece() {
        assertEquals(1, PieceDAO.getNumberOfPiece(1));
    }

    @Test
    void getPictureName() {
        assertEquals("piece1red.png", PieceDAO.getPictureName(1));
    }
}