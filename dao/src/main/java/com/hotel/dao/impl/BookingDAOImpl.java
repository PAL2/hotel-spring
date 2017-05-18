package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDAOImpl extends AbstractDAO<Booking> implements BookingDAO {
    private final Logger LOG = Logger.getLogger(BookingDAOImpl.class);

    @Autowired
    private BookingDAOImpl(SessionFactory sessionFactory) {
        super(Booking.class, sessionFactory);
    }

    @Override
    public void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException {
        try {
            Session session = getCurrentSession();
            User user = (User) session.get(User.class, userId);
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setPlace(place);
            booking.setCategory(category);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setStatus("new");
            booking.setUser(user);
            session.save(booking);
        } catch (HibernateException e) {
            LOG.error("Unable to add a booking. Error in DAO. " + e);
            throw new DaoException("Unable to add a booking. Error in DAO. " + e);
        }
    }

    @Override
    public List<Booking> getAllNewBooking() throws DaoException {
        List<Booking> bookings;
        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(Booking.class);
            criteria.add(Restrictions.eq("status", "new"));
            bookings = criteria.list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of bookings. Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public void chooseRoom(int bookingId, int roomId) throws DaoException {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("UPDATE Booking B SET B.roomId=:roomId, B.status=:status " +
                    "WHERE B.bookingId=:bookingId");
            query.setParameter("roomId", roomId);
            query.setParameter("status", "billed");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            LOG.error("Failed to assign the appropriate booking number. Error in DAO. " + e);
            throw new DaoException("Failed to assign the appropriate booking number. Error in DAO. " + e);
        }
    }

    @Override
    public List<Booking> getAllBookingByUser(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM Booking B WHERE B.userId=:userId");
            query.setParameter("userId", userId);
            bookings = query.list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list bookings. Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public void rejectBooking(int bookingId) throws DaoException {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setParameter("status", "rejected");
            query.setParameter("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            LOG.error("Unable to reject the book. Error in DAO. " + e);
            throw new DaoException("Unable to reject the book. Error in DAO. " + e);
        }
    }

    @Override
    public List<Booking> getAllBookingWithAccount() throws DaoException {
        List<Booking> bookings;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM Booking B WHERE B.accountId!=0");
            bookings = query.list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of bookings with the invoice. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of bookings with the invoice. Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllBookingWithAccountByUser(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM Booking B " +
                    "WHERE B.accountId!=0 AND B.status=:status AND B.userId=:userId");
            query.setParameter("status", "billed");
            query.setParameter("userId", userId);
            bookings = query.list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of bookings with the invoice for the customer. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of bookings with the invoice for the customer. " +
                    "Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public void payBooking(int bookingId) throws DaoException {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "paid");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            LOG.error("Unable to pay for a booking. Error in DAO. " + e);
            throw new DaoException("Unable to pay for a booking. Error in DAO. " + e);
        }
    }

    @Override
    public void refuseBooking(int bookingId) throws DaoException {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("UPDATE Booking B SET B.status=:status WHERE B.bookingId=:bookingId");
            query.setString("status", "refused");
            query.setInteger("bookingId", bookingId);
            query.executeUpdate();
        } catch (HibernateException e) {
            LOG.error("The client was unable to refuse the book. Error in DAO. " + e);
            throw new DaoException("The client was unable to refuse the book. Error in DAO. " + e);
        }
    }

    @Override
    public List<Booking> getAllBookingWithFinishedAccount(int userId) throws DaoException {
        List<Booking> bookings;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("FROM Booking B " +
                    "WHERE B.accountId!=0 AND (B.status=:paid OR B.status=:refused) AND B.userId=:userId");
            query.setString("paid", "paid");
            query.setString("refused", "refused");
            query.setInteger("userId", userId);
            bookings = query.list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of fully processed bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of fully processed bookings. Error in DAO. " + e);
        }
        return bookings;
    }
}