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

    /**
     * Check user in chat
     * @param idUser user entity id
     * @param idChat chat id
     * @return true/false
     */
    @Override
    public Boolean checkUserInChat(Long idUser, Long idChat){
        User user = userRepository.findOne(idUser);
        Chat chat = chatRepository.findOne(idChat);
        UserChat userChat = userChatRepository.findByChatAndUser(chat, user);
        return (userChat != null);
    }

    /**
     * get list of userchat entity by chat
     * @param chat entity
     * @return list of userchat entity
     */
    @Override
    public List<UserChat> findByChat(Chat chat) {
        return userChatRepository.findByChat(chat);
    }

    /**
     *  get list of userchat entity by user entity
     * @param user
     * @return list of userchat entity
     */
    @Override
    public List<UserChat> findChatsByUser(User user){
        return userChatRepository.findByUser(user);
    }

    /**
     * get list od userchat entity by not user entity
     * @param user entity
     * @return list of userchat entity
     */
    @Override
    public List<UserChat> findChatsUserNot(User user){
        List<UserChat> userChats = userChatRepository.findByUser(user);
        List<UserChat> filteredChats = new ArrayList<>();
        for (UserChat userChat : userChats) {
            UserChat filteredChat = userChatRepository.findByChatAndUserNot(userChat.getChat(), user);
            filteredChats.add(filteredChat);
        }
        return filteredChats;
    }

    /**
     * Delete userchat entity
     * @param chat entity
     */
    @Override
    public void delete(UserChat chat) {
        userChatRepository.delete(chat);
    }
}
