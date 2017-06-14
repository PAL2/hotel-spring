package com.hotel.service;

import com.hotel.entity.User;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface UserService {

    List<User> findAll();

    User findByLogin(String login);

    boolean register(String firstName, String lastName, String login, String password);

    String hash(String input);
}