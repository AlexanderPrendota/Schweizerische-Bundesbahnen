package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Role;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 * Created by aleksandrprendota on 06.05.17.
 */
@RestController
@RequestMapping(value = "/adminparam")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Boolean isAdmin(Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName());
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0){
            for (Role role : roles) {
                if (role.getRole().equals("ADMIN")){
                    return true;
                }
            }
        }
        return false;
    }
}
