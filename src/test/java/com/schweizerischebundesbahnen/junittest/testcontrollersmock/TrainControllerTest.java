package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.restcontroller.TrainController;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.TrainService;
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
 * Created by aleksandrprendota on 15.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrainControllerTest {


    private Train train;

    @Mock
    private TrainService trainService;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private TrainController trainController;

    @Before
    public void setup(){
        train = new Train();
        train.setId("T");
    }


    @Test
    public void testGetListOfTrainNameInController(){
        when(trainService.findAllTrains()).thenReturn(new ArrayList<Train>());
        trainController.getListOfTrainsName();
        verify(trainService).findAllTrains();
    }

    @Test
    public void testGetListOfTrainAllInController(){
        when(trainService.findAllTrains()).thenReturn(new ArrayList<Train>());
        trainController.getListOfTrains();
        verify(trainService).findAllTrains();
    }

    @Test
    public void testAddTrainInController(){
       when(trainService.findTrainByName("T")).thenReturn(train);
       trainController.addNewTrain(train.getId(), "1");
        verify(trainService).findTrainByName(train.getId());
    }

    @Test
    public void testDeleteTrainInController(){
        when(trainService.findTrainByName("T")).thenReturn(train);
        when(scheduleService.findByTrain(train)).thenReturn(new ArrayList<Schedule>());
        trainController.addNewTrain(train.getId(), "1");
        verify(trainService).findTrainByName(train.getId());
    }


}
