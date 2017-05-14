package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.TicketRepository;
import com.schweizerischebundesbahnen.service.imp.TicketServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceMockTest {


    private Ticket ticket;
    private User user;
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImp ticketService;

    @Before
    public void setup(){
        user = new User();
        user.setId(1L);
        user.setEmail("pren@mail.ru");

        ticket = new Ticket();
        ticket.setId(26L);
        ticket.setUser(user);

    }
    @Test
    public void testFindTicketById(){
        when(ticketRepository.findOne(21L)).thenReturn(ticket);
        ticketService.findById(ticket.getId());
        verify(ticketRepository).findOne(ticket.getId());
    }
    @Test
    public void testFindTicketByUser(){
        when(ticketRepository.findByUser(user)).thenReturn(new ArrayList<Ticket>());
        ticketService.findTicketsByUser(user);
        verify(ticketRepository).findByUser(user);

    }

}
