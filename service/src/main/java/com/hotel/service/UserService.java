package com.hotel.service;

import com.hotel.entity.User;
import com.hotel.service.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface UserService {

    List<User> findAll() throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    boolean register(String firstName, String lastName, String login, String password) throws ServiceException;

    String hash(String input);
}