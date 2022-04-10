package com.example.task.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserLoginIsAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = 5448425855715732314L;

    public UserLoginIsAlreadyInUseException(String login) {
        super("User login '" + login + "' is aleady in use.");
    }

}
