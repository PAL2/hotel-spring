package com.hotel.service.impl;

import com.hotel.dao.UserDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.dao.impl.UserDAOImpl;
import com.hotel.entity.User;
import com.hotel.service.AbstractService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    public User logIn(String login, String password) throws ServiceException {
        User user;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = userDAO.logIn(login, password);
            transaction.commit();
            LOG.info(user);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {
        List<User> users;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            users = userDAO.getAll();
            transaction.commit();
            LOG.info(users);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return users;
    }

    public void register(String firstName, String lastName, String login, String password) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.register(firstName, lastName, login, password);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public void save(User user) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.save(user);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(User entity) throws ServiceException {

    }

    public void delete(int id) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDAO.delete(id);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }
}