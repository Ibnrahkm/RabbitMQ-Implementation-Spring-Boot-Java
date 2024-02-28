package com.ibrahim.implementation.rabbitmq.service;


import com.ibrahim.implementation.rabbitmq.entites.User;
import com.ibrahim.implementation.rabbitmq.response.Response;

public interface QueueService {

    public String createQueue(String name);
}
