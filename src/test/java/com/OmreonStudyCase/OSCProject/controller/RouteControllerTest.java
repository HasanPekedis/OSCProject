package com.OmreonStudyCase.OSCProject.controller;

import com.OmreonStudyCase.OSCProject.entity.Airport;
import com.OmreonStudyCase.OSCProject.entity.Route;
import com.OmreonStudyCase.OSCProject.services.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private Route route;
    private Airport departureAirport;
    private Airport destinationAirport;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the airports
        departureAirport = new Airport();
        departureAirport.setId(1L);
        departureAirport.setName("New York Airport");

        destinationAirport = new Airport();
        destinationAirport.setId(2L);
        destinationAirport.setName("Los Angeles Airport");

        // Initialize the route object
        route = new Route();
        route.setId(1L);
        route.setCode("R123");
        route.setDeparture(departureAirport);
        route.setDestination(destinationAirport);
    }

    @Test
    public void testCreateRoute() {
        // Arrange
        when(routeService.saveRoute(route)).thenReturn(route);

        // Act
        ResponseEntity<Route> response = routeController.createRoute(route);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(route.getCode(), response.getBody().getCode());
        assertEquals(route.getDeparture().getName(), response.getBody().getDeparture().getName());
        assertEquals(route.getDestination().getName(), response.getBody().getDestination().getName());
        verify(routeService, times(1)).saveRoute(route);
    }

    @Test
    public void testGetAllRoutes() {
        // Arrange
        List<Route> routes = Arrays.asList(route);
        when(routeService.getAllRoutes()).thenReturn(routes);

        // Act
        ResponseEntity<List<Route>> response = routeController.getAllRoutes();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(route.getCode(), response.getBody().get(0).getCode());
        assertEquals(route.getDeparture().getName(), response.getBody().get(0).getDeparture().getName());
        assertEquals(route.getDestination().getName(), response.getBody().get(0).getDestination().getName());
        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    public void testGetRouteById() {
        // Arrange
        when(routeService.getRouteById(1L)).thenReturn(Optional.of(route));

        // Act
        ResponseEntity<Route> response = routeController.getRouteById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(route.getCode(), response.getBody().getCode());
        assertEquals(route.getDeparture().getName(), response.getBody().getDeparture().getName());
        assertEquals(route.getDestination().getName(), response.getBody().getDestination().getName());
        verify(routeService, times(1)).getRouteById(1L);
    }

    @Test
    public void testGetRouteById_NotFound() {
        // Arrange
        when(routeService.getRouteById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Route> response = routeController.getRouteById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(routeService, times(1)).getRouteById(1L);
    }

    @Test
    public void testDeleteRoute() {
        // Arrange
        doNothing().when(routeService).deleteRoute(1L);

        // Act
        ResponseEntity<Void> response = routeController.deleteRoute(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(routeService, times(1)).deleteRoute(1L);
    }

    @Test
    public void testGetRouteByCode() {
        // Arrange
        when(routeService.getRouteByCode("R123")).thenReturn(route);

        // Act
        Route result = routeController.getRouteByCode("R123");

        // Assert
        assertNotNull(result);
        assertEquals(route.getCode(), result.getCode());
        assertEquals(route.getDeparture().getName(), result.getDeparture().getName());
        assertEquals(route.getDestination().getName(), result.getDestination().getName());
        verify(routeService, times(1)).getRouteByCode("R123");
    }
}
