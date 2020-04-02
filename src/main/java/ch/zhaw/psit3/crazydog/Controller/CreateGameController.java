package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Piece.Piece;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreateGameController {

    Piece piece = new Piece(1, 2, 3, "Identität");

    // Bei dieser Methode wird das Objekt korrekt übergeben, aber die URL wird nachher http://localhost:8080/creategame1 sein...
    @GetMapping("/creategame1")
    @ResponseBody
    public ModelAndView createGameWithModelAndView() {
        ModelAndView mv = new ModelAndView("game");
        mv.addObject("attribut1", "wert1");
        return mv;
    }

    // Bei dieser Methode wird das Objekt korrekt übergeben, und die URL bleibt auf /game
    @GetMapping("/creategame2")
    public RedirectView createGame(RedirectAttributes redir){
        RedirectView redirectView = new RedirectView("/game", true);
        redir.addFlashAttribute("attribut2", piece);
        return redirectView;
    }

    // Bei dieser Methode wird das Objekt korrekt übergeben, aber die URL wird nachher http://localhost:8080/creategame3 sein...
    @GetMapping("/creategame3")
    public String createGameWithModel(Model model){
        model.addAttribute("attribut3", "wert3");
        return "game";
    }
}