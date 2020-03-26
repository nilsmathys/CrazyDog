package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerDAO {

    /**
     * Methode um einen Spieler von der Datenbank auszulesen anhand der ID.
     *
     * @param id Integer der Id der Spielfigur
     * @return player
     */
    public Player getPlayerById(Integer id) {
        String query = "SELECT playerId, username, email, password FROM Players WHERE playerID=?";
        Object[] params = {id};
        DBCon.open();
        ResultSet rs = DBCon.giveResult(query, params);
        Player player = new Player();
        try {
            if (rs.next()) {
                player.setId(rs.getInt("playerID"));
                player.setUsername(rs.getString("username"));
                player.setEmail(rs.getString("email"));
                player.setPw(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return player;
    }

    /**
     * Methode um alle Spieler der Datenbank auszulesen und in eine Liste abzuspeichern.
     *
     * @return playerList, Liste der Spieler in der Datenbank
     */
    public List<Player> getAllPlayers() {
        String query = "SELECT playerID, username, email FROM players";
        DBCon.open();
        ResultSet rs = DBCon.giveResult(query);
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

    /**
     * Methode um einen Spieler von der Datenbank auszulesen anhand des Usernamens und des Passwortes.
     *
     * @param username Username des Spielers
     * @param pw       Passwort des Spielers
     * @return player
     */
    public Player getPlayerByUsernameAndPw(String username, String pw) {
        String query = "SELECT playerID, username, email, password FROM Players WHERE username=? AND password=?";
        Object[] params = {username, pw};
        DBCon.open();
        ResultSet rs = DBCon.giveResult(query, params);
        Player player = new Player();
        try {
            if (rs.next()) {
                player.setId(rs.getInt("playerID"));
                player.setUsername(rs.getString("username"));
                player.setEmail(rs.getString("email"));
                player.setPw(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBCon.close();
        return player;
    }

    /**
     * Methode um einen Spieler der Datenbank hinzuzufügen
     *
     * @param player Spieler der hinzugefügt werden soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
     */
    public boolean insertPlayer(Player player) {
        String query = "INSERT INTO Players (username, email, password) VALUES (?,?,?)";
        Object[] params = {player.getUsername(), player.getEmail(), player.getPw()};
        DBCon.open();
        int i = DBCon.executeUpdate(query, params);
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

    /**
     * Überschreibt einen gewünschten Spieler mit einem neuen Spieler
     *
     * @param player der neue Spieler
     * @param id     die Id, an der der neue Spieler den alten Spieler ersetzen soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
     */
    public boolean updatePlayer(Player player, Integer id) {
        String query = "UPDATE Players SET username=?, email=?, password=? WHERE playerID=?";
        Object[] params = {player.getUsername(), player.getEmail(), player.getPw(), id};
        DBCon.open();
        int i = DBCon.executeUpdate(query, params);
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

    /**
     * Löscht ein gewünschten Spieler aus der Datenbank anhand der ID.
     *
     * @param player Spieler der gelöscht werden soll
     * @return true, falls der Spieler gelöscht werden konnte, ansonsten false
     */
    public boolean deletePlayer(Player player) {
        String query = "DELETE FROM players WHERE playerID=" + player.getId();
        DBCon.open();
        int i = DBCon.executeUpdate(query);
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
