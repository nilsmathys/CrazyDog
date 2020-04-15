package ch.zhaw.psit3.crazydog.Model.Player;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerDAOTest {
    static Player firstPlayerDbLocal;
    static Player secondPlayerDbLocal;
    static Player thirdPlayerDbLocal;
    static Player fourthPlayerDbLocal;
    static Player newPlayer;
    static Player updatePlayer;
    static Player deletePlayer;
    static Player dbPlayer;


    @BeforeAll
    static void beforeAll() {
        firstPlayerDbLocal = new Player(1, "Spieler1", "spieler1@crazydog.ch", "test123");
        secondPlayerDbLocal = new Player(2, "Spieler2", "spieler2@crazydog.ch", "test123");
        thirdPlayerDbLocal = new Player(3, "Spieler3", "spieler3@crazydog.ch", "test123");
        fourthPlayerDbLocal = new Player(4, "Spieler4", "spieler4@crazydog.ch", "test123");
        newPlayer = new Player("SpielerNeu", "spielerneu@spielerneu.ch", "New123");
        deletePlayer = new Player("SpielerDelete", "spieledelete@spielerdelete.ch", "Delete123");
        updatePlayer = new Player(5, "UpdateSpieler", "updatespieler@updatespieler.ch", "Update123");
    }

    @BeforeEach
    void setUp() {
        dbPlayer = null;
    }

    @Test
    void getPlayerById() {
        dbPlayer = PlayerDAO.getPlayerById(1);
        assertEquals(firstPlayerDbLocal.getId(), dbPlayer.getId());
        assertEquals(firstPlayerDbLocal.getUsername(), dbPlayer.getUsername());
        assertEquals(firstPlayerDbLocal.getEmail(), dbPlayer.getEmail());
        assertEquals(firstPlayerDbLocal.getPw(), dbPlayer.getPw());
    }

    @Test
    void getPlayerByUsername() {
        dbPlayer = PlayerDAO.getPlayerByUsername("Spieler1");
        assertEquals(firstPlayerDbLocal.getId(), dbPlayer.getId());
        assertEquals(firstPlayerDbLocal.getUsername(), dbPlayer.getUsername());
        assertEquals(firstPlayerDbLocal.getEmail(), dbPlayer.getEmail());
        assertEquals(firstPlayerDbLocal.getPw(), dbPlayer.getPw());
    }

    @Test
    void getAllPlayers() {
        List<Player> players = PlayerDAO.getAllPlayers();
        assertEquals(firstPlayerDbLocal.getId(), players.get(0).getId());
        assertEquals(firstPlayerDbLocal.getUsername(), players.get(0).getUsername());
        assertEquals(firstPlayerDbLocal.getEmail(), players.get(0).getEmail());
        assertEquals(secondPlayerDbLocal.getId(), players.get(1).getId());
        assertEquals(secondPlayerDbLocal.getUsername(), players.get(1).getUsername());
        assertEquals(secondPlayerDbLocal.getEmail(), players.get(1).getEmail());
        assertEquals(thirdPlayerDbLocal.getId(), players.get(2).getId());
        assertEquals(thirdPlayerDbLocal.getUsername(), players.get(2).getUsername());
        assertEquals(thirdPlayerDbLocal.getEmail(), players.get(2).getEmail());
        assertEquals(fourthPlayerDbLocal.getId(), players.get(3).getId());
        assertEquals(fourthPlayerDbLocal.getUsername(), players.get(3).getUsername());
        assertEquals(fourthPlayerDbLocal.getEmail(), players.get(3).getEmail());
    }

    @Test
    void getPlayerByUsernameAndPw() {
        dbPlayer = PlayerDAO.getPlayerByUsernameAndPw("Spieler1", "test123");
        assertEquals(firstPlayerDbLocal.getId(), dbPlayer.getId());
        assertEquals(firstPlayerDbLocal.getUsername(), dbPlayer.getUsername());
        assertEquals(firstPlayerDbLocal.getEmail(), dbPlayer.getEmail());
        assertEquals(firstPlayerDbLocal.getPw(), dbPlayer.getPw());
    }

    @Test
    void inserPlayer() {
        assertTrue(PlayerDAO.inserPlayer(newPlayer));
        System.out.println(PlayerDAO.getPlayerByUsername("SpielerNeu").getId());
        newPlayer.setId(PlayerDAO.getPlayerByUsername("SpielerNeu").getId());
        PlayerDAO.deletePlayer(newPlayer);
    }

    @Test
    void updatePlayer() {
        PlayerDAO.inserPlayer(newPlayer);
        assertTrue(PlayerDAO.updatePlayer(updatePlayer, PlayerDAO.getPlayerByUsername("SpielerNeu").getId()));
        updatePlayer.setId(PlayerDAO.getPlayerByUsername("UpdateSpieler").getId());
        PlayerDAO.deletePlayer(updatePlayer);
    }

    @Test
    void deletePlayer() {
        PlayerDAO.inserPlayer(deletePlayer);
        deletePlayer.setId(PlayerDAO.getPlayerByUsername("SpielerDelete").getId());
        assertTrue(PlayerDAO.deletePlayer(deletePlayer));
    }
}