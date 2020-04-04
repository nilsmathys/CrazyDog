package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    Piece piece = new Piece(1, 2, 3, "Identität");

    @GetMapping("/gametest")
    @ResponseBody
    public ModelAndView createGameWithModelAndView() {
        System.out.println("Activated");
        ModelAndView mv = new ModelAndView("game");
        mv.addObject("attribut1", "wertwichtig");
        return mv;
    }
}