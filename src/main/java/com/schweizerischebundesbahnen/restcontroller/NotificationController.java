package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Mail;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.imp.MailService;
import com.schweizerischebundesbahnen.service.api.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
@RestController
@Log4j
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/notify")
public class NotificationController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    /**
     * Send notification to all users
     * @param mail
     * @return sending status
     * @throws MessagingException
     */
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public ResponseEntity<?> sendNotify(@RequestBody Mail mail) throws MessagingException{
        if(mail.getSubject().equals("") || mail.getText().equals("")){
            return new ResponseEntity<>("Wrong params", HttpStatus.BAD_REQUEST);
        } else {
            List<User> users = userService.findAllUser();
            for (User user : users) {
                mail.setTo(user.getEmail());
                mailService.send(mail);
            }
            log.info("Messages to all users were sent");
            return ResponseEntity.ok("Messages were sent");
        }
    }
}
