package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.GameState;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class GameController {

    @RequestMapping(value = { "/game" }, method = RequestMethod.GET)
    public String foo(Model model) {
        List<Player> players = GameState.getPlayers();
        model.addAttribute("players", players);

        Map<String, String> fieldsAndPieces = GameState.getAllFieldsAndPieces();
        model.addAttribute("fieldsandpieces", fieldsAndPieces);

        CardsOnHand cardsOnHand = new CardsOnHand();
        cardsOnHand.takeCard(new Card(2, "standard", 2));
        cardsOnHand.takeCard(new Card(10, "standard", 10));
        cardsOnHand.takeCard(new Card(5, "standard", 5));
        cardsOnHand.takeCard(new Card(9, "standard", 9));
        cardsOnHand.takeCard(new Card(6, "standard", 6));
        cardsOnHand.takeCard(new Card(11, "eleven", 0));
        model.addAttribute("cardsOnHand", cardsOnHand.getHand());

        System.out.println("Return to game");
        return "game";
    }
}

