package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Ticket;
import com.OmreonStudyCase.OSCProject.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize ticket object
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCode("TICKET123");
        ticket.setCreditCard("1234-5678-9876-5432");
    }

    @Test
    public void testGetAllTickets() {
        // Arrange
        List<Ticket> tickets = Arrays.asList(ticket);
        when(ticketService.getAllTickets()).thenReturn(tickets);

        // Act
        List<Ticket> response = ticketController.getAllTickets();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(ticket.getCode(), response.get(0).getCode());
        verify(ticketService, times(1)).getAllTickets();
    }

    @Test
    public void testGetTicketById() {
        // Arrange
        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        // Act
        ResponseEntity<Ticket> response = ticketController.getTicketById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket.getCode(), response.getBody().getCode());
        verify(ticketService, times(1)).getTicketById(1L);
    }

    @Test
    public void testGetTicketById_NotFound() {
        // Arrange
        when(ticketService.getTicketById(1L)).thenReturn(null);

        // Act
        ResponseEntity<Ticket> response = ticketController.getTicketById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(ticketService, times(1)).getTicketById(1L);
    }

    @Test
    public void testAddTicket() {
        // Arrange
        Ticket ticketToAdd = new Ticket();
        ticketToAdd.setCreditCard("1111-1111-1111-1111");
        ticketToAdd.setCode("TICKET456");

        String maskedCardNumber = "111111******1111";

        when(ticketService.addTicket(ticketToAdd)).thenReturn(ticketToAdd);

        // Act
        Ticket response = ticketController.addTicket(ticketToAdd);

        // Assert
        assertNotNull(response);
        assertEquals(ticketToAdd.getCode(), response.getCode());
        assertEquals(maskedCardNumber, response.getCreditCard());
        verify(ticketService, times(1)).addTicket(ticketToAdd);
    }

    @Test
    public void testAddTicket_InvalidCreditCard() {
        // Arrange
        Ticket ticketToAdd = new Ticket();
        ticketToAdd.setCreditCard("1234");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ticketController.addTicket(ticketToAdd));
        verify(ticketService, times(0)).addTicket(ticketToAdd);
    }

    @Test
    public void testAddTickets() {
        // Arrange
        List<Ticket> tickets = Arrays.asList(ticket);
        when(ticketService.saveAll(tickets)).thenReturn(tickets);

        // Act
        ResponseEntity<List<Ticket>> response = ticketController.addTickets(tickets);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(ticket.getCode(), response.getBody().get(0).getCode());
        verify(ticketService, times(1)).saveAll(tickets);
    }

    @Test
    public void testUpdateTicket() {
        // Arrange
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setCode("TICKET123");
        updatedTicket.setCreditCard("5678-1234-8765-4321");

        when(ticketService.updateTicket(1L, updatedTicket)).thenReturn(updatedTicket);

        // Act
        ResponseEntity<Ticket> response = ticketController.updateTicket(1L, updatedTicket);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTicket.getCode(), response.getBody().getCode());
        verify(ticketService, times(1)).updateTicket(1L, updatedTicket);
    }

    @Test
    public void testUpdateTicket_NotFound() {
        // Arrange
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(1L);
        updatedTicket.setCode("TICKET123");

        when(ticketService.updateTicket(1L, updatedTicket)).thenReturn(null);

        // Act
        ResponseEntity<Ticket> response = ticketController.updateTicket(1L, updatedTicket);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(ticketService, times(1)).updateTicket(1L, updatedTicket);
    }

    @Test
    public void testDeleteTicket() {
        // Arrange
        doNothing().when(ticketService).deleteTicket(1L);

        // Act
        ResponseEntity<Void> response = ticketController.deleteTicket(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ticketService, times(1)).deleteTicket(1L);
    }

    @Test
    public void testDeleteTicketByCode() {
        // Arrange
        doNothing().when(ticketService).deleteTicketByCode("TICKET123");

        // Act
        ResponseEntity<Void> response = ticketController.deleteTicketByCode("TICKET123");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ticketService, times(1)).deleteTicketByCode("TICKET123");
    }

    @Test
    public void testGetTicketByCode() {
        // Arrange
        when(ticketService.getTicketByCode("TICKET123")).thenReturn(ticket);

        // Act
        Ticket response = ticketController.getTicketByCode("TICKET123");

        // Assert
        assertNotNull(response);
        assertEquals(ticket.getCode(), response.getCode());
        verify(ticketService, times(1)).getTicketByCode("TICKET123");
    }
}
