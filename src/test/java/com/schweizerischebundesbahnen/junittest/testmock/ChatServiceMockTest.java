package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.Chat;
import com.schweizerischebundesbahnen.repository.ChatRepository;
import com.schweizerischebundesbahnen.service.imp.ChatServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatServiceMockTest {

    private Chat chat;
    @Mock
    private ChatRepository chatRepository;

    @InjectMocks
    private ChatServiceImp chatService;

    @Before
    public void setup(){
        chat = new Chat();
        chat.setId(23L);
    }

    @Test
    public void testFindStationById(){
        when(chatRepository.findOne(21L)).thenReturn(chat);
        chatService.findChatById(chat.getId());
        verify(chatRepository).findOne(chat.getId());
    }


}
