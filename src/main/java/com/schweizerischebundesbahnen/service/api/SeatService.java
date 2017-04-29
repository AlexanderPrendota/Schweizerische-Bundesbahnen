package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;

import java.util.List;

/**
 * Created by aleksandrprendota on 12.04.17.
 */
public interface SeatService {

    Seat save(Seat seat);

    void delete(Seat seat);

    Seat findByTrainAndNumberAndCarriage(Train train,int number, int cabine);

    List<Seat> findSeatByTrain(Train train);


}
