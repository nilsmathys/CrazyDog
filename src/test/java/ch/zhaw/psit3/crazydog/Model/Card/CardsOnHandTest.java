package ch.zhaw.psit3.crazydog.Model.Card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardsOnHandTest {
    CardsOnHand cardsOnHand;
    static Card card2;
    static Card card3;
    static Card card4;
    static Card card5;

    @BeforeAll
    static void beforeAll() {
        card2 = new Card(2, "standard", 2);
        card3 = new Card(3, "changeDirection", 3);
        card4 = new Card(4, "four", 4);
        card5 = new Card(5, "standard", 5);
    }

    @BeforeEach
    void setUp() {
        cardsOnHand = new CardsOnHand();
    }

    @Test
    void getHand() {
        cardsOnHand.takeCard(card2);
        cardsOnHand.takeCard(card4);
        assertEquals(card2.getValue(), cardsOnHand.getHand().get(0).getValue());
        assertEquals(card4.getValue(), cardsOnHand.getHand().get(1).getValue());
    }

    @Test
    void takeCard() {
        cardsOnHand.takeCard(card2);
        assertEquals(1, cardsOnHand.getHand().size());

    }

    @Test
    void discardCard() {
        cardsOnHand.takeCard(card2);
        cardsOnHand.takeCard(card3);
        cardsOnHand.takeCard(card4);
        assertEquals(card2.getValue(), cardsOnHand.discardCard(2).getValue());
    }

    @Test
    void isHandEmpty_noCardsAtHand() {
        assertTrue(cardsOnHand.isHandEmpty());
    }

    @Test
    void isHandEmpty_withCardAtHand() {
        cardsOnHand.takeCard(card2);
        assertFalse(cardsOnHand.isHandEmpty());
    }

    @Test
    void testDiscardAll() {
        cardsOnHand.takeCard(card2);
        cardsOnHand.takeCard(card3);
        cardsOnHand.takeCard(card4);
        cardsOnHand.takeCard(card5);
        cardsOnHand.discardAllCards();
        assertTrue(cardsOnHand.isHandEmpty());
    }

}