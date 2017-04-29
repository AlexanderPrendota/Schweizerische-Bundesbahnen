package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.StationService;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.TrainService;
import com.sun.tools.javac.api.ClientCodeWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 04.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RideServiceTest {

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Test
    public void checkFindRideByTicket(){
        Ticket ticket = ticketService.findById(1012L);
        Assert.assertEquals(rideService.findByTicket(ticket).getId(),1013L);
    }

    @Test
    public void checkFindRideByTicketWithUnexistingId(){
        Ticket ticket = ticketService.findById(1L);
        Ride ride = rideService.findByTicket(ticket);
        Assert.assertNull(ride);

    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkFindRideByTicketWithNull(){
        Ticket ticket = ticketService.findById(null);
        Ride ride = rideService.findByTicket(ticket);
        Assert.assertNull(ride);
    }

    @Test
    public void checkFindRideById(){
        Assert.assertEquals(rideService.findRideById(1020L).getId(), 1020L);
    }

    @Test
    public void checkIncorrectRideById(){
        Ride ride = rideService.findRideById(1L);
        Assert.assertNull(ride);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRideWithNullId(){
        rideService.findRideById(null);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkFindRideByNull(){
        Ride ride = rideService.findRideById(null);
        Assert.assertNull(ride);
    }

    @Test
    public void checkFindRideByUnexistingRideId(){
        Ride ride = rideService.findRideById(1L);
        Assert.assertNull(ride);
    }

    @Test
    public void checkRideFindByTrainNull(){
        List<Ride> ride = rideService.findByTrain(null);
        Assert.assertTrue(ride.size() == 0);

    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRideFindByEmpryObjectTrain(){
        List<Ride> ride = rideService.findByTrain(new Train());
        Assert.assertTrue(ride.size() == 0);

    }

    @Test
    public void checkRideFindByTrain(){
        Train train = trainService.findTrainByName("F01");
        List<Ride> ride = rideService.findByTrain(train);
        Assert.assertTrue(ride.size() > 0);
    }

    @Test
    public void checkRideByIncorrectTrain(){
        Train train = trainService.findTrainByName("A");
        List<Ride> ride = rideService.findByTrain(train);
        Assert.assertTrue(ride.size() == 0);
    }

    @Test
    public void checkRideByEmptyStringTrain(){
        Train train = trainService.findTrainByName("");
        List<Ride> ride = rideService.findByTrain(train);
        Assert.assertTrue(ride.size() == 0);
    }

    @Test
    public void checkRideByStationDeparture(){
        Station station = stationService.findStationByName("London");
        List<Ride> rides = rideService.findByStationDeparture(station);
        Assert.assertTrue(rides.size() > 0);
    }

    @Test
    public void checkRideByNullStationDeparture(){
        List<Ride> ride = rideService.findByStationDeparture(null);
        Assert.assertTrue(ride.size() == 0);
    }

    @Test
    public void checkRideByUnexistingStationDeparture(){
        Station station = stationService.findStationByName("Y");
        List<Ride> rides = rideService.findByStationDeparture(station);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test
    public void checkRideByEmptyStringStationDeparture(){
        Station station = stationService.findStationByName("");
        List<Ride> rides = rideService.findByStationDeparture(station);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRideByEmptyObjectStationDeparture(){
        List<Ride> ride = rideService.findByStationDeparture(new Station());
        Assert.assertTrue(ride.size() == 0);
    }

    @Test
    public void checkRideByStationArrival(){
        Station station = stationService.findStationByName("Basel");
        List<Ride> rides = rideService.findByStationArrival(station);
        Assert.assertTrue(rides.size() > 0);
    }

    @Test
    public void checkRideByUnexistingStationArrival(){
        Station station = stationService.findStationByName("S");
        List<Ride> rides = rideService.findByStationArrival(station);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test
    public void checkRideByEmptyStringStationArrival(){
        Station station = stationService.findStationByName("");
        List<Ride> rides = rideService.findByStationArrival(station);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test
    public void checkRideByNullStationArrival(){
        List<Ride> ride = rideService.findByStationArrival(null);
        Assert.assertTrue(ride.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRideByEmptyObjectStationArrival(){
        List<Ride> ride = rideService.findByStationArrival(new Station());
        Assert.assertTrue(ride.size() == 0);
    }

    @Test
    public void checkRideByIncorrectTrainAndTimeDeparture(){
        Train train = trainService.findTrainByName("A303");
        Date time = new Date(1L);
        List<Ride> rides = rideService.findByTrainAndTime(train,time);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test
    public void checkRidesByNullTrainAndTimeDeparture(){
        List<Ride> rides = rideService.findByTrainAndTime(null,null);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRidesByEpmptyTrainAndTimeDeparture() {
        List<Ride> rides = rideService.findByTrainAndTime(new Train(),new Date());
        Assert.assertTrue(rides.size() == 0);
    }

    @Test
    public void checkFindRidesByTrainAndDate(){

        Train train = trainService.findTrainByName("F01");
        Date startDate = new Date(1494460800000L);
        Date endDate = new Date(startDate.getTime() + 86399L * 1000);
        List<Ride> rides = rideService.findRidesByTrainAndDate(train,startDate,endDate);
        Assert.assertTrue(rides.size() > 0);

    }

    @Test
    public void checkFindRidesByWrongTrainAndDate(){

        Train train = trainService.findTrainByName("Incorrect");
        Date startDate = new Date(1493251200000L);
        Date endDate = new Date(startDate.getTime() + 86399L * 1000);
        List<Ride> rides = rideService.findRidesByTrainAndDate(train,startDate,endDate);
        Assert.assertTrue(rides.size() == 0);

    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkFindRidesByNullTrainAndAllNullDates(){
        List<Ride> rides = rideService.findRidesByTrainAndDate(null,null, null);
        Assert.assertTrue(rides.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRindRidesByEmptyTrainAndDates(){
        List<Ride> rides = rideService.findRidesByTrainAndDate(new Train(),new Date(),new Date());
        Assert.assertTrue(rides.size() == 0);
    }

}
