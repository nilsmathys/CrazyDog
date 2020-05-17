package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import ch.zhaw.psit3.crazydog.Model.Game.Round;
import ch.zhaw.psit3.crazydog.Model.Player.Player;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin(HttpServletRequest request) {
        return "admin";
    }

    // Starts the game
    @RequestMapping(value = "/startgame", method = RequestMethod.POST)
    public String startGame() {
        CrazyDog.initializeGame();
        return "admin";
    }

    // Resets the game
    @RequestMapping(value = "/killserver", method = RequestMethod.POST)
    public String killServer() {
        System.out.println("Kill Server was called");
        CrazyDog.kill();
        return "admin";
    }

}
