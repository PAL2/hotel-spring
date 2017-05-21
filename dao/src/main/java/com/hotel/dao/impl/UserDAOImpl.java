package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.UserDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    private final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private UserDAOImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        User user;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM User WHERE login= :login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            LOG.error("Unable to login. Error in DAO. " + e);
            throw new DaoException("Unable to login. Error in DAO. " + e);
        }
        return user;
    }

    @Override
    public void register(String firstName, String lastName, String login, String password) throws DaoException {
        try {
            Session session = getCurrentSession();
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserRole("client");
            user.setLogin(login);
            user.setPassword(hash(password));
            session.save(user);
        } catch (HibernateException e) {
            LOG.error("Could not register. Error in DAO. " + e);
            throw new DaoException("Could not register. Error in DAO. " + e);
        }
    }

    @Override
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