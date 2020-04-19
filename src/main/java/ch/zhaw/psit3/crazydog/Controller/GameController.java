package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class GameController {

    @ModelAttribute("team1")
    public List<Player> populateTeam1() {
        List<Player> team1 = new ArrayList<>();
        team1.add(CrazyDog.getTeam1().getPlayer1());
        team1.add(CrazyDog.getTeam1().getPlayer2());
        return team1;
    }
    @ModelAttribute("team2")
    public List<Player> populateTeam2() {
        List<Player> team2 = new ArrayList<>();
        team2.add(CrazyDog.getTeam2().getPlayer1());
        team2.add(CrazyDog.getTeam2().getPlayer2());
        return team2;
    }

    @RequestMapping(value = { "/game" }, method = RequestMethod.GET)
    public String foo(Model model) {
        Map<String, String> fieldsAndPieces = CrazyDog.getGameBoard().getFieldsAndPieces();
        model.addAttribute("fieldsandpieces", fieldsAndPieces);

        int playerId = 1;
        Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
        model.addAttribute("playerandhand", playerAndHand.get(playerId).getHand());

        System.out.println("Return to game");
        return "game";
    }

}

