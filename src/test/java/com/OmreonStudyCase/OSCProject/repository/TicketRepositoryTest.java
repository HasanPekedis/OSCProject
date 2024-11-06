package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    private Flight testFlight;

    @BeforeEach
    public void setUp() {
        // Create and save a test flight for associating with the ticket
        testFlight = new Flight();
        testFlight.setFlightNumber("FL001");
        testFlight.setTotalSeats(200);
        flightRepository.save(testFlight);
    }

    @Test
    public void testSaveAndFindByCode() {
        // Create and save a ticket with a specific code
        Ticket ticket = new Ticket();
        ticket.setPassengerName("John Doe");
        ticket.setPassengerEmail("johndoe@example.com");
        ticket.setCode("TCK001");
        ticket.setPrice(150.0);
        ticket.setStatus("booked");
        ticket.setFlight(testFlight);

        Ticket savedTicket = ticketRepository.save(ticket);

        // Verify the ticket was saved and can be found by code
        Ticket foundTicket = ticketRepository.findByCode("TCK001");
        assertNotNull(foundTicket);
        assertEquals("John Doe", foundTicket.getPassengerName());
        assertEquals("TCK001", foundTicket.getCode());
        assertEquals(150.0, foundTicket.getPrice());
        assertEquals("booked", foundTicket.getStatus());
    }

    @Test
    public void testDeleteByCode() {
        // Create and save a ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Jane Doe");
        ticket.setPassengerEmail("janedoe@example.com");
        ticket.setCode("TCK002");
        ticket.setPrice(120.0);
        ticket.setStatus("check-in");
        ticket.setFlight(testFlight);

        ticketRepository.save(ticket);

        // Delete the ticket by code
        ticketRepository.deleteByCode("TCK002");

        // Verify the ticket is deleted
        Ticket foundTicket = ticketRepository.findByCode("TCK002");
        assertNull(foundTicket);
    }

    @Test
    public void testUpdateTicketStatus() {
        // Create and save a ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Sam Smith");
        ticket.setPassengerEmail("samsmith@example.com");
        ticket.setCode("TCK003");
        ticket.setPrice(130.0);
        ticket.setStatus("booked");
        ticket.setFlight(testFlight);

        Ticket savedTicket = ticketRepository.save(ticket);

        // Update ticket status
        savedTicket.setStatus("check-in");
        Ticket updatedTicket = ticketRepository.save(savedTicket);

        // Verify that the ticket status is updated
        assertEquals("check-in", updatedTicket.getStatus());
    }
}
