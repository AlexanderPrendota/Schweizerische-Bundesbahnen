package com.schweizerischebundesbahnen.service.api;

import java.util.List;
import java.util.Map;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
public interface StatisticService {
    List<Map> getMoneyStatistics();
    List<Map> getBoughtStationDepartureStatistics();
    List<Map> getBoughtStationArrivalStatistics();
}
