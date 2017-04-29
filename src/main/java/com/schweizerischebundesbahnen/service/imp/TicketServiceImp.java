package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.TicketRepository;
import com.schweizerischebundesbahnen.service.api.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrprendota on 01.04.17.
 *
 * Implimentation TicketService interface
 */
@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Greating a new Ticket entity in database
     * @param ticket ticker entity for saving
     */
    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    /**
     * Finding List of ticket by his owner
     * @param user User entity who has tickets
     * @return List of existing tickets
     */
    @Override
    public List<Ticket> findTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    /**
     * Delete ticket from database
     * @param ticket Existing ticket entity
     */
    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    /**
     * Getting ticket entity by Id
     * @param id Long id existing ticket entity
     * @return ticket entity
     */
    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findOne(id);
    }
}
