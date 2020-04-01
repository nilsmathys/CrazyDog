package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

public class CardStock {
    private static List<Card> cardStock;

    public CardStock() {
        cardStock = new ArrayList<>();
    }

    /**
     * Creates new stock with 110 cards (6x questionmark, 8x other cards) and shuffles cards.
     */
    public void createStock() {
        List<Card> cardList = CardDAO.getAllCards();
        cardStock = new ArrayList<>();
        for(int i=0; i<cardList.size(); i++) {
            if("questionmark".equals(cardList.get(i).getName())) {
                for(int k=1; k<=6; k++) {
                    cardStock.add(cardList.get(i));
                }
            } else {
                for(int l=1; l<=8; l++) {
                    cardStock.add(cardList.get(i));
                }
            }
        }
        Collections.shuffle(cardStock);
    }

    public int getStockSize() {
        return cardStock.size();
    }

    public static List<Card> getCardStock() {
        return cardStock;
    }

    public void setCardStock(List<Card> list) {
        this.cardStock = list;
    }

    public Card getCardFromStock() {
        if (cardStock.isEmpty()) {
            createStock();
        }
        return cardStock.remove(0);
    }
}
