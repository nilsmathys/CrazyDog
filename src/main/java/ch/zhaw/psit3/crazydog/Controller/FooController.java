package ch.zhaw.psit3.crazydog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController {

    @GetMapping("/foo")
    public String foo() {
        System.out.println("Return to foo");
        return "foo";
    }
}

