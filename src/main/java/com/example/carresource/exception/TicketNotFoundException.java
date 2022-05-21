package com.example.carresource.exception;

public class TicketNotFoundException extends RuntimeException {
    private static final String message = "ticket not found";

    public TicketNotFoundException() {
        super(message);
    }
}
