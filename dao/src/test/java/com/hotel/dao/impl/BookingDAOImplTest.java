package com.hotel.dao.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.UserDAO;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
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

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Алексей on 21.05.2017.
 */
@ContextConfiguration("/test-dao-context.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
@Transactional
public class BookingDAOImplTest {
    Booking bookingExpected, bookingActual;

    @Autowired
    BookingDAO bookingDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountDAO accountDAO;

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    BookingDAO bookingDAOImpl = new BookingDAOImpl(sessionFactory);

    @Mock
    private Session session;

    @Mock
    private Query query;

    @Mock
    private Query queryReject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, 1, 1, "new", null, null, null);
        bookingActual = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, 1, 1, "new", null, null, null);
    }

    @After
    public void tearDown() throws Exception {
        bookingExpected = null;
        bookingActual = null;
    }

    @Test
    public void addBooking() throws Exception {
        bookingDAO.save(bookingExpected);
        bookingActual = bookingDAO.get(bookingDAO.getLastGeneratedValue().intValue());
        assertEquals(bookingExpected, bookingActual);
    }

    @Test
    public void getAllNewBooking() throws Exception {
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAllNewBooking().size());
    }

    @Test
    public void chooseRoom() throws Exception {

        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, 1, 1, "billed", null, null, null);
        bookingExpected.setBookingId(1);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("UPDATE Booking B SET B.roomId=:roomId, B.status='billed' " +
                "WHERE B.bookingId=:bookingId")).thenReturn(query);
        when(query.setParameter("roomId", bookingExpected.getRoomId())).thenReturn(query);
        when(query.setParameter("bookingId", bookingExpected.getBookingId())).thenReturn(query);
        when(query.executeUpdate()).getMock();
        assertEquals("billed", bookingExpected.getStatus());
    }

    @Test
    public void getAllBookingByUser() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userDAO.save(user);
        int u = userDAO.getLastGeneratedValue().intValue();
        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, 1, "new", null, user, null);
        bookingActual = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, 1, "new", null, user, null);
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAllBookingByUser(u).size());
    }

    @Test
    public void getAllBookingWithAccount() throws Exception {
        Account account = new Account(400, null);
        accountDAO.save(account);
        int i = accountDAO.getLastGeneratedValue().intValue();
        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, 1, i, "new", null, null, account);
        bookingActual = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, 1, i, "new", null, null, account);
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAllBookingWithAccount().size());
    }

    @Test
    public void getAllBookingWithAccountByUser() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userDAO.save(user);
        int u = userDAO.getLastGeneratedValue().intValue();
        Account account = new Account(400, null);
        accountDAO.save(account);
        int i = accountDAO.getLastGeneratedValue().intValue();
        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, i, "billed", null, user, account);
        bookingActual = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, i, "billed", null, user, account);
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAllBookingWithAccountByUser(u).size());
    }

    @Test
    public void getAllBookingWithFinishedAccount() throws Exception {
        User user = new User("firstName", "lastName", "client", "login", "password");
        userDAO.save(user);
        int u = userDAO.getLastGeneratedValue().intValue();
        Account account = new Account(400, null);
        accountDAO.save(account);
        int i = accountDAO.getLastGeneratedValue().intValue();
        bookingExpected = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, i, "paid", null, user, account);
        bookingActual = new Booking(LocalDate.now(), LocalDate.now(), 1, "lux", 1, u, i, "refused", null, user, account);
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAllBookingWithFinishedAccount(u).size());
    }

    @Test
    public void delete() throws Exception {
        bookingDAO.save(bookingExpected);
        int lastGeneratedValue = bookingDAO.getLastGeneratedValue().intValue();
        assertNotNull(bookingDAO.get(lastGeneratedValue));
        bookingDAO.delete(lastGeneratedValue);
        assertNull(bookingDAO.get(lastGeneratedValue));
    }

    @Test
    public void getAll() throws Exception {
        bookingDAO.save(bookingExpected);
        bookingDAO.save(bookingActual);
        assertEquals(2, bookingDAO.getAll().size());
    }
}