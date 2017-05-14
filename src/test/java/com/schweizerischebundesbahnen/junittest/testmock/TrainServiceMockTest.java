package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.TrainRepository;
import com.schweizerischebundesbahnen.service.imp.TrainServiceImp;
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
public class TrainServiceMockTest {

    private Train train;

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImp trainService;

    @Before
    public void setUp() {
        train = new Train();
        train.setId("MockTrain");

    }

    @Test
    public void testFindTrainByIfMock(){
        when(trainRepository.findById("MockTrain")).thenReturn(train);
        trainService.findTrainByName(train.getId());
        verify(trainRepository).findById(train.getId());
    }

    @Test
    public void testMockGetUsers(){
        when(trainRepository.findAll()).thenReturn(new ArrayList<Train>());
        trainService.findAllTrains();
        verify(trainRepository).findAll();
    }


    @Test
    public void testMockAddTrain(){
        when(trainRepository.save(train)).thenReturn(train);
        trainService.addTrain(train);
        verify(trainRepository).save(train);
    }




}

