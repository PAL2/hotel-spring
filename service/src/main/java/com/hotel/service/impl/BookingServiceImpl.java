package com.hotel.service.impl;

import com.hotel.dao.AccountDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.AbstractService;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BookingServiceImpl extends AbstractService<Booking> implements BookingService {
    private final Logger LOG = Logger.getLogger(BookingServiceImpl.class);

    private BookingDAO bookingDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAllBookingWithAccount() throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAllBookingWithAccount();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAll() throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAll();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void chooseRoom(int bookingId, int roomId) throws ServiceException {
        Booking booking;
        Room room;
        try {
            bookingDAO.chooseRoom(bookingId, roomId);
            booking = bookingDAO.get(bookingId);
            room = roomDAO.get(booking.getRoomId());
            final Duration duration = Duration.between(booking.getEndDate(), booking.getStartDate());
            int total = (int) duration.toDays() * room.getPrice();
            accountDAO.addAccount(total, booking);
            LOG.info(booking);
            LOG.info(room);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int bookingId) throws ServiceException {
        try {
            bookingDAO.delete(bookingId);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAllNewBooking() throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAllNewBooking();
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rejectBooking(int bookingId) throws ServiceException {
        try {
            bookingDAO.rejectBooking(bookingId);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAllBookingWithFinishedAccount(userId);
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAllBookingByUser(int userId) throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAllBookingByUser(userId);
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBooking(LocalDate startDate, LocalDate endDate, int userId, int place, String category)
            throws ServiceException {
        try {
            bookingDAO.addBooking(userId, place, category, startDate, endDate);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void payBooking(int bookingId) throws ServiceException {
        try {
            bookingDAO.payBooking(bookingId);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void refuseBooking(int bookingId) throws ServiceException {
        try {
            bookingDAO.refuseBooking(bookingId);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Booking> getAllBookingWithAccountByUser(int userId) throws ServiceException {
        List<Booking> bookings;
        try {
            bookings = bookingDAO.getAllBookingWithAccountByUser(userId);
            LOG.info(bookings);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return bookings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Booking booking) throws ServiceException {
        try {
            bookingDAO.save(booking);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Override
    public void update(Booking entity) throws ServiceException {

    }
}