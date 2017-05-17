package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Attendance;
import com.schweizerischebundesbahnen.restcontroller.AttendanceController;
import com.schweizerischebundesbahnen.service.api.AttendanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @Test
    public void testAttendanceStatisticMock(){
        when(attendanceService.findSortedAttendance()).thenReturn(new ArrayList<Attendance>());
        attendanceController.getAttendanceStatistic();
        verify(attendanceService).findSortedAttendance();
    }
}
