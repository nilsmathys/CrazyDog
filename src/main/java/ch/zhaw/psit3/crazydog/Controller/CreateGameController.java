package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreateGameController {

    @GetMapping("/creategame")
    public String createGame(HttpServletRequest request, Model model) {
        //boolean cookieIsSet = CookieChecker.isCookieSet(request);         // Might be used later.

        if (request.getSession().getAttribute("id") != null) {
            // Create Game Logic ....
            // More Create Game Logic ....
            model.addAttribute("sessionid", request.getSession().getAttribute("id"));
            return "creategame";
        } else {
            model.addAttribute("player", new Player());
            model.addAttribute("loginerror", "Bitte melden Sie sich an");
            return "login";
        }
    }
}
