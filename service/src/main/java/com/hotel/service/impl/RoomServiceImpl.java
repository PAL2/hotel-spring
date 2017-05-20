package com.hotel.service.impl;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.AbstractService;
import com.hotel.service.RoomService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
    private final Logger LOG = Logger.getLogger(RoomServiceImpl.class);

    private RoomDAO roomDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Room> getAll(int recordsPerPage, int currentPage) throws ServiceException {
        List<Room> rooms;
        try {
            rooms = roomDAO.getAll(recordsPerPage, currentPage);
            LOG.info(rooms);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return rooms;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Room> getAvailableRooms(int bookingId) throws ServiceException {
        Booking booking;
        List<Room> rooms;
        try {
            booking = bookingDAO.get(bookingId);
            rooms = roomDAO.getAvailableRooms(booking);
            LOG.info(booking);
            LOG.info(rooms);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return rooms;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int getNumberOfPages(int recordsPerPage) throws ServiceException {
        int numberOfPages;
        try {
            Long numberOfRecords = roomDAO.getAmount();
            numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
            LOG.info(numberOfPages);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return numberOfPages;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Room room) throws ServiceException {
        try {
            roomDAO.save(room);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(int id) throws ServiceException {
        try {
            roomDAO.delete(id);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
    }

    @Override
    public List<Room> getAll() throws ServiceException {
        return null;
    }
}