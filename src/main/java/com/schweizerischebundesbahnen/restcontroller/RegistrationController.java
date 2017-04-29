package com.schweizerischebundesbahnen.restcontroller;

import com.schweizerischebundesbahnen.model.Mail;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.service.imp.MailService;
import com.schweizerischebundesbahnen.service.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Created by aleksandrprendota on 25.03.17.
 */

@RestController
@Log4j
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * Registration user in system.
     * Check valid user's params and save ones in database
     * also send the email notification with congratulations
     * @param userNew new User entity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody User userNew) {
        User user = userService.findUserByEmail(userNew.getEmail());
        if (null == user) {
            userNew.setPassword(passwordEncoder.encode(userNew.getPassword()));
            log.info("new User = " + userNew.toString());
            userNew.setImagepath("./images/DEFAULT_IMAGE.png");
            userService.addUser(userNew);

            Mail mail = new Mail();
            mail.setTo(userNew.getEmail());
            mail.setSubject("Регистрация на justrailways");
            mail.setText(String.format("Привет %s, ты успешно зарегистрировался:)", userNew.getFirstname()));

            try {
                mailService.send(mail);
            } catch (MessagingException ex){
                ex.printStackTrace();
            }
            return ResponseEntity.ok(userNew);
        }
        else {
            log.warn("Wrong information with user registration");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
