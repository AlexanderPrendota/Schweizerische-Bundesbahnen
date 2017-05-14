package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.restcontroller.StatisticsController;
import com.schweizerischebundesbahnen.service.api.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerTest {

    @Mock
    private StatisticService statisticService;

    @InjectMocks
    private StatisticsController statisticsController;

    @Test
    public void getStatisticAboutBoughtDeparture(){
        when(statisticService.getBoughtStationDepartureStatistics()).thenReturn(new ArrayList<Map>());
        statisticsController.getStatisticsAboutBoughtDepartureTickets();
        verify(statisticService).getBoughtStationDepartureStatistics();

    }

    @Test
    public void getStatisticAboutBoughtArrival(){
        when(statisticService.getBoughtStationArrivalStatistics()).thenReturn(new ArrayList<Map>());
        statisticsController.getStatisticsAboutBoughtArrivalTickets();
        verify(statisticService).getBoughtStationArrivalStatistics();

    }

    @Test
    public void getStatisticMoney(){
        when(statisticService.getMoneyStatistics()).thenReturn(new ArrayList<Map>());
        statisticsController.getMoneyStatisticsByDate();
        verify(statisticService).getMoneyStatistics();

    }
}
