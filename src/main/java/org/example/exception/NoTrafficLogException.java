package org.example.exception;

public class NoTrafficLogException extends RuntimeException {
    public NoTrafficLogException(String errorMessage) {
        super(errorMessage);
    }

}
