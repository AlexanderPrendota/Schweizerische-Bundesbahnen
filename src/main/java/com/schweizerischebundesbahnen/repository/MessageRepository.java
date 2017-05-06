package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface MessageRepository extends CrudRepository<Message,Long> {
}
