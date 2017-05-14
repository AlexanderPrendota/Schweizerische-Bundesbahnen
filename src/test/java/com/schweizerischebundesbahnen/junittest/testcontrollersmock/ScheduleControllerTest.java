package com.schweizerischebundesbahnen.junittest.testcontrollersmock;


import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.restcontroller.ScheduleController;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
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

    }

    @Test
    public void testGetScheduleByStationDeparture() throws Exception{
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        when(scheduleService.findByUserStation(stationDeparture)).thenReturn(new ArrayList<Schedule>());
        scheduleController.getListOfScheduleByStationDeparture("Yaroslavl");
        verify(scheduleService).findByUserStation(stationDeparture);

    }

    @Test
    public void testFindTransferSchedule() throws Exception{
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        when(stationService.findStationByName("Basel")).thenReturn(stationArrival);
        when(scheduleService.findTransferSchedule(stationDeparture,stationArrival, new Date(1496260800000L)))
                .thenReturn(new ArrayList<Schedule>());

        scheduleController.getTransferSchedule("Yaroslavl","Basel","2017-06-01");

        verify(scheduleService).findTransferSchedule(stationDeparture,stationArrival, new Date(1496260800000L));
    }

    @Test
    public void testGetAllScheduleControllerMock(){
        when(scheduleService.findAllSchedules()).thenReturn(new ArrayList<Schedule>());
        scheduleController.getAllSchedules();
        verify(scheduleService).findAllSchedules();
    }
}
