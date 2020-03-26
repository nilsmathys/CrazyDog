package ch.zhaw.psit3.crazydog.db;



import java.sql.*;
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
     *
     * @param query als String
     * @param params Parameterwerte als Int oder String
     * @return ResultSet mit den Abfrageresultaten
     */
    public static ResultSet giveResult(String query, Object[] params) {
        ResultSet rs = null;
        try {
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    ps.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) params[i]);
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
     * Methode für eine Abfrage ohne Paratmeterwerte
     *
     * @param query als String
     * @return ResultSet mit den Abfrageresultaten
     */
    public static ResultSet giveResult(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Methode für eine Updateabfrage mit String- und Int-Paratmeterwerte für das PreparedStatement
     *
     * @param query als String
     * @param params Parameterwerte als Int oder String
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
    public static int executeUpdate(String query, Object[] params) {
        int isUpdatet = 0;
        try {

            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    ps.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) params[i]);
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
     * Methode für eine Updateabfrage ohne Paratmeterwerte für das PreparedStatement
     *
     * @param query als String
     * @return int, falls Update funktioniert hat = 1, falls nicht = 0
     */
    public static int executeUpdate(String query) {
        int isUpdatet = 0;
        try {
            Statement stmt = con.createStatement();
            isUpdatet = stmt.executeUpdate(query);
            return isUpdatet;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return isUpdatet;
    }

}