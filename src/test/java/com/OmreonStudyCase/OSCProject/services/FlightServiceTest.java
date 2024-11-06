package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Airline;
import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Route;
import com.OmreonStudyCase.OSCProject.repository.FlightRepository;
import com.OmreonStudyCase.OSCProject.repository.AirlineRepository;
import com.OmreonStudyCase.OSCProject.repository.RouteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFlights() {
        // Arrange
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        when(flightRepository.findAll()).thenReturn(Arrays.asList(flight1, flight2));

        // Act
        List<Flight> flights = flightService.getAllFlights();

        // Assert
        assertEquals(2, flights.size());
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    public void testGetFlightById() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        // Act
        Flight foundFlight = flightService.getFlightById(1L);

        // Assert
        assertNotNull(foundFlight);
        assertEquals(1L, foundFlight.getId());
        verify(flightRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddFlight() {
        // Arrange
        Flight flight = new Flight();
        Airline airline = new Airline();
        airline.setCode("AL123");
        Route route = new Route();
        route.setCode("RT123");

        flight.setAirline(airline);
        flight.setRoute(route);

        when(airlineRepository.findByCode("AL123")).thenReturn(airline);
        when(routeRepository.findByCode("RT123")).thenReturn(route);
        when(flightRepository.save(flight)).thenReturn(flight);

        // Act
        Flight savedFlight = flightService.addFlight(flight);

        // Assert
        assertNotNull(savedFlight);
        assertEquals("AL123", savedFlight.getAirline().getCode());
        assertEquals("RT123", savedFlight.getRoute().getCode());
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    public void testUpdateFlight() {
        // Arrange
        Flight existingFlight = new Flight();
        existingFlight.setId(1L);
        existingFlight.setFlightNumber("FN100");

        Flight flightDetails = new Flight();
        flightDetails.setFlightNumber("FN200");
        flightDetails.setDepartureTime(LocalDateTime.of(2024, 11, 10, 10, 0));
        flightDetails.setArrivalTime(LocalDateTime.of(2024, 11, 10, 12, 0));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(existingFlight));
        when(flightRepository.save(existingFlight)).thenReturn(existingFlight);

        // Act
        Flight updatedFlight = flightService.updateFlight(1L, flightDetails);

        // Assert
        assertNotNull(updatedFlight);
        assertEquals("FN200", updatedFlight.getFlightNumber());
        assertEquals(LocalDateTime.of(2024, 11, 10, 10, 0), updatedFlight.getDepartureTime());
        assertEquals(LocalDateTime.of(2024, 11, 10, 12, 0), updatedFlight.getArrivalTime());
        verify(flightRepository, times(1)).findById(1L);
        verify(flightRepository, times(1)).save(existingFlight);
    }

    @Test
    public void testDeleteFlight() {
        // Arrange
        Long flightId = 1L;

        // Act
        flightService.deleteFlight(flightId);

        // Assert
        verify(flightRepository, times(1)).deleteById(flightId);
    }
}
