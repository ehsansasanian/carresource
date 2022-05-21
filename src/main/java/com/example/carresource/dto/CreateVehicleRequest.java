package com.example.carresource.dto;

import lombok.Data;

@Data
public class CreateVehicleRequest {
    private long customerId;
    private String brand;
    private String model;
    private String plateNumber;
}
