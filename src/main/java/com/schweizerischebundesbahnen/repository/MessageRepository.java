package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message> findByChat(Chat chat);
}
