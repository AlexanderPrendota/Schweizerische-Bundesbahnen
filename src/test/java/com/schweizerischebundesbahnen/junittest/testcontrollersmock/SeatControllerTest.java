package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.restcontroller.SeatsController;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.SeatService;
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
 * Created by aleksandrprendota on 15.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SeatControllerTest {

    private Train train;

    @Mock
    private SeatService seatService;

    @Mock
    private TrainService trainService;


    @InjectMocks
    private SeatsController seatsController;

    @Before
    public void setup(){
        train = new Train();
        train.setId("T");
    }
    @Test
    public void testCheckSeatSize(){
        when(trainService.findTrainByName("T")).thenReturn(train);
        when(seatService.findSeatByTrain(train)).thenReturn(new ArrayList<Seat>());
        seatsController.getCountOfCarriage("T");
        verify(seatService).findSeatByTrain(train);
        verify(trainService).findTrainByName("T");

    }


}
