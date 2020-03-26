package ch.zhaw.psit3.crazydog.Model.Piece;

import java.util.List;

public interface PieceDAO {
    Piece getPieceById(Integer id);

    List<Piece> getAllPieces();

    int getColourIdFromPeace(Integer id);

    int getNumberOfPiece(Integer id);
}
