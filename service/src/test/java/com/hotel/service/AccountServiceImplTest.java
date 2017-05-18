package com.hotel.service;

import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Алексей on 26.10.2016.
 */
@ContextConfiguration("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    /*@Mock
    private AccountDAO accountDAO;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockCreation() {
        assertNotNull(accountDAO);
    }

    @Test
    public void testGetAll() throws DaoException {
        List<Account> list = new LinkedList<>();
        list.add(new Account(100));
        list.add(new Account(200));
        list.add(new Account(300));

        when(accountDAO.getAll()).thenReturn(list);
    }*/

    private Account expectedAccount;
    private Account expectedAccount2;
    private Account actualAccount;
    private Booking booking;
    private Booking booking2;
    private Room room;
    private User user;
    private int accountId;
    private int bookingId;
    private int roomId;
    private int userId;

   /* @Before
    public void setUp() throws Exception {
        // room = new Room("TEST", 33, 1000);
        expectedAccount = new Account(5000);
        expectedAccount2 = new Account(6666);
        user = new User("TEST", "TEST", "client", "TEST", "TEST", null);
        GregorianCalendar calendar = new GregorianCalendar(2016, Calendar.OCTOBER, 30);
        GregorianCalendar calendar2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);
        Date startDate = calendar.getTime();
        Date endDate = calendar2.getTime();
        booking = new Booking(startDate, endDate, 1, "TEST", 1, 1, 1, "TEST", null, null, null);
        booking2 = new Booking(endDate, startDate, 2, "TEST", 2, 2, 2, "TEST", null, null, null);
        booking.setAccount(expectedAccount);
        booking2.setAccount(expectedAccount2);
        //   booking.setRoom(room);
        booking.setUser(user);
        booking2.setUser(user);
        save();
    }*/

    @Ignore
    @Test
    public void getAllAccountById() throws Exception {
        user.setUserId(userId);
        getAllAccountById();


    }

    @Test
    public void testGetAll() {
        List<Account> list = new LinkedList<>();
        list.add(expectedAccount);
        list.add(expectedAccount2);
        assertEquals(2, list.size());

    }

   /* @After
    public void tearDown() throws Exception {
        expectedAccount = null;
        actualAccount = null;
        booking = null;
        room = null;
        user = null;
        accountId = 0;
        bookingId = 0;
        roomId = 0;
        userId = 0;
    }

    private void save() throws Exception {
        accountService.save(expectedAccount);
    }

    private void delete() throws Exception {
        accountService.delete(accountId);
    */

}
