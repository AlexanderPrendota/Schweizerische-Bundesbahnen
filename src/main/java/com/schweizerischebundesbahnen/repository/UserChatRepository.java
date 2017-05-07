package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface UserChatRepository extends CrudRepository<UserChat,Long> {
    List<UserChat> findByUser(User user);
    UserChat findByChatAndUser(Chat chat, User user);
    UserChat findByChatAndUserNot(Chat chat, User user);
    List<UserChat> findByChat(Chat chat);
}
