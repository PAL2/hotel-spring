package com.hotel.service.impl;

import com.hotel.entity.User;
import com.hotel.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Алексей on 10.06.2017.
 */
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByLogin() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    @Ignore
    public void register() throws Exception {
        userService.register("firstName", "lastName", "login", "password");
        User userActual = userService.findByLogin("login");
        assertEquals("lastName", userActual.getLastName());
    }

    @Test
    @Ignore
    public void hash() throws Exception {
        String expected = "ee11cbb19052e40b07aac0ca060c23ee";
        String actual = userService.hash("user");
        assertEquals(expected, actual);
    }

}