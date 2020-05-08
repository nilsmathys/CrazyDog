package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

public class CardsOnHand {
    private List<Card> hand;

    public CardsOnHand() {
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public Card discardCard(int cardId) {
        Card cardToDiscard = null;
        for(int i=0; i<hand.size(); i++) {
            if (hand.get(i).getId() == cardId) {
                cardToDiscard = hand.get(i);
            }
        }
        hand.remove(cardToDiscard);
        return cardToDiscard;
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    /**
     * Remove all cards from the hand
     */
    public void discardAllCards() {
        hand.clear();
    }
}
