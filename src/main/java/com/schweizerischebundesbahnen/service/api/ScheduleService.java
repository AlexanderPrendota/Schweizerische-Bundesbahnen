package com.schweizerischebundesbahnen.service.api;


import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 29.03.17.
 */


public interface ScheduleService {

    void addSchedule(Schedule schedule);

    void delete(Schedule schedule);

    Schedule findScheduleById(Long id);

    List<Schedule> findUserRides(Station stationDeparture, Station stationArrival, Date start, Date end);

    List<Schedule> findByStationDeparture(Station station);

    List<Schedule> findAllSchedules();

    List<Schedule> findByTrain(Train train);

    List<Schedule> findByStationArrival(Station station);

    List<Schedule> findByUserStation(Station station);

    List<Schedule> findByTimeDeparture(Date date);

    List<Schedule> findByTimeDepartureMoreThan(Date date);

    List<Schedule> findTransferSchedule(Station departure, Station arrival, Date dateDeparture);

}
