package com.hotel.dao.impl;

import com.hotel.dao.AccountRepository;
import com.hotel.dao.BookingRepository;
import com.hotel.dao.UserRepository;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Алексей on 21.05.2017.
 */
@ContextConfiguration("/test-dao-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
@Transactional
@Ignore
public class BookingRepositoryTest {
    private Booking bookingExpected, bookingActual;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setup() {
        bookingExpected = new Booking(new Date(), new Date(), 1, "lux", 1, 1, 1, "new", null, null, null);
        bookingActual = new Booking(new Date(), new Date(), 1, "lux", 1, 1, 1, "new", null, null, null);
    }

    @Test
    public void addBooking() throws Exception {
        bookingRepository.save(bookingExpected);
        bookingActual = bookingRepository.findOne(bookingExpected.getBookingId());
        assertEquals(bookingExpected, bookingActual);
    }

    @Test
    public void findByStatus() throws Exception {
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findByStatus("new").size());
    }

    @Test
    public void getAllBookingByUser() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(user);
        int userId = user.getUserId();
        bookingExpected = new Booking(new Date(), new Date(), 1, "lux", 1, userId, 1, "new", null, user, null);
        bookingActual = new Booking(new Date(), new Date(), 1, "lux", 1, userId, 1, "new", null, user, null);
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findByUserId(userId).size());
    }

    @Test
    public void getAllBookingWithAccount() throws Exception {
        Account account = new Account(400, null);
        accountRepository.save(account);
        int accountId = account.getAccountId();
        bookingExpected = new Booking(new Date(), new Date(), 1, "lux", 1, 1, accountId, "new", null, null, account);
        bookingActual = new Booking(new Date(), new Date(), 1, "lux", 1, 1, accountId, "new", null, null, account);
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findByAccountIdNot(0).size());
    }

    @Test
    public void getAllBookingWithAccountByUser() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(user);
        int userId = user.getUserId();
        Account account = new Account(400, null);
        accountRepository.save(account);
        int accountId = account.getAccountId();
        bookingExpected = new Booking(new Date(), new Date(), 1, "lux", 1, userId, accountId, "billed", null, user, account);
        bookingActual = new Booking(new Date(), new Date(), 1, "lux", 1, userId, accountId, "billed", null, user, account);
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findByAccountIdNotAndStatusAndUserId(0, "billed", userId).size());
    }

    @Test
    public void getAllBookingWithFinishedAccount() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userRepository.save(user);
        int userId = user.getUserId();
        Account account = new Account(400, null);
        accountRepository.save(account);
        int accountId = account.getAccountId();
        bookingExpected = new Booking(new Date(), new Date(), 1, "lux", 1, userId, accountId, "paid", null, user, account);
        bookingActual = new Booking(new Date(), new Date(), 1, "lux", 1, userId, accountId, "refused", null, user, account);
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findByAccountIdNotAndStatusAndUserIdOrStatus(0, "paid", userId, "refused").size());
    }

    @Test
    public void delete() throws Exception {
        bookingRepository.save(bookingExpected);
        int bookingId = bookingExpected.getBookingId();
        assertNotNull(bookingRepository.findOne(bookingId));
        bookingRepository.delete(bookingId);
        assertNull(bookingRepository.findOne(bookingId));
    }

    @Test
    public void getAll() throws Exception {
        bookingRepository.save(bookingExpected);
        bookingRepository.save(bookingActual);
        assertEquals(2, bookingRepository.findAll().size());
    }

    @After
    public void tearDown() throws Exception {
        bookingExpected = null;
        bookingActual = null;
    }
}