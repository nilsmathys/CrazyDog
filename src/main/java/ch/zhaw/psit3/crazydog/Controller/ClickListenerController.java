package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Piece.FieldAndPiece;
import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/* This controller is responsible for providing data to ajax calls. */
@Controller
public class ClickListenerController {

    FieldAndPiece source;
    FieldAndPiece dest;
    FieldAndPiece[] sourceAndDestination = new FieldAndPiece[2];
    boolean flagOverwrite;

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
        flagOverwrite = true;

        // Debugging
        System.out.println(source.getField());
        System.out.println(source.getPiece());
        System.out.println(dest.getField());
        System.out.println(dest.getPiece());

        // .... Server Logic ....
        // .... More Server Logic ....

        // FieldAndPiece Objects that are returned and will change how the frontend looks
        String temp = source.getPiece();
        source.setPiece(dest.getPiece());
        dest.setPiece(temp);

        return sourceAndDestination;
    }

    // This method is reponsible for listening to the continous ajax frontend-updater.
    // It returns the data that was processed by the server, when the listenToClicks-Controller was called.
    @RequestMapping(value = "/getchanges", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] returnChanges() {
        System.out.println("/getchanges Controller was called");
        return sourceAndDestination;
    }
}