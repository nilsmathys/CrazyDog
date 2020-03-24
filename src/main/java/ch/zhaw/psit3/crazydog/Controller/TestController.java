package ch.zhaw.psit3.crazydog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String makeTest() {
        System.out.println("Ajax hat Zugriff auf den Controler");
        return "index";
    }
}