package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;

import java.util.List;

/**
 * Created by aleksandrprendota on 01.04.17.
 */
public interface TicketService {

    void addTicket(Ticket ticket);

    List<Ticket> findTicketsByUser(User user);

    void delete(Ticket ticket);

    Ticket findById(Long id);

}
