package ch.zhaw.psit3.crazydog.Controller;

import ch.zhaw.psit3.crazydog.CrazyDog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class AdminController {
    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

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
        LOGGER.info("kill Server was called");
        CrazyDog.kill();
        return "admin";
    }

}
