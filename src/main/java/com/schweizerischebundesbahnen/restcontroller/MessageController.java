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


    @RequestMapping(value = "/user/chat/{chatId}",method = RequestMethod.GET)
    public List<Message> getUserMessage(Authentication authentication, @PathVariable String chatId){
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

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public List<Message> postUserMessage(@RequestBody MessageDTO messageDTO,Authentication authentication){

        return null;
    }

}
