package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.service.api.PriceService;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@Service
public class StatisticServiceImp implements StatisticService{

    @Autowired
    private RideService rideService;

    @Autowired
    private PriceService priceService;

    @Override
    public List<Map> getMoneyStatistics(){
        List<Ride> rides = rideService.findRidesOrderByTimeDeparture();
        List<Map> statistics = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Ride ride : rides) {
            String date = dateFormat.format(ride.getTimeDeparture());
            Double price = priceService.getPrice(ride.getStationDeparture(),ride.getStationArrival());
            if (dates.contains(date)){
                for (Map statistic : statistics) {
                    if(statistic.get("date").equals(date)) {
                        Double priceTMP = (Double) statistic.get("value") + price;
                        statistic.put("value", priceTMP);
                    }
                }
            } else {
                dates.add(date);
                Map value = new HashMap();
                value.put("date",date);
                value.put("value",price);
                statistics.add(value);
            }
        }

        return statistics;
    }

    @Override
    public List<Map> getBoughtStationDepartureStatistics() {
        List<Ride> rides = rideService.findAllRides();
        long count = 0;
        List<Map> statistics = new ArrayList<>();
        Set<String> cities = new HashSet<>();
        for (Ride ride : rides) {
            cities.add(ride.getStationDeparture().getStationName());
        }
        for (Object city : cities) {
            for (Ride ride : rides) {
                if(ride.getStationDeparture().getStationName().equals(city)){
                    count++;
                }
            }
            Map value = new HashMap();
            value.put("City", city);
            value.put("Bought", count);
            statistics.add(value);
            count=0;
        }

        return statistics;
    }

    @Override
    public List<Map> getBoughtStationArrivalStatistics() {
        List<Ride> rides = rideService.findAllRides();
        long count = 0;
        List<Map> statistics = new ArrayList<>();
        Set<String> cities = new HashSet<>();
        for (Ride ride : rides) {
            cities.add(ride.getStationArrival().getStationName());
        }
        for (Object city : cities) {
            for (Ride ride : rides) {
                if(ride.getStationArrival().getStationName().equals(city)){
                    count++;
                }
            }
            Map value = new HashMap();
            value.put("City", city);
            value.put("Bought", count);
            statistics.add(value);
            count=0;
        }

        return statistics;
    }
}
