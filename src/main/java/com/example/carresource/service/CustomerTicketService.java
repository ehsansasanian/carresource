package com.example.carresource.service;

import com.example.carresource.dto.CustomerCreateTicketRequest;
import com.example.carresource.dto.CustomerUpdateTicketRequest;
import com.example.carresource.model.Ticket;

import java.util.List;

public interface CustomerTicketService {
    Ticket submit(CustomerCreateTicketRequest ticket);

    List<Ticket> getTickets(long id);

    Ticket update(CustomerUpdateTicketRequest ticket);

    boolean closeTicket(long customerId, long ticketId);
}
