package com.schweizerischebundesbahnen.model;

import lombok.Builder;
import lombok.Data;
/**
 * Created by aleksandrprendota on 18.04.17.
 */
@Data
@Builder
public class TimeSchedule {
    private String train;
    private String stationDeparture;
    private String timeDeparture;
    private String stationArrival;
    private String timeArrival;
}
