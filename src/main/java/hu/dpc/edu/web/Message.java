package hu.dpc.edu.web;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 * Created by vrg on 25/10/16.
 */

public class Message {

    private String message;
    private int errorCode;

    private Message(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @JsonGetter("uzenet")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static Message notFound(String message) {
        return new Message(message, HttpStatus.NOT_FOUND.value());
    }

    public static Message success(String message) {
        return new Message(message, HttpStatus.OK.value());
    }

    public static Message unprocessableEntity(String message) {
        return new Message(message, HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}
