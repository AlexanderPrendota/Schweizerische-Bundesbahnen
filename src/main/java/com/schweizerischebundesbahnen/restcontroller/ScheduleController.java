package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.config.MQSender;
import com.schweizerischebundesbahnen.exceptions.StationNotFoundException;
import com.schweizerischebundesbahnen.exceptions.TrainNotFoundException;
import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.TimeSchedule;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
import com.schweizerischebundesbahnen.service.api.TrainService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by aleksandrprendota on 29.03.17.
 */

@RestController
@Log4j
@RequestMapping(value = "/schedule")
public class ScheduleController {


    public static final long CURRENT_DAY = 86399L * 1000;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    private MQSender mqSender = new MQSender();


    /**
     * Getting Schedules by two stations and date
     * Schedules will be found for 1 calendar day
     * @param stationDeparture station entity departure
     * @param stationArrival station entity arrival
     * @param date Date of travel
     * @return List of existing Schedule
     * @throws Exception
     */
    @RequestMapping(value = "/departure/{stationDeparture}/arrival/{stationArrival}/date/{date}"
            , method = RequestMethod.GET)
    public List<Schedule> getScheduleByUsersWish(@PathVariable String stationDeparture,
                                                 @PathVariable String stationArrival,
                                                 @PathVariable String date) throws Exception{

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFromUser = formatData.parse(date);

        Date endDate = new Date(dateFromUser.getTime() + CURRENT_DAY);
        Station stationArrivalFromUser = stationService.findStationByName(stationArrival);
        Station stationDepartureFromUser = stationService.findStationByName(stationDeparture);


        List<Schedule> schedules = scheduleService.findUserRides(stationDepartureFromUser,
                stationArrivalFromUser,
                dateFromUser, endDate);
        return schedules;
    }

    /**
     * Find List of Schedules by Station Departure field
     * @param stationDeparture station entity
     * @return List of schedule on current station in param
     */
    @RequestMapping(value = "/station/{stationDeparture}",method = RequestMethod.GET)
    public List<Schedule> getListOfScheduleByStationDeparture(@PathVariable String stationDeparture){

        Station stationDepartureFromUser = stationService.findStationByName(stationDeparture);

        return scheduleService.findByUserStation(stationDepartureFromUser);
    }

    @RequestMapping(value = "/timeschedule/station/{stationDeparture}",method = RequestMethod.GET)
    public List<TimeSchedule> getListOfTimeScheduleByStationDeparture(@PathVariable String stationDeparture){
        List<TimeSchedule> timeSchedules = new ArrayList<>();
        Station stationDepartureFromAPI = stationService.findStationByName(stationDeparture);
        if (stationDeparture != null){
            List<Schedule> schedules = scheduleService.findByUserStation(stationDepartureFromAPI);
            for (Schedule schedule : schedules) {
                TimeSchedule timeSchedule = TimeSchedule.builder()
                        .train(schedule.getTrain().getId())
                        .stationDeparture(schedule.getStationDeparture().getStationName())
                        .stationArrival(schedule.getStationArrival().getStationName())
                        .timeDeparture(schedule.getTimeDeparture().toString())
                        .timeArrival(schedule.getTimeArrival().toString())
                        .build();
                timeSchedules.add(timeSchedule);
            }
        }
        return timeSchedules;
    }


    @RequestMapping(value = "/todays",method = RequestMethod.GET)
    public List<TimeSchedule> getTodaysTimeSchedule(){
        List<TimeSchedule> timeSchedules = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(date);

        for (Schedule schedule : schedules) {
            Date departure = DateUtils.addHours(schedule.getTimeDeparture(),-1);
            Date arrival = DateUtils.addHours(schedule.getTimeArrival(),-1);
            TimeSchedule timeSchedule = TimeSchedule.builder()
                    .train(schedule.getTrain().getId())
                    .stationDeparture(schedule.getStationDeparture().getStationName())
                    .stationArrival(schedule.getStationArrival().getStationName())
                    .timeDeparture(departure.toString())
                    .timeArrival(arrival.toString())
                    .build();
            timeSchedules.add(timeSchedule);
        }
        return timeSchedules;
    }


