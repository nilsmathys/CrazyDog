package ch.zhaw.psit3.crazydog.db;


import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;

import java.sql.*;
import java.util.logging.Logger;

public class DBCon {
    private static final Logger LOGGER = Logger.getLogger(DBCon.class.getName());

    private static Connection con;

    public static void main(String[] args) throws SQLException {

//        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
//
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // Load SQL Server JDBC driver and establish connection.
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection(connectionUrl);
//            System.out.print("Connecting to SQL Server ... ");
//            String SQL = "SELECT cardID, name FROM dbo.Cards";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(SQL);
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString(2));
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println();
//            e.printStackTrace();
//        }

        PlayerDAO playerDbDAO = new PlayerDAO();
        Player playerinDb = new Player("Alt", "altt@alt.ch", "Alt123");
        playerDbDAO.inserPlayer(playerinDb);
        Player playerAltPwUsername = playerDbDAO.getPlayerByUsernameAndPw("Alt", "Alt123");
        Player playerByIdalt = playerDbDAO.getPlayerById(1);
        Player updatePlayerAlt = new Player("Alt2", "alt2@alt2.ch", "Alt2");
        Player playerDeleteAlt = playerDbDAO.getPlayerByUsernameAndPw("Spieler1", "test123");


        System.out.println();
        System.out.println();
        System.out.println("NEW PLAYER");
        System.out.println("Player alt getUsernamePW Username: " + playerAltPwUsername.getUsername() + ", Email: " + playerAltPwUsername.getEmail() + ", PW: " + playerAltPwUsername.getPw());
        System.out.println("Player alt getPlayerById Username: " + playerByIdalt.getUsername() + ", Email: " + playerByIdalt.getEmail() + ", PW: " + playerByIdalt.getPw());
        if (playerDbDAO.updatePlayer(updatePlayerAlt, 2) == true) {
            System.out.println("Player alt Update funktioniert");
        } else {
            System.out.println("Player alt Update fehlgeschlagen");
        }
        if (playerDbDAO.deletePlayer(playerDeleteAlt) == true) {
            System.out.println("Player alt Delete funktioniert");
        } else {
            System.out.println("Player alt Delete fehlgeschlagen");
        }
        if (playerDbDAO.inserPlayer(playerinDb) == false) {
            System.out.println("Überprüfung bei Spielereinfügen erfolgreich");
        } else {
            System.out.println("Überprüfung bei Spielereinfügen fehlgeschlagen");
        }


        PieceDAO pieceDAO = new PieceDAO();
        Piece pieceById = pieceDAO.getPieceById(1);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("NEW PIECE ");
        System.out.println("getPieceById New: " + pieceById.getNumber());
        System.out.println("getColourIDNew: " + pieceDAO.getColourIdFromPeace(1));
        System.out.println("getNumberofPeace NEW: " + pieceDAO.getNumberOfPiece(1));
        System.out.println("getPictureId: " + pieceDAO.getPictureId(1));
        System.out.println("Should be RED: " +pieceDAO.getColourFromPiece(1));
        System.out.println("Should be GREEN: " +pieceDAO.getColourFromPiece(5));
        System.out.println("Should be YELLOW: " +pieceDAO.getColourFromPiece(9));
        System.out.println("Should be BLUE: " +pieceDAO.getColourFromPiece(15));

    }
}