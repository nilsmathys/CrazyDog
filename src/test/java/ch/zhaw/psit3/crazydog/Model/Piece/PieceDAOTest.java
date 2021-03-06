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
        piece1 = new Piece(1, 1, "red", "piece1red.png",4);
        piece2 = new Piece(2, 2, "red", "piece2red.png",3);
        piece3 = new Piece(3, 3, "red", "piece3red.png",2);
        piece4 = new Piece(4, 4, "red", "piece4red.png",1);
        piece5 = new Piece(5, 1, "green", "piece1green.png",20);
        piece6 = new Piece(6, 2, "green", "piece2green.png",19);
        piece7 = new Piece(7, 3, "green", "piece3green.png",18);
        piece8 = new Piece(8, 4, "green", "piece4green.png",17);
        piece9 = new Piece(9, 1, "yellow", "piece1yellow.png",36);
        piece10 = new Piece(10, 2, "yellow", "piece2yellow.png",35);
        piece11 = new Piece(11, 3, "yellow", "piece3yellow.png",34);
        piece12 = new Piece(12, 4, "yellow", "piece4yellow.png",33);
        piece13 = new Piece(13, 1, "blue", "piece1blue.png",52);
        piece14 = new Piece(14, 2, "blue", "piece2blue.png",51);
        piece15 = new Piece(15, 3, "blue", "piece3blue.png",50);
        piece16 = new Piece(16, 4, "blue", "piece4blue.png",49);
    }

    @Test
    void getPieceById() {
        Piece dbPiece = PieceDAO.getPieceById(1);
        assertEquals(piece1.getId(), dbPiece.getId());
        assertEquals(piece1.getNumber(), dbPiece.getNumber());
        assertEquals(piece1.getColor(), dbPiece.getColor());
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