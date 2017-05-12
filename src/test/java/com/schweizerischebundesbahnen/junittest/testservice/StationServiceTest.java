package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.service.api.StationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aleksandrprendota on 04.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StationServiceTest {

    @Autowired
    private StationService stationService;

    @Test
    public void testFindStationByName(){
        Assert.assertEquals(stationService.findStationByName("Yaroslavl").getStationName(),"Yaroslavl");
    }

    @Test
    public void testFindStationByNullName(){
        Station station = stationService.findStationByName(null);
        Assert.assertNull(station);
    }

    @Test
    public void testStationByEmptyName(){
        Station station = stationService.findStationByName("");
        Assert.assertNull(station);
    }

    @Test
    public void testStationByWrongName(){
        Station station = stationService.findStationByName("IncorrectStation");
        Assert.assertNull(station);
    }

    @Test
    public void testFindStationById(){
        Assert.assertEquals(stationService.findStationById(10024L).getStationName(),"Laufen");
    }

    @Test
    public void testFindStationByWrongId(){
        Station station = stationService.findStationById(1L);
        Assert.assertNull(station);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindStationByNullId(){
        Station station = stationService.findStationById(null);
        Assert.assertNull(station);
    }

    @Test
    public void testFindAllStation(){
        List<Station> stations = stationService.findAllStation();
        Assert.assertTrue(stations.size() > 0);
    }

}
