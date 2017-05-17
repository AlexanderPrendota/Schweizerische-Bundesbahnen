package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Attendance;
import com.schweizerischebundesbahnen.repository.AttendanceRepository;
import com.schweizerischebundesbahnen.service.imp.AttendanceServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by aleksandrprendota on 17.05.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AttendanceServiceTestMock {

    private Attendance attendance;
    private Date date;

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceServiceImp attendanceService;

    @Before
    public void setup(){
        date = new Date();
        attendance = new Attendance();
        attendance.setId(32L);
        attendance.setDate(date);
        attendance.setCount(1);
    }

    @Test
    public void testFindAllAttendanceMock(){
        when(attendanceRepository.findAll()).thenReturn(new ArrayList<Attendance>());
        attendanceService.findAllAttendance();
        verify(attendanceRepository).findAll();
    }

    @Test
    public void testGetAttendanceByIdMock(){
        when(attendanceRepository.findOne(32L)).thenReturn(attendance);
        attendanceService.getAttendanceById(attendance.getId());
        verify(attendanceRepository).findOne(attendance.getId());
    }

    @Test
    public void testAddAttendanceMock(){
        when(attendanceRepository.save(attendance)).thenReturn(attendance);
        attendanceService.addAttendance(attendance);
        verify(attendanceRepository).save(attendance);
    }


    @Test
    public void testFindAttendanceByDate(){
        when(attendanceRepository.findByDate(date)).thenReturn(attendance);
        attendanceService.findAttendanceByDate(date);
        verify(attendanceRepository).findByDate(date);
    }

    @Test
    public void testFindSortedAttendanceMock(){
        when(attendanceRepository.findByOrderByDate()).thenReturn(new ArrayList<Attendance>());
        attendanceService.findSortedAttendance();
        verify(attendanceRepository).findByOrderByDate();
    }
}
