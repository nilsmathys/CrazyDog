package ch.zhaw.psit3.crazydog.Model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team team;
    Player player1;
    Player player2;
    Player player1New;
    Player player2New;
    @BeforeEach
    public void setup() {
        player1 = new Player(1, "Player1", "player1@player.ch", "test123");
        player2 = new Player(2, "Player2", "player2@player.ch", "test123");
        team = new Team(player1, player2, 1, 2);
    }

    @Test
    void getPlayer1() {
        assertEquals(player1, team.getPlayer1());
    }

    @Test
    void setPlayer1() {
        player1New = new Player("Player1New", "player1New@player.ch", "test123");
        team.setPlayer1(player1New);
        assertEquals(player1New, team.getPlayer1());
    }

    @Test
    void getPlayer2() {
        assertEquals(player2, team.getPlayer2());
    }

    @Test
    void setPlayer2() {
        player2New = new Player("Player2New", "player2New@player.ch", "test123");
        team.setPlayer2(player2New);
        assertEquals(player2New, team.getPlayer2());
    }

    @Test
    void getColourIdPlayer1() {
        assertEquals(1, team.getColourIdPlayer1());
    }

    @Test
    void setColourIdPlayer1() {
        team.setColourIdPlayer1(5);
        assertEquals(5, team.getColourIdPlayer1());
    }

    @Test
    void getColourIdPlayer2() {
        team.setColourIdPlayer2(20);
        assertEquals(20, team.getColourIdPlayer2());
    }

    @Test
    void setColourIdPlayer2() {
        assertEquals(2, team.getColourIdPlayer2());
    }
}