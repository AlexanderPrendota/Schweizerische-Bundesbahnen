package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.repository.UserChatRepository;
import com.schweizerischebundesbahnen.service.imp.UserChatServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserChatServiceMockTest {

    private UserChat userChat;
    private User user;
    private Chat chat;

    @Mock
    private UserChatRepository userChatRepository;

    @InjectMocks
    private UserChatServiceImp userChatService;

    @Before
    public void setup(){
        user = new User();
        user.setId(1L);
        user.setEmail("pren@mail.ru");

        chat = new Chat();
        chat.setId(23L);
        userChat = new UserChat();
        userChat.setId(9L);
        userChat.setUser(user);
        userChat.setChat(chat);
    }

    @Test
    public void testSaveUserchatMock(){
        when(userChatRepository.save(userChat)).thenReturn(userChat);
        userChatService.save(userChat);
        verify(userChatRepository).save(userChat);
    }

    @Test
    public void testFindChatsByUserMock(){
        when(userChatRepository.findByUser(user)).thenReturn(new ArrayList<UserChat>());
        userChatService.findChatsByUser(user);
        verify(userChatRepository).findByUser(user);
    }

    @Test
    public void testFindByChatMock(){
        when(userChatRepository.findByChat(chat)).thenReturn(new ArrayList<UserChat>());
        userChatService.findByChat(chat);
        verify(userChatRepository).findByChat(chat);
    }


}
