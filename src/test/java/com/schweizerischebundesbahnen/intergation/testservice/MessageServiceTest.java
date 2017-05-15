package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.service.api.ChatService;
import com.schweizerischebundesbahnen.service.api.MessageService;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 10.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageServiceTest {

    private MessageDTO messageDTO;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserChatService userChatService;

    @Before
    public void setup(){
        messageDTO = new MessageDTO();
        messageDTO.setText("Test Message");
        messageDTO.setTimeStamp(new Date());
        messageDTO.setRecipientId(115L);
    }

    @Test
    public void testPostMessage(){
        User user = userService.getUserById(228L);
        messageService.postMessage(user,messageDTO);
        List<Message> messages = messageService.findBySender(user);
        Assert.assertTrue(messages.size() > 0);
    }

    @Test
    public void testFindMessagegByChat(){
        Chat chat = chatService.findChatById(1527L);
        List<Message> messages = messageService.findByChat(chat);
        Assert.assertTrue(messages.size() > 0);
    }

    @Test
    public void testFindUserChat(){
        User user = userService.getUserById(228L);
        List<UserChat> userChat = userChatService.findChatsByUser(user);
        Assert.assertTrue(userChat.size() > 0);
    }

    @Test
    public void testFindUserChatWrong(){
        User user = userService.getUserById(212L);
        List<UserChat> userChat = userChatService.findChatsByUser(user);
        Assert.assertTrue(userChat.size() == 0);
    }

    @Test
    public void testFindUserChatNot(){
        User user = userService.getUserById(228L);
        List<UserChat> userChat = userChatService.findChatsUserNot(user);
        Assert.assertTrue(userChat.size() == 1);
    }

    @Test
    public void testCheckNullNotUserInChat(){
        List<UserChat> userChat = userChatService.findChatsUserNot(null);
        Assert.assertTrue(userChat.size() == 0);
    }

    @Test
    public void testCheckNullUserInChat(){
        List<UserChat> userChat = userChatService.findChatsByUser(null);
        Assert.assertTrue(userChat.size() == 0);
    }
    @Test
    public void testFindChatById(){
        Chat chat = chatService.findChatById(1527L);
        Assert.assertNotNull(chat);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindNullChatById(){
        Chat chat = chatService.findChatById(null);
        Assert.assertNull(chat);
    }

    @Test
    public void testFindWrongChatById(){
        Chat chat = chatService.findChatById(227L);
        Assert.assertNull(chat);
    }

    @Test
    public void testFindMessageWrongByChat(){
        Chat chat = chatService.findChatById(15L);
        List<Message> messages = messageService.findByChat(chat);
        Assert.assertTrue(messages.size() == 0);
    }

    @Test
    public void testFindMessageNullByChat(){
        List<Message> messages = messageService.findByChat(null);
        Assert.assertTrue(messages.size() == 0);
    }

    @Test
    public void testfindUserChatByChat(){
        Chat chat = chatService.findChatById(1527L);
        List<UserChat> userChat = userChatService.findByChat(chat);
        Assert.assertTrue(userChat.size() > 0);
    }

    @Test
    public void testfindUserChatByNullChat(){
        List<UserChat> userChat = userChatService.findByChat(null);
        Assert.assertTrue(userChat.size() == 0);
    }

    @Test
    public void testtestfindUserChatByWrongChat(){
        Chat chat = chatService.findChatById(157L);
        List<UserChat> userChat = userChatService.findByChat(chat);
        Assert.assertTrue(userChat.size() == 0);
    }

    @Test
    public void testCheckUserInChat(){
        Assert.assertTrue(userChatService.checkUserInChat(228L,1527L));
    }

    @Test
    public void testCheckWrongUserInChat(){
        Assert.assertFalse(userChatService.checkUserInChat(227L,1527L));
    }

    @Test
    public void testCheckUserInWrongChat(){
        Assert.assertFalse(userChatService.checkUserInChat(227L,15L));
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testCheckNullUserInWrongChat(){
        Assert.assertFalse(userChatService.checkUserInChat(null,1527L));
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testCheckUserInNullChat(){
        Assert.assertFalse(userChatService.checkUserInChat(228L,null));
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testCheckNullUserInNullChat(){
        Assert.assertFalse(userChatService.checkUserInChat(null,null));
    }

    @Test
    public void testDeleteMessage(){
        User user = userService.getUserById(228L);
        messageService.postMessage(user,messageDTO);
        List<Message> messages = messageService.findBySender(user);
        messages.forEach(message -> messageService.delete(message));
        List<Message> messagesNew = messageService.findBySender(user);
        Assert.assertTrue(messagesNew.size() == 0);
    }

}
