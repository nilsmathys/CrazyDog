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
        int questionCardCounter = 0; //must be 6 at the end
        int pieceexchangeCounter = 0; // must be 8 at the end
        int i = 0;

        while (cardDeck.getDeckSize() > 0) {
            i = cardDeck.getCardFromDeck().getId();
            if (i % 150 == i) {
                if (i % 140 == i) {
                    if (i % 130 == i) {
                        if (i % 120 == i) {
                            if (i % 110 == i) {
                                if (i % 100 == i) {
                                    if (i % 90 == i) {
                                        if (i % 80 == i) {
                                            if (i % 70 == i) {
                                                if (i % 60 == i) {
                                                    if (i % 50 == i) {
                                                        if (i % 40 == i) {
                                                            if (i % 30 == i) {
                                                                if (i % 20 == i) {
                                                                } else {
                                                                    card2Counter += 1;
                                                                }
                                                            } else {
                                                                card3Counter += 1;
                                                            }
                                                        } else {
                                                            card4Counter += 1;
                                                        }
                                                    } else {
                                                        card5Counter += 1;
                                                    }
                                                } else {
                                                    card6Counter += 1;
                                                }
                                            } else {
                                                card7Counter += 1;
                                            }
                                        } else {
                                            card8Counter += 1;
                                        }
                                    } else {
                                        card9Counter += 1;
                                    }
                                } else {
                                    card10Counter += 1;
                                }
                            } else {
                                card11Counter += 1;
                            }
                        } else {
                            card12Counter += 1;
                        }
                    } else {
                        card13Counter += 1;
                    }
                } else {
                    questionCardCounter += 1;
                }
            } else {
                pieceexchangeCounter += 1;
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