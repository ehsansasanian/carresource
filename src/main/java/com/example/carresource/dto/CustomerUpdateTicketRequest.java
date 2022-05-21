package com.example.carresource.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerUpdateTicketRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long ticketId;
    @NotEmpty
    private String description;
}
