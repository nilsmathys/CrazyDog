package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.db.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlayerDbDAO implements PlayerDAO {

    /**
     * Methode um einen Spieler von der Datenbank auszulesen anhand der ID.
     * @param id Integer der Id der Spielfigur
     * @return player
     */
    @Override
    public Player getPlayerById(Integer id) {
        String querry = "SELECT * FROM Players WHERE playerID=?";
        HashMap<Integer, Integer> intParameters = new HashMap<>();
        intParameters.put(1, id);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithIntKey(querry, intParameters);
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
     * @return playerList, Liste der Spieler in der Datenbank
     */
    @Override
    public List<Player> getAllPlayers() {
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

    /**
     * Methode um einen Spieler von der Datenbank auszulesen anhand des Usernamens und des Passwortes.
     * @param username Username des Spielers
     * @param pw Passwort des Spielers
     * @return player
     */
    @Override
    public Player getPlayerByUsernameAndPw(String username, String pw) {
        String querry = "SELECT * FROM Players WHERE username=? AND password=?";
        HashMap<Integer, String> stringParameters = new HashMap<>();
        stringParameters.put(1, username);
        stringParameters.put(2, pw);
        DBCon.open();
        ResultSet rs = DBCon.giveResultWithStringKey(querry, stringParameters);
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
     * @param player Spieler der hinzugefügt werden soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
     */
    @Override
    public boolean insertPlayer(Player player) {
        String querry = "INSERT INTO Players (username, email, password) VALUES (?,?,?)";
        HashMap<Integer, String> stringParameters = new HashMap<>();
        stringParameters.put(1, player.getUsername());
        stringParameters.put(2, player.getEmail());
        stringParameters.put(3, player.getPw());
        DBCon.open();
        int i = DBCon.executeUpdateWithStringKey(querry, stringParameters);
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
     * @param player der neue Spieler
     * @param id die Id, an der der neue Spieler den alten Spieler ersetzen soll
     * @return true, falls der Spieler der Datenbank hinzugefügt werden konnte, ansonsten false
     */
    @Override
    public boolean updatePlayer(Player player, int id) {
        String querry = "UPDATE Players SET username=?, email=?, password=? WHERE playerID=?)";
        HashMap<Integer, String> stringParameters = new HashMap<>();
        HashMap<Integer, Integer> intParameters = new HashMap<>();
        stringParameters.put(1, player.getUsername());
        stringParameters.put(2, player.getEmail());
        stringParameters.put(3, player.getPw());
        intParameters.put(4, id);
        DBCon.open();
        int i = DBCon.executeUpdateWithIntAndStringKey(querry, stringParameters, intParameters);
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
     * Löscht ein gewüunschten Spieler aus der Datenbank anhand der ID.
     * @param player Spieler der gelöscht werden soll
     * @return true, falls der Spieler gelöscht werden konnte, ansonsten false
     */
    @Override
    public boolean deletePlayer(Player player) {
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
