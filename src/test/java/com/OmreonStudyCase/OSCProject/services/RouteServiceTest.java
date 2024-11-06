package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.entity.Route;
import com.OmreonStudyCase.OSCProject.repository.RouteRepository;
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

public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRoute() {
        // Arrange
        Route route = new Route();
        route.setCode("RT123");
        when(routeRepository.save(route)).thenReturn(route);

        // Act
        Route savedRoute = routeService.saveRoute(route);

        // Assert
        assertNotNull(savedRoute);
        assertEquals("RT123", savedRoute.getCode());
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    public void testGetAllRoutes() {
        // Arrange
        Route route1 = new Route();
        Route route2 = new Route();
        when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        // Act
        List<Route> routes = routeService.getAllRoutes();

        // Assert
        assertEquals(2, routes.size());
        verify(routeRepository, times(1)).findAll();
    }

    @Test
    public void testGetRouteById() {
        // Arrange
        Route route = new Route();
        route.setId(1L);
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));

        // Act
        Optional<Route> foundRoute = routeService.getRouteById(1L);

        // Assert
        assertTrue(foundRoute.isPresent());
        assertEquals(1L, foundRoute.get().getId());
        verify(routeRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteRoute() {
        // Arrange
        Long routeId = 1L;

        // Act
        routeService.deleteRoute(routeId);

        // Assert
        verify(routeRepository, times(1)).deleteById(routeId);
    }

    @Test
    public void testGetRouteByCode() {
        // Arrange
        Route route = new Route();
        route.setCode("RT123");
        when(routeRepository.findByCode("RT123")).thenReturn(route);

        // Act
        Route foundRoute = routeService.getRouteByCode("RT123");

        // Assert
        assertNotNull(foundRoute);
        assertEquals("RT123", foundRoute.getCode());
        verify(routeRepository, times(1)).findByCode("RT123");
    }
}
