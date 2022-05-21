package com.example.carresource.dto;

import lombok.Data;

@Data
public class UpdateVehicleRequest {
    private long customerId;
    private long ticketId;
    private String brand;
    private String model;
    private String plateNumber;
}
