package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.PointDTO;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.service.api.PriceService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
@Service
@ConfigurationProperties(prefix="coefficient")
public class PriceServiceImp implements PriceService {

    private static Double PRICE = 10.3;

    @Override
    public Double calculationPrice(PointDTO firstWay, PointDTO secondWay) {
        Double price = calculateDistance(firstWay, secondWay) * PRICE;
        return Math.round(price * 100.0) / 100.0;
    }

    @Override
    public Double calculateDistance(PointDTO firstWay, PointDTO secondWay){
        return Math.sqrt(Math.pow(secondWay.getX() - firstWay.getX(), 2) + (Math.pow(secondWay.getY() - firstWay.getY(),2)));
    }

    @Override
    public Double getPrice(Station stationDeparture, Station stationArrival) {
        PointDTO firstWay = new PointDTO();
        PointDTO secondWay = new PointDTO();
        firstWay.setX(stationDeparture.getX());
        firstWay.setY(stationDeparture.getY());
        secondWay.setX(stationArrival.getX());
        secondWay.setY(stationArrival.getY());
        return calculationPrice(firstWay,secondWay);
    }
}
