package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerAndHandTest {
    Player player;
    CardsOnHand hand;
    PlayerAndHand playerAndHand;

    @BeforeEach
    void setup() {
        player = new Player(1, "Tester", "tester@tester.ch", "test123");
        hand = new CardsOnHand();
        playerAndHand = new PlayerAndHand(player, hand);
    }

    @Test
    void getPlayer() {
        assertEquals(player, playerAndHand.getPlayer());
    }

    @Test
    void getHand() {
        assertEquals(hand, playerAndHand.getHand());
    }
}