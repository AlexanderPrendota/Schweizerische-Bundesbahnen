package com.schweizerischebundesbahnen.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/adminparam")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public Boolean isAdmin(){
        return true;
    }
}
