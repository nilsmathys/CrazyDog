package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class GameController {

    boolean roundStarted = false;

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

/*    @ModelAttribute("fieldsandpieces")
    public Map<String, String> populateFieldsAndPieces() {
        Map<String, String> fieldsAndPieces = CrazyDog.getGameBoard().getFieldsAndPieces();
        return fieldsAndPieces;
    }

    @ModelAttribute("playerandhand")
    public List<Card> populatePlayerAndHand() {
        int playerId = 1;
        Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
        return playerAndHand.get(playerId).getHand();
    }*/


    @RequestMapping(value = { "/game" }, method = RequestMethod.GET)
    public String foo(Model model) {
        Map<String, String> fieldsAndPieces = CrazyDog.getGameBoard().getFieldsAndPieces();
        model.addAttribute("fieldsandpieces", fieldsAndPieces);

        int playerId = 1;
        Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
        model.addAttribute("playerandhand", playerAndHand.get(playerId).getHand());

        model.addAttribute("roundStarted", roundStarted);
        System.out.println("Return to game");
        return "game";
    }

    @RequestMapping(value="exchangeCard")
    public ModelAndView exchangeCardService(@RequestParam(value = "selectedCardId") String selectedCardId, Model model) {

        int playerId = 1;
        Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
        CardsOnHand hand = playerAndHand.get(playerId);
        Card cardToChange = hand.discardCard(Integer.parseInt(selectedCardId));

        // hand teamplayer
        int playerId2 = 2;
        CardsOnHand handTeamplayer = playerAndHand.get(playerId2);
        handTeamplayer.takeCard(cardToChange);

        roundStarted = true;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/game");
        return modelAndView;
    }

}

