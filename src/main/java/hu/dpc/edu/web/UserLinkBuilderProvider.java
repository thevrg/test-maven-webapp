package hu.dpc.edu.web;

import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@ForUsers
public class UserLinkBuilderProvider implements LinkBuilderProvider<User> {

    public LinkBuilder getLinkBuilder(User user) {
        try {
            return ControllerLinkBuilder.linkTo(UserController.class,
                    UserController.class.getMethod("findById", long.class),
                    user.getId());
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Never happens", ex);
        }
    }
}