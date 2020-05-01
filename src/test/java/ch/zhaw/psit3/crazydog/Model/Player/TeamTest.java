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
    void setup() {
        player1 = new Player(1, "Player1", "player1@player.ch", "test123");
        player2 = new Player(2, "Player2", "player2@player.ch", "test123");
        team = new Team(player1, player2, "red", "blue");
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
        assertEquals("red", team.getColourPlayer1());
    }

    @Test
    void setColourIdPlayer1() {
        team.setColourPlayer1("yellow");
        assertEquals("yellow", team.getColourPlayer1());
    }

    @Test
    void getColourIdPlayer2() {
        team.setColourPlayer2("green");
        assertEquals("green", team.getColourPlayer2());
    }

    @Test
    void setColourIdPlayer2() {
        assertEquals("blue", team.getColourPlayer2());
    }
}