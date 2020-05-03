package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Card.Card;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class GameController {

    boolean buttonClicked = false;

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

    @GetMapping("/game")
    public String playGame(HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("id") != null) {
            Map<String, String> fieldsAndPieces = CrazyDog.getGameBoard().getFieldsAndPieces();
            model.addAttribute("fieldsandpieces", fieldsAndPieces);

            List<GameField> fields = CrazyDog.getGameBoard().getFields();
            model.addAttribute("fields", fields);

            int playerId = Integer.parseInt(request.getSession().getAttribute("id").toString());
            Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
            model.addAttribute("playerandhand", playerAndHand.get(playerId).getHand());

            if (buttonClicked) {
                model.addAttribute("roundStarted", true);
            } else {
                model.addAttribute("roundStarted", Round.isRoundStarted());
            }

            //model.addAttribute("sessionId", request.getSession().getAttribute("id"));
            model.addAttribute("userInstructions", UserInstructions.getUserInstructions());
            model.addAttribute("currentPlayerID", CrazyDog.getNextPlayer());
            model.addAttribute("sessionId", request.getSession().getAttribute("id"));

            Map<Direction, String> directionMap = Map.of(Direction.CLOCKWISE,"clockwise", Direction.COUNTERCLOCKWISE, "counterclockwise");
            model.addAttribute("gameDirection", directionMap.get(CrazyDog.getDirection()));

            return "game";
        }
        else {
            model.addAttribute("player", new Player());
            model.addAttribute("loginerror", "Please login to play a game");
            return "login";
        }
    }

    @RequestMapping(value="exchangeCard")
    public ModelAndView exchangeCardService(@RequestParam(value = "selectedCardId") String selectedCardId,
                                            @RequestParam(value = "sessionId") String sessionId) {

        Round.setExchangeCard(Integer.parseInt(sessionId), Integer.parseInt(selectedCardId));
        buttonClicked = true;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/game");
        return modelAndView;
    }

}

