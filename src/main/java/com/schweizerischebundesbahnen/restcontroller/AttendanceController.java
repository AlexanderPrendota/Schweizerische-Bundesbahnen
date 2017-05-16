package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Attendance;
import com.schweizerischebundesbahnen.service.api.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Attendance> getAttendanceStatistic(){
        return attendanceService.findAllAttendance();
    }
}
