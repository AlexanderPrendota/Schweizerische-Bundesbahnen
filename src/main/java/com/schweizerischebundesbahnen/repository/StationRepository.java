package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrprendota on 29.03.17.
 */
@Repository
public interface StationRepository extends CrudRepository<Station,Long> {
    Station findByStationName(String name);
}
