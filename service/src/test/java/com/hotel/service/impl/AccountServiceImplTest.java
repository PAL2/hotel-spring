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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by Алексей on 22.05.2017.
 */

@ContextConfiguration("/test-service-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Mock
    private AccountDAO accountDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        Account account = new Account(300, null);
        accountService.save(account);
        Account account1 = new Account(400, null);
        accountService.save(account1);
        assertEquals(2, accountService.getAll().size());
        accountService.delete(account.getAccountId());
        accountService.delete(account1.getAccountId());
    }

    @Test
    public void getAllAccountByUser() throws Exception {

        int userId = 11;
        Account account = new Account(201, null);
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account(200, null);
        accounts.add(account);
        accounts.add(account1);

        when(accountDAO.getAllAccountByUser(userId)).thenReturn(accounts);
        assertEquals(2, accountDAO.getAllAccountByUser(userId).size());
    }

    @Test
    public void delete() throws Exception {
        Account account = new Account(300, null);
        accountService.save(account);
        Account accountActual = accountService.get(accountService.getLastGeneratedValue());
        assertNotNull(accountActual);
        accountService.delete(account.getAccountId());
        accountActual = accountService.get(accountService.getLastGeneratedValue());
        assertNull(accountActual);
    }

    @Test
    public void save() throws Exception {
        Account account = new Account(300, null);
        accountService.save(account);
        Account accountActual = accountService.get(accountService.getLastGeneratedValue());
        assertEquals(account.getSum(), accountActual.getSum());
        assertEquals(account.getAccountId(), accountActual.getAccountId());
        accountService.delete(account.getAccountId());
    }

    @Test
    public void get() throws Exception {
        Account account = new Account(300, null);
        accountService.save(account);
        Account accountActual = accountService.get(accountService.getLastGeneratedValue());
        assertEquals(300, accountActual.getSum());
        accountService.delete(account.getAccountId());
    }
}