package com.example.carresource.service.impl;

import com.example.carresource.dto.CustomerCreateTicketRequest;
import com.example.carresource.dto.CustomerUpdateTicketRequest;
import com.example.carresource.exception.CreateTicketException;
import com.example.carresource.exception.CustomerNotFoundException;
import com.example.carresource.exception.TicketNotFoundException;
import com.example.carresource.exception.VehicleNotFoundException;
import com.example.carresource.model.Customer;
import com.example.carresource.model.Ticket;
import com.example.carresource.model.TicketStatus;
import com.example.carresource.model.Vehicle;
import com.example.carresource.repo.CustomerRepository;
import com.example.carresource.repo.TicketRepository;
import com.example.carresource.service.CustomerTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerTicketServiceImpl implements CustomerTicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Ticket submit(CustomerCreateTicketRequest createRequest) {
        Customer customer = customerRepository.findById(createRequest.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);
        Vehicle vehicle = customer.getVehicles().stream()
                .filter(v -> v.getId() == createRequest.getVehicleId())
                .findFirst()
                .orElseThrow(VehicleNotFoundException::new);

        if (vehicle.isReserved()) {
            throw new CreateTicketException("seems like you already have a ticket for this vehicle");
        }
        vehicle.setReserved(true);

        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setVehicle(vehicle);
        ticket.setDescription(createRequest.getDescription());

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTickets(long customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Ticket update(CustomerUpdateTicketRequest request) {
        Ticket ticket = ticketRepository.findById(request.getTicketId()).orElseThrow(TicketNotFoundException::new);
        if (ticket.getCustomer().getId() != request.getCustomerId()) {
            throw new TicketNotFoundException();
        }
        ticket.setDescription(request.getDescription());
        ticket.setUpdatedDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public boolean closeTicket(long customerId, long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .filter(t -> t.getCustomer().getId() == customerId)
                .orElseThrow(TicketNotFoundException::new);
        if (cancelable.test(ticket)) {
            ticket.setStatus(TicketStatus.CLOSED);
            Vehicle vehicle = ticket.getVehicle();
            vehicle.setReserved(false);
            return true;
        }
        return false;
    }

    public static Predicate<Ticket> cancelable =
            t -> t.getStatus().equals(TicketStatus.OPEN) || t.getStatus().equals(TicketStatus.CONFIRMED);
}