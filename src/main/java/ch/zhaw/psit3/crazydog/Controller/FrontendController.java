package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Card.CardsOnHand;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Player.Team;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class FrontendController {

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesPieces", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GameField> getChangesPieces() {
        List<GameField> gameFieldList = CrazyDog.getGameBoard().getFields();
        // TODO: Improve performance by only returning the fields that actually changed!!!
        // TODO: We can get all the fields that changed by asking the GameLogic
        return gameFieldList;
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesInstructions", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List returnChangesInstructions() {
        //System.out.println("/getchangesInstructions Controller was called");
        return UserInstructions.getUserInstructions();
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesCurrentPlayer", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int returnChangesCurrentPlayer() {
        //System.out.println("/getchangesCurrentPlayer Controller was called");
        return CrazyDog.getNextPlayer();
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesCurrentDirection", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String returnChangesCurrentDirection() {
        //System.out.println("/getchangesCurrentDirection Controller was called");
        Map<Direction, String> directionMap = Map.of(Direction.CLOCKWISE,"clockwise", Direction.COUNTERCLOCKWISE, "counterclockwise");
        return directionMap.get(CrazyDog.getDirection());
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server, when the listenToClicks-Controller was called.
    @RequestMapping(value = "/getchangesAllGamefields", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GameField> returnAllGameFields() {
        //System.out.println("/getchangesAllGamefields Controller was called");
        return CrazyDog.getGameBoard().getFields();
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server, when the listenToClicks-Controller was called.
    @RequestMapping(value = "/getchangesRoundNr", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int returnRoundNr() {
        //System.out.println("/getchangesRoundNr Controller was called");
        return CrazyDog.getRoundNumber();
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the hand for a specific player.
    @RequestMapping(value = "/getchangesCardsOnHand")
    public String returnChangesCardsOnHand(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("id") != null) {
            int playerId = Integer.parseInt(request.getSession().getAttribute("id").toString());
            Map<Integer, CardsOnHand> playerAndHand = Round.getPlayerAndHand();
            model.addAttribute("playerandhand", playerAndHand.get(playerId).getHand());
        }
        return "game :: #handBlock";
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the boolean value whether or not the round has started. If so the button will be hidden.
    @RequestMapping(value = "/getChangesForButton", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boolean returnChangesForButton() {
        return Round.isRoundStarted();
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the winner team if there is already one, otherwise null.
    @RequestMapping(value = "/getWinningTeam", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Team returnWinningTeam() {
        return CrazyDog.getWinnerTeam();
    }
}