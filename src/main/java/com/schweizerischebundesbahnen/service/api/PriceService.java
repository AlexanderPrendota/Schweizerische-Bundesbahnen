package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.PointDTO;
import com.schweizerischebundesbahnen.model.Station;

/**
 * Created by aleksandrprendota on 12.05.17.
 */
public interface PriceService {

    Double calculationPrice(PointDTO firstStation, PointDTO SecondStation);

    Double calculateDistance(PointDTO firstWay, PointDTO secondWay);

    Double getPrice(Station stationDeparture, Station stationArrival);
}
