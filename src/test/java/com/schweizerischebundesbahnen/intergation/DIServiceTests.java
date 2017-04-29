package com.schweizerischebundesbahnen.intergation;

import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.service.api.*;
import com.schweizerischebundesbahnen.service.imp.MailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aleksandrprendota on 27.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DIServiceTests {

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Test
    public void trainServiceTest(){
        Assert.assertNotNull(trainService);
    }

    @Test
    public void ticketServiceTest(){
        Assert.assertNotNull(ticketService);
    }

    @Test
    public void stationServiceTest(){
        Assert.assertNotNull(stationService);
    }

    @Test
    public void rideServiceTest(){
        Assert.assertNotNull(rideService);
    }

    @Test
    public void userServiceTest(){
        Assert.assertNotNull(userService);
    }

    @Test
    public void seatServiceTest(){
        Assert.assertNotNull(seatService);
    }

    @Test
    public void storageServiceTest(){
        Assert.assertNotNull(storageService);
    }

    @Test
    public void scheduleServiceTest(){
        Assert.assertNotNull(scheduleService);
    }

    @Test
    public void mailServiceTest(){
        Assert.assertNotNull(mailService);
    }
}
