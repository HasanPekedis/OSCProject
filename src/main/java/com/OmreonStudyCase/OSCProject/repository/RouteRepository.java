package com.OmreonStudyCase.OSCProject.repository;

import com.OmreonStudyCase.OSCProject.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    // Additional query methods can be defined here if needed
}

