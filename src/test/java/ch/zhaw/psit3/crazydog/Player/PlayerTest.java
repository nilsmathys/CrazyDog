package ch.zhaw.psit3.crazydog.Player;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;


    @BeforeEach
    public void setup() {
        player = new Player(1, "Tester", "test@test.ch", "test123");
    }

    @Test
    void getId() {
        assertEquals(1, player.getId());
    }

    @Test
    void setId() {
        player.setId(5);
        assertEquals(5, player.getId());
    }

    @Test
    void getUsername() {
        assertEquals("Tester", player.getUsername());
    }

    @Test
    void setUsername() {
        player.setUsername("Tester2");
        assertEquals("Tester2", player.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("test@test.ch", player.getEmail());
    }

    @Test
    void setEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           player.setEmail("Test");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           player.setEmail("Test@");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           player.setEmail("Test.");
        });
        player.setEmail("test2@test.ch");
        assertEquals("test2@test.ch", player.getEmail());
    }

    @Test
    void getPw() {
        assertEquals("test123", player.getPassword());
    }

    @Test
    void setPw() {
        player.setPassword("test1234");
        assertEquals("test1234", player.getPassword());
    }
}