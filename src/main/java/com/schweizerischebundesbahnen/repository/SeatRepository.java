package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aleksandrprendota on 12.04.17.
 */
public interface SeatRepository extends CrudRepository<Seat,Long> {
    Seat findByTrainAndNumberAndCabine(Train train, int number, int cabine);

    List<Seat> findByTrain(Train train);
}
