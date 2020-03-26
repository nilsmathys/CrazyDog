package ch.zhaw.psit3.crazydog.db;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Piece.PieceDbDAO;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDbDAO;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Logger;

public class DBCon {
    private static final Logger LOGGER = Logger.getLogger(DBCon.class.getName());

    private static Connection con;

    public static void main(String[] args) throws SQLException {

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load SQL Server JDBC driver and establish connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            String SQL = "SELECT cardID, name FROM dbo.Cards";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }

        PlayerDbDAO playerDbDAO = new PlayerDbDAO();
        Player playerinDbAlt = new Player("Alt", "altt@alt.ch", "Alt123");
        Player playerinDbNeu = new Player("Neu", "Neu@Neu.ch", "Neu123");
        playerDbDAO.insertPlayer(playerinDbAlt);
        Player playerAltPwUsername = playerDbDAO.getPlayerByUsernameAndPw("Alt", "Alt123");
        Player playerByIdalt = playerDbDAO.getPlayerById(1);
        Player updatePlayerAlt = new Player("Alt2", "alt2@alt2.ch", "Alt2");
        Player playerDeleteAlt = playerDbDAO.getPlayerByUsernameAndPw("Spieler1", "test123");


        System.out.println();
        System.out.println();
        System.out.println("ALT PLAYER");
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


        PieceDbDAO pieceDbDAO = new PieceDbDAO();
        Piece piecebyIDalt = pieceDbDAO.getPieceById(1);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("ALT PIECE ");
        System.out.println("getPieceById ALT: " + piecebyIDalt.getNumber());
        System.out.println("getColourIDALT: " + pieceDbDAO.getColourIdFromPeace(1));
        System.out.println("getNumberofPeace ALT: " + pieceDbDAO.getNumberOfPiece(1));


    }

    public static void open() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //input querry, schlüssel int & string. return resultset
    public static ResultSet giveResultWithIntAndStringKey(String querry, HashMap<Integer, String> stringKey, HashMap<Integer, Integer> integerKey) {
        ResultSet rs = null;
        try {
            System.out.print("Connecting to SQL Server ... ");
            int sizeHashMap = stringKey.size() + integerKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                if (stringKey.containsValue(i)) {
                    ps.setString(i, stringKey.get(i));
                } else {
                    ps.setInt(i, integerKey.get(i));
                }
            }
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet giveResultWithIntKey(String querry, HashMap<Integer, Integer> integerKey) {
        ResultSet rs = null;
        try {
            int sizeHashMap = integerKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setInt(i, integerKey.get(i));
            }
            rs = ps.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet giveResultWithStringKey(String querry, HashMap<Integer, String> stringKey) {
        ResultSet rs = null;
        try {
            int sizeHashMap = stringKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setString(i, stringKey.get(i));
            }
            rs = ps.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet giveResult(String querry) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(querry);
            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdateWithIntAndStringKey(String querry, HashMap<Integer, String> stringKey, HashMap<Integer, Integer> integerKey) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = stringKey.size() + integerKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                if (stringKey.containsValue(i)) {
                    ps.setString(i, stringKey.get(i));
                } else {
                    ps.setInt(i, integerKey.get(i));
                }
            }
            isUpdatet = ps.executeUpdate();
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

    public static int executeUpdateWithIntKey(String querry, HashMap<Integer, Integer> integerKey) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = integerKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setInt(i, integerKey.get(i));
            }
            isUpdatet = ps.executeUpdate();
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

    public static int executeUpdateWithStringKey(String querry, HashMap<Integer, String> stringKey) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = stringKey.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setString(i, stringKey.get(i));
            }
            isUpdatet = ps.executeUpdate();
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

    public static int executeUpdate(String querry) {
        int isUpdatet = 0;
        try {
            Statement stmt = con.createStatement();
            isUpdatet = stmt.executeUpdate(querry);
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

}