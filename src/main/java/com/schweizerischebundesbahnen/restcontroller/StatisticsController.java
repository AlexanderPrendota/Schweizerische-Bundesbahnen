package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "statistics/")
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "bought/departure",method = RequestMethod.GET)
    public ResponseEntity<?> getStatisticsAboutBoughtDepartureTickets(){
        return ResponseEntity.ok(statisticService.getBoughtStationDepartureStatistics());
    }

    @RequestMapping(value = "bought/arrival",method = RequestMethod.GET)
    public ResponseEntity<?> getStatisticsAboutBoughtArrivalTickets(){
        return ResponseEntity.ok(statisticService.getBoughtStationArrivalStatistics());
    }

    @RequestMapping(value = "cash",method = RequestMethod.GET)
    public ResponseEntity<?> getMoneyStatisticsByDate(){
        return ResponseEntity.ok(statisticService.getMoneyStatictics());
    }
}
