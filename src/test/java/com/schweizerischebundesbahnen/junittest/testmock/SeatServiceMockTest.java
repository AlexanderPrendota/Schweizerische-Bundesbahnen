package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.SeatRepository;
import com.schweizerischebundesbahnen.service.imp.SeatServiceImp;
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
public class SeatServiceMockTest {

    private Seat seat;
    private Train train;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatServiceImp seatService;

    @Before
    public void setup(){
        train = new Train();
        train.setId("MockTrain");
        seat = new Seat();
        seat.setId(7L);
        seat.setTrain(train);
        seat.setCabine(1);
        seat.setNumber(1);
    }

    @Test
    public void testSaveSeatMock(){
        when(seatRepository.save(seat)).thenReturn(seat);
        seatService.save(seat);
        verify(seatRepository).save(seat);
    }

    @Test
    public void testfindByTrainAndNumberAndCarriageMock(){
        when(seatRepository.findByTrainAndNumberAndCabine(train,1,1)).thenReturn(seat);
        seatService.findByTrainAndNumberAndCarriage(train,1,1);
        verify(seatRepository).findByTrainAndNumberAndCabine(train,1,1);
    }

    @Test
    public void testfindSeatByTrainMock(){
        when(seatRepository.findByTrain(train)).thenReturn(new ArrayList<Seat>());
        seatService.findSeatByTrain(train);
        verify(seatRepository).findByTrain(train);
    }


//
//    Seat findByTrainAndNumberAndCarriage(Train train, int number, int cabine);
//
//    List<Seat> findSeatByTrain(Train train);

}
