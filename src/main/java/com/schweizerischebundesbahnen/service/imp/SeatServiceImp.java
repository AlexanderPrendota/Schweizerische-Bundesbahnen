package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Seat;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.repository.SeatRepository;
import com.schweizerischebundesbahnen.service.api.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aleksandrprendota on 12.04.17.
 */

@Service
public class SeatServiceImp implements SeatService{

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void delete(Seat seat) {
        seatRepository.delete(seat);
    }

    /**
     * Find specific seat by train, carriage and seat number
     * @param train
     * @param number
     * @param cabine
     * @return seat entity
     */
    @Override
    public Seat findByTrainAndNumberAndCarriage(Train train, int number, int cabine) {
        return seatRepository.findByTrainAndNumberAndCabine(train,number,cabine);
    }

    /**
     * get list of seats by train entity
     * @param train
     * @return list of seat entity
     */
    @Override
    public List<Seat> findSeatByTrain(Train train) {
        return seatRepository.findByTrain(train);
    }
}
