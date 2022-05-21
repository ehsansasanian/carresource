package com.example.carresource.service;

import com.example.carresource.dto.CreateVehicleRequest;
import com.example.carresource.dto.UpdateVehicleRequest;
import com.example.carresource.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(CreateVehicleRequest createVehicleRequest);

    List<Vehicle> getVehicles(long customerId);

    List<Vehicle> updateVehicle(UpdateVehicleRequest updateVehicleRequest);

    boolean removeVehicle(long customerId, long vehicleId);
}
