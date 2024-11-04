package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.services.TicketService;
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
}
