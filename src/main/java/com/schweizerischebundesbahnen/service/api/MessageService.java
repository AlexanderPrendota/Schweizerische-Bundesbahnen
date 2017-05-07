package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.Message;
import com.schweizerischebundesbahnen.model.MessageDTO;
import com.schweizerischebundesbahnen.model.User;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
public interface MessageService {

    Message postMessage(User from, MessageDTO messageDTO);

    List<Message> findByChat(Chat id);

    void delete(Message message);
}
