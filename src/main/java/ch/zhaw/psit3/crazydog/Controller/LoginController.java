package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("player", new Player());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Player player, Model model, HttpServletResponse response, HttpServletRequest request) {
        // Try to get the User Data out of the data base
        player = PlayerDAO.getPlayerByUsernameAndPw(player.getUsername(), player.getPassword());
        // Check if the user exists. If the player ID is 0, the user doesn't exist in the database.
        if (player.getId() != 0) {
            // Set session
            HttpSession session = request.getSession();
            session.setAttribute("id", String.valueOf(player.getId()));
            session.setMaxInactiveInterval(60);
            return "redirect:/index";   // redirect is necessary to change to URL to /index
        } else {
            LOGGER.warning("This user doesn't exist. Email or Password was wrong.");
            model.addAttribute("loginerror", "Username oder Passwort falsch");
            model.addAttribute("player", new Player()); // Return empty player to reset the HTML form
            return "login";
        }
    }
}
