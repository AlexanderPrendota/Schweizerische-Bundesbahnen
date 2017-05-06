package com.schweizerischebundesbahnen.config;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by aleksandrprendota on 21.04.17.
 */
public class MQSender {

    private final static String QUEUE_NAME = "mylittlequeue";

    public void sendMessage(String message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
