package ch.zhaw.psit3.crazydog.Model.Card;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {
    CardDeck cardDeck;
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

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    void createDeck() {
        cardDeck.createDeck();
        assertEquals(110, cardDeck.getDeckSize());
        int card2Counter = 0; // must be 8 at the end
        int card3Counter = 0; // must be 8 at the end
        int card4Counter = 0; // must be 8 at the end
        int card5Counter = 0; // must be 8 at the end
        int card6Counter = 0; // must be 8 at the end
        int card7Counter = 0; // must be 8 at the end
        int card8Counter = 0; // must be 8 at the end
        int card9Counter = 0; // must be 8 at the end
        int card10Counter = 0; // must be 8 at the end
        int card11Counter = 0; // must be 8 at the end
        int card12Counter = 0; // must be 8 at the end
        int card13Counter = 0; // must be 8 at the end
        int pieceexchangeCounter = 0; // must be 8 at the end
        int questionCardCounter = 0; //must be 6 at the end

        while (cardDeck.getDeckSize() > 0) {
            switch (cardDeck.getCardFromDeck().getId()) {
                case 2:
                    card2Counter += 1;
                    break;
                case 3:
                    card3Counter += 1;
                    break;
                case 4:
                    card4Counter += 1;
                    break;
                case 5:
                    card5Counter += 1;
                    break;
                case 6:
                    card6Counter += 1;
                    break;
                case 7:
                    card7Counter += 1;
                    break;
                case 8:
                    card8Counter += 1;
                    break;
                case 9:
                    card9Counter += 1;
                    break;
                case 10:
                    card10Counter += 1;
                    break;
                case 11:
                    card11Counter += 1;
                    break;
                case 12:
                    card12Counter += 1;
                    break;
                case 13:
                    card13Counter += 1;
                    break;
                case 14:
                    questionCardCounter += 1;
                    break;
                case 15:
                    pieceexchangeCounter += 1;
                    break;
                default:
                    System.out.println("Test fehlgeschlagen");
            }

        }
        assertEquals(8, card2Counter);
        assertEquals(8, card3Counter);
        assertEquals(8, card4Counter);
        assertEquals(8, card5Counter);
        assertEquals(8, card6Counter);
        assertEquals(8, card7Counter);
        assertEquals(8, card8Counter);
        assertEquals(8, card9Counter);
        assertEquals(8, card10Counter);
        assertEquals(8, card11Counter);
        assertEquals(8, card12Counter);
        assertEquals(8, card13Counter);
        assertEquals(8, pieceexchangeCounter);
        assertEquals(6, questionCardCounter);
    }

    @Test
    void getDeckSize() {
        assertEquals(0, cardDeck.getDeckSize());
        cardDeck.createDeck();
        assertEquals(110, cardDeck.getDeckSize());
        cardDeck.getCardFromDeck();
        assertEquals(109, cardDeck.getDeckSize());
    }

    @Test
    void getCardFromDeck() {
        assertEquals(0, cardDeck.getDeckSize());
        cardDeck.getCardFromDeck();
        assertEquals(109, cardDeck.getDeckSize());
        assertEquals(Card.class, cardDeck.getCardFromDeck().getClass());
    }
}