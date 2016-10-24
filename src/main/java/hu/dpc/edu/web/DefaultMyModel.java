package hu.dpc.edu.web;

import org.springframework.stereotype.Service;

/**
 * Created by vrg on 24/10/16.
 */
@Service
public class DefaultMyModel implements MyModel {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
