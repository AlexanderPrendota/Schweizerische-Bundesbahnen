package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    private UserChatService userChatService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/chats",method = RequestMethod.GET)
    public List<UserChat> getUserChats(Authentication authentication){
        User user = userService.findUserByEmail(authentication.getName());
        return userChatService.findChats(user);
    }
}
