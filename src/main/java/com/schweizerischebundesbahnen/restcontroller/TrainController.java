package com.schweizerischebundesbahnen.restcontroller;


import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.SeatService;
import com.schweizerischebundesbahnen.service.api.TrainService;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aleksandrprendota on 31.03.17.
 *
 * Only user with role 'ADMIN' can use that controller
 */
@RestController

@Log4j
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private RideService rideService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatService seatService;
    /**
     * Getting all of train entity from database
     * @return List of train entity
     */
    @RequestMapping(value = "admin/alltrains",method = RequestMethod.GET)
    public List<Train> getListOfTrains(){
        return trainService.findAllTrains();
    }

    /**
     * Add new train entity to database.
     * Save train with seats.
     * 1 carriage = 20 seats
     * @param trainname Name of Train
     * @param carriage Count of carriage
     * @return new Train entity
     */
    @RequestMapping(value = "/new/{trainname}/carriage/{carriage}", method = RequestMethod.POST)
    public ResponseEntity<?> addNewTrain(@PathVariable String trainname,
                                         @PathVariable String carriage){
        if (trainService.findTrainByName(trainname) == null){
            Train train = new Train();
            train.setId(trainname);
            for(int cabine = 1; cabine <= Integer.valueOf(carriage); cabine++){
                for (int numberOfSeat = 1; numberOfSeat <= 20; numberOfSeat++){
                    Seat seat = new Seat();
                    seat.setTrain(train);
                    seat.setCabine(cabine);
                    seat.setNumber(numberOfSeat);
                    seatService.save(seat);
                }
            }
            trainService.addTrain(train);
            log.info("Train '" + trainname + "' was added");
            return ResponseEntity.ok(train);
        } else {
            log.warn("Train has already exist");
            return new ResponseEntity<>("Train has already exist", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find passenger on specific train
     * @param train Train name
     * @param dateDeparture station Departure
     * @return List of users on train
     */
    @RequestMapping(value = "/passangers/train/{train}/departure/{dateDeparture}")
    public List<User> getUserListOnTrain(@PathVariable String train,
                                         @PathVariable String dateDeparture){
        List<User> users = new ArrayList<>();
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Train mainTrain = trainService.findTrainByName(train);
            Date dateFromUser = formatData.parse(dateDeparture);
            Date endDate = new Date(dateFromUser.getTime() + 86399L * 1000);
            List<Ride> rides = rideService.findRidesByTrainAndDate(mainTrain,dateFromUser,endDate);
            if (rides.size() != 0){
                for (Ride ride : rides) {
                    users.add(ride.getTicket().getUser());
                }
            }
            return users;
        } catch (ParseException e){
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Delete eisting train entity
     * @param train name of train
     * @return uccess or not success
     */
    @RequestMapping(value = "/delete/{train}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTrain(@PathVariable String train){

        Train trainFromDB = trainService.findTrainByName(train);

        if (!train.equals("")) {

            List<Schedule> schedulesWithTrain = scheduleService.findByTrain(trainFromDB);
            if (schedulesWithTrain.size() > 0) {
                log.warn("Exception with deleting train");
                return new ResponseEntity<>("Cannot delete the train cause it in schedule", HttpStatus.BAD_REQUEST);
            }

            trainService.delete(trainFromDB);
            log.info("Train '" + train + "' was deleted" );
            return ResponseEntity.ok("Train was deleted");
        } else {
            return new ResponseEntity<>("Why have u put wrong params?", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Getting list of trains string name
     * @return list of trains name
     */
    @RequestMapping(value = "/trains/name",method = RequestMethod.GET)
    public List<String> getListOfTrainsName(){
        List<Train> trains = trainService.findAllTrains();
        List<String> trainsName = new ArrayList<>();
        for (Train train : trains) {
            trainsName.add(train.getId());
        }
        return trainsName;
    }
}
