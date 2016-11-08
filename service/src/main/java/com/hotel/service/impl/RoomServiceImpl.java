package com.hotel.service.impl;

import com.hotel.dao.RoomDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.AbstractService;
import com.hotel.service.RoomService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
    private final Logger LOG = Logger.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomDAO roomDAO;

    public List<Room> getAll(int recordsPerPage, int currentPage) throws ServiceException {
        List<Room> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            rooms = roomDAO.getAll(recordsPerPage, currentPage);
            transaction.commit();
            LOG.info(rooms);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    public List<Room> getAvailableRooms(int bookingId) throws ServiceException {
        Booking booking;
        List<Room> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            booking = (Booking) session.get(Booking.class, bookingId);
            rooms = roomDAO.getAvailableRooms(booking);
            transaction.commit();
            LOG.info(booking);
            LOG.info(rooms);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    public int getNumberOfPages(int recordsPerPage) throws ServiceException {
        int numberOfPages;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Long numberOfRecords = roomDAO.getAmount();
            numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
            transaction.commit();
            LOG.info(numberOfPages);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
        return numberOfPages;
    }

    public void save(Room room) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            roomDAO.save(room);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Room entity) throws ServiceException {

    }

    public void delete(int id) throws ServiceException {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            roomDAO.delete(id);
            transaction.commit();
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error(TRANSACTION_FAIL);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Room> getAll() throws ServiceException {
        return null;
    }
}