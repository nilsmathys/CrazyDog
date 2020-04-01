package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

public class CardOnHand {
    private List<Card> hand;

    public CardOnHand() {
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getSelectedCard() {
        Card selectedCard = null;
        for(int i=0; i<hand.size(); i++) {
            if (hand.get(i).getIsCardSelected() == true) {
                selectedCard = hand.get(i);
            } else {
                // TODO: return automatic selected card
            }
        }
        return selectedCard;
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }
}
