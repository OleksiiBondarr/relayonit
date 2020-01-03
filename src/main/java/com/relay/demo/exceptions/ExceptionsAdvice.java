package com.relay.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistHandler(UserAlreadyExistException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String personNotFoundHandler(PersonNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(WrongDataException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String wrongDataHandler(WrongDataException ex) {
        return ex.getMessage();
    }

}
