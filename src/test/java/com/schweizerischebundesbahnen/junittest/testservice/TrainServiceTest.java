package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.service.api.TrainService;
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
public class TrainServiceTest {

    @Autowired
    private TrainService trainService;

    @Test
    public void testTrainId() {
        Train train = trainService.findTrainByName("A303");
        Assert.assertEquals(train.getId(),"A303");
    }

    @Test
    public void testTrainWithEmptyName(){
        Train train = trainService.findTrainByName("");
        Assert.assertNull(train);
    }

    @Test
    public void testTrainWithWrongName(){
        Train train = trainService.findTrainByName("IncorrectTrain");
        Assert.assertNull(train);
    }

    @Test
    public void testTrainWithNullName(){
        Train train = trainService.findTrainByName(null);
        Assert.assertNull(train);
    }

    @Test
    public void testListOfTrain(){
        List<Train> listOfTrain = trainService.findAllTrains();
        Assert.assertTrue(listOfTrain.size() > 0);
    }

    @Test
    public void testAddTrain(){
        Train train = new Train();
        train.setId("TEST_TRAIN");
        trainService.addTrain(train);
        Assert.assertEquals(trainService.findTrainByName("TEST_TRAIN").getId(), "TEST_TRAIN");
    }

    @Test
    public void testDeleteTrain(){
        Train train = trainService.findTrainByName("TEST_TRAIN");
        trainService.delete(train);
        Assert.assertNull(trainService.findTrainByName("TEST_TRAIN"));
    }
}
