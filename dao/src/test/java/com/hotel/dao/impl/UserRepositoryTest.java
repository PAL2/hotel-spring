package com.hotel.dao.impl;

import com.hotel.dao.UserRepository;
import com.hotel.entity.User;
import org.junit.Ignore;
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
@Ignore
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByLogin() throws Exception {
        User userExpected = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(userExpected);
        User userActual = userRepository.findByLogin("login");
        assertEquals(userExpected, userActual);
    }

    @Test
    public void delete() throws Exception {
        User userExpected = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(userExpected);
        int userId = userExpected.getUserId();
        assertNotNull(userRepository.findOne(userId));
        userRepository.delete(userId);
        assertNull(userRepository.findOne(userId));
    }

    @Test
    public void findAllAndDeleteAll() throws Exception {
        User userExpected = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(userExpected);
        User userActual = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(userActual);
        assertEquals(2, userRepository.findAll().size());
        userRepository.deleteAll();
        assertEquals(0, userRepository.findAll().size());
    }
}