package com.ibrahim.implementation.rabbitmq.service.impl;

import com.ibrahim.implementation.rabbitmq.service.QueueService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("QueueService")
public class QueueServiceImpl implements QueueService {

    @Autowired
    CachingConnectionFactory connectionFactory;

    @Override
    public String createQueue(String name) {
        try {
            Connection connection = connectionFactory.createConnection();
            Channel channel = connection.createChannel(true);
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(name, true, false, false, null);
            channel.close();
            return declareOk.getQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed to create queue";
    }
}
