package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.repository.TicketRepository;
import com.OmreonStudyCase.OSCProject.repository.FlightRepository;
import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Ticket;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket addTicket(Ticket ticket) {

        Flight currentFlight = flightRepository.getReferenceById(ticket.getFlight().getId());

        if (currentFlight.getBookedSeats() < currentFlight.getTotalSeats()) {
            currentFlight.setBookedSeats(currentFlight.getBookedSeats() + 1);

            double updatedPrice = calculateTicketPrice(currentFlight);

            currentFlight.setCurrentTicketPrice(updatedPrice);
            flightRepository.save(currentFlight);

            ticket.setPrice(updatedPrice);
        } else {
            throw new RuntimeException("There is no empty seats!");
        }

        return ticketRepository.save(ticket);
    }
    
    public List<Ticket> saveAll(List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets); // saveAll çağrısı
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setPassengerName(ticketDetails.getPassengerName());
            ticket.setPassengerEmail(ticketDetails.getPassengerEmail());
            ticket.setPrice(ticketDetails.getPrice());
            ticket.setStatus(ticketDetails.getStatus());
            ticket.setFlight(ticketDetails.getFlight()); // Uçuş bilgisi güncellenir
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Transactional 
    public void deleteTicketByCode(String code) {
        Ticket ticket = ticketRepository.findByCode(code);
        if (ticket != null) {
            Flight flight = ticket.getFlight();
            ticketRepository.deleteByCode(code);

            flight.setBookedSeats(flight.getBookedSeats() - 1);
            flightRepository.save(flight);
        } else {
            throw new RuntimeException("Ticket with code " + code + " not found");
        }
    }

    public double calculateTicketPrice(Flight flight) {
        int occupancyRate = (int) ((double) flight.getBookedSeats() / flight.getTotalSeats() * 100);
        double updatedPrice = flight.getBaseTicketPrice();

        // Her %10'luk artış için fiyatı %10 arttır
        for (int i = 10; i <= occupancyRate; i += 10) {
            updatedPrice += updatedPrice * 0.10;
        }

        return updatedPrice;
    }



    public Ticket getTicketByCode(String code) {
        return ticketRepository.findByCode(code);
    }
}
