package com.ibrahim.implementation.rabbitmq.service.impl;

import com.ibrahim.implementation.rabbitmq.service.QueueService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("QueueService")
public class QueueServiceImpl implements QueueService {

    @Autowired
    CachingConnectionFactory connectionFactory;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public String createQueue(String name) {
        try {
            Connection connection = connectionFactory.createConnection();
            Channel channel = connection.createChannel(true);
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(name, true, false, false, null);
            channel.close();
            BindingBuilder.bind(new Queue(name, true)).to(new DirectExchange(name, true, false)).with(name);
            return declareOk.getQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed to create queue";
    }
}
