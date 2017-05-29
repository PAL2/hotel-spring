package com.hotel.dao.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.entity.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Алексей on 20.05.2017.
 */

@ContextConfiguration("/test-dao-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)

@Transactional
public class AccountDAOImplTest {
    private Account accountExpected, accountActual;
    private int accountId;

    @Autowired
    private AccountDAO accountDAO;

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    private AccountDAO accountDAOImpl = new AccountDAOImpl(sessionFactory);

    @Mock
    private Session session;

    @Mock
    private Query query;

    @Before
    /* Initialized mocks */
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accountExpected = new Account(500, null);
    }

    @Test
    public void addAccount() throws Exception {
        accountDAO.save(accountExpected);
        accountActual = accountDAO.get(accountExpected.getAccountId());
        assertEquals(accountExpected, accountActual);
    }

    @Test
    public void getAllAccountByUser() throws Exception {

        int userId = 11;
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account(200, null);
        accounts.add(accountExpected);
        accounts.add(account1);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("FROM Account WHERE booking.userId=:userId")).thenReturn(query);
        when(query.setParameter("userId", userId)).thenReturn(query);
        when(query.list()).thenReturn(accounts);
        assertEquals(2, accountDAOImpl.getAllAccountByUser(userId).size());
    }

    @Test
    public void delete() throws Exception {
        accountDAO.save(accountExpected);
        accountId = accountExpected.getAccountId();
        assertNotNull(accountDAO.get(accountId));
        accountDAO.delete(accountId);
        assertNull(accountDAO.get(accountId));
    }

    @Test
    public void getAll() throws Exception {
        accountDAO.save(accountExpected);
        accountDAO.save(new Account());
        assertEquals(2, accountDAO.getAll().size());
    }

    @After
    public void tearDown() throws Exception {
        accountExpected = null;
        accountActual = null;
    }
}