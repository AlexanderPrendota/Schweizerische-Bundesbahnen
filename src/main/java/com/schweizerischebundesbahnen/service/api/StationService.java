package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Station;

import java.util.List;

/**
 * Created by aleksandrprendota on 29.03.17.
 */
public interface StationService {

     Station findStationByName(String name);

     Station findStationById(Long id);

     void addStation(Station station);

     void delete(Station station);

     List<Station> findAllStation();



}
