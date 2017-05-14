package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.ScheduleRepository;
import com.schweizerischebundesbahnen.service.imp.ScheduleServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTestMock {

    private Schedule schedule;
    private Station stationDeparture;
    private Station stationArrival;
    private Train train;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImp scheduleService;


    @Before
    public void setup(){

        train = new Train();
        train.setId("MockTrain");

        stationDeparture = new Station();
        stationDeparture.setStationName("StationMock");
        stationDeparture.setId(21L);

        stationArrival = new Station();
        stationArrival.setStationName("StationMockArrival");
        stationArrival.setId(22L);

        schedule = new Schedule();
        schedule.setId(5L);
        schedule.setStationDeparture(stationDeparture);
        schedule.setStationArrival(stationArrival);
        schedule.setTimeDeparture(new Date(1493211200000L));
        schedule.setTimeArrival(new Date(1493251200000L));
        schedule.setTrain(train);

    }
    @Test
    public void testFindScheduleByIdMock(){
        when(scheduleRepository.findOne(5L)).thenReturn(schedule);
        scheduleService.findScheduleById(schedule.getId());
        verify(scheduleRepository).findOne(schedule.getId());
    }

    @Test
    public void testFindScheduleByTrainMock(){
        when(scheduleRepository.findByTrain(train)).thenReturn(new ArrayList<Schedule>());
        scheduleService.findByTrain(train);
        verify(scheduleRepository).findByTrain(train);
    }


    @Test
    public void testFindAllScheduleMock(){
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<Schedule>());
        scheduleService.findAllSchedules();
        verify(scheduleRepository).findAll();
    }


    @Test
    public void testFindScheduleByArrivalMock(){
        when(scheduleRepository.findByStationArrival(stationArrival)).thenReturn(new ArrayList<Schedule>());
        scheduleService.findByStationArrival(stationArrival);
        verify(scheduleRepository).findByStationArrival(stationArrival);
    }


    @Test
    public void testFindScheduleByDepartureMock(){
        when(scheduleRepository.findByStationDeparture(stationDeparture)).thenReturn(new ArrayList<Schedule>());
        scheduleService.findByStationDeparture(stationDeparture);
        verify(scheduleRepository).findByStationDeparture(stationDeparture);
    }


    @Test
    public void testFindScheduleByTimeDepartureMock(){
        when(scheduleRepository.findByTimeDeparture(new Date(1493211200000L)))
                .thenReturn(new ArrayList<Schedule>());
        scheduleService.findByTimeDeparture(new Date(1493211200000L));
        verify(scheduleRepository).findByTimeDeparture(new Date(1493211200000L));
    }

    @Test
    public void testFindScheduleByTimeDepartureMoreMock(){
        when(scheduleRepository.findByTimeDepartureGreaterThanEqual(new Date(1493201200000L)))
                .thenReturn(new ArrayList<Schedule>());
        scheduleService.findByTimeDepartureMoreThan(new Date(1493201200000L));
        verify(scheduleRepository).findByTimeDepartureGreaterThanEqual(new Date(1493201200000L));
    }

    @Test
    public void testFindUserRidesMock(){
        when(scheduleRepository
                .findByStationDepartureAndStationArrivalAndTimeDepartureBetween(stationDeparture,stationArrival,
                        new Date(1493201200000L),
                        new Date(1493221200000L)))
                .thenReturn(new ArrayList<Schedule>());
        scheduleService.findUserRides(stationDeparture,stationArrival,
                new Date(1493201200000L),
                new Date(1493221200000L));
        verify(scheduleRepository)
                .findByStationDepartureAndStationArrivalAndTimeDepartureBetween(stationDeparture,
                        stationArrival,
                        new Date(1493201200000L),
                        new Date(1493221200000L));
    }


}


