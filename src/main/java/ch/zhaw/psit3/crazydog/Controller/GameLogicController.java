package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Game.GameLogic;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class GameLogicController {

    // This method is reponsible for listening to clicks on cards and then return the fields the pieces would
    // land on, if the player would play that card.
    @RequestMapping(value = "/calculatemoves", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<GameField> calculateMoves(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        // Get the Choosen Card and the Players
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class

        // Calculate the Destinations in the GameLogic
        GameLogic.calculateDestinations(cardValue, sessionId);

        // We don't have to update the game state yet, because the fields returned are not definitely choosen yet.
        // They just show where a user would land with his pieces.
        //GameBoard.put(source);      // Update the game state!
        //GameBoard.put(dest);        // Update the game state!

        return GameLogic.getDestinations();
    }
    /*
    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server, when the listenToClicks-Controller was called.
    @RequestMapping(value = "/getchanges", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] returnChanges() {
        System.out.println("/getchanges Controller was called");
        return sourceAndDestination;
    }
    */
}