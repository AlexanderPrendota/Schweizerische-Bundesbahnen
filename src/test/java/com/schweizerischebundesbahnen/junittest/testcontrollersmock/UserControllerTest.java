package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.model.UserChat;
import com.schweizerischebundesbahnen.restcontroller.UserController;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.UserChatService;
import com.schweizerischebundesbahnen.service.api.UserService;
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
 * Created by aleksandrprendota on 15.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private User user;
    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserChatService userChatService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("pren@mail.ru");
    }
    @Test
    public void testGetAllUserInController(){
       when(userService.findAllUser()).thenReturn(new ArrayList<User>());
       userController.getAllUsers();
       verify(userService).findAllUser();
    }

    @Test
    public void testGetAllUserMailInController(){
        when(userService.findAllUser()).thenReturn(new ArrayList<User>());
        userController.getListOfUsersMail();
        verify(userService).findAllUser();
    }

    @Test
    public void testDeleteUserInController(){
        when(userService.findUserByEmail("pren@mail.ru")).thenReturn(user);
        when(ticketService.findTicketsByUser(user)).thenReturn(new ArrayList<Ticket>());
        when(userChatService.findChatsByUser(user)).thenReturn(new ArrayList<UserChat>());
        userController.deleteUser(user.getEmail());
        verify(userService).findUserByEmail(user.getEmail());
        verify(ticketService).findTicketsByUser(user);
        verify(userChatService).findChatsByUser(user);
    }
}
