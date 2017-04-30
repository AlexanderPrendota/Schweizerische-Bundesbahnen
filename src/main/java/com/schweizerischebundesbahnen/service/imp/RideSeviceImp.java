package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.service.api.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 01.04.17.
 *
 * Implimentation RideService interface
 */
@Service
public class RideSeviceImp implements RideService {

    @Autowired
    private RideRepository rideRepository;

    /**
     * Creating a ride entity in dtabase
     * @param ride new Ride entity
     */
    @Override
    public void addRide(Ride ride) {
        rideRepository.save(ride);
    }

    /**
     * Finding rides by Ticket entity
     * @param ticket Ticket entity
     * @return List of rides which existing in Ticket
     */
    @Override
    public Ride findByTicket(Ticket ticket) {
        return rideRepository.findByTicket(ticket);
    }

    /**
     * Finding rides by Train and times between two dates
     * @param train Train entity
     * @param start start date
     * @param end end date
     * @return List of rides in the period of time at the train
     */
    @Override
    public List<Ride> findRidesByTrainAndDate(Train train, Date start, Date end) {
        return rideRepository.findByTrainAndTimeDepartureBetween(train, start, end);
    }

    /**
     * Getting ride entity by Id
     * @param id ride's id
     * @return existing Ride entity
     */
    @Override
    public Ride findRideById(Long id) {
        return rideRepository.findOne(id);
    }

    /**
     * get list of ride entity by specific train
     * @param train
     * @return list of ride entity
     */
    @Override
    public List<Ride> findByTrain(Train train) {
        return rideRepository.findByTrain(train);
    }

    /**
     * get list of ride entity by station departure
     * @param station
     * @return list of ride entity
     */
    @Override
    public List<Ride> findByStationDeparture(Station station) {
        return rideRepository.findByStationDeparture(station);
    }

    /**
     * get list of ride entity by station arrival
     * @param station
     * @return list of ride entity
     */
    @Override
    public List<Ride> findByStationArrival(Station station) {
        return rideRepository.findByStationArrival(station);
    }

    /**
     * get list of rides by train and time departure
     * @param train
     * @param time
     * @return list of ride entity
     */
    @Override
    public List<Ride> findByTrainAndTime(Train train, Date time) {
        return rideRepository.findПожалуйстаByTrainAndTimeDeparture(train,time);
    }
}
