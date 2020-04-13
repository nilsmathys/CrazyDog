package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

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

    public int getDeckSize() {
        return cardDeck.size();
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    public Card getCardFromDeck() {
        if (cardDeck.isEmpty()) {
            createDeck();
        }
        return cardDeck.remove(0);
    }
}
