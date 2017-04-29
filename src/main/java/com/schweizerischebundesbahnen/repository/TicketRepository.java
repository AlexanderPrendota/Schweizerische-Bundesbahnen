package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aleksandrprendota on 28.03.17.
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}
