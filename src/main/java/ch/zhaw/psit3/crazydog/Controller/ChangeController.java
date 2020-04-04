package ch.zhaw.psit3.crazydog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChangeController {

    Student gina = new Student("Gina", 2);
    Student severin = new Student("Severin", 3);
    Student josi = new Student("Josiane", 4);
    Student marc = new Student("Marc", 5);
    List<Student> students = new ArrayList<>();
    @GetMapping("/foochange")
    @ResponseBody
    public List<Student> foochange() {
        System.out.println("ChangeController activated");

        gina.increaseID();
        severin.increaseID();
        josi.increaseID();
        marc.increaseID();

        students.add(gina);
        students.add(severin);
        students.add(josi);
        students.add(marc);

        return students;
    }
}

class Student {
    private String name = "remo";
    private int ID = 7;

    public Student(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public void increaseID() {
        ID++;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
}
