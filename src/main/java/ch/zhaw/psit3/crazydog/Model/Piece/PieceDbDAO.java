package ch.zhaw.psit3.crazydog.Model.Piece;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PieceDbDAO implements PieceDAO {

    /**
     * Methode um die Spielfigur von der Datenbank auszulesen anhand der ID.
     * @param id Integer der Id der Spielfigur
     * @return piece
     */
    @Override
    public Piece getPieceById(Integer id) {
        String querry = "SELECT * FROM Pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intParameters = new HashMap<>();
        intParameters.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intParameters);
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

    /**
     * Methode um alle Figuren der Datenbank auszulesen und in eine Liste abzuspeichern.
     * @return pieceList, Liste der Figuren in der Datenbank
     */
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

    /**
     * Gibt die ColourId der gewünschten Figur zurück
     * @param id FigurenId, bei der der man die Farbid wissen möchte
     * @return int mit der Colourid
     */
    @Override
    public int getColourIdFromPeace(Integer id) {
        String querry = "SELECT * FROM pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intParameters = new HashMap<>();
        intParameters.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intParameters);
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

    /**
     * Gibt die Nummer der gewünschten Figur zurück
     * @param id FigurenId, bei der der man die Nummer wissen möchte
     * @return int mit der Nummer
     */
    @Override
    public int getNumberOfPiece(Integer id) {
        String querry = "SELECT number FROM pieces WHERE pieceID=?";
        HashMap<Integer, Integer> intParameters = new HashMap<>();
        intParameters.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intParameters);
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
