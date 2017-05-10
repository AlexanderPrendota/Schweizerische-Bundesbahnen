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

    /**
     * get chat by id
     * @param id
     * @return chat entity
     */
    @Override
    public Chat findChatById(Long id) {
        return chatRepository.findOne(id);
    }

    /**
     * Save chat to db
     * @param chat
     */
    @Override
    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    /**
     * Delete chat
     * @param chat
     */
    @Override
    public void deleteChat(Chat chat) {
        chatRepository.delete(chat);
    }
}
