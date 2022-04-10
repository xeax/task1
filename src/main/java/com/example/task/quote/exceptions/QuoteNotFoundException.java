package com.example.task.quote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuoteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5448425855715708741L;

    public QuoteNotFoundException(long id) {
        super("Couldn't find a quote with an id=" + id);
    }

}
