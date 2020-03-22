package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerDbDAO implements PlayerDAO {


    @Override
    public Player getPlayerById(Integer id) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Players WHERE playerID=?");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();

            if (rs.next()) {
                Player player = new Player();
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
                return player;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Player> getAllPlayers() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM players");
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();

            List<Player> playerList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("playerID");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Player dbPlayer = new Player(id, username, email);
                playerList.add(dbPlayer);
            }
            return playerList;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Player getPlayerByUsernameAndPw(String username, String pw) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Players WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, pw);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();

            if (rs.next()) {
                Player player = new Player();
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
                return player;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUser(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("INSERT INTO dbo.Players (username, email, password) VALUES (?,?,?)");
            ps.setString(1, player.getUsername());
            ps.setString(1, player.getEmail());
            ps.setString(1, player.getPw());
            // Load SQL Server JDBC driver and establish connection.
            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return false;
    }
}
