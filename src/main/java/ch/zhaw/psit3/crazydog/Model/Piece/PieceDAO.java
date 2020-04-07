package ch.zhaw.psit3.crazydog.Model.Piece;

import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {

    /**
     * Methode um die Spielfigur von der Datenbank auszulesen anhand der ID.
     *
     * @param id Integer der Id der Spielfigur
     * @return piece
     */
    public static Piece getPieceById(int id) {
        Connection con = null;
        Piece piece = null;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT pieceID, colourID, number, pictureName FROM Pieces WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int pieceId = 0;
            int colourId = 0;
            int number = 0;
            String pictureName = null;
            if (rs.next()) {
                pieceId = rs.getInt("pieceID");
                colourId = rs.getInt("colourID");
                number = rs.getInt("number");
                pictureName = rs.getString("pictureName");

            }
            piece = new Piece(pieceId, number, colourId, pictureName);
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return piece;
    }

    /**
     * Methode um alle Figuren der Datenbank auszulesen und in eine Liste abzuspeichern.
     *
     * @return pieceList, Liste der Figuren in der Datenbank
     */
    public static List<Piece> getAllPieces() {
        Connection con = null;
        List<Piece> pieceList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT pieceID, number, colourID, pictureName FROM pieces";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int pieceId = rs.getInt("pieceID");
                int number = rs.getInt("number");
                int colourId = rs.getInt("colourId");
                String pictureName = rs.getString("pictureName");
                Piece dbPiece = new Piece(pieceId, number, colourId, pictureName);
                pieceList.add(dbPiece);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pieceList;
    }

    /**
     * Gibt die ColourId der gewünschten Figur zurück
     *
     * @param id FigurenId, bei der der man die Farbid wissen möchte
     * @return int mit der Colourid
     */
    public static int getColourId(int id) {
        Connection con = null;
        Piece piece = new Piece();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT colourID FROM pieces WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            piece.setColourId(0);
            if (rs.next()) {
                piece.setColourId(rs.getInt("colourID"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return piece.getColourId();
    }

    /**
     * Methode um die Farbe einer Figur zu ermitteln anhand ihrer Id.
     * @param id FigurenId, bei der der man die Farbe wissen möchte
     * @return String mit der Farbe
     */
    public static String getColourFromPiece(int id) {
        Connection con = null;
        String colour = null;
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT colourname FROM pieces p JOIN colour c ON c.colourID = p.colourID WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                colour = rs.getString("colourname");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return colour;
    }

    /**
     * Gibt die Nummer der gewünschten Figur zurück
     *
     * @param id FigurenId, bei der der man die Nummer wissen möchte
     * @return int mit der Nummer
     */
    public static int getNumberOfPiece(int id) {
        Connection con = null;
        Piece piece = new Piece();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT number FROM pieces WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            piece.setNumber(0);
            if (rs.next()) {
                piece.setNumber(rs.getInt("number"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return piece.getNumber();
    }

    /**
     * Methode um den PictureName der Figur zu ermitteln
     * @param id FigurenId, bei der der man die PictureId wissen möchte
     * @return String mit dem PictureName
     */
    public static String getPictureName(int id) {
        Connection con = null;
        Piece piece = new Piece();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT pictureName FROM pieces WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            piece.setNumber(0);
            if (rs.next()) {
                piece.setPictureName(rs.getString("pictureName"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return piece.getPictureName();
    }
}
