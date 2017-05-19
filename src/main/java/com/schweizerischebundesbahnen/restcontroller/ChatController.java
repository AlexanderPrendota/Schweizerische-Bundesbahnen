package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.Message;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.service.api.ChatService;
import com.schweizerischebundesbahnen.service.api.MessageService;
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


    @Autowired
    private MessageService messageService;

    /**
     * getting list user chats without his params
     * @param authentication
     * @return  list of userrchat entity
     */
    @RequestMapping(value = "/chats",method = RequestMethod.GET)
    public List<UserChat> getUserChats(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        return userChatService.findChatsUserNot(user);
    }

    /**
     * Get count of user chats
     * @param authentication
     * @ count
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public int getCountOfUserChat(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        return userChatService.findChatsByUser(user).size();
    }

    /**
     * Delete chat/message/userchat.
     * Clean task in db
     * @param chatID
     * @return
     */
    @RequestMapping(value = "/delete/{chatID}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserChats(@PathVariable String chatID){
        List<Message> messages;
        List<UserChat> userChats;
       try{
           long id = Long.valueOf(chatID);
           Chat chat = chatService.findChatById(id);
           messages = messageService.findByChat(chat);
           userChats = userChatService.findByChat(chat);
           messages.forEach(message -> messageService.delete(message));
           userChats.forEach(userChat -> userChatService.delete(userChat));
           chatService.deleteChat(chat);
           return ResponseEntity.ok("Chat was deleted!");
       } catch (NumberFormatException e){
           log.warn("Cannot parse the chat ID in deleting chat");
           return new ResponseEntity<>("Cannot parse the chat ID",
                   HttpStatus.BAD_REQUEST);
       }
    }
}
