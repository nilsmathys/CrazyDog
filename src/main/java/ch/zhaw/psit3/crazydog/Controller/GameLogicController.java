package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.GameField.GameBoard;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class GameLogicController {

    FieldAndPiece source;
    FieldAndPiece dest;
    FieldAndPiece[] sourceAndDestination = new FieldAndPiece[1];

    // This method is reponsible for listening to ajax click events and handle their data.
    // It returns an array containing two FieldAndPiece Objects: The source and destination.
    @RequestMapping(value = "/calculatemoves", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] listenToClicks(@RequestBody String json) {
        System.out.println("/calculatemoves has been called");
        JSONObject jsonObj =new JSONObject(json);
        System.out.println("JSON Object was created");
        // FieldAndPiece Objects that store the original user values
        System.out.println(jsonObj.getInt("chosenCard"));
        System.out.println(jsonObj.getInt("sessionId"));
        //source = new FieldAndPiece(jsonObj.getString("sourcefield"), jsonObj.getString("sourcepiece"));
        //dest = new FieldAndPiece(jsonObj.getString("destfield"), jsonObj.getString("destpiece"));
        //sourceAndDestination[0] = source;
        //sourceAndDestination[1] = dest;

        // FieldAndPiece Objects that are returned and will change how the frontend looks
        //String temp = source.getPiece();
        //source.setPiece(dest.getPiece());
        //dest.setPiece(temp);

        //GameBoard.put(source);      // Update the game state!
        //GameBoard.put(dest);        // Update the game state!

        return sourceAndDestination;
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