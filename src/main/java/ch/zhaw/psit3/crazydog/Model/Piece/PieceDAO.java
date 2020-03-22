package ch.zhaw.psit3.crazydog.Model.Piece;

import java.util.List;

public interface PieceDAO {
    Piece getPieceById();
    List<Piece> getAllPieces();
    Piece getColourFromPeace();
    Piece getNumberOfPiece();
}
