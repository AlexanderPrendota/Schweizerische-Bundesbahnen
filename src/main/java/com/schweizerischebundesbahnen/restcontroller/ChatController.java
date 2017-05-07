package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.service.api.ChatService;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import com.schweizerischebundesbahnen.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@RestController
@Log4j
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    private UserChatService userChatService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;


    @RequestMapping(value = "/chats",method = RequestMethod.GET)
    public List<UserChat> getUserChats(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        return userChatService.findChats(user);
    }

    @RequestMapping(value = "/delete/{chatID}",method = RequestMethod.DELETE)
    public ResponseEntity<?> getUserChats(@PathVariable String chatID, Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
       try{
           long id = Long.valueOf(chatID);
            Chat chat = chatService.findChatById(id);
            // TODO DELETE CHAT
           return ResponseEntity.ok("Chat was deleted!");
       } catch (NumberFormatException e){
           log.warn("Cannot parse the chat ID in deleting chat");
           return new ResponseEntity<>("Cannot parse the chat ID",
                   HttpStatus.BAD_REQUEST);
       }
    }
}
