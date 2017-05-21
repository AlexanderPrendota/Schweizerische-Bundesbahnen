package com.schweizerischebundesbahnen.junittest.testmock;

import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.UserRepository;
import com.schweizerischebundesbahnen.service.api.UserService;
import com.schweizerischebundesbahnen.service.imp.UserServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 14.05.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {

    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;


    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("pren@mail.ru");
    }
    @Test
    public void testMockGetUsersById(){
        when(userRepository.findOne(1L)).thenReturn(user);
        userService.getUserById(user.getId());
        verify(userRepository).findOne(user.getId());
    }

    @Test
    public void testMockGetUsersByEmail(){
        when(userRepository.findByEmail("pren@mail.ru")).thenReturn(user);
        userService.findUserByEmail(user.getEmail());
        verify(userRepository).findByEmail(user.getEmail());
    }


    @Test
    public void testMockGetUsers(){
        when(userRepository.findAll()).thenReturn(new ArrayList<User>());
        userService.findAllUser();
        verify(userRepository).findAll();
    }

    @Test
    public void testMockAddUser(){
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);
        verify(userRepository).save(user);
    }
}

