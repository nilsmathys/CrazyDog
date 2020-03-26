package ch.zhaw.psit3.crazydog.Model.Piece;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PieceDbDAO implements PieceDAO {


    @Override
    public Piece getPieceById(Integer id) {
        String querry = "SELECT * FROM Pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intKey = new HashMap<>();
        intKey.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intKey);
        Piece piece = new Piece();
        try {
            if (rs.next()) {
                piece.setId(rs.getInt("pieceID"));
                piece.setColourId(rs.getInt("colourID"));
                piece.setNumber(rs.getInt("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return piece;
    }


    @Override
    public List<Piece> getAllPieces() {
        String querry = "SELECT * FROM pieces";
        DBCon.open();
        ResultSet rs = DBCon.giveResult(querry);
        List<Piece> pieceList = new ArrayList<>();
        try {
            while (rs.next()) {
                int pieceId = rs.getInt("pieceID");
                int number = rs.getInt("number");
                int colourId = rs.getInt("colourId");
                Piece dbPiece = new Piece(pieceId, number, colourId);
                pieceList.add(dbPiece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return pieceList;
    }


    @Override
    public int getColourIdFromPeace(Integer id) {
        String querry = "SELECT * FROM pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intKey = new HashMap<>();
        intKey.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intKey);
        Piece piece = new Piece();
        try {
            if (rs.next()) {
                piece.setColourId(rs.getInt("colourID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return piece.getColourId();
    }


    @Override
    public int getNumberOfPiece(Integer id) {
        String querry = "SELECT number FROM pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intKey = new HashMap<>();
        intKey.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intKey);
        Piece piece = new Piece();
        try {
            if (rs.next()) {
                piece.setNumber(rs.getInt("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return piece.getNumber();
    }
}
