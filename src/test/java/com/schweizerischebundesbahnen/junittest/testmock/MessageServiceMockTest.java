package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.model.Message;
import com.schweizerischebundesbahnen.model.MessageDTO;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.MessageRepository;
import com.schweizerischebundesbahnen.service.imp.MessageServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageServiceMockTest {

    private User user;
    private Chat chat;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImp messageService;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("pren@mail.ru");

        chat = new Chat();
        chat.setId(15L);
    }


    @Test
    public void testFindBySenderMock(){
        when(messageRepository.findBySender(user)).thenReturn(new ArrayList<Message>());
        messageService.findBySender(user);
        verify(messageRepository).findBySender(user);
    }


    @Test
    public void testFindByChatMock(){
        when(messageRepository.findByChat(chat)).thenReturn(new ArrayList<Message>());
        messageService.findByChat(chat);
        verify(messageRepository).findByChat(chat);
    }

}
