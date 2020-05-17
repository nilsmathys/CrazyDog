package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class GameController {

    @ModelAttribute("team1")
    public List<Player> populateTeam1() {
        if(CrazyDog.isInitialized()) {
            List<Player> team1 = new ArrayList<>();
            team1.add(CrazyDog.getTeam1().getPlayer1());
            team1.add(CrazyDog.getTeam1().getPlayer2());
            return team1;
        }
        else {
            return null;
        }
    }

    @ModelAttribute("team2")
    public List<Player> populateTeam2() {
        if(CrazyDog.isInitialized()) {
            List<Player> team2 = new ArrayList<>();
            team2.add(CrazyDog.getTeam2().getPlayer1());
            team2.add(CrazyDog.getTeam2().getPlayer2());
            return team2;
        }
        else {
            return null;
        }
    }

    @GetMapping("/game")
    public String playGame(HttpServletRequest request, Model model) {

        if(CrazyDog.isInitialized()) {
            if (request.getSession().getAttribute("id") != null) {

                List<GameField> fields = CrazyDog.getGameBoard().getFields();
                model.addAttribute("fields", fields);

                int playerId = Integer.parseInt(request.getSession().getAttribute("id").toString());
                Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
                model.addAttribute("playerandhand", playerAndHand.get(playerId).getHand());

                model.addAttribute("userInstructions", UserInstructions.getUserInstructions());
                model.addAttribute("currentPlayerID", CrazyDog.getNextPlayer());
                model.addAttribute("roundNr", CrazyDog.getNextPlayer());
                model.addAttribute("sessionId", request.getSession().getAttribute("id"));

                Map<Direction, String> directionMap = Map.of(Direction.CLOCKWISE, "clockwise", Direction.COUNTERCLOCKWISE, "counterclockwise");
                model.addAttribute("gameDirection", directionMap.get(CrazyDog.getDirection()));

                return "game";
            } else {
                model.addAttribute("player", new Player());
                model.addAttribute("loginerror", "Bitte melden Sie sich an");
                return "login";
            }
        }
        else {
            return "index";
        }
    }

    //Set the selected card as the exchange card for specific player in Round
    @RequestMapping(value = "/exchangeCard", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void exchangeCardService(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        int sessionId = jsonObj.getInt("sessionId");
        int chosenCardId = jsonObj.getInt("chosenCardId");
        Round.setExchangeCard(sessionId, chosenCardId);
    }
}

