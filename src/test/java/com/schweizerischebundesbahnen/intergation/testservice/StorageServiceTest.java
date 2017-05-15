package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.service.api.StorageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aleksandrprendota on 04.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;

    @Test
    public void testStorageLoadImage(){
        String fileName = storageService.loadAsResource("Landscape.png").getFilename();
        Assert.assertEquals(fileName, "Landscape.png");
    }

    @Test(expected = com.schweizerischebundesbahnen.exceptions.StorageFileNotFoundException.class)
    public void testStorageLoadIncorrectImage(){
        Resource resource = storageService.loadAsResource("Incorrect.png");
    }

    @Test(expected = NullPointerException.class)
    public void testStorageLoadNullImage(){
        Resource resource = storageService.loadAsResource(null);
    }

    @Test
    public void testStorageLoadEmptyImage(){
        Resource resource = storageService.loadAsResource("");
        System.out.println(resource.getFilename());
        Assert.assertEquals(resource.getFilename(), "images");
    }



}
