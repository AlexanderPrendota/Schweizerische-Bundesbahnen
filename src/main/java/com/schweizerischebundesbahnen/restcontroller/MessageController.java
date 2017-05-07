package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.service.api.ChatService;
import com.schweizerischebundesbahnen.service.api.MessageService;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import com.schweizerischebundesbahnen.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@Log4j
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserChatService userChatService;

    @Autowired
    private ChatService chatService;


    @RequestMapping(value = "/post/user",method = RequestMethod.POST)
    public Message postUserMessage(@RequestBody MessageDTO messageDTO, Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        List<UserChat> userChats = userChatService.findChatsByUser(user);
        if (userChats.size() > 0){
            Chat userChat = userChats.get(0).getChat();
            messageDTO.setChatId(userChat.getId());
        }
        return messageService.postMessage(user,messageDTO);
    }

    @RequestMapping(value = "/post/admin",method = RequestMethod.POST)
    public Message postAdminMessage(@RequestBody MessageDTO messageDTO, Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        return messageService.postMessage(user, messageDTO);
    }

    @RequestMapping(value = "user/chat",method = RequestMethod.GET)
    public List<Message> getUserMessage(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        List<Message> messages = new ArrayList<>();
        List<UserChat> userChats = userChatService.findChatsByUser(user);
        if (userChats.size() > 0){
            messages = messageService.findByChat(userChats.get(0).getChat());
        }
        return messages;
    }

    @RequestMapping(value = "/admin/chat/{chatId}",method = RequestMethod.GET)
    public List<Message> getAdminMessage(Authentication authentication, @PathVariable String chatId){
        User user = userService.findUserByEmail(authentication.getName());
        List<Message> messages = new ArrayList<>();
        try{
            long longChat = Long.valueOf(chatId);
            Chat chat = chatService.findChatById(longChat);
            List<UserChat> userChats = userChatService.findChatsByUser(user);
            if (userChats.size() > 0 && chat != null){
                for (UserChat userChat : userChats) {
                    if (userChat.getChat().equals(chat)){
                        messages = messageService.findByChat(chat);
                    }
                }
            }
        } catch (NumberFormatException e){
            log.warn("Wrong chat id in getting message");
        }
        return messages;
    }

    @RequestMapping(value = "/messages",method = RequestMethod.GET)
    public int getUserCountMessages(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        List<UserChat> userChats = userChatService.findChatsByUser(user);
        List<Message> messages = new ArrayList<>();
        for (UserChat userChat : userChats) {
            messages.addAll(messageService.findByChat(userChat.getChat()));
        }
        return messages.size();
    }



}
