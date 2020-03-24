package ch.zhaw.psit3.crazydog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCon {

    public static void main(String[] args) {

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