package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.Train;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by aleksandrprendota on 01.04.17.
 */
public interface RideService {

    void addRide(Ride ride);

    void delete(Ride ride);

    Ride findByTicket(Ticket ticket);

    List<Ride> findRidesByTrainAndDate(Train train, Date start, Date end);

    Ride findRideById(Long id);

    List<Ride> findAllRides();

    List<Ride> findByTrain(Train train);

    List<Ride> findByStationDeparture(Station station);

    List<Ride> findByStationArrival(Station station);

    List<Ride> findByTrainAndTime(Train train, Date time);

    List<Ride> findRidesOrderByTimeDeparture();

}
