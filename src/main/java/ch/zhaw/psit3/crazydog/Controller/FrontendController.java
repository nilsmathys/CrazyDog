package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class FrontendController {

    FieldAndPiece source;
    FieldAndPiece dest;
    FieldAndPiece[] sourceAndDestination = new FieldAndPiece[2];
    List<String> userInstructions;
    int currentPlayerID;
    Direction direction;


    // This method is reponsible for listening to ajax click events and handle their data.
    // It returns an array containing two FieldAndPiece Objects: The source and destination.
    @RequestMapping(value = "/listenclicks", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] listenToClicks(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        // FieldAndPiece Objects that store the original user values
        source = new FieldAndPiece(jsonObj.getString("sourcefield"), jsonObj.getString("sourcepiece"));
        dest = new FieldAndPiece(jsonObj.getString("destfield"), jsonObj.getString("destpiece"));
        sourceAndDestination[0] = source;
        sourceAndDestination[1] = dest;

        // Debugging
        System.out.println(source.getPiece() + " on " + source.getField() + " wants to move to " + dest.getField());
        System.out.println("At the moment, " + dest.getPiece() + " is on this field.");

        // .... Server Logic ....
        // .... More Server Logic ....

        // FieldAndPiece Objects that are returned and will change how the frontend looks
        String temp = source.getPiece();
        source.setPiece(dest.getPiece());
        dest.setPiece(temp);

        GameBoard.put(source);      // Update the game state!
        GameBoard.put(dest);        // Update the game state!

        return sourceAndDestination;
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server, when the listenToClicks-Controller was called.
    @RequestMapping(value = "/getchanges", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] returnChanges() {
        //System.out.println("/getchanges Controller was called");
        return sourceAndDestination;
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

}