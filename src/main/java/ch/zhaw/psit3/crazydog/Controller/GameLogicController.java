package ch.zhaw.psit3.crazydog.Controller;

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

    GameField gameField1;
    GameField gameField2;
    GameField gameField3;
    GameField gameField4;
    ArrayList<GameField> gameFieldList = new ArrayList<>();

    // This method is reponsible for listening to ajax click events and handle their data.
    // It returns an array containing two FieldAndPiece Objects: The source and destination.
    @RequestMapping(value = "/calculatemoves", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<GameField> calculateMoves(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        // Get the Choosen Card and the Players
        int cardvalue = jsonObj.getInt("chosenCard"); // This value will be given to the gamelogic class
        int sessionId = jsonObj.getInt("sessionId"); // This value will be given to the gamelogic class

        // This will be the objects that the Calculator class returns
        gameField1 = new GameField("field1");
        gameField2 = new GameField("field2");
        gameField3 = new GameField("field3");
        gameField4 = new GameField("field4");
        gameFieldList.add(gameField1);
        gameFieldList.add(gameField2);
        gameFieldList.add(gameField3);
        gameFieldList.add(gameField4);

        // We don0t have to update the game state yet, because the fields returned are not definitely choosen yet.
        //GameBoard.put(source);      // Update the game state!
        //GameBoard.put(dest);        // Update the game state!

        return gameFieldList;
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