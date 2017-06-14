package com.hotel.service.impl;

import com.google.common.collect.Lists;
import com.hotel.dao.AccountRepository;
import com.hotel.dao.BookingRepository;
import com.hotel.dao.RoomRepository;
import com.hotel.dao.UserRepository;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
public class BookingServiceImpl implements BookingService {
    private final Logger LOG = Logger.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Booking> getAllBookingWithAccount() throws ServiceException {
        try {
            return Lists.newArrayList(bookingRepository.findByAccountIdNot(0));
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void chooseRoom(int bookingId, int roomId) {
        bookingRepository.chooseRoom(bookingId, roomId);
        Booking booking = bookingRepository.findOne(bookingId);
        Room room = roomRepository.findOne(booking.getRoomId());
        java.util.Date date = new java.util.Date(booking.getEndDate().getTime());
        Instant instant = date.toInstant();
        LocalDateTime endDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        date = new java.util.Date(booking.getStartDate().getTime());
        instant = date.toInstant();
        LocalDateTime startDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(startDate, endDate);
        int sum = (int) duration.toDays() * room.getPrice();
        Account account = new Account();
        account.setSum(sum);
        booking.setStatus("billed");
        account.setBooking(booking);
        booking.setAccount(account);
        bookingRepository.save(booking);
        accountRepository.save(account);
        LOG.info(booking);
        LOG.info(room);
        LOG.info("");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int bookingId) throws ServiceException {
        try {
            bookingRepository.delete(bookingId);
            LOG.info("Deleted booking №" + bookingId);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Booking> getAllNewBooking() {
        return Lists.newArrayList(bookingRepository.findByStatus("new"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rejectBooking(int bookingId) throws ServiceException {
        try {
            LOG.info("Booking № " + bookingId + " rejected");
            bookingRepository.rejectBooking(bookingId);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException {
        try {
            return bookingRepository.findByAccountIdNotAndStatusAndUserIdOrStatus(0, "paid", userId, "refused");
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Booking> findAll() throws ServiceException {
        try {
            return Lists.newArrayList(bookingRepository.findAll());
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Booking> getAllBookingByUser(int userId) throws ServiceException {
        try {
            return Lists.newArrayList(bookingRepository.findByUserId(userId));
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBooking(Date startDate, Date endDate, int userId, int place, String category)
            throws ServiceException {
        try {
            User user = userRepository.findOne(userId);
            Booking booking = new Booking(startDate, endDate, place, category, userId, "new");
            booking.setUser(user);
            bookingRepository.save(booking);
            LOG.info("New booking ordered: " + booking);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void payBooking(int bookingId) throws ServiceException {
        try {
            bookingRepository.payBooking(bookingId);
            LOG.info("Booking № " + bookingId + " payed");
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Booking> getAllBookingWithAccountByUser(int userId) throws ServiceException {
        try {
            return bookingRepository.findByAccountIdNotAndStatusAndUserId(0, "billed", userId);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }
}