package hu.dpc.edu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by vrg on 24/10/16.
 */
@RestController
@RequestMapping("/simple")
public class SimpleRestController {

    private MyModel model;

    @Autowired
    public SimpleRestController(MyModel model) {
        this.model = model;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity getMessage() {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(model.getMessage());
    }

    @RequestMapping(value="/message", method = RequestMethod.PUT, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void setMessage(@RequestBody String message) {
        model.setMessage(message);
    }
}
