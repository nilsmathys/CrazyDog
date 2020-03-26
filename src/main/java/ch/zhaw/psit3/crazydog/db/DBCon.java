package ch.zhaw.psit3.crazydog.db;

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

//        PlayerDbDAO playerDbDAO = new PlayerDbDAO();
//        Player playerinDbAlt = new Player("Alt", "altt@alt.ch", "Alt123");
//        Player playerinDbNeu = new Player("Neu", "Neu@Neu.ch", "Neu123");
//        playerDbDAO.insertPlayer(playerinDbAlt);
//        Player playerAltPwUsername = playerDbDAO.getPlayerByUsernameAndPw("Alt", "Alt123");
//        Player playerByIdalt = playerDbDAO.getPlayerById(1);
//        Player updatePlayerAlt = new Player("Alt2", "alt2@alt2.ch", "Alt2");
//        Player playerDeleteAlt = playerDbDAO.getPlayerByUsernameAndPw("Spieler1", "test123");
//
//
//        System.out.println();
//        System.out.println();
//        System.out.println("ALT PLAYER");
//        System.out.println("Player alt getUsernamePW Username: " + playerAltPwUsername.getUsername() + ", Email: " + playerAltPwUsername.getEmail() + ", PW: " + playerAltPwUsername.getPw());
//        System.out.println("Player alt getPlayerById Username: " + playerByIdalt.getUsername() + ", Email: " + playerByIdalt.getEmail() + ", PW: " + playerByIdalt.getPw());
//        if (playerDbDAO.updatePlayer(updatePlayerAlt, 2) == true) {
//            System.out.println("Player alt Update funktioniert");
//        } else {
//            System.out.println("Player alt Update fehlgeschlagen");
//        }
//        if (playerDbDAO.deletePlayer(playerDeleteAlt) == true) {
//            System.out.println("Player alt Delete funktioniert");
//        } else {
//            System.out.println("Player alt Delete fehlgeschlagen");
//        }
//
//
//        PieceDbDAO pieceDbDAO = new PieceDbDAO();
//        Piece piecebyIDalt = pieceDbDAO.getPieceById(1);
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("ALT PIECE ");
//        System.out.println("getPieceById ALT: " + piecebyIDalt.getNumber());
//        System.out.println("getColourIDALT: " + pieceDbDAO.getColourIdFromPeace(1));
//        System.out.println("getNumberofPeace ALT: " + pieceDbDAO.getNumberOfPiece(1));


    }

    /**
     * Öffnet die Verbindung zum Server
     */
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

    /**
     * Schliesst die Verbindung
     */
    public static void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode für eine Abfrage mit String- und Int-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param stringParameters Stringparameterwerte des Preparedstatement als HashMap Bsp: <3, "test"> wird dem dritten Fragezeichen des preparedstatement mitgegeben
     * @param integerParameters Intparatmeterwerte des Preparedstatement als Hashmap
     * @return ResultSet mit den Abfrageresultaten
     */
    public static ResultSet giveResultWithIntAndStringKey(String querry, HashMap<Integer, String> stringParameters, HashMap<Integer, Integer> integerParameters) {
        ResultSet rs = null;
        try {
            System.out.print("Connecting to SQL Server ... ");
            int sizeHashMap = stringParameters.size() + integerParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                if (stringParameters.containsValue(i)) {
                    ps.setString(i, stringParameters.get(i));
                } else {
                    ps.setInt(i, integerParameters.get(i));
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

    /**
     * Methode für eine Abfrage mit Int-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param integerParameters Intparatmeterwerte des Preparedstatement als Hashmap
     * @return ResultSet mit den Abfrageresultaten
     */
    public static ResultSet giveResultWithIntKey(String querry, HashMap<Integer, Integer> integerParameters) {
        ResultSet rs = null;
        try {
            int sizeHashMap = integerParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setInt(i, integerParameters.get(i));
            }
            rs = ps.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Methode für eine Abfrage mit String-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param stringParameters Stringparatmeterwerte des Preparedstatement als HashMap Bsp: <3, "test"> wird dem dritten Fragezeichen des preparedstatement mitgegeben
     * @return ResultSet mit den Abfrageresultaten
     */
    public static ResultSet giveResultWithStringKey(String querry, HashMap<Integer, String> stringParameters) {
        ResultSet rs = null;
        try {
            int sizeHashMap = stringParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setString(i, stringParameters.get(i));
            }
            rs = ps.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Methode für eine Abfrage ohne Paratmeterwerte
     * @param querry als String
     * @return ResultSet mit den Abfrageresultaten
     */
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

    /**
     * Methode für eine Updateabfrage mit String- und Int-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param stringParameters des Preparedstatement als HashMap Bsp: <3, "test"> wird dem dritten Fragezeichen des preparedstatement mitgegeben
     * @param integerParameters Intparatmeterwerte des Preparedstatement als Hashmap
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
    public static int executeUpdateWithIntAndStringKey(String querry, HashMap<Integer, String> stringParameters, HashMap<Integer, Integer> integerParameters) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = stringParameters.size() + integerParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                if (stringParameters.containsValue(i)) {
                    ps.setString(i, stringParameters.get(i));
                } else {
                    ps.setInt(i, integerParameters.get(i));
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

    /**
     * Methode für eine Updateabfrage mit Int-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param integerParameters Intparatmeterwerte des Preparedstatement als Hashmap
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
    public static int executeUpdateWithIntKey(String querry, HashMap<Integer, Integer> integerParameters) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = integerParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setInt(i, integerParameters.get(i));
            }
            isUpdatet = ps.executeUpdate();
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

    /**
     * Methode für eine Updateabfrage mit String-Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @param stringParameters Stringparatmeterwerte des Preparedstatement als HashMap Bsp: <3, "test"> wird dem dritten Fragezeichen des preparedstatement mitgegeben
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
    public static int executeUpdateWithStringKey(String querry, HashMap<Integer, String> stringParameters) {
        int isUpdatet = 0;
        try {
            int sizeHashMap = stringParameters.size();
            PreparedStatement ps = con.prepareStatement(querry);
            for (int i = 1; i <= sizeHashMap; i++) {
                ps.setString(i, stringParameters.get(i));
            }
            isUpdatet = ps.executeUpdate();
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

    /**
     * Methode für eine Updateabfrage ohne Paratmeterwerte für das PreparedStatement
     * @param querry als String
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
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