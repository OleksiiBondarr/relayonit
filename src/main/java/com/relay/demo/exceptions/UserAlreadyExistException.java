package com.relay.demo.exceptions;


public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String login) {
        super("User already exists: " + login);
    }
}
