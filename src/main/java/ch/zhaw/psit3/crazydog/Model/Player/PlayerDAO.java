package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.db.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerDAO {
    private static final Logger LOGGER = Logger.getLogger(PlayerDAO.class.getName());

    /**
     * get player by id from db
     *
     * @param id id from player
     * @return searched player
     */
    public static Player getPlayerById(int id) {
        Connection con = null;
        Player player = null;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT playerId, username, email, password FROM Players WHERE playerID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int playerId = 0;
            String username = null;
            String email = null;
            String password = null;
            if (rs.next()) {
                playerId = rs.getInt("playerID");
                username = rs.getString("username");
                email = rs.getString("email");
                password = rs.getString("password");
            }
            player = new Player(playerId, username, email, password);
            rs.close();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load player by ID.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error", e);
            }
        }
        return player;
    }

    /**
     * get player by username from db
     *
     * @param username username from player
     * @return searched player
     */
    public static Player getPlayerByUsername(String username) {
        Connection con = null;
        Player player = null;
        try {
            con = DBConnectionFactory.getConnection();

            String query = "SELECT playerId, username, email, password FROM players WHERE username=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                player = new Player(
                        rs.getInt("playerID"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load player by username.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error.", e);
            }
        }
        return player;
    }

    /**
     * get all players from db
     *
     * @return playerList, Liste der Spieler in der Datenbank
     */
    public static List<Player> getAllPlayers() {
        Connection con = null;
        List<Player> playerList = new ArrayList<>();
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT playerID, username, email FROM players";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("playerID");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Player dbPlayer = new Player(id, username, email);
                playerList.add(dbPlayer);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load all players.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error.", e);
            }
        }
        return playerList;
    }

    /**
     * get player by username and pw from db
     *
     * @param username username from player
     * @param pw       password from player
     * @return searched player
     */
    public static Player getPlayerByUsernameAndPw(String username, String pw) {
        Connection con = null;
        Player player = null;
        try {
            con = DBConnectionFactory.getConnection();
            String query = "SELECT playerID, username, email, password FROM Players WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            int playerId = 0;
            username = null;
            String email = null;
            String password = null;
            if (rs.next()) {
                playerId = rs.getInt("playerID");
                username = rs.getString("username");
                email = rs.getString("email");
                password = rs.getString("password");
            }
            player = new Player(playerId, username, email, password);
            rs.close();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't load player by username and password.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error.", e);
            }
        }
        return player;
    }

    /**
     * insert player in db
     *
     * @param player player who should be inserted
     * @return true, if the player is succesfull inserted, else false
     */
    public static boolean inserPlayer(Player player) {
        Connection con = null;
        int i = 0;

        if (player.getEmail().contains("@") && player.getEmail().contains(".")) {
            player.setEmail(player.getEmail().toLowerCase());
        } else {
            throw new IllegalArgumentException("Emailaddresse überprüfen");
        }
        if (getPlayerByUsername(player.getUsername()) == null) {
            try {
                con = DBConnectionFactory.getConnection();
                String query = "INSERT INTO Players (username, email, password) VALUES (?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, player.getUsername());
                ps.setString(2, player.getEmail());
                ps.setString(3, player.getPassword());
                i = ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Couln't insert a player in the db.", e);
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Connection error.", e);
                }
            }
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * update player in db
     *
     * @param player new player
     * @param id     id from the player who will be updated
     * @return true, if the player is successfully been updated, else false
     */
    public static boolean updatePlayer(Player player, int id) {
        Connection con = null;
        int i = 0;
        try {
            con = DBConnectionFactory.getConnection();
            String query = "UPDATE Players SET username=?, email=?, password=? WHERE playerID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getPassword());
            ps.setInt(4, id);
            i = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't update the player in db.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection error.", e);
            }
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * delets a player based on the id
     *
     * @param player player who should be deleted
     * @return true, if the player is been successfully deleted, else false
     */
    public static boolean deletePlayer(Player player) {
        Connection con = null;
        int i = 0;
        try {
            con = DBConnectionFactory.getConnection();
            String query = "DELETE FROM players WHERE playerID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, player.getId());
            i = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't delete the player from db.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Connection errror.", e);
            }
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

}
