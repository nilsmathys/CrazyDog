package ch.zhaw.psit3.crazydog.db;

import ch.zhaw.psit3.crazydog.Model.Card.CardDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
    String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static DBConnectionFactory dbConnectionFactory = null;

    private DBConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionUrl);
        } catch (Exception e){
            System.out.println();
            e.printStackTrace();
            throw new SQLException("Could not connect to database");
        }
        return conn;
    }

}
