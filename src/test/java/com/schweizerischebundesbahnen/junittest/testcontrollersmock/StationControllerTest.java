package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.restcontroller.StationsController;
import com.schweizerischebundesbahnen.service.api.StationService;
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

    @Mock
    private StationService stationService;

    @InjectMocks
    private StationsController stationsController;

    @Test
    public void testGetAllStationMock(){
        when(stationService.findAllStation()).thenReturn(new ArrayList<Station>());
        stationsController.getListOfStations();
        verify(stationService).findAllStation();
    }

}
