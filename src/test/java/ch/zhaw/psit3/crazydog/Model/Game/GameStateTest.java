package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
    static FieldAndPiece fieldAndPiece;
    static Map<String, String> fieldsAndPieces;
    static List<Player> players;
    static Map<Integer, CardsOnHand> playerAndHand;
    static CardsOnHand cardsOnHand;


    @BeforeAll
    static void beforeAll() {
        fieldAndPiece = new FieldAndPiece("Feld1", "Figur1");
        fieldsAndPieces = new HashMap<>();
        fieldsAndPieces.put("Feld1", "Figur1");
        fieldsAndPieces.put("Feld2", "Figur2");
        fieldsAndPieces.put("Feld3", "Figur3");
        fieldsAndPieces.put("Feld4", "Figur4");
        playerAndHand = new HashMap<>();
        cardsOnHand = new CardsOnHand();
        playerAndHand.put(1, cardsOnHand);
        players = PlayerDAO.getAllPlayers();
    }

    @BeforeEach
    void setUp() {
        GameState.getPlayers().clear();
        GameState.getAllFieldsAndPieces().clear();
        GameState.getAllPlayerAndHand().clear();
    }

    @Test
    void put() {
        assertTrue(GameState.getAllFieldsAndPieces().isEmpty());
        GameState.put(fieldAndPiece);
        assertEquals(1, GameState.getAllFieldsAndPieces().size());
    }

    @Test
    void get() {
        GameState.put(fieldAndPiece);
        assertEquals("Figur1", GameState.get("Feld1"));
    }

    @Test
    void getAllFieldsAndPieces() {
        GameState.put(fieldAndPiece);
        assertEquals(fieldsAndPieces.get("Figur1"), GameState.getAllFieldsAndPieces().get("Figur1"));
    }

    @Test
    void putAllFieldsAndPieces() {
        assertTrue(GameState.getAllFieldsAndPieces().isEmpty());
        GameState.putAllFieldsAndPieces(fieldsAndPieces);
        assertEquals(4, GameState.getAllFieldsAndPieces().size());
    }

    @Test
    void putPlayers() {
        assertTrue(GameState.getPlayers().isEmpty());
        GameState.putPlayers(players);
        assertEquals(4, GameState.getPlayers().size());
    }

    @Test
    void getPlayers() {
        GameState.putPlayers(players);
        assertEquals(players, GameState.getPlayers());
    }

    @Test
    void setAllPlayersAndHand() {
       assertTrue(GameState.getAllPlayerAndHand().isEmpty());
       GameState.setAllPlayersAndHand(playerAndHand);
       assertEquals(1, GameState.getAllPlayerAndHand().size());
    }

    @Test
    void getAllPlayerAndHand() {
        GameState.setAllPlayersAndHand(playerAndHand);
        assertEquals(playerAndHand, GameState.getAllPlayerAndHand());
    }
}