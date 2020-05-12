package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ManualController {

    @GetMapping("/spielanleitung")
    public String index(HttpServletRequest request, Model model) {

        if(request.getSession().getAttribute("id") != null) {

        } else {
            model.addAttribute("player", new Player());
            model.addAttribute("loginerror", "Bitte melden Sie sich an");
            return "login";
        }
        return "spielanleitung";
    }

}
