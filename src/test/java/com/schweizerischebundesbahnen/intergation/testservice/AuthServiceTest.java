package com.schweizerischebundesbahnen.intergation.testservice;

import com.schweizerischebundesbahnen.service.imp.AuthService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aleksandrprendota on 26.03.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    public void testUserLoadInAuth(){
        Assert.assertTrue(authService.loadUserByUsername("A@a").isEnabled());
    }

    @Test(expected = org.springframework.security.core.userdetails.UsernameNotFoundException.class)
    public void testUserLoadInAuthNot() {
        Assert.assertTrue(authService.loadUserByUsername("user1").isEnabled());
    }

    @Test(expected = org.springframework.security.core.userdetails.UsernameNotFoundException.class)
    public void testEmptyStringAuth(){
        Assert.assertTrue(authService.loadUserByUsername("").isEnabled());
    }

    @Test(expected = NullPointerException.class)
    public void testNullInAuth(){
        Assert.assertTrue(authService.loadUserByUsername(null).isEnabled());
    }

}
