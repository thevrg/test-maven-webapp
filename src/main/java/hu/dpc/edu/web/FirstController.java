package hu.dpc.edu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vrg on 24/10/16.
 */
@Controller
public class FirstController {

    @ModelAttribute("message")
    public String message() {
        return "Hello World!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("udvozlet", "Hello");
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model, @RequestParam(value="name", required = false, defaultValue = "Akárki") String nev) {
        model.addAttribute("szoveg", "Hello, dear " + nev + "!");
        return "udvozlet";
    }
}
