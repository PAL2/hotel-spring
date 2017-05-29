package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDAOImpl extends AbstractDAO<Booking> implements BookingDAO {
    private final Logger LOG = Logger.getLogger(BookingDAOImpl.class);

    @Autowired
    BookingDAOImpl(SessionFactory sessionFactory) {
        super(Booking.class, sessionFactory);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBooking(int userId, int place, String category, LocalDate startDate, LocalDate endDate)
            throws DaoException {
        try {
            Session session = getCurrentSession();
            User user = (User) session.get(User.class, userId);
            Booking booking = new Booking(startDate, endDate, place, category, userId, "new");
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
            bookings = session.createCriteria(Booking.class).add(Restrictions.eq("status", "new")).list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of bookings. Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public void chooseRoom(int bookingId, int roomId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("UPDATE Booking B SET B.roomId=:roomId, B.status='billed' " +
                    "WHERE B.bookingId=:bookingId");
            query = query.setParameter("roomId", roomId);
            query = query.setParameter("bookingId", bookingId);
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
            bookings = session.createQuery("FROM Booking B WHERE B.userId=:userId")
                    .setParameter("userId", userId).list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list bookings. Error in DAO. " + e);
        }
        return bookings;
    }

    @Override
    public void rejectBooking(int bookingId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.createQuery("UPDATE Booking B SET B.status='rejected' WHERE B.bookingId=:bookingId")
                    .setParameter("bookingId", bookingId).executeUpdate();
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
            bookings = session.createQuery("FROM Booking B WHERE B.accountId!=0").list();
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
            bookings = session.createQuery("FROM Booking B WHERE B.accountId!=0 AND B.status='billed'" +
                    "AND B.userId=:userId").setParameter("userId", userId).list();
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
            session.createQuery("UPDATE Booking B SET B.status='paid' WHERE B.bookingId=:bookingId")
                    .setInteger("bookingId", bookingId).executeUpdate();
        } catch (HibernateException e) {
            LOG.error("Unable to pay for a booking. Error in DAO. " + e);
            throw new DaoException("Unable to pay for a booking. Error in DAO. " + e);
        }
    }

    @Override
    public void refuseBooking(int bookingId) throws DaoException {
        try {
            Session session = getCurrentSession();
            session.createQuery("UPDATE Booking B SET B.status='refused' WHERE B.bookingId=:bookingId")
                    .setInteger("bookingId", bookingId).executeUpdate();
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
            bookings = session.createQuery("FROM Booking B WHERE B.accountId!=0 AND " +
                    "(B.status='paid' OR B.status='refused') AND B.userId=:userId").setInteger("userId", userId).list();
        } catch (HibernateException e) {
            LOG.error("Failed to create a list of fully processed bookings. Error in DAO. " + e);
            throw new DaoException("Failed to create a list of fully processed bookings. Error in DAO. " + e);
        }
        return bookings;
    }
}