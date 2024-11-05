package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.services.TicketService;
import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.entity.Ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        String sanitizedCardNumber = ticket.getCreditCard().replaceAll("[^0-9]", "");

        if (sanitizedCardNumber.length() < 12) {
            throw new IllegalArgumentException("Invalid credit card number");
        }

        String maskedCardNumber = sanitizedCardNumber.substring(0, 6)
            + "******"
            + sanitizedCardNumber.substring(sanitizedCardNumber.length() - 4);

        
        ticket.setCreditCard(maskedCardNumber);
        
        return ticketService.addTicket(ticket);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Ticket>> addTickets(@RequestBody List<Ticket> tickets) {
        List<Ticket> savedTickets = ticketService.saveAll(tickets);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTickets);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticketDetails);
        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<Void> deleteTicketByCode(@PathVariable String code) {
        ticketService.deleteTicketByCode(code);
        return ResponseEntity.noContent().build();
    }

    // Method to get an airline by code
    @GetMapping("/code/{code}")
    public Ticket getTicketByCode(@PathVariable String code) {
        return ticketService.getTicketByCode(code);
    }
}
