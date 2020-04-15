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
     * Methode um einen Spieler von der Datenbank auszulesen anhand der ID.
     *
     * @param id Integer der Id der Spielfigur
     * @return player
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
            LOGGER.log(Level.SEVERE, "Spieler konnte nicht aus Datenbank ausgelesen werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        return player;
    }

    /**
     * Gibt einen Spieler anhand seines Benutzernamens zurück
     *
     * @param username String Username des Spielers
     * @return gewünschter Spieler
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
            LOGGER.log(Level.SEVERE, "Spieler konnte nicht aus Datenbank ausgelesen werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        return player;
    }

    /**
     * Methode um alle Spieler der Datenbank auszulesen und in eine Liste abzuspeichern.
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
            LOGGER.log(Level.SEVERE, "Spieler konnten nicht aus Datenbank ausgelesen werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        return playerList;
    }

    /**
     * Methode um einen Spieler von der Datenbank auszulesen anhand des Usernamens und des Passwortes.
     *
     * @param username Username des Spielers
     * @param pw       Passwort des Spielers
     * @return player
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
            LOGGER.log(Level.SEVERE, "Spieler konnte nicht aus Datenbank ausgelesen werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        return player;
    }

    /**
     * Methode um einen Spieler der Datenbank hinzuzufügen
     *
     * @param player Spieler der hinzugefügt werden soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
     */
    public static boolean inserPlayer(Player player) {
        Connection con = null;
        int i = 0;

        if(player.getEmail().contains("@") && player.getEmail().contains(".")) {
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
                ps.setString(3, player.getPw());
                i = ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Spieler konnte nicht in Datenbank gespeichert werden.", e);
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
     * Überschreibt einen gewünschten Spieler mit einem neuen Spieler
     *
     * @param player der neue Spieler
     * @param id     die Id, an der der neue Spieler den alten Spieler ersetzen soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
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
            ps.setString(3, player.getPw());
            ps.setInt(4, id);
            i = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Spieler konnte nicht aktualisiert werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Löscht ein gewünschten Spieler aus der Datenbank anhand der ID.
     *
     * @param player Spieler der gelöscht werden soll
     * @return true, falls der Spieler gelöscht werden konnte, ansonsten false
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
            LOGGER.log(Level.SEVERE, "Spieler konnte nicht aus Datenbank gelöscht werden.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Datenbankverbindung schliessen fehlgeschlagen.", e);
            }
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

}
