package hu.dpc.edu.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by vrg on 25/10/16.
 */
public class UserNotFoundException extends UserException {
    private long userId;

    public UserNotFoundException(String message, long userId) {
        super(message);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
