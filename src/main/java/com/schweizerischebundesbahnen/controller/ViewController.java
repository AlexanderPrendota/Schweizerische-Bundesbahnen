package com.schweizerischebundesbahnen.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandrprendota on 25.03.17.
 *
 * Simple View Controller
 */

@Controller
public class ViewController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountPage(Model model) {
        return "account";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model) {
        return "home";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String purchasePage(Model model) {
        return "purchase";
    }
    @RequestMapping(value = "/multipurchase", method = RequestMethod.GET)
    public String multiPurchasePage(Model model) {
        return "multipurchase";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "admin";
    }

}
