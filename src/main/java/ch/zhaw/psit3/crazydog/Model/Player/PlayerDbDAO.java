package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Players WHERE playerID=?");
            ps.setInt(1, id);
            // Load SQL Server JDBC driver and establish connection.
            rs = ps.executeQuery();
            Player player = new Player();
            if (rs.next()) {
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
                player.setPw(rs.getString("password"));
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
    public Player getPlayerByIdNeu(Integer id) {
        String querry = "SELECT * FROM Players WHERE playerID=?";
        HashMap<Integer,Integer> intKey = new HashMap<>();
        intKey.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intKey);
        Player player = new Player();
        try {
            if (rs.next()) {
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
                player.setPw(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return player;
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
    public List<Player> getAllPlayersNeu() {
        String querry = "SELECT * FROM players";
        DBCon.open();
        ResultSet rs = DBCon.giveResult(querry);
        List<Player> playerList = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("playerID");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Player dbPlayer = new Player(id, username, email);
                playerList.add(dbPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return playerList;
    }

    @Override
    public Player getPlayerByUsernameAndPw(String username, String pw) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
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
                player.setPw(rs.getString("password"));
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
    public Player getPlayerByUsernameAndPwNeu(String username, String pw){
        String querry = "SELECT * FROM Players WHERE username=? AND password=?";
        HashMap<Integer,String> stringKey = new HashMap<>();
        stringKey.put(1, username);
        stringKey.put(2, pw);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithStringKey(querry, stringKey);
        Player player = new Player();
        try {
            if (rs.next()) {
                player.setId( rs.getInt("playerID") );
                player.setUsername( rs.getString("username") );
                player.setEmail( rs.getString("email") );
                player.setPw(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return player;
    }


    @Override
    public boolean insertPlayer(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
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
    public boolean insertPlayerNeu(Player player) {
        String querry = "INSERT INTO Players (username, email, password) VALUES (?,?,?)";
        HashMap<Integer,String> stringKey = new HashMap<>();
        stringKey.put(1, player.getUsername());
        stringKey.put(2, player.getEmail());
        stringKey.put(3, player.getPw());
        DBCon.open();
        int i = DBCon.executeUpdateWithStringKey(querry, stringKey);
        try {
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBCon.close();
        return false;
}

    @Override
    public boolean updatePlayer(Player player, int id) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = con.prepareStatement("UPDATE Players SET username=?, email=?, password=? WHERE playerID=?");
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getPw());
            ps.setInt(4, id);
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
    public boolean updatePlayerNeu(Player player, int id) {
        String querry = "UPDATE Players SET username=?, email=?, password=? WHERE playerID=?)";
        HashMap<Integer,String> stringKey = new HashMap<>();
        HashMap<Integer,Integer> intKey = new HashMap<>();
        stringKey.put(1, player.getUsername());
        stringKey.put(2, player.getEmail());
        stringKey.put(3, player.getPw());
        intKey.put(4,id);
        DBCon.open();
        int i = DBCon.executeUpdateWithIntAndStringKey(querry, stringKey, intKey);
        try {
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBCon.close();
        return false;
    }

    @Override
    public boolean deletePlayer(Player player) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CrazyDog;user=CrazyDog;password=CrazyDog123";
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
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

    @Override
    public boolean deletePlayerNeu(Player player) {
        String querry = "DELETE FROM players WHERE playerID=" + player.getId();
        DBCon.open();
        int i = DBCon.executeUpdate(querry);
        try {
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBCon.close();
        return false;
    }

}
