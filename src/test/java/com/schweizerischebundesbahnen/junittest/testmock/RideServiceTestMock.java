package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.service.imp.RideServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RideServiceTestMock {

    private Ticket ticket;
    private Train train;
    private Station stationDeparture;
    private Station stationArrival;
    private Ride ride;

    @Mock
    private RideRepository rideRepository;

    @InjectMocks
    private RideServiceImp rideService;

    @Before
    public void setup(){
        User user = new User();
        user.setId(1L);
        user.setEmail("p@m.ru");

        ticket = new Ticket();
        ticket.setId(26L);
        ticket.setUser(user);

        train = new Train();
        train.setId("MockTrain");

        stationDeparture = new Station();
        stationDeparture.setStationName("StationMock");
        stationDeparture.setId(21L);

        stationArrival = new Station();
        stationArrival.setStationName("StationMockArrival");
        stationArrival.setId(22L);

        ride = new Ride();
        ride.setId(4L);
        ride.setStationArrival(stationArrival);
        ride.setStationDeparture(stationDeparture);
        ride.setTicket(ticket);
        ride.setTimeDeparture(new Date(1493211200000L));
        ride.setTimeArrival(new Date(1493251200000L));

    }
    @Test
    public void testFindRideByIdMock(){
        when(rideRepository.findOne(4L)).thenReturn(ride);
        rideService.findRideById(ride.getId());
        verify(rideRepository).findOne(ride.getId());
    }

    @Test
    public void testFindByTicketMock(){
        when(rideRepository.findByTicket(ticket)).thenReturn(ride);
        rideService.findByTicket(ticket);
        verify(rideRepository).findByTicket(ticket);
    }

    @Test
    public void testFindAllRidesMock(){
        when(rideRepository.findAll()).thenReturn(new ArrayList<Ride>());
        rideService.findAllRides();
        verify(rideRepository).findAll();
    }

    @Test
    public void testFindRidesByTrainAndDateMock(){
        when(rideRepository
                .findByTrainAndTimeDepartureBetween(train,new Date(1493201200000L),new Date(1493241200000L)))
                .thenReturn(new ArrayList<Ride>());
        rideService.findRidesByTrainAndDate(train,new Date(1493201200000L),new Date(1493241200000L));
        verify(rideRepository).findByTrainAndTimeDepartureBetween(train,new Date(1493201200000L),new Date(1493241200000L));
    }

    @Test
    public void testFindByTrainMock(){
        when(rideRepository.findByTrain(train)).thenReturn(new ArrayList<Ride>());
        rideService.findByTrain(train);
        verify(rideRepository).findByTrain(train);
    }

    @Test
    public void testFindRidesOrderByTimeDepartureMock(){
        when(rideRepository.findByOrderByTimeDeparture()).thenReturn(new ArrayList<Ride>());
        rideService.findRidesOrderByTimeDeparture();
        verify(rideRepository).findByOrderByTimeDeparture();
    }

    @Test
    public void testFindByArrivalMock(){
        when(rideRepository.findByStationArrival(stationArrival)).thenReturn(new ArrayList<Ride>());
        rideService.findByStationArrival(stationArrival);
        verify(rideRepository).findByStationArrival(stationArrival);
    }


    @Test
    public void testFindByDepartureMock(){
        when(rideRepository.findByStationDeparture(stationDeparture)).thenReturn(new ArrayList<Ride>());
        rideService.findByStationDeparture(stationDeparture);
        verify(rideRepository).findByStationDeparture(stationDeparture);
    }
    @Test
    public void testFindByTrainAndTime(){
        when(rideRepository
                .findПожалуйстаByTrainAndTimeDeparture(train,new Date(1493201200000L)))
                .thenReturn(new ArrayList<Ride>());
        rideService.findByTrainAndTime(train,new Date(1493201200000L));
        verify(rideRepository).findПожалуйстаByTrainAndTimeDeparture(train,new Date(1493201200000L));

    }
}
