package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Helper.CookieChecker;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreateGameController {

    @GetMapping("/creategame")
    public String createGame(HttpServletRequest request, Model model) {
        boolean cookieIsSet = CookieChecker.isCookieSet(request);

        if(cookieIsSet) {
            // Create Game Logic ....
            // More Create Game Logic ....
            return "creategame";
        }
        else {
            model.addAttribute("player", new Player());
            model.addAttribute("loginerror", "Please login to create a game");
            return "login";
        }
    }
}
