package com.schweizerischebundesbahnen.junittest.testservice;


import com.schweizerischebundesbahnen.model.PointDTO;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.service.api.PriceService;
import com.schweizerischebundesbahnen.service.api.StationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DecimalFormat;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceServiceTest {

    private PointDTO pointDTOOne;
    private PointDTO pointDTOTwo;

    @Autowired
    private PriceService priceService;

    @Autowired
    private StationService stationService;

    @Before
    public void setup(){
        pointDTOOne = new PointDTO();
        pointDTOTwo = new PointDTO();
        pointDTOOne.setX(0);
        pointDTOOne.setY(0);
        pointDTOTwo.setY(1);
        pointDTOTwo.setX(1);
    }

    @Test
    public void testCalculateDistance(){
        Double distance = priceService.calculateDistance(pointDTOOne,pointDTOTwo);
        Assert.assertTrue(Math.round(distance * 1000.0) / 1000.0 == 1.414);
    }

    @Test
    public void testWrongCalculateDistance(){
        Double distance = priceService.calculateDistance(pointDTOOne,pointDTOTwo);
        Assert.assertFalse(Math.round(distance * 1000.0) / 1000.0 == 1.454);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateDistanceNull(){
        Double distance = priceService.calculateDistance(null,null);
        Assert.assertNull(distance);
    }

    @Test
    public void testCalculateDistanceEmptyObj(){
        Double distance = priceService.calculateDistance(new PointDTO(),new PointDTO());
        Assert.assertTrue(distance == 0.0);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculatePriceNullObj(){
        Double distance = priceService.calculationPrice(null,null);
        Assert.assertNull(distance);
    }


    @Test
    public void testCalculatePriceEmptyObj(){
        Double distance = priceService.calculationPrice(new PointDTO(),new PointDTO());
        Assert.assertTrue(distance == 0.0);
    }


    @Test
    public void testCalculatePrice(){
        Double distance = priceService.calculationPrice(pointDTOOne,pointDTOTwo);
        Assert.assertTrue(distance == 14.57);
    }

    @Test
    public void testCalculatePriceWrong(){
        Double distance = priceService.calculationPrice(pointDTOOne,pointDTOTwo);
        Assert.assertFalse(distance == 14.58);
    }

    @Test
    public void testCalculatePriceByStation(){
        Station stationDep = stationService.findStationByName("Yaroslavl");
        Station stationArr = stationService.findStationByName("Moscow");
        Double distance = priceService.getPrice(stationDep,stationArr);
        Assert.assertTrue(distance == 32.57);
    }

    @Test
    public void testCalculatePriceByStationWrong(){
        Station stationDep = stationService.findStationByName("Yaroslavl");
        Station stationArr = stationService.findStationByName("Moscow");
        Double distance = priceService.getPrice(stationDep,stationArr);
        Assert.assertFalse(distance == 32.58);
    }


    @Test(expected = NullPointerException.class)
    public void testCalculatePriceByStationNull(){
        Double distance = priceService.getPrice(null,null);
        Assert.assertNull(distance);
    }

    @Test
    public void testCalculatePriceByStationEmptyObj(){
        Double distance = priceService.getPrice(new Station(),new Station());
        Assert.assertTrue(distance == 0.0);
    }






}
