package hu.dpc.edu.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import sun.misc.Resource;

/**
 * Created by vrg on 26/10/16.
 */
public class UserResource extends ResourceSupport {

    private User content;

    public UserResource(User content, Link link) {
        this(content);
        add(link);
    }

    @JsonCreator
    public UserResource(@JsonProperty("content") User content) {
        this.content = content;
    }

    public User getContent() {
        return content;
    }
}
