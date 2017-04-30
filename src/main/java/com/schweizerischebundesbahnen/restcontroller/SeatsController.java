package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.SeatService;
import com.schweizerischebundesbahnen.service.api.TrainService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 15.04.17.
 */
@RestController
@RequestMapping(value = "/seats")
public class SeatsController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private RideService rideService;

    @Autowired
    private SeatService seatService;

    /**
     * Get free seats on the specific train in the specific carriage
     * @param train
     * @param timeDeparture
     * @param carriage
     * @return List of free number of seats
     * @throws ParseException
     */
    @RequestMapping(value = "/freeseats/train/{train}/time/{timeDeparture}/carriage/{carriage}",
    method = RequestMethod.GET)
    public List<Integer> getFreeSeatsOnRide(@PathVariable String train,
                                            @PathVariable String timeDeparture,
                                            @PathVariable String carriage) throws ParseException{
        List<Integer> seatsFreeNumber = new ArrayList<>();
        SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        Date dateDeparture = formatData.parse(timeDeparture);
        Train userTrain = trainService.findTrainByName(train);
        Date n = DateUtils.addHours(dateDeparture,1);
        List<Ride> rides = rideService.findByTrainAndTime(userTrain,n);
        if (rides.size() == 0){
            return seatsFreeNumber;
        } else {
            for (Ride ride : rides) {
                if(ride.getSeat().getCabine() == Integer.parseInt(carriage)){
                    seatsFreeNumber.add(ride.getSeat().getNumber());
                }
            }
        }
        return seatsFreeNumber;
    }

    /**
     * Get count of carriage in the specific train
     * 1 carriage = 20 seats
     * @param train entuty
     * @return coutn of train carriage
     */
    @RequestMapping(value = "/countofcarriage/train/{train}", method = RequestMethod.GET)
    public int getCountOfCarriage(@PathVariable String train){
        Train trainFromUser = trainService.findTrainByName(train);
        List<Seat> seats = seatService.findSeatByTrain(trainFromUser);
        return seats.size() / 20;
    }

}
