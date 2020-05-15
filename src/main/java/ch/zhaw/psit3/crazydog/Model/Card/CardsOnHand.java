package ch.zhaw.psit3.crazydog.Model.Card;

import java.util.*;

/**
 * <h1>CardsOnHand</h1>
 * The CardsOnHand object holds a list of cards of a certain player during a round.<br>
 * It can take a new card as well as discard one during the round.
 *
 * @author S. Werlin, R. Somma
 * @version 1.0
 * @since April 2020
 */
public class CardsOnHand {
    private List<Card> hand;

    public CardsOnHand() {
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    /**
     * Take a card given from the deck or another player.
     *
     * @param card Card object that needs to be added to the players hand
     */
    public void takeCard(Card card) {
        hand.add(card);
    }

    /**
     * Discard a card from the player's hand.
     *
     * @param cardId id of the card that is supposed to be discarded.
     * @return the Card object of the discarded card
     */
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

    /**
     * Retrieves the information whether or not a player is out of cards
     *
     * @return true if the player has no cards remaining
     */
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
