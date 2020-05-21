package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.Card.CardDeck;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.Team;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    static Round round;
    static Team t1;
    static Team t2;
    static CardDeck deck;

    @BeforeAll
    static void beforeAll() {
        Player p1 = new Player(1, "Spieler A", "a@test.ch");
        Player p2 = new Player(2, "Spieler B", "b@test.ch");
        Player p3 = new Player(3, "Spieler C", "c@test.ch");
        Player p4 = new Player(4, "Spieler D", "d@test.ch");
        t1 = new Team(p1, p2, "red", "green");
        t2 = new Team(p3, p4, "yellow", "blue");
        deck = new CardDeck();
    }

    @Test
    void getNumberOfCardsToDistribute_round8() {
        assertEquals(4, Round.getNumberOfCardsToDistribute(8));
    }

    @Test
    void getNumberOfCardsToDistribute_round12() {
        assertEquals(5, Round.getNumberOfCardsToDistribute(12));
    }

    @Test
    void isRoundStarted_atBeginNewRound() {
        round = new Round(2, deck, t1, t2);
        assertFalse(round.isRoundStarted());
    }

    @Test
    void allPlayerOutOfCards_falseAtStartOfRound() {
        round = new Round(4, deck, t1, t2);
        assertFalse(round.allPlayerOutOfCards());
    }

    @Test
    void getPlayerAndHand_all() {
        round = new Round(5, deck, t1, t2);
        assertEquals(4, round.getPlayerAndHand().size());
    }

    @Test
    void getPlayerAndHand_sizeHandP1InRound1() {
        round = new Round(1, deck, t1, t2);
        assertEquals(6, round.getPlayerAndHand().get(1).getHand().size());
    }

    @Test
    void getPlayerAndHand_sizeHandP2InRound5() {
        round = new Round(5, deck, t1, t2);
        assertEquals(2, round.getPlayerAndHand().get(2).getHand().size());
    }

    @Test
    void arePiecesOfPlayerAtDestination_yellowPicesInOrderAtDestination() {
        Map<Integer,String> currentPieces = new HashMap<>();
        currentPieces.put(1, "field96");
        currentPieces.put(2, "field95");
        currentPieces.put(3, "field94");
        currentPieces.put(4, "field93");
        Map<Integer,String> destFields = new HashMap<>();
        destFields.put(1, "field96");
        destFields.put(2, "field95");
        destFields.put(3, "field94");
        destFields.put(4, "field93");
        assertTrue(Round.arePiecesOfPlayerAtDestination(currentPieces, destFields));
    }

    @Test
    void arePiecesOfPlayerAtDestination_yellowPicesNotInOrderAtDestination() {
        Map<Integer,String> currentPieces = new HashMap<>();
        currentPieces.put(1, "field95");
        currentPieces.put(2, "field96");
        currentPieces.put(3, "field94");
        currentPieces.put(4, "field93");
        Map<Integer,String> destFields = new HashMap<>();
        destFields.put(1, "field96");
        destFields.put(2, "field95");
        destFields.put(3, "field94");
        destFields.put(4, "field93");
        assertFalse(Round.arePiecesOfPlayerAtDestination(currentPieces, destFields));
    }
}