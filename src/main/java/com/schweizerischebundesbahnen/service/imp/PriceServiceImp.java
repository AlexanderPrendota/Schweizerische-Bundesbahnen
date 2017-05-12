package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.PointDTO;
import com.schweizerischebundesbahnen.service.api.PriceService;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
@Service
public class PriceServiceImp implements PriceService {

    private static Double PRICE = 20.3;

    @Override
    public Double calculationPrice(PointDTO firstWay, PointDTO secondWay) {
        Double price = calculateDistance(firstWay, secondWay) * PRICE;
        return Math.round(price * 1000.0) / 1000.0;
    }

    public Double calculateDistance(PointDTO firstWay, PointDTO secondWay){
        return Math.sqrt(Math.pow(secondWay.getX() - firstWay.getX(), 2) + (Math.pow(secondWay.getY() - firstWay.getY(),2)));
    }
}
