package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.Model.Player.Player;
import ch.zhaw.psit3.crazydog.Model.Player.PlayerDAO;
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
    public String loginSubmit(@ModelAttribute Player player, Model model) {
        // Try to get the User Data out of the data base
        player = PlayerDAO.getPlayerByUsernameAndPw(player.getUsername(), player.getPassword());

        // Check if the user exists. If the player ID is 0, the user doesn't exist in the database.
        if(player.getId() != 0) {
            // Set session
            return "index";
        }
        else {
            System.out.println("This user doesn't exist.");
            model.addAttribute("loginerror", "Email or Password was wrong");
            model.addAttribute("player", new Player()); // Return empty player to reset the HTML form
            return "login";
        }
    }
}
