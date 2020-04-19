package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("player", new Player());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Player player) {
        System.out.println(player.getUsername());
        System.out.println(player.getPassword());
        return "login";
    }
/*
    @RequestMapping(value = "/checklogindata", method = RequestMethod.POST)
    public RedirectView checkLoginData(RedirectAttributes attributes) {
        System.out.println("/checklogindata wurde angesprochen");
        attributes.addFlashAttribute("error", "Email oder Passwort ist falsch");

        Player player = PlayerDAO.getPlayerByUsernameAndPw("Spieler1", "test123");
        System.out.println(player.getUsername());
        System.out.println(player.getPw());

        return new RedirectView("login");
    }
*/
}
