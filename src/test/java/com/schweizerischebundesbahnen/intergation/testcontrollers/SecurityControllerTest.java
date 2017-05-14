package com.schweizerischebundesbahnen.intergation.testcontrollers;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.TimeSchedule;
import com.schweizerischebundesbahnen.restcontroller.ScheduleController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aleksandrprendota on 10.05.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SecurityControllerTest {

    @Autowired
    private ScheduleController scheduleController;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetListOfUsers(){
        List<Schedule> todayTimeSchedule = scheduleController.getAllSchedules();
        Assert.assertTrue(todayTimeSchedule.size() > 0);
    }

    @Test
    public void testGetTodaysScheduleWithOutSecur(){
        List<TimeSchedule> todaysTimeSchedule = scheduleController.getTodaysTimeSchedule();
        Assert.assertTrue(todaysTimeSchedule.size() > 0);
    }
}

