package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.service.api.StatisticService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticServiceTest {
    @Autowired
    private StatisticService statisticService;

    @Test
    public void getCorrectMoneyStatistisc(){
        List<Map> statisctics = statisticService.getMoneyStatistics();
        Assert.assertTrue(statisctics.get(0).containsValue(116.53));
    }

    @Test
    public void getBoughtDepartureStatistics(){
        List<Map> statisctics = statisticService.getBoughtStationDepartureStatistics();
        Assert.assertTrue(statisctics.size() > 0);
    }

    @Test
    public void getBoughtArrivalStatistics(){
        List<Map> statisctics = statisticService.getBoughtStationArrivalStatistics();
        Assert.assertTrue(statisctics.size() > 0);
    }

    @Test
    public void getMoneyStatistisc(){
        List<Map> statisctics = statisticService.getMoneyStatistics();
        Assert.assertTrue(statisctics.size() > 0);
    }
}
