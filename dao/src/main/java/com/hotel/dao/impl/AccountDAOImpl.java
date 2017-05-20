package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.AccountDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl extends AbstractDAO<Account> implements AccountDAO {
    private final Logger LOG = Logger.getLogger(AccountDAOImpl.class);

    @Autowired
    private AccountDAOImpl(SessionFactory sessionFactory) {
        super(Account.class, sessionFactory);
    }

    @Override
    public void addAccount(int sum, Booking booking) throws DaoException {
        Account account = new Account();
        try {
            Session session = getCurrentSession();
            account.setSum(sum);
            booking.setStatus("billed");
            account.setBooking(booking);
            booking.setAccount(account);
            session.save(booking);
            session.save(account);
        } catch (HibernateException e) {
            LOG.error("Unable to add account. Error in DAO. " + e);
            throw new DaoException("Unable to add account. Error in DAO. " + e);
        }
    }

    @Override
    public List<Account> getAllAccountByUser(int userId) throws DaoException {
        List<Account> accounts;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM Account WHERE booking.userId=:userId");
            query.setParameter("userId", userId);
            accounts = query.list();
        } catch (HibernateException e) {
            LOG.error("Unable to create a list of accounts. Error in DAO. " + e);
            throw new DaoException("Unable to create a list of accounts. Error in DAO. " + e);
        }
        return accounts;
    }
}