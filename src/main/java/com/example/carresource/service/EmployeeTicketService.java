package com.example.carresource.service;

import com.example.carresource.model.Ticket;

import java.util.List;

public interface EmployeeTicketService {
    List<Ticket> getTickets(long employeeId);

    Ticket updateTicket(Ticket ticket);
}
