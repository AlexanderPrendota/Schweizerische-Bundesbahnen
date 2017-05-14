package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.service.api.StatisticService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
public class StatisticServiceTest {
    @Autowired
    private StatisticService statisticService;

    @Test
    public void getCorrectMoneyStatistisc(){
        List<Map> statisctics = statisticService.getMoneyStatictics();
        Assert.assertTrue(statisctics.get(0).containsValue(116.53));
    }

    @Test
    public void getBoughtStatistics(){
        List<Map> statisctics = statisticService.getBoughtStationDepartureStatistics();
        Assert.assertTrue(statisctics.size() > 0);
    }

    @Test
    public void getMoneyStatistisc(){
        List<Map> statisctics = statisticService.getMoneyStatictics();
        Assert.assertTrue(statisctics.size() > 0);
    }
}
