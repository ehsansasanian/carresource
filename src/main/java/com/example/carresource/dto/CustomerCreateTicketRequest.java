package com.example.carresource.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerCreateTicketRequest {
    @NotNull
    private Long customerId;
    @NotEmpty
    private String description;
    @NotNull
    private Long vehicleId;
}
