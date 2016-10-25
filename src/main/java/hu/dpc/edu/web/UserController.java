package hu.dpc.edu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by vrg on 25/10/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private MyModel model;

    @Autowired
    public UserController(MyModel model) {
        this.model = model;
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody User user) {

        final long id = model.addUser(user);

        return ResponseEntity
                .created(URI.create(Long.toString(id)))
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable("id") long id) {
        return model.findUserById(id);
    }

    @RequestMapping(value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User test() {
        return new User("kovacs", "bela");
    }
}
