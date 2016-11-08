package com.hotel.service.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.AbstractService;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
public class BookingServiceImpl extends AbstractService<Booking> implements BookingService {
    private final Logger LOG = Logger.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private AccountDAO accountDAO;

    public List<Booking> getAllBookingWithAccount() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithAccount();
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAll() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAll();
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;

    }

    public void chooseRoom(int bookingId, int roomId) throws ServiceException {
        Booking booking;
        Room room;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.chooseRoom(bookingId, roomId);
            booking = (Booking) session.get(Booking.class, bookingId);
            room = (Room) session.get(Room.class, booking.getRoomId());
            Date startDate = booking.getStartDate();
            Date endDate = booking.getEndDate();
            long st = startDate.getTime();
            long en = endDate.getTime();
            int summa = (int) ((en - st) / 24 / 60 / 60 / 1000) * room.getPrice();
            accountDAO.addAccount(summa, booking);
            transaction.commit();
            LOG.info(booking);
            LOG.info(room);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public void delete(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.delete(bookingId);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllNewBooking() throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllNewBooking();
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void rejectBooking(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.rejectBooking(bookingId);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithFinishedAccount(userId);
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public List<Booking> getAllBookingByUser(int userId) throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingByUser(userId);
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void addBooking(LocalDate startDate, LocalDate endDate, int userId, int place, String category)
            throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.addBooking(userId, place, category, startDate, endDate);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public void payBooking(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.payBooking(bookingId);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public void refuseBooking(int bookingId) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.refuseBooking(bookingId);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookingWithAccountByUser(int userId) throws ServiceException {
        List<Booking> bookings;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookings = bookingDAO.getAllBookingWithAccountByUser(userId);
            transaction.commit();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return bookings;
    }

    public void save(Booking booking) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookingDAO.save(booking);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Booking entity) throws ServiceException {

    }
}