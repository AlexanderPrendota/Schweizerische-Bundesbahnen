package com.schweizerischebundesbahnen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by aleksandrprendota on 25.03.17.
 *
 * Mapping view
 */

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("/registration");
        registry.addViewController("/account").setViewName("/account");
        registry.addViewController("/purchase").setViewName("/purchase");
        registry.addViewController("/multipurchase").setViewName("/multipurchase");
        registry.addViewController("/admin").setViewName("/admin");
    }
}
