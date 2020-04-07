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
}