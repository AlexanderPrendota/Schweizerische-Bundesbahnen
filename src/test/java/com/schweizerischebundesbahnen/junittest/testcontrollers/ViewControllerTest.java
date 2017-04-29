package com.schweizerischebundesbahnen.junittest.testcontrollers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by aleksandrprendota on 26.03.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ViewControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void registrationViewTest() throws Exception{
        this.mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andDo(print());
    }

    @Test
    public void accountViewTest() throws Exception{
        this.mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(view().name("account"))
                .andDo(print());
    }

    @Test
    public void loginViewTest() throws Exception{
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }

    @Test
    public void homeViewTest() throws Exception{
        this.mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    public void slashToHomeViewTest() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    public void purchaseViewTest() throws Exception{
        this.mockMvc.perform(get("/purchase"))
                .andExpect(status().isOk())
                .andExpect(view().name("purchase"))
                .andDo(print());
    }

}
