package com.hotel.service.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.entity.Account;
import com.hotel.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Алексей on 22.05.2017.
 */

@ContextConfiguration("/test-service-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest {
    private Account accountExpected, accountActual;

    @Autowired
    private AccountService accountService;

    @Mock
    private AccountDAO accountDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountExpected = new Account(300, null);
    }

    @After
    public void tearDown() throws Exception {
        accountExpected = null;
    }

    @Test
    public void getAll() throws Exception {
        accountService.save(accountExpected);
        accountActual = new Account(400, null);
        accountService.save(accountActual);
        assertEquals(2, accountService.getAll().size());
        accountService.delete(accountExpected.getAccountId());
        accountService.delete(accountActual.getAccountId());
    }

    @Test
    public void getAllAccountByUser() throws Exception {

        int userId = 11;
        List<Account> accounts = new ArrayList<>();
        accountActual = new Account(200, null);
        accounts.add(accountExpected);
        accounts.add(accountActual);

        when(accountDAO.getAllAccountByUser(userId)).thenReturn(accounts);
        assertEquals(2, accountDAO.getAllAccountByUser(userId).size());
    }

    @Test
    public void delete() throws Exception {
        accountService.save(accountExpected);
        accountActual = accountService.get(accountExpected.getAccountId());
        assertNotNull(accountActual);
        accountService.delete(accountExpected.getAccountId());
        accountActual = accountService.get(accountExpected.getAccountId());
        assertNull(accountActual);
    }

    @Test
    public void save() throws Exception {
        accountService.save(accountExpected);
        accountActual = accountService.get(accountExpected.getAccountId());
        assertEquals(accountExpected.getSum(), accountActual.getSum());
        assertEquals(accountExpected.getAccountId(), accountActual.getAccountId());
        accountService.delete(accountExpected.getAccountId());
    }

    @Test
    public void get() throws Exception {
        accountService.save(accountExpected);
        accountActual = accountService.get(accountExpected.getAccountId());
        assertEquals(300, accountActual.getSum());
        accountService.delete(accountExpected.getAccountId());
    }
}