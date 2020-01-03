package com.relay.demo.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(int id) {
        super("Could not find person " + id);
    }
}