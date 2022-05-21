package com.example.carresource.exception;

public class VehicleNotFoundException extends RuntimeException {
    private final static String message = "vehicle not found";

    public VehicleNotFoundException() {
        super(message);
    }
}
