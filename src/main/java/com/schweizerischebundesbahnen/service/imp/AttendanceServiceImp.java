package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Attendance;
import com.schweizerischebundesbahnen.repository.AttendanceRepository;
import com.schweizerischebundesbahnen.service.api.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
@Service
public class AttendanceServiceImp implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findOne(id);
    }

    @Override
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance updateAttendance(Date date) {
        Attendance attendance = findAttendanceByDate(date);
        if (attendance == null){
            Attendance newAtt = new Attendance();
            newAtt.setCount(1);
            newAtt.setDate(new Date());
            return addAttendance(newAtt);
        } else {
            int count = attendance.getCount() + 1;
            attendance.setCount(count);
            return attendanceRepository.save(attendance);
        }
    }

    @Override
    public Attendance findAttendanceByDate(Date date) {
        return attendanceRepository.findByDate(date);
    }

    @Override
    public List<Attendance> findAllAttendance() {
        List<Attendance> attendances = new ArrayList<>();
        attendanceRepository.findAll().forEach(attendances::add);
        return attendances;
    }

    @Override
    public List<Attendance> findSortedAttendance() {
        return attendanceRepository.findByOrderByDate();
    }
}
