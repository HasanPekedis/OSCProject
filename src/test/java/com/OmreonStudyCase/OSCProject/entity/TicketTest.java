package com.OmreonStudyCase.OSCProject.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    public void testTicketEntity() {
        // Create a sample Flight instance
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        flight.setTotalSeats(100);
        flight.setBookedSeats(50);
        flight.setCurrentTicketPrice(150.0);
        flight.setBaseTicketPrice(100.0);
        
        // Create an instance of Ticket
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setPassengerName("John Doe");
        ticket.setPassengerEmail("john.doe@example.com");
        ticket.setCode("TICKET123");
        ticket.setPrice(150.0);
        ticket.setStatus("booked");
        ticket.setCreditCard("4111-1111-1111-1111");
        ticket.setFlight(flight);

        // Verify that the getters return the expected values
        assertEquals(1L, ticket.getId());
        assertEquals("John Doe", ticket.getPassengerName());
        assertEquals("john.doe@example.com", ticket.getPassengerEmail());
        assertEquals(150.0, ticket.getPrice());
        assertEquals("booked", ticket.getStatus());
        assertEquals("TICKET123", ticket.getCode());
        assertEquals("4111-1111-1111-1111", ticket.getCreditCard());
        assertEquals(flight, ticket.getFlight());
    }

    @Test
    public void testTicketDefaultConstructor() {
        // Create an instance using the default constructor
        Ticket ticket = new Ticket();

        // Verify that fields are initially null or default values
        assertNull(ticket.getId());
        assertNull(ticket.getPassengerName());
        assertNull(ticket.getPassengerEmail());
        assertNull(ticket.getCode());
        assertNull(ticket.getPrice());
        assertNull(ticket.getStatus());
        assertNull(ticket.getCreditCard());
        assertNull(ticket.getFlight());
    }

    @Test
    public void testTicketSettersAndGetters() {
        // Create an instance and use setters to assign values
        Ticket ticket = new Ticket();
        ticket.setId(2L);
        ticket.setPassengerName("Jane Smith");
        ticket.setPassengerEmail("jane.smith@example.com");
        ticket.setCode("TICKET456");
        ticket.setPrice(200.0);
        ticket.setStatus("check-in");

        // Create a sample Flight instance
        Flight flight = new Flight();
        flight.setId(2L);
        flight.setFlightNumber("FL456");
        flight.setTotalSeats(150);
        flight.setBookedSeats(75);
        flight.setCurrentTicketPrice(200.0);
        flight.setBaseTicketPrice(150.0);
        
        ticket.setFlight(flight);
        ticket.setCreditCard("4222-2222-2222-2222");

        // Verify that getters return the expected values
        assertEquals(2L, ticket.getId());
        assertEquals("Jane Smith", ticket.getPassengerName());
        assertEquals("jane.smith@example.com", ticket.getPassengerEmail());
        assertEquals(200.0, ticket.getPrice());
        assertEquals("check-in", ticket.getStatus());
        assertEquals("TICKET456", ticket.getCode());
        assertEquals("4222-2222-2222-2222", ticket.getCreditCard());
        assertEquals(flight, ticket.getFlight());
    }
}
