package hu.dpc.edu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by vrg on 25/10/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private LinkBuilderProvider<User> userLinkBuilderProvider;
    private MyModel model;

    @Autowired
    public UserController(MyModel model, @ForUsers LinkBuilderProvider linkBuilderProvider) {
        this.model = model;
        this.userLinkBuilderProvider = linkBuilderProvider;
    }

    @ExceptionHandler
    public ResponseEntity<Message>handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Message.notFound(ex.getMessage()));
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(@RequestParam(name = "withContent", required = false, defaultValue = "true") boolean withContent) {
        if (withContent) {
            return ResponseEntity.ok(model.getUsers().stream()
                    .map(user -> new UserResource(user,
                            userLinkBuilderProvider.getLinkBuilder(user).withSelfRel()))
                    .collect(Collectors.toList()));

        } else {
            final ResourceSupport response = new ResourceSupport();

            model.getUsers().stream()
                    .map(user -> userLinkBuilderProvider.getLinkBuilder(user).withRel("user"))
                    .forEach(response::add);

            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {

        final long id = model.addUser(user);


        user.setId(id);

        return ResponseEntity
                .created(userLinkBuilderProvider.getLinkBuilder(user).toUri())
                .body(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable("id") long id) {
        return model.findUserById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id:\\d+}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable("id") long id, @RequestBody User user) {
        user.setId(id);
        model.updateUser(user);
        return ResponseEntity.ok(Message.success("Successfully updated user"));
    }

    @RequestMapping(value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User test() {
        return new User("kovacs", "bela");
    }
}
