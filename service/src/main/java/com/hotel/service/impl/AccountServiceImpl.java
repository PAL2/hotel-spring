package com.hotel.service.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.dao.impl.AccountDAOImpl;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.service.AbstractService;
import com.hotel.service.AccountService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */

@Service
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {
    private final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDAO accountDAO;

    public List<Account> getAll() throws ServiceException {
        List<Account> accounts;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAll();
            transaction.commit();
            LOG.info(accounts);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());

        }
        return accounts;
    }

    public List<Account> getAllAccountByUser(int userId) throws ServiceException {
        List<Account> accounts;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accounts = accountDAO.getAllAccountByUser(userId);
            transaction.commit();
            LOG.info(accounts);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }

    public void save(Account account) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accountDAO.save(account);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Account entity) throws ServiceException {

    }

    public void delete(int id) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            accountDAO.delete(id);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }
}