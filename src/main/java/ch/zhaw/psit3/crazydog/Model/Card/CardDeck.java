package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

/**
 * <h1>CardDeck</h1>
 * The CardDeck creates a shuffled deck of 110 cards (6x questionmark, 8x other cards).<br>
 * During the game this deck is used for distributing cards to the players.
 *
 * @author S. Werlin
 * @version 1.0
 * @since April 2020
 */
public class CardDeck {
    private static List<Card> cardDeck;

    public CardDeck() {
        cardDeck = new ArrayList<>();
    }

    /**
     * Creates new stock with 110 cards (6x questionmark, 8x other cards) and shuffles cards.
     */
    public void createDeck() {
        List<Card> cardList = CardDAO.getAllCards();
        cardDeck = new ArrayList<>();
        for(int i=0; i<cardList.size(); i++) {
            if("questionmark".equals(cardList.get(i).getName())) {
                for(int k=1; k<=6; k++) {
                    String customId = "" + cardList.get(i).getId() + k;
                    cardDeck.add(new Card(Integer.parseInt(customId), cardList.get(i).getName(), cardList.get(i).getValue()));
                }
            } else {
                for(int l=1; l<=8; l++) {
                    String customId = "" + cardList.get(i).getId() + l;
                    cardDeck.add(new Card(Integer.parseInt(customId), cardList.get(i).getName(), cardList.get(i).getValue()));
                }
            }
        }
        Collections.shuffle(cardDeck);
    }

    /**
     * Evaluates how many cards are left in the current deck
     *
     * @return number of remaining deck cards
     */
    public int getDeckSize() {
        return cardDeck.size();
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    /**
     * Gets the first card of the current card deck to distribute to a player
     *
     * @return Card object
     */
    public Card getCardFromDeck() {
        if (cardDeck.isEmpty()) {
            createDeck();
        }
        return cardDeck.remove(0);
    }
}
