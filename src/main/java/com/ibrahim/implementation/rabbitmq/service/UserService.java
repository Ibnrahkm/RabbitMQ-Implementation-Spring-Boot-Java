package com.ibrahim.implementation.rabbitmq.service;


import com.ibrahim.implementation.rabbitmq.entites.User;
import com.ibrahim.implementation.rabbitmq.response.Response;

public interface UserService {

    public Response addUser(User user);

    public Response getUser(String id);

    public Response updateUser(User user);

    public Response deleteUser(String id);

    public Response getUserList();

    public Response getUserListByFilter(String balance);
}
