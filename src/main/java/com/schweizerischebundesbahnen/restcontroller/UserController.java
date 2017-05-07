package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
@RestController
@Log4j
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;
    /**
     * Delete user entity by email
     * @param email field from user entity
     * @return success or not success
     */
    @RequestMapping(value = "/delete/{email:.*}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        if (user == null){
            return new ResponseEntity<>("Incorrect user email", HttpStatus.BAD_REQUEST);
        }
        List<Ticket> userTickets = ticketService.findTicketsByUser(user);

        if (!email.equals("") && userTickets.size() == 0) {
            userService.delete(user);
            log.info("User " + email + " was deleted" );
            return ResponseEntity.ok("User was deleted");

        } else {
            return new ResponseEntity<>("We cann't delete this user. He has a tickets", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Getting list of all users
     * @return list of users entity
     */
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.findAllUser();
    }

    /**
     * Getting all users mail
     * @return list of string mail
     */
    @RequestMapping(value = "/mails", method = RequestMethod.GET)
    public List<String> getListOfUsersMail(){
        List<String> mails = new ArrayList<>();
        List<User> users = userService.findAllUser();
        if (users.size() > 0){
            for (User user : users) {
                mails.add(user.getEmail());
            }
        }
        return mails;
    }

}
