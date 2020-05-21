package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManualController {

    @GetMapping("/spielanleitung")
    public String index(HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("id") == null) {
            model.addAttribute("player", new Player());
            model.addAttribute("loginerror", "Bitte melden Sie sich an");
            return "login";
        }
        return "spielanleitung";
    }

}
