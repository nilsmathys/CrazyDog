package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Game.Turn;
import ch.zhaw.psit3.crazydog.Model.Game.Move;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This controller is responsible for providing data to ajax calls.
 **/
@Controller
public class TurnController {
    private static final Logger LOGGER = Logger.getLogger(TurnController.class.getName());

    // This method is reponsible for listening to clicks on cards and then return the fields the pieces would
    // land on, if the player would play that card.
    @RequestMapping(value = "/getsourcefields", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<GameField> getSourceFields(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject(json);
        // Get all the Values
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class
        // Calculate the Destinations in the GameLogic
        Turn.calculateMoves(cardValue, sessionId);
        List<Move> moves = Turn.getMoves();

        List<GameField> sourceFields = new ArrayList<>();
        for (Move move : moves) {
            sourceFields.add(move.getSourceField());
        }

        return sourceFields;
    }

    // This method is responsible for sending back the calculated destination fields
    @RequestMapping(value = "/getdestinationfields", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<GameField> getDestinationFields(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject(json);
        // Get all the Values
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class
        String sourceFieldCSSId = jsonObj.getString("sourceField"); // This value will be given to the gamelogic class

        // Calculate the Destinations in the GameLogic
        Turn.calculateMoves(cardValue, sessionId);     // We don't need to give the sourceField as parameter. All possible moves with all possible pieces gets calculated everytime.
        List<Move> moves = Turn.getMoves();

        List<GameField> destinationFields = new ArrayList<>();
        for (Move move : moves) {
            if (move.getSourceField().getCssId().equals(sourceFieldCSSId)) {
                destinationFields.add(move.getDestinationField());
            }
        }
        return destinationFields;
    }

    // This method is responsible for taking JSON objects from javascript function sendCardAndIdAndDestination()
    // It creates new Objects from the JSON and gives these Objects to the GameLogic Class.
    @RequestMapping(value = "/makemove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Message makeMove(@RequestBody String json) {
        LOGGER.fine("/makemove was called.");
        JSONObject jsonObj = new JSONObject(json);
        // Get all the Values
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class
        String sourceFieldCSSId = jsonObj.getString("correctSourceField"); // This value will be given to the gamelogic class
        String destinationFieldCSSId = jsonObj.getString("destinationField"); // This value will be given to the gamelogic class
        int chosenCardId = jsonObj.getInt("chosenCardId"); // This value will be given to the gamelogic class
        // Give these values to the GameLogic to make the move
        Turn.makeMove(cardValue, sessionId, sourceFieldCSSId, destinationFieldCSSId, chosenCardId);

        return Turn.getSuccessMessage();
    }

    // Is responsible for telling the GameLogic to change Direction
    @RequestMapping(value = "/changedirection", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Message changedirection(@RequestBody String json) {
        LOGGER.fine("/changedirection was called.");
        JSONObject jsonObj = new JSONObject(json);
        int chosenCardId = jsonObj.getInt("chosenCardId"); // This value will be given to the gamelogic class
        Turn.changeDirection(chosenCardId);
        return Turn.getSuccessMessage();
    }
}