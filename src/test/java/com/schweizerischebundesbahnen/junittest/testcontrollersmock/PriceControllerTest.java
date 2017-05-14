package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.restcontroller.PriceController;
import com.schweizerischebundesbahnen.service.api.PriceService;
import com.schweizerischebundesbahnen.service.api.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceControllerTest {

    private Station stationDeparture;
    private Station stationArrival;

    @Mock
    private PriceService priceService;

    @Mock
    private StationService stationService;

    @InjectMocks
    private PriceController priceController;

    @Before
    public void setup(){
        stationDeparture = new Station();
        stationDeparture.setStationName("Yaroslavl");
        stationDeparture.setX(0);
        stationDeparture.setY(0);
        stationArrival = new Station();
        stationArrival.setStationName("Moscow");
        stationArrival.setX(1);
        stationArrival.setY(1);

    }
    @Test
    public void testGetPriceController(){
        when(stationService.findStationByName("Yaroslavl")).thenReturn(stationDeparture);
        when(stationService.findStationByName("Moscow")).thenReturn(stationArrival);
        when(priceService.getPrice(stationDeparture,stationArrival)).thenReturn(1.414);
        priceController.getRidePrice("Yaroslavl","Moscow");
        verify(priceService).getPrice(stationDeparture,stationArrival);
    }
}
