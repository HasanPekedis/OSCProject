package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Ticket;
import com.OmreonStudyCase.OSCProject.repository.TicketRepository;
import com.OmreonStudyCase.OSCProject.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;
    private Flight flight;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        flight = new Flight();
        flight.setId(1L);
        flight.setTotalSeats(100);
        flight.setBookedSeats(50);
        flight.setBaseTicketPrice(100.0);
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setFlight(flight);
        ticket.setPassengerName("John Doe");
        ticket.setPassengerEmail("johndoe@example.com");
        ticket.setStatus("Confirmed");
    }

    @Test
    public void testAddTicket_Success() {
        // Arrange
        when(flightRepository.getReferenceById(flight.getId())).thenReturn(flight);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Act
        Ticket savedTicket = ticketService.addTicket(ticket);

        // Assert
        assertNotNull(savedTicket);
        assertEquals(ticket.getPrice(), 161.051); // The price increases by 10%
        assertEquals(51, flight.getBookedSeats());
        verify(flightRepository, times(1)).save(flight);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testAddTicket_NoSeatsAvailable() {
        // Arrange
        flight.setBookedSeats(100);
        when(flightRepository.getReferenceById(flight.getId())).thenReturn(flight);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.addTicket(ticket));
        assertEquals("There is no empty seats!", exception.getMessage());
        verify(flightRepository, times(0)).save(flight);
        verify(ticketRepository, times(0)).save(ticket);
    }

    @Test
    public void testGetTicketById() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        Ticket foundTicket = ticketService.getTicketById(1L);

        // Assert
        assertNotNull(foundTicket);
        assertEquals(ticket.getId(), foundTicket.getId());
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTicketById_NotFound() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Ticket foundTicket = ticketService.getTicketById(1L);

        // Assert
        assertNull(foundTicket);
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateTicket() {
        // Arrange
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setPassengerName("Jane Doe");
        updatedTicket.setPassengerEmail("janedoe@example.com");
        updatedTicket.setPrice(120.0);
        updatedTicket.setStatus("Confirmed");
        updatedTicket.setFlight(flight);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(updatedTicket)).thenReturn(updatedTicket);

        // Act
        Ticket savedTicket = ticketService.updateTicket(1L, updatedTicket);

       }

    @Test
    public void testDeleteTicket() {
        // Arrange
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        ticketService.deleteTicket(1L);

        // Assert
        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTicketByCode() {
        // Arrange
        ticket.setCode("TICKET123");
        when(ticketRepository.findByCode("TICKET123")).thenReturn(ticket);
        when(flightRepository.save(flight)).thenReturn(flight);

        // Act
        ticketService.deleteTicketByCode("TICKET123");

        // Assert
        assertEquals(49, flight.getBookedSeats());  // Booked seats should be reduced by 1
        verify(ticketRepository, times(1)).deleteByCode("TICKET123");
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    public void testDeleteTicketByCode_TicketNotFound() {
        // Arrange
        when(ticketRepository.findByCode("TICKET123")).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.deleteTicketByCode("TICKET123"));
        assertEquals("Ticket with code TICKET123 not found", exception.getMessage());
        verify(ticketRepository, times(0)).deleteByCode("TICKET123");
        verify(flightRepository, times(0)).save(flight);
    }

    @Test
    public void testCalculateTicketPrice() {
        // Arrange
        double calculatedPrice = ticketService.calculateTicketPrice(flight);

        // Act & Assert
        assertEquals(161.051, calculatedPrice, 0.01); // 100 + 10% = 110.0
    }
}
