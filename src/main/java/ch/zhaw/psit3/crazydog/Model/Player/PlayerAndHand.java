package ch.zhaw.psit3.crazydog.Model.Player;

import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;


public class PlayerAndHand {
    private Player player;
    private CardsOnHand hand;

    public PlayerAndHand (Player player, CardsOnHand hand) {
        this.player = player;
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public CardsOnHand getHand() {
        return hand;
    }
}


