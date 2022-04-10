package com.example.task.quote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuotesNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5448425855715709933L;

    public QuotesNotFoundException() {
        super("The list of quotes is empty");
    }

}
