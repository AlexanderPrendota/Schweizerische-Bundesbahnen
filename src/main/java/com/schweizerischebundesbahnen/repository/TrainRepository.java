package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Train;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aleksandrprendota on 31.03.17.
 */

public interface TrainRepository extends CrudRepository<Train, Long> {

    Train findById(String id);
}
