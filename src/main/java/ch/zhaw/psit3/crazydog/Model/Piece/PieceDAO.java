package ch.zhaw.psit3.crazydog.Model.Piece;

import java.util.List;

public interface PieceDAO {
    Piece getPieceById(Integer id);
    Piece getPieceByIdNeu(Integer id);
    List<Piece> getAllPieces();
    List<Piece> getAllPiecesNeu();
    int getColourIdFromPeace(Integer id);
    int getColourIdFromPeaceNeu(Integer id);
    int getNumberOfPiece(Integer id);
    int getNumberOfPieceNeu(Integer id);
}
