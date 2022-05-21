package com.example.carresource.exception;

public class CustomerNotFoundException extends RuntimeException {
    private static final String message = "customer not found";

    public CustomerNotFoundException() {
        super(message);
    }
}
