package com.schweizerischebundesbahnen.junittest.testservice;


import com.schweizerischebundesbahnen.model.PointDTO;
import com.schweizerischebundesbahnen.service.api.PriceService;
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

}
