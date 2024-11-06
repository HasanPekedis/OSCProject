package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    private Flight flight;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Initialize the flight object
        flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        flight.setDepartureTime(LocalDateTime.of(2024, 12, 1, 10, 0));
        flight.setArrivalTime(LocalDateTime.of(2024, 12, 1, 12, 0));
        flight.setTotalSeats(200);
        flight.setBookedSeats(50);
        flight.setCurrentTicketPrice(150.0);
        flight.setBaseTicketPrice(100.0);
    }

    @Test
    public void testGetAllFlights() {
        // Arrange
        List<Flight> flights = Arrays.asList(flight);
        when(flightService.getAllFlights()).thenReturn(flights);

        // Act
        List<Flight> result = flightController.getAllFlights();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(flight.getId(), result.get(0).getId());
        verify(flightService, times(1)).getAllFlights();
    }

    @Test
    public void testGetFlightById() {
        // Arrange
        when(flightService.getFlightById(1L)).thenReturn(flight);

        // Act
        ResponseEntity<Flight> response = flightController.getFlightById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flight.getId(), response.getBody().getId());
        verify(flightService, times(1)).getFlightById(1L);
    }

    @Test
    public void testGetFlightById_NotFound() {
        // Arrange
        when(flightService.getFlightById(1L)).thenReturn(null);

        // Act
        ResponseEntity<Flight> response = flightController.getFlightById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(flightService, times(1)).getFlightById(1L);
    }

    @Test
    public void testAddFlight() {
        // Arrange
        when(flightService.addFlight(flight)).thenReturn(flight);

        // Act
        Flight result = flightController.addFlight(flight);

        // Assert
        assertNotNull(result);
        assertEquals(flight.getFlightNumber(), result.getFlightNumber());
        assertEquals(flight.getTotalSeats(), result.getTotalSeats());
        assertEquals(flight.getBookedSeats(), result.getBookedSeats());
        verify(flightService, times(1)).addFlight(flight);
    }

    @Test
    public void testUpdateFlight() {
        // Arrange
        Flight updatedFlight = new Flight();
        updatedFlight.setId(1L);
        updatedFlight.setFlightNumber("FL124");
        updatedFlight.setDepartureTime(LocalDateTime.of(2024, 12, 1, 14, 0));
        updatedFlight.setArrivalTime(LocalDateTime.of(2024, 12, 1, 16, 0));
        updatedFlight.setTotalSeats(250);
        updatedFlight.setBookedSeats(75);
        updatedFlight.setCurrentTicketPrice(180.0);
        updatedFlight.setBaseTicketPrice(120.0);

        when(flightService.updateFlight(1L, updatedFlight)).thenReturn(updatedFlight);

        // Act
        ResponseEntity<Flight> response = flightController.updateFlight(1L, updatedFlight);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFlight.getFlightNumber(), response.getBody().getFlightNumber());
        assertEquals(updatedFlight.getTotalSeats(), response.getBody().getTotalSeats());
        verify(flightService, times(1)).updateFlight(1L, updatedFlight);
    }

    @Test
    public void testUpdateFlight_NotFound() {
        // Arrange
        Flight updatedFlight = new Flight();
        updatedFlight.setId(1L);
        updatedFlight.setFlightNumber("FL124");

        when(flightService.updateFlight(1L, updatedFlight)).thenReturn(null);

        // Act
        ResponseEntity<Flight> response = flightController.updateFlight(1L, updatedFlight);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(flightService, times(1)).updateFlight(1L, updatedFlight);
    }

    @Test
    public void testDeleteFlight() {
        // Arrange
        doNothing().when(flightService).deleteFlight(1L);

        // Act
        ResponseEntity<Void> response = flightController.deleteFlight(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(flightService, times(1)).deleteFlight(1L);
    }
}
