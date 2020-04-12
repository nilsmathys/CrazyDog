package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.GameState;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Map<Integer, CardsOnHand> playerAndHand = GameState.getAllPlayerAndHand();
        model.addAttribute("playerandhand", playerAndHand.get(1).getHand());

        System.out.println("Return to game");
        return "game";
    }
}

