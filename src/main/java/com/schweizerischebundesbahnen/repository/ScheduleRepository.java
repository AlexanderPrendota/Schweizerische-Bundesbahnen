package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 28.03.17.
 */

public interface ScheduleRepository extends CrudRepository<Schedule,Long> {

    List<Schedule> findByStationDepartureAndStationArrivalAndTimeDepartureBetween
            (Station stationDeparture, Station stationArrival, Date start, Date end);

    List<Schedule> findByStationDeparture(Station stationDeparture);

    List<Schedule> findByTrain(Train train);

    List<Schedule> findByStationArrival(Station station);

    List<Schedule> findByTimeDeparture(Date date);

    List<Schedule> findByStationDepartureAndTimeDepartureGreaterThanEqual(Station station,Date date);

    List<Schedule> findByStationDepartureAndTimeDepartureBetween (Station stationDeparture, Date start, Date end);

    List<Schedule> findByStationDepartureAndStationArrivalAndTimeDepartureGreaterThanEqual
            (Station stationDeparture, Station stationArrival, Date start);

}
