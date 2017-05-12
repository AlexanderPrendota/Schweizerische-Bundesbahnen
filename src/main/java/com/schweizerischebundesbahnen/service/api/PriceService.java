package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.PointDTO;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
public interface PriceService {
    Double calculationPrice(PointDTO firstStation, PointDTO SecondStation);
    Double calculateDistance(PointDTO firstWay, PointDTO secondWay);
}
