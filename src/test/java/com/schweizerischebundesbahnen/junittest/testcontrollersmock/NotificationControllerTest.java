package com.schweizerischebundesbahnen.junittest.testcontrollersmock;

import com.schweizerischebundesbahnen.model.Mail;
import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.restcontroller.NotificationController;
import com.schweizerischebundesbahnen.service.api.UserService;
import com.schweizerischebundesbahnen.service.imp.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 15.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationControllerTest {

    private Mail mail;

    @Mock
    private UserService userService;

    @Mock
    private MailService mailService;

    @InjectMocks
    private NotificationController notificationController;

    @Before
    public void setup(){
        mail = new Mail();
        mail.setSubject("Sub");
        mail.setTo("to");
        mail.setText("text");
    }

    @Test
    public void testSendNotifyMock() throws Exception{
        when(userService.findAllUser()).thenReturn(new ArrayList<User>());
        doNothing().when(mailService).send(mail);
        notificationController.sendNotify(mail);
        verify(userService).findAllUser();

    }
}
