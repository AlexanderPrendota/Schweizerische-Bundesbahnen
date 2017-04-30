package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.repository.StationRepository;
import com.schweizerischebundesbahnen.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 29.03.17.
 *
 * Implimentation StationService interface
 */
@Service
public class StationServiceImp implements StationService {

    @Autowired
    private StationRepository stationRepository;

    /**
     * Getting a Station by name
     * @param name of station
     * @return existing Station entity
     */
    @Override
    public Station findStationByName(String name) {
        return stationRepository.findByStationName(name);
    }

    /**
     * Getting a Station by id
     * @param id station's id
     * @return existing Station entity
     */
    @Override
    public Station findStationById(Long id) {
        return stationRepository.findOne(id);
    }

    /**
     * Creating station entity in database
     * @param station new station entity
     */
    @Override
    public void addStation(Station station) {
        stationRepository.save(station);
    }

    /**
     * Delete station entity from database
     * @param station existing station entity
     */
    @Override
    public void delete(Station station) {
        stationRepository.delete(station);
    }

    /**
     * get list of all stations
     * @return list of all station entity
     */
    @Override
    public List<Station> findAllStation() {
        List<Station> stations = new ArrayList<>();
        stationRepository.findAll().forEach(stations::add);
        return stations;
    }
}