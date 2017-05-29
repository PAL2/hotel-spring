package com.hotel.service.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Account;
import com.hotel.service.AbstractService;
import com.hotel.service.AccountService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {
    private final Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    private AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Account> getAll() throws ServiceException {
        List<Account> accounts;
        try {
            accounts = accountDAO.getAll();
            LOG.info(accounts);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);

        }
        return accounts;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Account> getAllAccountByUser(int userId) throws ServiceException {
        List<Account> accounts;
        try {
            accounts = accountDAO.getAllAccountByUser(userId);
            LOG.info(accounts);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return accounts;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int id) throws ServiceException {
        try {
            accountDAO.delete(id);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Account account) throws ServiceException {
        try {
            accountDAO.save(account);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Account get(int id) throws ServiceException {
        Account account;
        try {
            account = accountDAO.get(id);
            LOG.info(account);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return account;
    }
}