    /**
     * Get list of Schedule with transfer ways
     * @param stationDeparture
     * @param stationArrival
     * @param date
     * @return list schedules with and without transfers ways
     * @throws Exception
     */
    @RequestMapping(value = "transfer/departure/{stationDeparture}/arrival/{stationArrival}/date/{date}"
            , method = RequestMethod.GET)
    public ResponseEntity<?> getTransferSchedule(@PathVariable String stationDeparture,
                                                 @PathVariable String stationArrival,
                                                 @PathVariable String date) throws Exception{
        Station departure = stationService
                .findStationByName(stationDeparture);
        Station arrival = stationService
                .findStationByName(stationArrival);

        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDeparture = formatData.parse(date);

        if (departure == null || arrival == null){
            log.warn("Station not found with getting transver schedule");
            return new ResponseEntity<>(new StationNotFoundException("Station not found"),HttpStatus.BAD_REQUEST);
        }

        List<Schedule> transverScheduule = scheduleService.findTransferSchedule(departure,
                arrival,
                dateDeparture);

        return ResponseEntity.ok(transverScheduule);
    }

    /**
     *
     * Create the new schedule entity in db
     * @param schedule new entity
     * @return success or not success
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ResponseEntity<?> addTheSchedule(@RequestBody Schedule schedule){

        Schedule newSchedule = new Schedule();
        Train trainFromSchedule = trainService.findTrainByName(schedule.getTrain().getId());
        Station stationDepartureFromSchedule = stationService
                .findStationByName(schedule.getStationDeparture().getStationName());
        Station stationArrivalFromSchedule = stationService
                .findStationByName(schedule.getStationArrival().getStationName());

        if (trainFromSchedule == null) {
            log.warn("Wrong params with adding schedule! Train not found");
            return new ResponseEntity<>(new TrainNotFoundException("Train not found"),HttpStatus.BAD_REQUEST);
        }

        if (stationDepartureFromSchedule == null || stationArrivalFromSchedule == null){
            log.warn("Wrong params with adding schedule! Station not found");
            return new ResponseEntity<>(new StationNotFoundException("Station not found"),HttpStatus.BAD_REQUEST);
        }

        newSchedule.setStationArrival(stationArrivalFromSchedule);
        newSchedule.setStationDeparture(stationDepartureFromSchedule);
        newSchedule.setTimeArrival(schedule.getTimeArrival());
        newSchedule.setTimeDeparture(schedule.getTimeDeparture());
        newSchedule.setTrain(trainFromSchedule);
        scheduleService.addSchedule(newSchedule);
        sendMessageForUpdating();
        log.info("Schedule was added successfully");
        return ResponseEntity.ok(newSchedule);

    }

    /**
     * Delete existing schedule entity
     * @param id of schedule entity
     * @return cuccess or not success
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSchedule(@PathVariable String id){
        if (!id.equals("")){
            scheduleService.delete(scheduleService.findScheduleById(Long.valueOf(id)));
            log.info("Schedule was deleted successfully");
            sendMessageForUpdating();
            return ResponseEntity.ok("Schedule was deleted");
        } else {
            return new ResponseEntity<>("Mistake with deleting schedule", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Getting all of schedule from db
     * @return list of schedule entity
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Schedule> getAllSchedules(){
        return scheduleService.findAllSchedules();
    }

    /**
     * Update schedule
     * @param schedule entity
     * @return updated schedule entity
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule){

        Schedule oldSchedule = scheduleService.findScheduleById(schedule.getId());

        Train trainFromSchedule = trainService.findTrainByName(schedule.getTrain().getId());
        Station stationDepartureFromSchedule = stationService
                .findStationByName(schedule.getStationDeparture().getStationName());
        Station stationArrivalFromSchedule = stationService
                .findStationByName(schedule.getStationArrival().getStationName());

        if (trainFromSchedule == null) {
            log.warn("Wrong params with adding schedule! Train not found");
            return new ResponseEntity<>(new TrainNotFoundException("Train not found"),HttpStatus.BAD_REQUEST);
        }

        if (stationDepartureFromSchedule == null || stationArrivalFromSchedule == null){
            log.warn("Wrong params with adding schedule! Station not found");
            return new ResponseEntity<>(new StationNotFoundException("Station not found"),HttpStatus.BAD_REQUEST);
        }

        oldSchedule.setTrain(trainFromSchedule);
        oldSchedule.setStationArrival(stationArrivalFromSchedule);
        oldSchedule.setStationDeparture(stationDepartureFromSchedule);
        oldSchedule.setTimeDeparture(schedule.getTimeDeparture());
        oldSchedule.setTimeArrival(schedule.getTimeArrival());

        scheduleService.addSchedule(oldSchedule);
        sendMessageForUpdating();
        log.info("Schedule id=" + String.valueOf(schedule.getId()) + " was updated");

        return ResponseEntity.ok(oldSchedule);
    }

    private void sendMessageForUpdating(){
        try {
            mqSender.sendMessage("Schedule update");
        } catch (Exception e){
            log.error("Have problems with sending massage");
        }
    }

}

