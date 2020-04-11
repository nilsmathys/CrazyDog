package ch.zhaw.psit3.crazydog.Model.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @BeforeEach
    void setUp() {
        card = new Card(1, "testCard", 13);
    }

    @Test
    void getId() {
        assertEquals(1, card.getId());
    }

    @Test
    void getName() {
        assertEquals("testCard", card.getName());
    }

    @Test
    void getValue() {
        assertEquals(13, card.getValue());
    }

    @Test
    void isSelected() {
        assertFalse(card.isSelected());
    }

    @Test
    void setIsCardSelected() {
        card.setIsCardSelected(true);
        assertTrue(card.isSelected());
        card.setIsCardSelected(false);
        assertFalse(card.isSelected());

    }
}