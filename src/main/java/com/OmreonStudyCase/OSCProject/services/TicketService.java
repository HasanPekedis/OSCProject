package com.OmreonStudyCase.OSCProject.services;

import com.OmreonStudyCase.OSCProject.repository.TicketRepository;
import com.OmreonStudyCase.OSCProject.repository.FlightRepository;
import com.OmreonStudyCase.OSCProject.entity.Flight;
import com.OmreonStudyCase.OSCProject.entity.Ticket;

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
    
        Flight flight = flightRepository.getById(ticket.getFlight().getId());       
        flight.setBookedSeats(flight.getBookedSeats() + 1 );

        
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
}
