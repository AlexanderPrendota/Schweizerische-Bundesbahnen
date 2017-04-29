package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Train;

import java.util.List;

/**
 * Created by aleksandrprendota on 31.03.17.
 */
public interface TrainService {

    void addTrain(Train train);

    void delete(Train train);

    Train findTrainByName(String name);

    List<Train> findAllTrains();
}
