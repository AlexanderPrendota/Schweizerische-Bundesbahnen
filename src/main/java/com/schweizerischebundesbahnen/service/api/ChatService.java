package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Chat;


/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface ChatService {

    Chat findChatById(Long id);

    void save(Chat chat);

    void deleteChat(Chat chat);

}
