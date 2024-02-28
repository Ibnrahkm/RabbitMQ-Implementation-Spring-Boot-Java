package com.ibrahim.implementation.rabbitmq.service.impl;

import com.google.gson.Gson;
import com.ibrahim.implementation.rabbitmq.db.DatabaseOperation;
import com.ibrahim.implementation.rabbitmq.entites.User;
import com.ibrahim.implementation.rabbitmq.response.Response;
import com.ibrahim.implementation.rabbitmq.service.UserService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    DatabaseOperation databaseOperation;

    @Autowired
    RabbitTemplate rabbitMessagingTemplate;

    @Autowired
    Queue queue;

    @Override
    public Response addUser(User user) {
        System.out.println("entered into addUser function");
        Response response = new Response(false, "failed to add user", "");
        try {
            user = databaseOperation.addUser(user);
            if (user.getId() > 0) {
                rabbitMessagingTemplate.convertAndSend(queue.getActualName(), user.getEmail(), new Gson().toJson(user));
                response.setMessage("successful");
                response.setData(user);
                response.setStatus(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getUser(String id) {
        System.out.println("entered into getUser function");
        Response response = new Response(false, "failed to get user", "");
        try {
            User user = databaseOperation.getUser(Long.parseLong(id));
            if (user.getId() > 0) {
                response.setMessage("successful");
                response.setData(user);
                response.setStatus(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getUserList() {
        System.out.println("entered into getUserList function");
        Response response = new Response(false, "failed to fetch users", "");
        try {
            response.setMessage("successful");
            response.setData(databaseOperation.getAllUsers());
            response.setStatus(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateUser(User user) {
        System.out.println("entered into updateUser function");
        Response response = new Response(false, "failed to update user", "");
        try {
            user = databaseOperation.updateUser(user);
            if (user.getId() > 0) {
                rabbitMessagingTemplate.convertAndSend(queue.getActualName(), user.getEmail(), new Gson().toJson(user));
                response.setMessage("successful");
                response.setData(user);
                response.setStatus(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteUser(String id) {
        System.out.println("entered into deleteUser function");
        Response response = new Response(false, "failed to delete user", "");
        try {
            boolean f = databaseOperation.deleteUser(id);
            if (f) {
                response.setMessage("successful");
                response.setData(id);
                response.setStatus(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getUserListByFilter(String balance) {
        System.out.println("entered into getUserList function");
        Response response = new Response(false, "failed to fetch users", "");
        try {
            response.setMessage("successful");
            response.setData(databaseOperation.getAllUsersFilterCache(Long.parseLong(balance)));
            response.setStatus(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
