package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.repository.StationRepository;
import com.schweizerischebundesbahnen.service.imp.StationServiceImp;
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
public class StationServiceMockTest {

    private Station station;

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationServiceImp stationService;

    @Before
    public void setup(){
        station = new Station();
        station.setStationName("StationMock");
        station.setId(21L);
    }

    @Test
    public void testFindStationById(){
        when(stationRepository.findOne(21L)).thenReturn(station);
        stationService.findStationById(station.getId());
        verify(stationRepository).findOne(station.getId());
    }

    @Test
    public void testFindStationByName(){
       when(stationRepository.findByStationName("StationMock")).thenReturn(station);
       stationService.findStationByName(station.getStationName());
       verify(stationRepository).findByStationName(station.getStationName());
    }

    @Test
    public void testFindAllStation(){
        when(stationRepository.findAll()).thenReturn(new ArrayList<Station>());
        stationService.findAllStation();
        verify(stationRepository).findAll();
    }


}
