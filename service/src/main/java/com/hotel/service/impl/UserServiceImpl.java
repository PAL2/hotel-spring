package com.hotel.service.impl;

import com.google.common.collect.Lists;
import com.hotel.dao.UserRepository;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {
    private final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public User findByLogin(String login) throws ServiceException {
        try {
            return userRepository.findByLogin(login);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<User> findAll() throws ServiceException {
        try {
            return Lists.newArrayList(userRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean register(String firstName, String lastName, String login, String password) throws ServiceException {
        boolean successRegistration = false;
        try {
            User user = new User(firstName, lastName, "client", login, hash(password));
            if (userRepository.findByLogin(login) == null) {
                userRepository.save(user);
                LOG.info("New user: " + user);
                successRegistration = true;
            }
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
        return successRegistration;
    }

    public String hash(String input) {
        String md5Hashed = null;
        if (null == input) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5Hashed = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Hashed;
    }
}