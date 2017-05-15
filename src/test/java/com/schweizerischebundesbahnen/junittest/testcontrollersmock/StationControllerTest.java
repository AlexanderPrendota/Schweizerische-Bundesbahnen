package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.restcontroller.StationsController;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
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
public class StationControllerTest {

    private Station stationDeparture;

    @Mock
    private StationService stationService;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private StationsController stationsController;


    @Before
    public void setup(){

        stationDeparture = new Station();
        stationDeparture.setStationName("Yaroslavl");
        stationDeparture.setX(0);
        stationDeparture.setY(0);
    }

    @Test
    public void testGetAllStationMock(){
        when(stationService.findAllStation()).thenReturn(new ArrayList<Station>());
        stationsController.getListOfStations();
        verify(stationService).findAllStation();
    }

    @Test
    public void testGetStationName(){
        when(stationService.findAllStation()).thenReturn(new ArrayList<Station>());
        stationsController.getListOfStationName();
        verify(stationService).findAllStation();
    }

    @Test
    public void testDeleteStationController(){
        when(scheduleService.findByStationDeparture(stationDeparture)).thenReturn(new ArrayList<Schedule>());
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        stationsController.deleteStation(stationDeparture.getStationName());
        verify(stationService).findStationByName(stationDeparture.getStationName());
        verify(scheduleService).findByStationDeparture(stationDeparture);
    }

    @Test
    public void testAddStationController(){
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        stationsController.addNewStation(stationDeparture.getStationName(), stationDeparture.getX(), stationDeparture.getY());
        verify(stationService).findStationByName(stationDeparture.getStationName());
    }

}
