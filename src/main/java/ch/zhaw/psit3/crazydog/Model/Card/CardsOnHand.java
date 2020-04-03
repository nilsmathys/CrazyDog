package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

public class CardsOnHand {
    private static List<Card> hand;

    public CardsOnHand() {
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void playCard(String playerName) {
        Card selectedCard = getSelectedCard();
        // TODO output should later be displayed in browser
        System.out.println(playerName + " hat die Karte " + selectedCard.getName() + " ausgespielt.");
        if ("standard".equals(selectedCard.getName())) {
            // TODO continue with selecting piece
        } else {
            // TODO continue selecting action
        }
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public Card discardCard() {
        Card cardToDiscard = getSelectedCard();
        hand.remove(cardToDiscard);
        return cardToDiscard;
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public Card getSelectedCard() {
        Card selectedCard = null;
        for(int i=0; i<hand.size(); i++) {
            if (hand.get(i).isSelected() == true) {
                selectedCard = hand.get(i);
            } else {
                // TODO: return automatic selected card
            }
        }
        return selectedCard;
    }
}
