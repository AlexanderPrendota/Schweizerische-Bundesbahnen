package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by aleksandrprendota on 04.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void checkUserId() {
        User user = userService.getUserById(129L);
        Assert.assertEquals(user.getEmail(),"S@s");
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkUserNullId(){
        User user = userService.getUserById(null);
        Assert.assertNull(user);
    }

    @Test
    public void checkUserWrongId(){
        User user = userService.getUserById(1L);
        Assert.assertNull(user);
    }

    @Test
    public void checkUserByNullMail(){
        User user = userService.findUserByEmail(null);
        Assert.assertNull(user);
    }

    @Test
    public void checkUserWithWrongEmail(){
        User user = userService.findUserByEmail("IncorrectEmail");
        Assert.assertNull(user);
    }

    @Test
    public void checkUserWithEmptyEmail(){
        User user = userService.findUserByEmail("");
        Assert.assertNull(user);
    }

    @Test
    public void checkUserEmail() {
        User user = userService.findUserByEmail("S@s");
        Assert.assertEquals(user.getEmail(),"S@s");
    }

    @Test
    public void checkSaveUser() {
        User user = new User();
        user.setEmail("TEST@TEST");
        user.setFirstname("Tester");
        user.setLastname("Tester's");
        user.setPassword("TEST");
        userService.save(user);
        Assert.assertEquals(userService.findUserByEmail("TEST@TEST").getEmail(),"TEST@TEST");
    }

    @Test
    public void checkDeleteUser(){
        userService.delete(userService.findUserByEmail("TEST@TEST"));
        Assert.assertNull(userService.findUserByEmail("TEST@TEST"));
    }

    @Test
    public void checkAllUsers(){
        List<User> users = userService.findAllUser();
        Assert.assertTrue(users.size() > 0);
    }
}
