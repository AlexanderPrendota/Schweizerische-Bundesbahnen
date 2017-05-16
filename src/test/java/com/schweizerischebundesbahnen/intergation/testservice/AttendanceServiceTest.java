package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.model.Attendance;
import com.schweizerischebundesbahnen.service.api.AttendanceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AttendanceServiceTest {


    @Autowired
    private AttendanceService attendanceService;

    @Test
    public void testUpdateAttendance(){
        Attendance attendance = attendanceService.updateAttendance(new Date());
        Assert.assertNotNull(attendance);
    }

}
