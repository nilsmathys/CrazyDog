package ch.zhaw.psit3.crazydog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/game")
    public String game() {
        System.out.println("Game started");
        return "game";
    }
}
