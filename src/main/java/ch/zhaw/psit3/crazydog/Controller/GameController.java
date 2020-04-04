package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
public class GameController {

    Piece piece = new Piece(1, 2, 3, "Identität");
    @GetMapping("/game")
    @ResponseBody
    public ModelAndView createGameWithModelAndView() {
        ModelAndView mv = new ModelAndView("game");
        mv.addObject("attribut1", "wert1");
        return mv;
    }

}
