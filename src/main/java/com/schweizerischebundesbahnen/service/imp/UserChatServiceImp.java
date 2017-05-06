package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.repository.ChatRepository;
import com.schweizerischebundesbahnen.repository.UserChatRepository;
import com.schweizerischebundesbahnen.repository.UserRepository;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@Service
public class UserChatServiceImp implements UserChatService {

    @Autowired
    private UserChatRepository userChatRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean checkUserInChat(Long idUser, Long idChat){
        User user = userRepository.findOne(idUser);
        Chat chat = chatRepository.findOne(idChat);
        UserChat userChat = userChatRepository.findByChatAndUser(chat, user);
        return (userChat != null);
    }

    public List<UserChat> findChatsByUser(User user){
        return userChatRepository.findByUser(user);
    }
}
