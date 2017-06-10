package com.hotel.dao.impl;

import com.hotel.dao.AccountRepository;
import com.hotel.entity.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Алексей on 20.05.2017.
 */

@ContextConfiguration("/test-dao-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)

@Transactional
public class AccountRepositoryTest {
    private Account accountExpected, accountActual;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        accountExpected = new Account(500, null);
    }

    @Test
    public void saveAndFindOne() {
        accountRepository.save(accountExpected);
        accountActual = accountRepository.findOne(accountExpected.getAccountId());
        assertEquals(accountExpected, accountActual);
    }

    @Test
    public void delete() {
        accountRepository.save(accountExpected);
        int accountId = accountExpected.getAccountId();
        assertNotNull(accountRepository.findOne(accountId));
        accountRepository.delete(accountId);
        assertNull(accountRepository.findOne(accountId));
    }

    @Test
    public void findAllAndCount() {
        accountRepository.save(accountExpected);
        accountRepository.save(new Account());
        assertEquals(2, accountRepository.findAll().size());
        assertEquals(2, accountRepository.count());
    }

    @Test
    public void existAndDeleteAll(){
        accountRepository.save(accountExpected);
        assertTrue(accountRepository.exists(accountExpected.getAccountId()));
        accountRepository.save(new Account(333, null));
        assertEquals(2, accountRepository.count());
        accountRepository.deleteAll();
        assertEquals(0, accountRepository.count());
    }

    @After
    public void tearDown() {
        accountExpected = null;
        accountActual = null;
    }
}