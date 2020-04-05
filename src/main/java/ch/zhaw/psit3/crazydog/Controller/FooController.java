package ch.zhaw.psit3.crazydog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* Dieser Controller ist dafür zuständig, ganz normale Reloads des Users auf /foo abzufangen */

// Leitet den Benutzer auf die Seite foo zurück.
@Controller
public class FooController {

    @GetMapping("/foo")
    public String foo() {
        System.out.println("Return to foo");
        return "foo";
    }
}

