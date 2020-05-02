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

            String query = "SELECT pieceID, colourname, number, pictureName, homeFieldId FROM dbo.Pieces p JOIN dbo.Colour c ON p.colourID = c.colourID WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int pieceId = 0;
            String colour = "";
            int number = 0;
            String pictureName = null;
            int homeFieldId =0;
            if (rs.next()) {
                pieceId = rs.getInt("pieceID");
                colour = rs.getString("colourname");
                number = rs.getInt("number");
                pictureName = rs.getString("pictureName");
                homeFieldId = rs.getInt("homeFieldId");
            }
            piece = new Piece(pieceId, number, colour, pictureName,homeFieldId);
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
            String query = "SELECT pieceID, number, colourname, pictureName, homeFieldId FROM dbo.Pieces p JOIN dbo.Colour c ON p.colourID = c.colourID";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int pieceId = rs.getInt("pieceID");
                int number = rs.getInt("number");
                String colour = rs.getString("colourname");
                String pictureName = rs.getString("pictureName");
                int homeFieldId = rs.getInt("homeFieldId");
                Piece dbPiece = new Piece(pieceId, number, colour, pictureName, homeFieldId);
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
    public static String getColourName(int id) {
        Connection con = null;
        Piece piece = new Piece();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT colourname FROM dbo.Pieces p JOIN dbo.Colour c ON p.colourID = c.colourID WHERE pieceID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                piece.setColor(rs.getString("colourname"));
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
        return piece.getColor();
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
