package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.exceptions.StationNotFoundException;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.service.api.PriceService;
import com.schweizerischebundesbahnen.service.api.StationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
@RestController
@Log4j
@RequestMapping(value = "/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/departure/{departure}/arrival/{arrival}")
    public ResponseEntity<?> getRidePrice(@PathVariable String departure,
                                          @PathVariable String arrival){

        Station stationDeparture = stationService
                .findStationByName(departure);
        Station stationArrival = stationService
                .findStationByName(arrival);

        if (stationDeparture == null || stationArrival == null){
            log.warn("Station not found with getting price");
            return new ResponseEntity<>(new StationNotFoundException("Station not found"), HttpStatus.BAD_REQUEST);
        }
        Double price = priceService.getPrice(stationDeparture,stationArrival);

     return ResponseEntity.ok(price);
    }
}
