package ch.zhaw.psit3.crazydog.Controller;

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

    Piece piece = new Piece(1, 1, 2, "piece1blue");

    @RequestMapping(value = "/footest", method = RequestMethod.POST,  consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Piece checkUsername(@RequestBody String json) {
        JSONObject jsonObj =new JSONObject(json);
        System.out.println(jsonObj.getString("sourceid"));
        System.out.println(jsonObj.getString("destinationid"));
        return piece;
    }
}