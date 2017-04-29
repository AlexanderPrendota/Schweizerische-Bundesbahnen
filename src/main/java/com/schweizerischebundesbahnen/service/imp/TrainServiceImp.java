package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.TrainRepository;
import com.schweizerischebundesbahnen.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 31.03.17.
 *
 * Implimentation TrainService interface
 */

@Service
public class TrainServiceImp implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    /**
     * Add a new Train entity to database
     * @param train new Train entity
     */
    @Override
    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    /**
     * Finding train by his Name like a train id
     * @param name Existing train name
     * @return Train entity from database
     */
    @Override
    public Train findTrainByName(String name) {
        return trainRepository.findById(name);
    }

    /**
     * Delete train from database
     * @param train existing train entity
     */
    @Override
    public void delete(Train train){
        trainRepository.delete(train);
    }

    /**
     * Getting List of All train from database
     * @return List of Train
     */
    @Override
    public List<Train> findAllTrains() {
        List<Train> trains = new ArrayList<>();
        trainRepository.findAll().forEach(trains::add);
        return trains;
    }
}
