package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.service.api.SeatService;
import com.schweizerischebundesbahnen.service.api.TrainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aleksandrprendota on 17.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @Autowired
    private TrainService trainService;

    @Test
    public void testfindSeatByAllParams(){
        Train train = trainService.findTrainByName("F01");
        Seat seat = seatService.findByTrainAndNumberAndCarriage(train,1,1);
        Assert.assertNotNull(seat);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindSeatEmptyTrain(){
        Seat seat = seatService.findByTrainAndNumberAndCarriage(new Train(),1,1);
        Assert.assertNull(seat);
    }

    @Test
    public void testFindSeatByWrongSeatNumberParams(){
        Train train = trainService.findTrainByName("F01");
        Seat seat = seatService.findByTrainAndNumberAndCarriage(train,1,-1);
        Assert.assertNull(seat);
    }

    @Test
    public void testFindSeatByWrongCabineParams(){
        Train train = trainService.findTrainByName("F01");
        Seat seat = seatService.findByTrainAndNumberAndCarriage(train,-1,1);
        Assert.assertNull(seat);
    }

    @Test
    public void testFindSeatByTrain(){
        Train train = trainService.findTrainByName("F01");
        List<Seat> seats = seatService.findSeatByTrain(train);
        Assert.assertTrue(seats.size() > 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindSeatByEmptyTrain(){
        List<Seat> seats = seatService.findSeatByTrain(new Train());
        Assert.assertNull(seats);
    }

    @Test
    public void testFindSeatByIncorrectTrain(){
        Train train = trainService.findTrainByName("Incorrect Train");
        List<Seat> seats = seatService.findSeatByTrain(train);
        Assert.assertTrue(seats.size() == 0);
    }
}
