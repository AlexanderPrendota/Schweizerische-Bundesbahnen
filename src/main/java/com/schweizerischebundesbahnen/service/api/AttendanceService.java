package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Attendance;

import java.util.Date;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
public interface AttendanceService {

    Attendance getAttendanceById(Long id);

    Attendance addAttendance(Attendance attendance);

    Attendance updateAttendance(Date date);

    Attendance findAttendanceByDate(Date date);

}
