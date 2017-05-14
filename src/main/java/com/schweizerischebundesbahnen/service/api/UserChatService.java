package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface UserChatService {

    List<UserChat> findChatsByUser(User user);

    Boolean checkUserInChat(Long idUser, Long idChat);

    List<UserChat> findByChat(Chat chat);

    List<UserChat> findChatsUserNot(User user);

    void delete(UserChat chat);

    UserChat save(UserChat userChat);

}
