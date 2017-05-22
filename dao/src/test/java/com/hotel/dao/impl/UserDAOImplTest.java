package com.hotel.dao.impl;

import com.hotel.dao.UserDAO;
import com.hotel.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Алексей on 21.05.2017.
 */
@ContextConfiguration("/test-dao-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
@Transactional
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void getUserByLogin() throws Exception {
        User userExpected = new User("firstName", "lastName", "client", "login", "password");
        userDAO.save(userExpected);
        User userActual = userDAO.getUserByLogin("login");
        assertEquals(userExpected, userActual);
    }

    @Test
    public void register() throws Exception {
        userDAO.register("firstName", "lastName", "login", "password");
        User userActual = userDAO.get(userDAO.getLastGeneratedValue().intValue());
        assertEquals("firstName", userActual.getFirstName());
    }

    @Test
    public void hash() throws Exception {
        String expected = "ee11cbb19052e40b07aac0ca060c23ee";
        String actual = userDAO.hash("user");
        assertEquals(expected, actual);
    }

    @Test
    public void delete() throws Exception {
        User userExpected = new User("firstName", "lastName", "client", "login", "password");
        userDAO.save(userExpected);
        int lastGeneratedValue = userDAO.getLastGeneratedValue().intValue();
        assertNotNull(userDAO.get(lastGeneratedValue));
        userDAO.delete(lastGeneratedValue);
        assertNull(userDAO.get(lastGeneratedValue));
    }

    @Test
    public void getAll() throws Exception {
        userDAO.register("firstName", "lastName", "login", "password");
        userDAO.register("firstName", "lastName", "login", "password");
        assertEquals(2, userDAO.getAll().size());
    }
}