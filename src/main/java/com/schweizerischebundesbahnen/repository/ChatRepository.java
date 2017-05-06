package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Chat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface ChatRepository extends CrudRepository<Chat,Long> {
}
