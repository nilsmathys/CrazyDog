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
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Players WHERE playerID=?");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();
            Player player = new Player();
            if (rs.next()) {
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
            }
            con.close();
            return player;
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
            stmt = con.createStatement();
            // Load SQL Server JDBC driver and establish connection.
            rs = stmt.executeQuery("SELECT * FROM players");

            List<Player> playerList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("playerID");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Player dbPlayer = new Player(id, username, email);
                playerList.add(dbPlayer);
            }
            con.close();
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
            Player player = new Player();
            if (rs.next()) {
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
            }
            con.close();
            return player;
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertPlayer(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("INSERT INTO Players (username, email, password) VALUES (?,?,?)");
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getPw());
            // Load SQL Server JDBC driver and establish connection.
            int i = ps.executeUpdate();
            con.close();
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePlayer(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            PreparedStatement ps = con.prepareStatement("UPDATE Players SET username=?, email=?, password=? WHERE playerID=?");
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getPw());
            ps.setInt(4, player.getId());
            // Load SQL Server JDBC driver and establish connection.
            int i = ps.executeUpdate();
            con.close();
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePlayer(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            System.out.print("Connecting to SQL Server ... ");
            Statement stmt = con.createStatement();
            // Load SQL Server JDBC driver and establish connection.
            int i = stmt.executeUpdate("DELETE FROM players WHERE playerID=" +player.getId());
            con.close();
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
