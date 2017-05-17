package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 16.05.17.
 */
public interface AttendanceRepository extends CrudRepository<Attendance, Long>{

    Attendance findByDate(Date date);

    List<Attendance> findByOrderByDate();
}
