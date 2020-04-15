package ch.zhaw.psit3.crazydog.Model.Card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDAOTest {
    static Card card2;
    static Card card3;
    static Card card4;
    static Card card5;
    static Card card1or11;
    static Card cardQuestion;
    static Card cardPieceExchange;

    @BeforeAll
    static void beforeAll() {
        card2 = new Card(2, "standard", 2);
        card3 = new Card(3, "changeDirection", 3);
        card4 = new Card(4, "four", 4);
        card5 = new Card(5, "standard", 5);
        card1or11 = new Card(11, "oneEleven", 0);
        cardQuestion = new Card(14, "questionmark", 0);
        cardPieceExchange = new Card(15, "pieceExchange", 0);
    }

    @Test
    void getCardById() {
        assertEquals(card2.getValue(), CardDAO.getCardById(2).getValue());
        assertEquals(card3.getName(), CardDAO.getCardById(3).getName());
        assertEquals(card4.getName(), CardDAO.getCardById(4).getName());
        assertEquals(card5.getId(), CardDAO.getCardById(5).getId());
        assertEquals(card1or11.getName(), CardDAO.getCardById(11).getName());
        assertEquals(cardQuestion.getValue(), CardDAO.getCardById(14).getValue());
        assertEquals(cardPieceExchange.getName(), CardDAO.getCardById(15).getName());
    }

    @Test
    void getAllCards() {
        List<Card> cards = CardDAO.getAllCards();
        assertEquals(card2.getValue(), cards.get(0).getValue());
        assertEquals(card3.getName(), cards.get(1).getName());
        assertEquals(card4.getName(), cards.get(2).getName());
        assertEquals(card5.getId(), cards.get(3).getId());
        assertEquals(card1or11.getId(), cards.get(9).getId());
        assertEquals(cardQuestion.getValue(), cards.get(12).getValue());
        assertEquals(cardPieceExchange.getName(), cards.get(13).getName());
    }

    @Test
    void testExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CardDAO.getCardById(1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CardDAO.getCardById(16);
        });
    }
}