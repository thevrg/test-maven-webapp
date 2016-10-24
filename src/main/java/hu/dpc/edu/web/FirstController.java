package hu.dpc.edu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by vrg on 24/10/16.
 */
@Controller
public class FirstController {

    @ModelAttribute("message")
    public String message(@RequestParam(required = false, defaultValue = "Hello world!") String message) {
        return message;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("udvozlet", "Hello");
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model, @RequestParam(value="name", required = false, defaultValue = "Akárki") String nev, HttpServletRequest request) {
        model.addAttribute("szoveg", "Hello, dear " + nev + "!");
        model.addAttribute("acceptHeader", request.getHeader("Accept"));
        return "udvozlet";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String add(@RequestParam(name="value") String[] values) {

//        new ToIntFunction<String>() {
//            @Override
//            public int applyAsInt(String value) {
//                return Integer.parseInt(value);
//            }
//        }

        final int sum = Stream.of(values)
                .mapToInt(Integer::parseInt)
                .sum();
        return "Result is : " + sum;
    }

    @RequestMapping(value = "/add/{szam1:\\d{2}}/{szam2:\\d{2,5}}", method = RequestMethod.GET)
    @ResponseBody
    public String add2(@PathVariable(name="szam1") int szam1, @PathVariable(name="szam2") int szam2) {
        final int sum = szam1 + szam2;
        return "Result is : " + sum;
    }


    @RequestMapping(value = "/add3/{szam1:\\d{2}}/{szam2:\\d{2,5}}", method = RequestMethod.GET)
    public void add3(@PathVariable(name="szam1") int szam1, @PathVariable(name="szam2") int szam2, Writer writer) throws IOException {
        final int sum = szam1 + szam2;
        writer.write("Result is : " + sum);
    }

}
