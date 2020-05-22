package ch.zhaw.psit3.crazydog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(DBConnectionFactory.class.getName());
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123"; //localhost durch 192.168.0.23 ersetzt und user CrazyDog durch sa
    //public static final String connectionUrl = "jdbc:sqlserver://192.168.0.23:1433;databaseName=CrazyDog;user=sa;password=CrazyDog123"; //localhost durch 192.168.0.23 ersetzt und user CrazyDog durch sa
    String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private DBConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Class com.microsoft.sqlserver.jdbc.SQLServerDriver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn;
        try {
            LOGGER.log(Level.INFO, "Establishing database connection.");
            conn = DriverManager.getConnection(connectionUrl);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Could not establish database connection.", e);
            throw new SQLException("Could not connect to database.");
        }
        return conn;
    }

}
