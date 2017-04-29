package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Ride;
import com.schweizerischebundesbahnen.model.Ticket;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 26.03.17.
 */

@RestController
@Log4j
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    /**
     * Getting user entity from database
     * @param authentication — current user in session
     * @return User with his fiels from database
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserParamsOnAccountPage(Authentication authentication){
        User us = userService.findUserByEmail(authentication.getName());
        return userService.findUserByEmail(authentication.getName());
    }

    /**
     * Update user params
     * @param user with new field
     * @return update User info
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public User updateUserParamsOnAccountPage(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByEmail(auth.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setBirthday(user.getBirthday());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        userService.save(currentUser);
        log.info("Update params on user " + currentUser.getEmail() );
        return currentUser;
    }

    /**
     * Getting all users rides
     * @param authentication — current user in session
     * @return List of users rides
     */
    @RequestMapping(value = "/rides", method = RequestMethod.GET)
    public List<Ride> getUsersRides(Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());
        List<Ride> userRides = new ArrayList<>();
        List<Ticket> userTickets = ticketService.findTicketsByUser(currentUser);
        if (userTickets != null) {
            for (Ticket userTicket : userTickets) {
                userRides.add(rideService.findByTicket(userTicket));
            }
        }
        return userRides;
    }
    @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserRide(@PathVariable String id, Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());
        List<Ticket> userTickets = ticketService.findTicketsByUser(currentUser);
        try {
            long longRide = Long.valueOf(id);
            if ((longRide > 0)) {
                Ride userRide = rideService.findRideById(longRide);
                if (userTickets.contains(userRide.getTicket())){
                    return ResponseEntity.ok(userRide);
                } else {
                    return new ResponseEntity<>("It's not you ride", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Cannot parse your ride!", HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e){
            return new ResponseEntity<>("Cannot parse your ride!", HttpStatus.BAD_REQUEST);
        }
    }
}
