package com.schweizerischebundesbahnen.junittest.testcontrollersmock;


import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.restcontroller.ScheduleController;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
import com.schweizerischebundesbahnen.service.api.TrainService;
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
public class ScheduleControllerTest {

    private Station stationDeparture;
    private Station stationArrival;
    private Schedule schedule;
    private Train train;

    @Mock
    private TrainService trainService;

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private StationService stationService;

    @InjectMocks
    private ScheduleController scheduleController;

    @Before
    public void setup(){

        stationDeparture = new Station();
        stationDeparture.setStationName("Yaroslavl");
        stationDeparture.setX(0);
        stationDeparture.setY(0);

        stationArrival = new Station();
        stationArrival.setStationName("Basel");
        stationArrival.setX(1);
        stationArrival.setY(1);

        train = new Train();
        train.setId("T");

        schedule = new Schedule();
        schedule.setId(17L);
        schedule.setTrain(train);
        schedule.setStationArrival(stationArrival);
        schedule.setStationDeparture(stationDeparture);



    }

    @Test
    public void testGetScheduleByStationDeparture() throws Exception{
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        when(scheduleService.findByUserStation(stationDeparture)).thenReturn(new ArrayList<Schedule>());
        scheduleController.getListOfScheduleByStationDeparture("Yaroslavl");
        verify(scheduleService).findByUserStation(stationDeparture);
        verify(stationService).findStationByName(stationDeparture.getStationName());

    }

    @Test
    public void testFindTransferSchedule() throws Exception{
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        when(stationService.findStationByName("Basel")).thenReturn(stationArrival);
        when(scheduleService.findTransferSchedule(stationDeparture,stationArrival, new Date(1496260800000L)))
                .thenReturn(new ArrayList<Schedule>());

        scheduleController.getTransferSchedule("Yaroslavl","Basel","2017-06-01");

        verify(scheduleService).findTransferSchedule(stationDeparture,stationArrival, new Date(1496260800000L));
        verify(stationService).findStationByName(stationDeparture.getStationName());
        verify(stationService).findStationByName(stationArrival.getStationName());
    }

    @Test
    public void testGetAllScheduleControllerMock(){
        when(scheduleService.findAllSchedules()).thenReturn(new ArrayList<Schedule>());
        scheduleController.getAllSchedules();
        verify(scheduleService).findAllSchedules();
    }

    @Test
    public void testGetFutureSchedule(){
        when(scheduleService.findByTimeDepartureMoreThan(new Date())).thenReturn(new ArrayList<Schedule>());
        scheduleController.getFutureStation();
        verify(scheduleService).findByTimeDepartureMoreThan(new Date());
    }

    @Test
    public void testGetTodaysSchedule(){
        when(scheduleService.findByTimeDepartureMoreThan(new Date())).thenReturn(new ArrayList<Schedule>());
        scheduleController.getTodaysTimeSchedule();
        verify(scheduleService).findByTimeDepartureMoreThan(new Date());
    }

    @Test
    public void testAddTheScheduleMock(){
        when(trainService.findTrainByName("T")).thenReturn(train);
        when(stationService.findStationByName("Basel")).thenReturn(stationArrival);
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        scheduleController.addTheSchedule(schedule);
        verify(stationService).findStationByName(stationDeparture.getStationName());
        verify(stationService).findStationByName(stationArrival.getStationName());
        verify(trainService).findTrainByName(train.getId());
    }

    @Test
    public void testUpdateTheScheduleMock(){
        when(scheduleService.findScheduleById(17L)).thenReturn(schedule);
        when(trainService.findTrainByName("T")).thenReturn(train);
        when(stationService.findStationByName("Basel")).thenReturn(stationArrival);
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        scheduleController.updateSchedule(schedule);
        verify(scheduleService).findScheduleById(schedule.getId());
        verify(stationService).findStationByName(stationDeparture.getStationName());
        verify(stationService).findStationByName(stationArrival.getStationName());
        verify(trainService).findTrainByName(train.getId());

    }


}
