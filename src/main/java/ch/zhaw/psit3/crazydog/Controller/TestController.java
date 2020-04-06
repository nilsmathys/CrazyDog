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

/* Dieser Kontroller ist zuständig für das Empfangen und verarbeiten der Klicks*/
@Controller
public class TestController {

    FieldAndPiece source;
    FieldAndPiece dest;
    FieldAndPiece[] sourceAndDestination = new FieldAndPiece[2];

    @RequestMapping(value = "/footest", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FieldAndPiece[] checkUsername(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        // FieldAndPiece Objects that store the original user values
        source = new FieldAndPiece(jsonObj.getString("sourcefield"), jsonObj.getString("sourcepiece"));
        dest = new FieldAndPiece(jsonObj.getString("destfield"), jsonObj.getString("destpiece"));
        sourceAndDestination[0] = source;
        sourceAndDestination[1] = dest;

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
}