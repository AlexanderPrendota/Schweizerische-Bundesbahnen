package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aleksandrprendota on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Test
    public void testFindTicketByUser(){
        User user = userService.findUserByEmail("S@s");
        List<Ticket> ticket = ticketService.findTicketsByUser(user);
        Assert.assertTrue(ticket.size() > 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindTicketByEmptyUser(){
        List<Ticket> ticket = ticketService.findTicketsByUser(new User());
        Assert.assertTrue(ticket.size() > 0);
    }

    @Test
    public void testFindTicketByWrongUser(){
        User user = userService.findUserByEmail("IncorrectUser");
        List<Ticket> ticket = ticketService.findTicketsByUser(user);
        Assert.assertTrue(ticket.size() == 0);
    }

    @Test
    public void testFindTicketByUserWithOutTickets(){
        User user = userService.findUserByEmail("TEST@TEST");
        List<Ticket> ticket = ticketService.findTicketsByUser(user);
        Assert.assertTrue(ticket.size() == 0);
    }

    @Test
    public void testFindTicketByNullUser(){
        List<Ticket> ticket = ticketService.findTicketsByUser(null);
        Assert.assertTrue(ticket.size() == 0);
    }

    @Test
    public void testFindTicket(){
        Assert.assertEquals(ticketService.findById(1012L).getId(),1012L);
    }

    @Test
    public void testFindStationByWrongId(){
        Ticket ticket = ticketService.findById(1L);
        Assert.assertNull(ticket);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindStationByNullId(){
        Ticket ticket = ticketService.findById(null);
        Assert.assertNull(ticket);
    }

}
