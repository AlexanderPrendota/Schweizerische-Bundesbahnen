package com.schweizerischebundesbahnen.intergation;


import com.schweizerischebundesbahnen.repository.*;
import com.schweizerischebundesbahnen.service.api.StationService;
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
public class DIDaoTest {


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StationService stationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserChatRepository userChatRepository;

    @Autowired
    private  ChatRepository chatRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;


    @Test
    public void rideDAOTest(){
        Assert.assertNotNull(rideRepository);
    }

    @Test
    public void seatDAOTest(){
        Assert.assertNotNull(seatRepository);
    }

    @Test
    public void trainDAOTest(){
        Assert.assertNotNull(trainRepository);
    }

    @Test
    public void scheduleDAOTest(){
        Assert.assertNotNull(scheduleRepository);
    }

    @Test
    public void userDAOTest(){
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void ticketDAOTest(){
        Assert.assertNotNull(ticketRepository);
    }

    @Test
    public void stationDAOTest(){
        Assert.assertNotNull(stationService);
    }

    @Test
    public void chatDAOTest(){
        Assert.assertNotNull(chatRepository);
    }

    @Test
    public void messageDAOTest(){
        Assert.assertNotNull(messageRepository);
    }

    @Test
    public void userChatDAOTest(){
        Assert.assertNotNull(userChatRepository);
    }

    @Test
    public void attendanceDAOTest(){
        Assert.assertNotNull(attendanceRepository);
    }
}
