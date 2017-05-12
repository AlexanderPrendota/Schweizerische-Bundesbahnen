package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.Train;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 28.03.17.
 */
public interface RideRepository  extends CrudRepository<Ride, Long> {

    Ride findByTicket(Ticket ticket);

    List<Ride> findByTrain(Train train);

    List<Ride> findByStationDeparture(Station station);

    List<Ride> findByStationArrival(Station station);

    List<Ride> findByTrainAndTimeDepartureBetween(Train train, Date start, Date end);

    List<Ride> findПожалуйстаByTrainAndTimeDeparture(Train train, Date date);

    List<Ride> findByOrderByTimeDeparture();
}
