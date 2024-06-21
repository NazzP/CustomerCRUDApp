package org.example.customercrudapp.exception;

public class NullEntityReferenceException extends RuntimeException {

    public NullEntityReferenceException(String message) {
        super(message);
    }
}