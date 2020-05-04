package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Game.Direction;
import ch.zhaw.psit3.crazydog.Model.Game.UserInstructions;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchanges", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GameField> getChanges() {
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
        userInstructions = UserInstructions.getUserInstructions();
        return userInstructions;
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesCurrentPlayer", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int returnChangesCurrentPlayer() {
        //System.out.println("/getchangesCurrentPlayer Controller was called");
        currentPlayerID = CrazyDog.getNextPlayer();
        return currentPlayerID;
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server
    @RequestMapping(value = "/getchangesCurrentDirection", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String returnChangesCurrentDirection() {
        //System.out.println("/getchangesCurrentDirection Controller was called");
        direction = CrazyDog.getDirection();
        Map<Direction, String> directionMap = Map.of(Direction.CLOCKWISE,"clockwise", Direction.COUNTERCLOCKWISE, "counterclockwise");
        return directionMap.get(direction);
    }

}