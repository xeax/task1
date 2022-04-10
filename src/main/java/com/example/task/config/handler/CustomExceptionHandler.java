package com.example.task.config.handler;

import java.util.Map;
import java.util.HashMap;
 
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
 
    // Exception method for Invalid request body and Method Argument.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        for(ObjectError error : ex.getBindingResult().getFieldErrors()) {
            Object argument = error.getArguments()[0];
            if (argument instanceof DefaultMessageSourceResolvable){
                DefaultMessageSourceResolvable arg = (DefaultMessageSourceResolvable)argument;
                details.put(arg.getCodes()[1], error.getDefaultMessage());
            }
        }
        Object error = new ErrorResponse("Validation failed", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}

@Getter
@RequiredArgsConstructor
class ErrorResponse {
    private final String message;
    private final Map<String, String> details;
}