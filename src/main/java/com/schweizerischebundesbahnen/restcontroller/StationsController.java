package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 31.03.17.
 */

@RestController
@Log4j
@RequestMapping(value = "/station")
public class StationsController {

    @Autowired
    private StationService stationService;


    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a station on save one to databese
     * Only user with role 'ADMIN' can to what
     * @param stationname
     * @return 200 and new Station entity
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add/{stationname}/x_cor/{x_cor}/y_cor/{y_cor}", method = RequestMethod.POST)
    public ResponseEntity<?> addNewStation(@PathVariable String stationname,
                                           @PathVariable String x_cor,
                                           @PathVariable String y_cor){
        Integer x;
        Integer y;
        try {
            x = Integer.valueOf(x_cor);
            y = Integer.valueOf(y_cor);
        } catch (Exception e){
            return new ResponseEntity<>("Please add correct integer number ", HttpStatus.BAD_REQUEST);
        }
        if (stationService.findStationByName(stationname) == null){
            Station station = new Station();
            station.setStationName(stationname);
            station.setX(x);
            station.setY(y);
            stationService.addStation(station);
            log.info("New Station '" + stationname + "' was added");
            return ResponseEntity.ok(station);
        } else {
            log.warn("Station's already exist");
            return new ResponseEntity<>("Station's already exist", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete existing station entity
     * @param station name of station
     * @return cuccess or not success
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{station}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStation(@PathVariable String station){

        Station stationFromDB = stationService.findStationByName(station);

        if (!station.equals("")){

            List<Schedule> schedulesWithStationDeparture = scheduleService.findByStationDeparture(stationFromDB);

            if (schedulesWithStationDeparture.size() > 0){
                log.warn("Exception with deleting station departure");
                return new ResponseEntity<>("Cannot delete the station cause it in schedule", HttpStatus.BAD_REQUEST);
            }

            List<Schedule> schedulesWithStationArrival = scheduleService.findByStationArrival(stationFromDB);

            if(schedulesWithStationArrival.size() > 0){
                log.warn("Exception with deleting station arrival");
                return new ResponseEntity<>("Cannot delete the station cause it in schedule", HttpStatus.BAD_REQUEST);
            }

            stationService.delete(stationFromDB);
            log.info("Station " + station + " was deleted");
            return ResponseEntity.ok("Station was deleted");
        } else {
            return new ResponseEntity<>("Why have you sent wrong params?",HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Getting list of station entity
     * @return list of station
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/allstations",method = RequestMethod.GET)
    public List<Station> getListOfStations(){
        return stationService.findAllStation();
    }

    /**
     * Getting list of station string name
     * @return list of station name
     */
    @RequestMapping(value = "/stations/name",method = RequestMethod.GET)
    public List<String> getListOfStationName(){
        List<Station> stations = stationService.findAllStation();
        List<String> stationsName = new ArrayList<>();
        for (Station station : stations) {
            stationsName.add(station.getStationName());
        }
        return stationsName;
    }
}
