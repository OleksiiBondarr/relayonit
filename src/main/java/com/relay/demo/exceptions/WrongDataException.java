package com.relay.demo.exceptions;

public class WrongDataException extends RuntimeException {
    public WrongDataException() {
        super("Provided Data is incorrect");
    }
}