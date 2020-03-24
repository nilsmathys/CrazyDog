package ch.zhaw.psit3.crazydog.Model.Piece;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDbDAO implements PieceDAO {

    @Override
    public Piece getPieceById(Integer id) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Pieces WHERE pieceID=?");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();
            Piece piece = new Piece();
            if (rs.next()) {
                piece.setId(rs.getInt("pieceID"));
                piece.setColour(rs.getInt("colourID"));
                piece.setNumber(rs.getInt("number"));
            }
            con.close();
            return piece;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Piece> getAllPieces() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            stmt = con.createStatement();
            // Load SQL Server JDBC driver and establish connection.
            rs = stmt.executeQuery("SELECT * FROM pieces");

            List<Piece> pieceList = new ArrayList<>();

            while (rs.next()) {
                int pieceId = rs.getInt("pieceID");
                int number = rs.getInt("number");
                int colourId = rs.getInt("colourId");
                Piece dbPiece = new Piece(pieceId, number, colourId);
                pieceList.add(dbPiece);
            }
            con.close();
            return pieceList;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int getColourIdFromPeace(Integer id) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT colorId FROM pieces WHERE pieceID=? ");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();

            List<Piece> pieceList = new ArrayList<>();
            Piece piece = new Piece();
            if (rs.next()) {
                piece.setColour(rs.getInt("colourID"));
            }
            con.close();
            return piece.getColourId();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();

        }
        return 0;
    }

    @Override
    public int getNumberOfPiece(Integer id) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT number FROM pieces WHERE pieceID=? ");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();

            List<Piece> pieceList = new ArrayList<>();
            Piece piece = new Piece();
            if (rs.next()) {
                piece.setNumber(rs.getInt("number"));
            }
            con.close();
            return piece.getNumber();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();

        }
        return 0;
    }
}
