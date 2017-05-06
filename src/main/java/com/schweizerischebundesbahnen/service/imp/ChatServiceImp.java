package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.repository.ChatRepository;
import com.schweizerischebundesbahnen.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@Service
public class ChatServiceImp implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat findChatById(Long id) {
        return chatRepository.findOne(id);
    }

    @Override
    public void save(Chat chat) {
        chatRepository.save(chat);
    }
}
