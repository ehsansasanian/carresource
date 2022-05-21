package com.example.carresource.api;

import com.example.carresource.dto.CustomerCreateTicketRequest;
import com.example.carresource.dto.CustomerUpdateTicketRequest;
import com.example.carresource.model.Ticket;
import com.example.carresource.service.impl.CustomerTicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/customer-tickets")
@RequiredArgsConstructor
public class CustomerTicketController {
    private final CustomerTicketServiceImpl customerTicketServiceImpl;

    /**
     * Due to the project description (mentioned in the email) the focus is on implementing some restAPIs.
     * So there is no security implementation in this project.
     * based on the security mechanism, getting ID of the client (customer or employee) can be different
     * an alternative for the current implementation, might be using JWT and getting the client-ID from it's token
     */

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Ticket>> tickets(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerTicketServiceImpl.getTickets(customerId));
    }

    @PostMapping
    public ResponseEntity<Ticket> submit(@RequestBody @Valid CustomerCreateTicketRequest createRequest) {
        return new ResponseEntity<>(customerTicketServiceImpl.submit(createRequest), CREATED);
    }

    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody @Valid CustomerUpdateTicketRequest updateRequest) {
        return ResponseEntity.ok(customerTicketServiceImpl.update(updateRequest));
    }

    @PutMapping("/{customerId}/close/{ticketId}")
    public ResponseEntity<Void> closeTicket(@PathVariable Long customerId, @PathVariable Long ticketId) {
        HttpStatus status = customerTicketServiceImpl.closeTicket(customerId, ticketId) ? OK : NO_CONTENT;
        return new ResponseEntity<>(status);
    }
}
