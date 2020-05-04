package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Game.GameLogic;
import ch.zhaw.psit3.crazydog.Model.Game.Move;
import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Message.Message;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class GameLogicController {

    // This method is reponsible for listening to clicks on cards and then return the fields the pieces would
    // land on, if the player would play that card.
    @RequestMapping(value = "/calculatemoves", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GameField> calculateMoves(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        // Get the Choosen Card and the Players
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class
        // Calculate the Destinations in the GameLogic
        GameLogic.calculateMoves(cardValue, sessionId);
        List<Move> moves = GameLogic.getMoves();

        List<GameField> sourceFields = new ArrayList<>();
        System.out.println("Wichtig!!!");
        for ( Move move : moves) {
            System.out.println("Standort source Field: " + move.getSourceField().getIdForCalculation());
            System.out.println("Standort destination Field: " + move.getDestinationField().getIdForCalculation());
            sourceFields.add(move.getSourceField());      // Momentan wird nur die Source zurückgegeben.
        }

        return sourceFields;
    }

    // This method is reponsible for taking JSON objects from javascript function sendCardAndIdAndDestination()
    // It creates new Objects from the JSON and gives these Objects to the GameLogic Class.
    @RequestMapping(value = "/makemove", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message makeMove(@RequestBody String json) {
        System.out.println("/makemove was called");
        JSONObject jsonObj =new JSONObject(json);
        // Get the Choosen Card and the Players
        int cardValue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class
        String destinationField = jsonObj.getString("destinationField"); // This value will be given to the gamelogic class

        // Give these values to the GameLogic to check if this is a legal move
        GameLogic.makeMove(cardValue, sessionId, destinationField);

        return GameLogic.getSuccessMessage();
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