package ch.zhaw.psit3.crazydog.Model.Piece;

import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>PieceDAO</h1>
 * The PieceDAO queries the database to get all stored pieces<br>
 *
 * @author N. Mathys
 * @version 1.0
 * @since April 2020
 */
public class PieceDAO {
    private static final Logger LOGGER = Logger.getLogger(PieceDAO.class.getName());

    /**
     * get piece from db
     *
     * @param id id from piece
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
            int homeFieldId = 0;
            if (rs.next()) {
                pieceId = rs.getInt("pieceID");
                colour = rs.getString("colourname");
                number = rs.getInt("number");
                pictureName = rs.getString("pictureName");
                homeFieldId = rs.getInt("homeFieldId");
            }
            piece = new Piece(pieceId, number, colour, pictureName, homeFieldId);
            rs.close();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load piece by ID.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return piece;
    }

    /**
     * get all pieces from db
     *
     * @return pieceList, list with all pieces
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
            LOGGER.log(Level.SEVERE, "Couldn't load all pieces.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return pieceList;
    }

    /**
     * get colour from piece from db
     *
     * @param id id from piece
     * @return colour as String
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
            LOGGER.log(Level.SEVERE, "Couldn't load colour from piece.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return colour;
    }

    /**
     * get number from piece from db
     *
     * @param id from piece
     * @return number as int
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
            LOGGER.log(Level.SEVERE, "Couldn't load number of piece.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return piece.getNumber();
    }

    /**
     * get pictureName from piece from db
     *
     * @param id id from piece
     * @return pictureName as String
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
            LOGGER.log(Level.SEVERE, "Couldn't load picturename from piece.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return piece.getPictureName();
    }
}
