package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.UserChat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface UserChatRepository extends CrudRepository<UserChat,Long> {
}
