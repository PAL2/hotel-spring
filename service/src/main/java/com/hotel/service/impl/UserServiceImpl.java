package com.hotel.service.impl;

import com.hotel.dao.UserDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;
import com.hotel.service.AbstractService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User getUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDAO.getUserByLogin(login);
            LOG.info(user);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<User> getAll() throws ServiceException {
        List<User> users;
        try {
            users = userDAO.getAll();
            LOG.info(users);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(String firstName, String lastName, String login, String password) throws ServiceException {
        try {
            userDAO.register(firstName, lastName, login, password);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int id) throws ServiceException {
        try {
            userDAO.delete(id);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(User user) throws ServiceException {
        try {
            userDAO.save(user);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }
}